package cnam.glg204.quiz.common.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * 
 * Configuration Spring de la partie Web pour les services REST et les Web Sockets
 * 
 * @see <a href="http://websystique.com/springmvc/spring-mvc-4-angularjs-example/">spring-mvc-4 et angularjs tutorial</a>
 * 
 * @author Pierre Faraco 
 * 
*/
@Configuration
@EnableWebMvc
@ComponentScan(basePackages ="cnam.glg204")
public class MVCConfig extends WebMvcConfigurerAdapter{
		
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
    }
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
       
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }    
   
   // Web-socket config
    @Bean
    public WebContentInterceptor webContentInterceptor() {
      WebContentInterceptor interceptor = new WebContentInterceptor();
      interceptor.setCacheSeconds(0);
      interceptor.setUseExpiresHeader(true);
      interceptor.setUseCacheControlHeader(true);
      interceptor.setUseCacheControlNoStore(true);
      return interceptor;
    }
  
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(webContentInterceptor());
    }	
}