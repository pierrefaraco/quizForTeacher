package com.cnam.quiz.server.restfull;

import javax.servlet.http.Cookie;

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
 


import com.cnam.quiz.common.dto.UserDto;
import com.cnam.quiz.server.service.user.UserService;

@RestController
public class UserRestWebService {
	
	 @Autowired
	 UserService userService;	
	 
	  @RequestMapping(value = "/user/{email}/{password}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<UserDto> login(@PathVariable("email") String email,@PathVariable("password") String password) {
	      	UserDto userDto = userService.login(email, password);
	        if (userDto   == null) {
	            System.out.println("User with email  " + email + " not found");
	            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
	        }
	        HttpHeaders headers = new HttpHeaders();       
	        headers.add("Set-Cookie", "connected=" + "yes");
	        headers.add("Set-Cookie", "userId=" + userDto.getId() );
	        headers.add("Set-Cookie", "accountType=" + userDto.getAccountType());
	        headers.add("Set-Cookie", "tocken=" +"#$"+ (int)(Math.random()*100000000)+"$#");
	        return new ResponseEntity<UserDto>(userDto,headers, HttpStatus.OK);
	    }
	 
	 
	 @RequestMapping(value = "/user/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<UserDto> createAccount (@RequestBody UserDto userDto,  UriComponentsBuilder ucBuilder) {	        
	    	System.out.println("Creating User " + userDto.getFirstName());	  
	    	userService.createAccount(userDto);  
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/topic/{id}").buildAndExpand(userDto.getId()).toUri());
	        return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
	  }
	 
	 
	
	 @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Void> updateProfil (@RequestBody UserDto userDto,  UriComponentsBuilder ucBuilder) {	        
	    	System.out.println("Creating User " + userDto.getFirstName());	  
	    	userService.updateProfil(userDto);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/topic/{id}").buildAndExpand(userDto.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.ACCEPTED);
	  }
	 
	 
	  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<UserDto> getUser(@PathVariable("id") long id) {
		  
		  	UserDto userDto = userService.findUser(id);
	   
	        if (userDto   == null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	    }
	    

	    


}
