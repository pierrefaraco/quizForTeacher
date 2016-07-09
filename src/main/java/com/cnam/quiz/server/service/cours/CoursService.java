package com.cnam.quiz.server.service.cours;

import java.util.List;
import java.util.Map;

import com.cnam.quiz.common.dto.CoursDto;
import com.cnam.quiz.common.dto.CoursWithSubscribersDto;
import com.cnam.quiz.common.dto.UserDto;
import com.cnam.quiz.common.enums.SubscriberStatus;


public interface CoursService {	
	
		CoursDto findCours(long coursId);
			
		void  createCours(CoursDto coursDto );
		
		void  updateCours(CoursDto coursDto);
		
		void deleteCours(long coursId);
		
		List <CoursDto>getAllProfessorCours(long professorId);
		
		List <CoursDto>getAllActiveCours();		
			
		void suscribe(long suscriberId, long coursId);
		
		void unSuscribe(long suscriberId, long coursId);
		
		void updateSuscriberStatus (long suscriberId, long coursId, SubscriberStatus status);
		
		CoursWithSubscribersDto getAllSuscribers(long coursId);		
					
}
