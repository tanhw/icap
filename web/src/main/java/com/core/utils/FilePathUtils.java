package com.core.utils;

import java.net.URL;

public enum FilePathUtils {
	INSTANCE;

	public String getFilePath(String sourceFolderPath, String sourceFileName) {
		URL classPath = FilePathUtils.class.getResource("/");

		StringBuffer sourcePath = new StringBuffer(classPath.getPath());

		sourcePath.append(sourceFolderPath);

		sourcePath.append("/");

		sourcePath.append(sourceFileName);

		return sourcePath.toString();
	}
}
