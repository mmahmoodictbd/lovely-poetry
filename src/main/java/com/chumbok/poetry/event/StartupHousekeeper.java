package com.chumbok.poetry.event;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.chumbok.poetry.util.PathBuilder;

@Component
public class StartupHousekeeper
		implements
			ApplicationListener<ContextRefreshedEvent> {

	Logger logger = LoggerFactory.getLogger(StartupHousekeeper.class);

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		createAppDirectory();
		createAppImageDirectory();
	}

	private void createAppDirectory() {

		File appDir = new File(PathBuilder.buildAppDirPath());

		if (appDir.exists()) {
			logger.info("AppDir {} already exist.", appDir);
		} else if (appDir.mkdirs()) {
			logger.info("AppDir {} created.", appDir);
		} else {
			throw new IllegalStateException(
					"Failed to create AppDir {}" + appDir);
		}
	}

	private void createAppImageDirectory() {

		File appImgDir = new File(PathBuilder.buildAppImageDirPath());

		if (appImgDir.exists()) {
			logger.info("AppImgDir {} already exist.", appImgDir);
		} else if (appImgDir.mkdirs()) {
			logger.info("AppImgDir {} created.", appImgDir);
		} else {
			throw new IllegalStateException(
					"Failed to create AppImgDir {}" + appImgDir);
		}

	}

}