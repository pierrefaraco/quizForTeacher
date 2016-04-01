package com.pfaraco;


import javax.servlet.Filter;
 
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
 
public class App extends AbstractAnnotationConfigDispatcherServletInitializer {
  
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { MVCConfiguration.class };
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
      //  Filter [] singleton = { new CORSFilter() };
     //   return singleton;
    	return null;
    }
  
}