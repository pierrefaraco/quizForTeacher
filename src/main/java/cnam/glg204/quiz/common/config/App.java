package cnam.glg204.quiz.common.config;
/**
 * 
 * Cette classe est le point d'entré de l'application.
 * 
 */
import javax.servlet.Filter;
import javax.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 
 * Initialisation de "Spring security et de spring Websocket"
 *@author Pierre Faraco 
 * 
 */

public class App extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {  SecurityConfig.class, WebSocketConfig.class };
	}

	/**
	 * 
	 * Initialisation de "Spring MVC"
	 * 
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { MVCConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	


	/**
	 * 
	 * Mechanisme qui permet de contrôller les requetes avant de les traiter.
	 * 
	 */
	@Override
	protected Filter[] getServletFilters() {
		Filter[] singleton = { new CORSFilter() };
		return singleton;

	}

	/**
	 * 
	 * Configuration pour l'utilisation des webs sockets
	 * 
	 */
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setInitParameter("dispatchOptionsRequest", "true");
		registration.setAsyncSupported(true);
	}

}