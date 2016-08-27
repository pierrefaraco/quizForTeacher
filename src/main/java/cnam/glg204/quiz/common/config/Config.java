package cnam.glg204.quiz.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages ="cnam.glg204")
public class Config {
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
											 
	public static final String DATE_FORMAT2 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
}
