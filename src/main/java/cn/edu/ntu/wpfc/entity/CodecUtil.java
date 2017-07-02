package cn.edu.ntu.wpfc.entity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodecUtil {

	private static final Logger logger = LoggerFactory.getLogger(CodecUtil.class);
	
	private static final String ENCODE = "UTF-8";
	
	/**
	 * url编码
	 * @param source
	 * @return
	 */
	public static String encodeURL(String source){
		String target = null;
		try {
			target = URLEncoder.encode(source, ENCODE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return target;
	} 
	
	/**
	 * url解码
	 * @param source
	 * @return
	 */
	public static String decodeURL(String source){
		String target = null;
		try {
			target = URLDecoder.decode(source, ENCODE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return target;
	} 
}
