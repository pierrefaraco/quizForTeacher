package com.pfaraco.quiz.server.restfull;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
 

import com.pfaraco.quiz.server.domain.topic.Topic;
import com.pfaraco.quiz.server.domain.topic.TopicDto;
import com.pfaraco.quiz.server.service.topic.TopicService;

@RestController
public class topicRestWebService {
	
	 @Autowired
	 TopicService topicService;	
	
	  //-------------------Retrieve All Topics--------------------------------------------------------
    
    @RequestMapping(value = "/topic/", method = RequestMethod.GET)
    public ResponseEntity<List<TopicDto>> listAllTopics() {
        List<TopicDto> topics = topicService.findAllTopics();
        if( topics.isEmpty()){
        	System.out.println("Topic with id  not found");
            return new ResponseEntity<List<TopicDto>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<TopicDto>>( topics, HttpStatus.OK);
    }
    
    
    //-------------------Retrieve Single Topic--------------------------------------------------------
    
    @RequestMapping(value = "/topic/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicDto> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching topic with id " + id);
        TopicDto topic = topicService.findById(id);
        if (topic  == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<TopicDto>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TopicDto>(topic, HttpStatus.OK);
    }
    
    //-------------------Create a Topic--------------------------------------------------------
    
    @RequestMapping(value = "/topic/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Topic topic,  UriComponentsBuilder ucBuilder) {
        
    	System.out.println("Creating User " + topic.getName());
  
        if (topicService.isTopicExist(topic)){
            System.out.println("A topic with name " + topic.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
  
        topicService.saveTopic(topic);
  
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/topic/{id}").buildAndExpand(topic.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    
    
    //------------------- Update a Topic --------------------------------------------------------
      
    @RequestMapping(value = "/topic/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TopicDto> updateTopic(@PathVariable("id") long id, @RequestBody Topic topic) {
        System.out.println("Updating User " + id);
          
        TopicDto currentTopic = topicService.findById(id);
          
        if (currentTopic==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<TopicDto>(HttpStatus.NOT_FOUND);
        }
  
        currentTopic.setName(topic.getName());
        currentTopic.setDescription(topic.getDescription());
 
          
        topicService.updateTopic(currentTopic);
        return new ResponseEntity<TopicDto>(currentTopic, HttpStatus.OK);
    }
    
    
   //------------------- Delete a Topic --------------------------------------------------------
     
   @RequestMapping(value = "/topic/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Topic> deleteUser(@PathVariable("id") long id) {
       System.out.println("Fetching & Deleting User with id " + id);
 
       TopicDto topic = topicService.findById(id);
       if (topic== null) {
           System.out.println("Unable to delete. User with id " + id + " not found");
           return new ResponseEntity<Topic>(HttpStatus.NOT_FOUND);
       }
 
       topicService.deleteTopicById(id);
       return new ResponseEntity<Topic>(HttpStatus.NO_CONTENT);
   }
   
   //------------------- Delete All Topics --------------------------------------------------------
   
   @RequestMapping(value = "/topic/", method = RequestMethod.DELETE)
   public ResponseEntity<Topic> deleteAllUsers() {
       System.out.println("Deleting All Users");
 
       topicService.deleteAllTopics();
       return new ResponseEntity<Topic>(HttpStatus.NO_CONTENT);
   }
 
  
}
