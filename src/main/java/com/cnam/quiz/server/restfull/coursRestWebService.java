package com.cnam.quiz.server.restfull;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import org.springframework.web.bind.annotation.RestController;

import com.cnam.quiz.common.dto.CoursDto;
import com.cnam.quiz.server.service.cours.CoursService;

@RestController
public class coursRestWebService {

	
	
	@Autowired
	CoursService coursService;	
	 
	
  //-------------------Retrieve All Topics--------------------------------------------------------
   
   @RequestMapping(value = "/user/{id}/cours/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<List<CoursDto>> listAllTopics(@PathVariable("id") long  userId) {
       List<CoursDto> cours =  coursService.getAllProfessorCours(userId);
       if( cours.isEmpty()){
           return new ResponseEntity<List<CoursDto>>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<List<CoursDto>>( cours, HttpStatus.OK);
   }
   
   
   @RequestMapping(value = "/user/{id}/cours/{coursId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<CoursDto> getCours(@PathVariable("id") long  userId,@PathVariable("coursId")long   coursId) {
       CoursDto cours = coursService.findCours(coursId);
       if( cours == null)
           return new ResponseEntity<CoursDto>(HttpStatus.NO_CONTENT);
      return new ResponseEntity<CoursDto>( cours, HttpStatus.OK);
   }
   
 

}
