package cnam.glg204.quiz.server.service.cours;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cnam.glg204.quiz.common.dto.CoursDto;
import cnam.glg204.quiz.common.dto.CoursWithStatusDto;
import cnam.glg204.quiz.common.dto.CoursWithSubscribersDto;
import cnam.glg204.quiz.common.dto.PoolNumberDto;
import cnam.glg204.quiz.common.dto.UserDto;
import cnam.glg204.quiz.common.enums.SubscriberStatus;
import cnam.glg204.quiz.common.exceptions.CheckException;
import cnam.glg204.quiz.common.exceptions.NoWebSocketMethodToSubscribeException;


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
		
		PoolNumberDto getWebSocketMethodeNumber(long coursId,long  userId) throws NoWebSocketMethodToSubscribeException;
}
