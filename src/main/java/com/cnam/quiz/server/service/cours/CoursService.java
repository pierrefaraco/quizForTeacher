package com.cnam.quiz.server.service.cours;

import java.util.List;

import com.cnam.quiz.common.dto.CoursDto;
import com.cnam.quiz.common.dto.SessionDto;
import com.cnam.quiz.common.dto.UserDto;

public interface CoursService {	
	
		CoursDto getCours(long coursId);
	
		CoursDto createCours();
		
		CoursDto updateCours(CoursDto coursDto);
		
		void deleteCours(long coursId);
		
		List <CoursDto>getAllProfessorCours(long professorId);
		
		List <UserDto> getAllSuscribers(long coursId);
		
		List <UserDto> updateSuscriberStatus (long suscriberId, long coursId);
		
		List <UserDto> suscribe(long suscriberId, long coursId);
		
		List <SessionDto> getAllSession(long coursId);
		
		List <CoursDto>getAllActiveCours();
		
}
