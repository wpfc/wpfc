package cn.edu.ntu.utils;

public class StringUtil {

	public static final String  SAPARATOR= String.valueOf((char)29);
	
	public static String[] splitString(String source, String regx){
		return source.split(regx);
	}
	
	
	public static void main(String[] args){
		System.out.println(SAPARATOR);
	}
}
