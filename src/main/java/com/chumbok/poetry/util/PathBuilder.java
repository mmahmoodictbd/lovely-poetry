package com.chumbok.poetry.util;

import java.io.File;

import org.joda.time.DateTime;

public class PathBuilder {

	private static String APP_DIR_NAME = ".LovelyPoetry";
	private static String APP_IMG_DIR_NAME = "img";
	private static String DIR_DATE_FORMAT = "ddMMyy";

	public static String buildAppDirPath() {
		StringBuilder appPath = new StringBuilder();
		appPath.append(System.getProperty("user.home"));
		appPath.append(File.separator);
		appPath.append(APP_DIR_NAME);
		return appPath.toString();
	}

	public static String buildAppImageDirPath() {
		StringBuilder appImgPath = new StringBuilder();
		appImgPath.append(buildAppDirPath());
		appImgPath.append(File.separator);
		appImgPath.append(APP_IMG_DIR_NAME);
		appImgPath.append(File.separator);
		appImgPath.append(new DateTime().toString(DIR_DATE_FORMAT));
		return appImgPath.toString();
	}
}
