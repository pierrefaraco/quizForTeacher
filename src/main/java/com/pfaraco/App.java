package com.pfaraco;


import javax.servlet.Filter;
 



import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.pfaraco.quiz.server.config.MVCConfig;
import com.pfaraco.quiz.server.config.PersistenceJPAConfig;
 
public class App extends AbstractAnnotationConfigDispatcherServletInitializer {

	
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { MVCConfig.class };
    }
   
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
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
 
} //*/