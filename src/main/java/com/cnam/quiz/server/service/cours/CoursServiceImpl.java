package com.cnam.quiz.server.service.cours;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import com.cnam.quiz.common.dto.CoursDto;
import com.cnam.quiz.common.dto.CoursWithSubscribersDto;
import com.cnam.quiz.common.dto.UserDto;
import com.cnam.quiz.common.enums.SubscriberStatus;
import com.cnam.quiz.server.domain.cours.Cours;
import com.cnam.quiz.server.domain.cours.CoursDao;
import com.cnam.quiz.server.domain.user.UserDao;
import com.cnam.quiz.server.domain.user.User ;

@Service("coursService")
@Transactional
@Rollback(true)
public class CoursServiceImpl implements CoursService{

	@Autowired
	CoursDao coursDao;
	
	@Autowired
	UserDao userDao;

	@Override
	public CoursDto findCours(long coursId) {
		Cours cours = coursDao.find(coursId);
		return coursToCoursDto( cours ) ;
	}
	
	@Override
	public void createCours(CoursDto coursDto) {
		Cours cours  = this.coursDtoToCours(coursDto);
                
		System.out.println("Save cours =>  "+ cours.getId()+  " " +cours.getName() +
				" "+  cours.getDescription()+ " " +cours.isActive() 
				+cours.getUser().getId());

		coursDao.save(cours);
		coursDto.setId(cours.getId());
	}

	@Override
	public void updateCours(CoursDto coursDto) {
		Cours cours  = this.coursDtoToCours(coursDto);
		coursDao.update(cours);
	}

	@Override
	public void deleteCours(long coursId) {
		Cours cours = coursDao.find(coursId);
		coursDao.delete(cours);
		
	}

	@Override
	public List<CoursDto> getAllProfessorCours(long professorId) {		
		User user = userDao.find(professorId);	
		List<Cours> listCours = coursDao.getCoursProfessor(user);
		return listOfcoursToListOfCoursDto(listCours);
	}

	@Override
	public List<CoursDto> getAllActiveCours() {
		List<Cours> listCours = coursDao.getActiveCours();
		return listOfcoursToListOfCoursDto(listCours);
	}
	
	@Override
	public void suscribe(long suscriberId, long coursId) {
		Cours cours = coursDao.find(coursId);
		User user = userDao.find(suscriberId);			
		if	(cours.getSubscribers()==null)
			cours.setSubscribers(new HashMap <User,SubscriberStatus>());
		cours.getSubscribers().put(user, SubscriberStatus.WAITING_ANSWER);
		coursDao.save(cours );
	}
		
	@Override
	public void unSuscribe(long suscriberId, long coursId) {
		Cours cours = coursDao.find(coursId);
		User user = userDao.find(suscriberId);	
		cours.getSubscribers().remove(user);
	}
	
	@Override
	public CoursWithSubscribersDto getAllSuscribers(long coursId) {
		
		CoursWithSubscribersDto coursWithSubscribersDto = new CoursWithSubscribersDto();
		Cours cours = coursDao.find(coursId);		
		coursWithSubscribersDto.setId(cours.getId());
		coursWithSubscribersDto.setActive(cours.isActive());	
		coursWithSubscribersDto.setDescription(cours.getDescription());
		coursWithSubscribersDto.setName(cours.getName());
		long userId = cours.getUser().getId();
		coursWithSubscribersDto.setUserId(userId);	
		Map <User,SubscriberStatus>  subscribers = cours.getSubscribers();
		HashMap <UserDto ,SubscriberStatus>  subscribersDto = new HashMap <UserDto ,SubscriberStatus>();	
		System.out.println(subscribers.size() +" suscribers :");
		for (Map.Entry<User,SubscriberStatus>  e : subscribers .entrySet()) {
			User user = e.getKey();		
			UserDto userDto = userToUserDto(user);
			SubscriberStatus status = e.getValue();	
			System.out.println( userDto.getFirstName()+ " "+ userDto.getEmail());
			subscribersDto.put(userDto,status);		
			}	
		coursWithSubscribersDto.setSubscribers(subscribersDto);
		return coursWithSubscribersDto;
	}
	
	@Override
	public void updateSuscriberStatus(long suscriberId, long coursId, SubscriberStatus status) {
		Cours cours = coursDao.find(coursId);
		User user = userDao.find(suscriberId);	
		cours.getSubscribers().put(user, status);
		coursDao.save(cours );	
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
	
	public Cours coursDtoToCours ( CoursDto coursDto){
		Cours cours = new Cours ();
		cours.setId(coursDto.getId());
		cours.setActive(coursDto.isActive());	
		cours.setDescription(coursDto.getDescription());
		cours.setName(coursDto.getName());
		User user  = userDao.find(coursDto.getUserId());
		cours.setUser(user);
		cours.setSubscribers(new HashMap<User, SubscriberStatus>());
		return cours;	
	}

	public CoursDto coursToCoursDto ( Cours cours){
		CoursDto coursDto = new CoursDto ();
		coursDto.setId(cours.getId());
		coursDto.setActive(cours.isActive());	
		coursDto.setDescription(cours.getDescription());
		coursDto.setName(cours.getName());
		long userId = cours.getUser().getId();
		coursDto.setUserId(userId);
		return coursDto;	
	}
	
	private UserDto userToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setAccountType(user.getAccountType());
		return userDto;
	}
	
}
