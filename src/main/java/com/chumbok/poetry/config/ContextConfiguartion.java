package com.chumbok.poetry.config;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import sun.awt.AppContext;
import sun.awt.SunToolkit;

/*
 * This class is added because of image upload bug
 * See more - http://stackoverflow.com/questions/22475739/could-not-initialize-class-javax-imageio-imageio
 */

@Component
public class ContextConfiguartion
		implements
			ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if (AppContext.getAppContext() == null) {
			SunToolkit.createNewAppContext();
		}
	}
}