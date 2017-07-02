package cn.edu.ntu.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {

	private final static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	
	
	private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	/**
	 * pojo 转 json
	 */
	public static <T> String toJson(T obj){
		String target = null;
		try {
			target = OBJECT_MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return target;
	}
	
	
	/**
	 * json 转 pojo
	 */
	public static <T> Object toPojo(String source, Class<T> clazz){
		T target = null;
		try {
			target = (T) OBJECT_MAPPER.readValue(source, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return target;
	}
}
