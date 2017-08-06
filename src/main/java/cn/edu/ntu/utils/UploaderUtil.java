package cn.edu.ntu.utils;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploaderUtil {

	private static final Logger logger = LoggerFactory.getLogger(UploaderUtil.class);
	
	private static ServletFileUpload upload;
	
	public void init(ServletConfig servletConfig) {
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = servletConfig.getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Create a new file upload handler
		upload = new ServletFileUpload(factory);
	}
	
}
