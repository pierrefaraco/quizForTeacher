package com.cnam.quiz.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
@Configuration
@ComponentScan(basePackages ="com.cnam")
public class Config {
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
											 
	public static final String DATE_FORMAT2 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
}
