package cn.edu.ntu.wpfc.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.ntu.utils.StringUtil;

/**
 * 参数包装类
 * @author Administrator
 *
 */
public class Param {

	private List<FieldParam> fieldParamList;
	
	private List<FileParam> fileParamList;

	public Param(List<FieldParam> fieldParamList) {
		super();
		this.fieldParamList = fieldParamList;
	}

	public Param(List<FieldParam> fieldParamList, List<FileParam> fileParamList) {
		super();
		this.fieldParamList = fieldParamList;
		this.fileParamList = fileParamList;
	}

	public boolean isEmpty(){
		return fieldParamList.isEmpty() && fileParamList.isEmpty();
	}
	
	public Map<String, Object> getFieldParamMap(){
		Map<String, Object> fieldParamMap = new HashMap<String, Object>();
		if(!fieldParamList.isEmpty()){
			for(FieldParam param : fieldParamList){
				String fieldName = param.getFieldName();
				Object fieldValue = param.getFieldValue();
				if(fieldParamMap.containsKey(fieldName)){
					fieldValue = fieldParamMap.get(fieldName) 
							     + StringUtil.SAPARATOR + fieldValue;
				}
				fieldParamMap.put(fieldName, fieldValue);
			}
		}
		return fieldParamMap;
	}
	
	/**
	 * 获取上传文件映射对象
	 * @return
	 */
	public Map<String, List<FileParam>> getFileParamList(){
		Map<String, List<FileParam>> fileParamMap = new HashMap<String, List<FileParam>>();
		if(fileParamList.isEmpty()){
			for(FileParam param : fileParamList){
				String fieldName = param.getFieldName();
				if(fileParamMap.containsKey(fieldName)){
					fileParamMap.get(fieldName).add(param);
				}else{
					List<FileParam> tempList = new ArrayList<FileParam>();
					tempList.add(param);
					fileParamMap.put(fieldName, tempList);
				}
			}
		}
		return fileParamMap;
	}
	
	/**
	 * 根据属性名称获取文件对象列表
	 * @param fileName
	 * @return
	 */
	public List<FileParam> getFileParamList(String fileName){
		Map<String, List<FileParam>> fileParamMap = getFileParamList();
		return fileParamMap.get(fileName);
	}
	
	public FileParam getFileParamByFieldName(String fileName){
		FileParam result = null;
		Map<String, List<FileParam>> fileParamMap = getFileParamList();
		List<FileParam> fileParamList = fileParamMap.get(fileName);
		if(!fileParamList.isEmpty() && 1 == fileParamList.size()){
			result = fileParamList.get(0);
		}
		return result;
	}
	
}
