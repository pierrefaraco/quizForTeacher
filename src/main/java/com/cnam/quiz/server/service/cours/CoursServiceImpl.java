package com.cnam.quiz.server.service.cours;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cnam.quiz.common.dto.CoursDto;
import com.cnam.quiz.common.dto.SessionDto;
import com.cnam.quiz.common.dto.UserDto;
import com.cnam.quiz.server.domain.cours.Cours;
import com.cnam.quiz.server.domain.cours.CoursDao;
import com.cnam.quiz.server.domain.user.UserDao;
import com.cnam.quiz.server.domain.user.User ;

@Service("coursService")
@Transactional
public class CoursServiceImpl implements CoursService{

	@Autowired
	CoursDao coursDao;
	
	@Autowired
	UserDao userDao;
	
	@Override
	public CoursDto createCours() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CoursDto updateCours(CoursDto coursDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCours(long coursId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CoursDto> getAllProfessorCours(long professorId) {		
		User user = userDao.find(professorId);	
		List<Cours> listCours = coursDao.getCoursProfessor(user);
		return listOfcoursToListOfCoursDto(listCours);
	}

	@Override
	public List<UserDto> getAllSuscribers(long coursId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> updateSuscriberStatus(long suscriberId, long coursId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> suscribe(long suscriberId, long coursId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SessionDto> getAllSession(long coursId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CoursDto> getAllActiveCours() {
		// TODO Auto-generated method stub
		return null;
	}


	
	public List <CoursDto> listOfcoursToListOfCoursDto (List <Cours> listCours){
		ArrayList<CoursDto> listCoursDto = new ArrayList<CoursDto>();
		for (Cours cours : listCours)
			 listCoursDto.add( coursToCoursDto(cours) );
		return	listCoursDto;	
	}
	
	public List <Cours> listOfcoursDtoToListOfCours (List <CoursDto> listCoursDto){
		ArrayList<Cours> listCours = new ArrayList<Cours>();
		for (CoursDto cours : listCoursDto)
			 listCours.add( coursDtoToCours(cours) );
		return	listCours;	
	}
	
	public CoursDto coursToCoursDto ( Cours cours){
		CoursDto coursDto = new CoursDto ();
		coursDto.setId(cours.getId());
		coursDto.setActive(cours.isActive());	
		coursDto.setDescription(cours.getDescription());
		coursDto.setName(cours.getName());
		//coursDto.setUser(cours.getUser());
		return coursDto;	
	}
	
	
	public Cours coursDtoToCours ( CoursDto coursDto){
		Cours cours = new Cours ();
		cours.setId(coursDto.getId());
		cours.setActive(coursDto.isActive());	
		cours.setDescription(coursDto.getDescription());
		cours.setName(coursDto.getName());
		cours.setUser(coursDto.getUser());
		return cours;	
	}
}
