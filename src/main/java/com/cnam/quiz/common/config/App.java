package com.cnam.quiz.common.config;


import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;




 //Cette classe initialise l'application
public class App extends AbstractAnnotationConfigDispatcherServletInitializer {
	

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {SecurityConfig.class,WebSocketConfig.class};
    }   

    @Override
    protected Class<?>[] getServletConfigClasses() {
    //	return null;
    	return new Class< ?>[] { MVCConfig.class };
    }
   
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        Filter [] singleton = { new CORSFilter() };
        return singleton;

    }
    
    // pour les websockets
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
      registration.setInitParameter("dispatchOptionsRequest", "true");
      registration.setAsyncSupported(true);
    }

} 