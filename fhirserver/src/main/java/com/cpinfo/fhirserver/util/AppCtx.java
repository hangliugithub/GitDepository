package com.cpinfo.fhirserver.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppCtx {
	public static ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
}
