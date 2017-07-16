package cn.edu.ntu.aop;

import java.lang.reflect.Method;

import cn.edu.ntu.annotation.Transactional;
import cn.edu.ntu.utils.DBUtil;

public class TransactionProxy implements Proxy{

	private static ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>(){
		@Override
		protected Boolean initialValue() {
			return false;
		};
	};
	
	@Override
	public Object doProxy(ProxyChain proxyChain) {
		Object result = null;
		Method targetMethod = proxyChain.getTargetMethod();
		Boolean flag = FLAG_HOLDER.get();
		if(!flag && targetMethod.isAnnotationPresent(Transactional.class)){
			FLAG_HOLDER.set(true);
			try{
				DBUtil.startTransaction();
				result = proxyChain.doProxyChain();
				DBUtil.endTransaction();
			}catch(Throwable ex){
				ex.printStackTrace();
				DBUtil.rollback();
			}finally{
				FLAG_HOLDER.remove();
			}
		}else{
			try {
				result = proxyChain.doProxyChain();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
