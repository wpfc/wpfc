package cn.edu.ntu.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class StreamUtil {

	//public static final Logger logger = LoggerFactory.getLogger(StreamUtil.class);
	
	
	public static String getStringFromStrean(InputStream is){
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			while((line = br.readLine()) != null){
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static void copyStream(InputStream source, OutputStream target){
		if(source == null || target == null){
			return;
		}
		try {
			//文件拷贝      
	        byte flush[]  = new byte[1024];  
	        int len = 0;  
				while((len= source.read(flush)) != -1){  
					target.write(flush, 0, len);  
				}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			//关闭流的注意 先打开的后关  
	        try {
				target.close();
				source.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
	}
	
}
