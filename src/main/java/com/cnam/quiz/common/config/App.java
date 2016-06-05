package com.cnam.quiz.common.config;


import javax.servlet.Filter;
 






import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
 //Cette classe lasse qui initialise l'application
public class App extends AbstractAnnotationConfigDispatcherServletInitializer {

	
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { MVCConfig.class   };
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