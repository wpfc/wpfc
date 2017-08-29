package cn.edu.ntu.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtil {

	public static File createFile(String path) {
		File file = null;
		try {
			file = new File(path);
			File parentDir = file.getParentFile();
			if(!parentDir.exists()){
				FileUtils.forceMkdir(parentDir);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	
	
}
