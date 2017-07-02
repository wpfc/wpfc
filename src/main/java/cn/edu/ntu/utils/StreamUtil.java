package cn.edu.ntu.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
