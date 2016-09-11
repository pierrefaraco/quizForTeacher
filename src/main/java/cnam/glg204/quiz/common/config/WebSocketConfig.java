package cnam.glg204.quiz.common.config;
/**
 * 
 * Configuration des websockets
 * @see <a href="http://g00glen00b.be/spring-angular-sockjs/">spring-websocket</a>
 * @author Pierre Faraco 
 * 
*/
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker  
@ComponentScan(basePackages ="cnam.glg204")
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	
  /**
	  * "/app" represente la racine  des URL d'entrée du controller WebSocketControler.<br/>
	  * "/auditors" represente la racine des  URL  de sortie  du controller WebSocketControler.<br/>
	 
  */
  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/auditors");
    config.setApplicationDestinationPrefixes("/app");
  }
  
  /**
   * 
   * Configure l'adresse du web socket,  le client devra se connecter à cette adresse avant de commencer à utiliser le mechanisme des web sockets.
   * 
  */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/all/socket").withSockJS();
  }
  
}