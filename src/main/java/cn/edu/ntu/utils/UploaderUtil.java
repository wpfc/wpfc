package cn.edu.ntu.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.ntu.wpfc.entity.FieldParam;
import cn.edu.ntu.wpfc.entity.FileParam;
import cn.edu.ntu.wpfc.entity.Param;

public class UploaderUtil {

	private static final Logger logger = LoggerFactory.getLogger(UploaderUtil.class);
	
	private static ServletFileUpload upload;
	
	public static void init(ServletConfig servletConfig) {
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = servletConfig.getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Create a new file upload handler
		upload = new ServletFileUpload(factory);
		
		int maxUploadSize = ConfigUtil.getAppUploadLimit();
		if(maxUploadSize != 0){
			upload.setFileSizeMax(maxUploadSize * 1024 * 1024);
		}
	}
	
	/**
	 * 判断请求是否是multipart类型
	 * @param request
	 * @return
	 */
	public static boolean isMultipart(HttpServletRequest request){
		return ServletFileUpload.isMultipartContent(request);
	}
	
	/**
	 * 创建请求参数（针对ismultipart请求）
	 * @param request
	 * @return
	 */
	public static Param createParam(HttpServletRequest request){
		List<FileParam> fileParamList = new ArrayList<FileParam>();
		List<FieldParam> fieldParamList = new ArrayList<FieldParam>();
		try {
			Map<String, List<FileItem>> paramMap = upload.parseParameterMap(request);
			if(!paramMap.isEmpty()){
				for(Map.Entry<String, List<FileItem>> entry : paramMap.entrySet()){
					String fieldName = entry.getKey();
					List<FileItem> fields = entry.getValue();
					if(!fields.isEmpty()){
						for(FileItem item : fields){
							//表单对象
							if(item.isFormField()){
								fieldParamList.add(new FieldParam(fieldName, item.getString("UTF-8")));
							}else{
								FileParam tempFileParam = 
										new FileParam(item.getFieldName(), item.getName(), 
												item.getSize(), item.getContentType(), item.getInputStream());
								fileParamList.add(tempFileParam);
							}
						}
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Param(fieldParamList, fileParamList);
	}
	
	/**
	 * 上传文件
	 * @param basePath    文件上传地址
	 * @param fileParam   文件对象
	 */
	public static void uploadFile(String basePath, FileParam fileParam){
		try {
			if(fileParam != null){
				String path = basePath + fileParam.getFieldName();
				//创建该对象
				FileUtil.createFile(path);
				BufferedInputStream in = new BufferedInputStream(fileParam.getInputStream());
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path));
				StreamUtil.copyStream(in, out);
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void uploadFile(String basePath, List<FileParam> fileParamList){ 
		if(fileParamList != null && fileParamList.size() > 0){
			for(FileParam item : fileParamList){
				uploadFile(basePath, item);
			}
		}
	}
	
	
	
	
	
	
	
}
