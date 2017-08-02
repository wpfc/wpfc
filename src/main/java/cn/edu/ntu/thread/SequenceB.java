package cn.edu.ntu.thread;

public class SequenceB implements Sequence {

//	private static ThreadLocal<Integer> container = new ThreadLocal<Integer>(){
//		@Override
//		protected Integer initialValue() {
//			return 0;
//		}
//	};
	
	private static MyThreadLocal<Integer> container = new MyThreadLocal<Integer>(){
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};
	
	@Override
	public int getNumber() {
		container.set(container.get() + 1);
		return container.get();
	}
	
	public static void main(String[] args){
		SequenceB a = new SequenceB();
		
		ClientThread c1 = new ClientThread(a);
		ClientThread c2 = new ClientThread(a);
		ClientThread c3 = new ClientThread(a);
		
		c1.start();
		c2.start();
		c3.start();
	}

}
