package com.cnam.quiz.server.service.cours;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cnam.quiz.common.dto.CoursDto;
import com.cnam.quiz.common.dto.CoursWithStatusDto;
import com.cnam.quiz.common.dto.CoursWithSubscribersDto;
import com.cnam.quiz.common.dto.PoolNumberDto;
import com.cnam.quiz.common.dto.UserDto;
import com.cnam.quiz.common.enums.SubscriberStatus;
import com.cnam.quiz.common.exceptions.CheckException;


public interface CoursService {	
	
		CoursDto findCours(long coursId);
			
		void  createCours(CoursDto coursDto )throws CheckException;
		
		void  updateCours(CoursDto coursDto)throws CheckException;
		
		void deleteCours(long coursId);
		
		List <CoursDto>getAllProfessorCours(long professorId);
		
		List <CoursDto>getAllActiveCours();		
			
		void suscribe(long suscriberId, long coursId)throws CheckException;
		
		void unSuscribe(long suscriberId, long coursId)throws CheckException;
		
		void updateSuscriberStatus (long suscriberId, long coursId, SubscriberStatus status)throws CheckException;
		
		CoursWithSubscribersDto getCourWithSuscribers(long coursId);		
				
		void updateCourWithSuscribers(CoursWithSubscribersDto coursWithSubscribersDto)throws CheckException;	
		
		List<CoursWithStatusDto> findAllCoursWithAuditorStatus(long auditorID);
		
		PoolNumberDto getWebSocketMethodeNumber(long coursId,long  userId);
}
