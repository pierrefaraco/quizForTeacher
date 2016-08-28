package cnam.glg204.quiz.server.service.cours;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import cnam.glg204.quiz.common.dto.CoursDto;
import cnam.glg204.quiz.common.dto.CoursWithStatusDto;
import cnam.glg204.quiz.common.dto.CoursWithSubscribersDto;
import cnam.glg204.quiz.common.dto.PoolNumberDto;
import cnam.glg204.quiz.common.dto.UserDto;
import cnam.glg204.quiz.common.enums.AccountType;
import cnam.glg204.quiz.common.enums.SubscriberStatus;
import cnam.glg204.quiz.common.exceptions.CheckException;
import cnam.glg204.quiz.common.exceptions.NoWebSocketMethodToSubscribeException;
import cnam.glg204.quiz.server.domain.cours.Cours;
import cnam.glg204.quiz.server.domain.cours.CoursDao;
import cnam.glg204.quiz.server.domain.user.UserDao;
import cnam.glg204.quiz.server.websocket.WebSocketPoolManager;
import cnam.glg204.quiz.server.domain.user.User ;

@Service("coursService")
@Transactional
@Rollback(true)
public class CoursServiceImpl implements CoursService{

	@Autowired
	CoursDao coursDao;
	
	@Autowired
	UserDao userDao;

	@Autowired
	WebSocketPoolManager webSocketPoolManager;

	@Override
	public CoursDto findCours(long coursId)  {
		Cours cours = coursDao.find(coursId);
		return coursToCoursDto( cours ) ;
	}
	
	@Override
	public void createCours(CoursDto coursDto) throws CheckException {
		Cours cours  = this.coursDtoToCours(coursDto);
		cours.checkData();
        coursDao.save(cours);
		coursDto.setId(cours.getId());
	}

	@Override
	public void updateCours(CoursDto coursDto) throws CheckException {
		Cours cours  = this.coursDtoToCours(coursDto);
		Cours cours2 = coursDao.find(cours.getId());
		cours.setSubscribers(cours2.getSubscribers());
		cours.checkData();
		coursDao.update(cours);
	}

	@Override
	public void deleteCours(long coursId) {
		Cours cours = coursDao.find(coursId);
		coursDao.delete(cours);
		
	}

	@Override
	public List<CoursDto> findCoursByProfessor(long professorId) {		
		User professor = userDao.find(professorId);	
		List<Cours> listCours = coursDao.findByProfessor(professor);
		return listOfcoursToListOfCoursDto(listCours);
	}

	@Override
	public List<CoursDto> getAllActiveCours() {
		List<Cours> listCours = coursDao.findActiveCours();
		return listOfcoursToListOfCoursDto(listCours);
	}
	
	@Override
	public void suscribe(long suscriberId, long coursId) throws CheckException {
		Cours cours = coursDao.find(coursId);
		User user = userDao.find(suscriberId);			
		if	(cours.getSubscribers()==null)
			cours.setSubscribers(new HashMap <User,SubscriberStatus>());
		cours.getSubscribers().put(user, SubscriberStatus.WAITING_ANSWER);
		cours.checkData();
		coursDao.save(cours );
	}
		
	@Override
	public void unSuscribe(long suscriberId, long coursId) throws CheckException {
		Cours cours = coursDao.find(coursId);
		User user = userDao.find(suscriberId);	
		cours.getSubscribers().remove(user);
		cours .checkData();
		coursDao.save(cours ); 
	}
	
	@Override
	public CoursWithSubscribersDto getCourWithSuscribers(long coursId) {
		
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
		for (Map.Entry<User,SubscriberStatus>  e : subscribers .entrySet()) {
			User user = e.getKey();		
			UserDto userDto = userToUserDto(user);
			SubscriberStatus status = e.getValue();	
			subscribersDto.put(userDto,status);		
			}	
		coursWithSubscribersDto.setSubscribers(subscribersDto);
		return coursWithSubscribersDto;
	}
	
	@Override
	public void updateCourWithSuscribers(CoursWithSubscribersDto coursWithSubscribersDto) throws CheckException {
		Cours cours = new Cours();	
		cours.setId(coursWithSubscribersDto.getId());
		cours.setActive(coursWithSubscribersDto.isActive());	
		cours.setDescription(coursWithSubscribersDto.getDescription());
		cours.setName(coursWithSubscribersDto.getName());
		User professor = userDao.find(coursWithSubscribersDto.getUserId());
		cours.setUser(professor);	
		Map <User,SubscriberStatus>  subscribers = new HashMap <User ,SubscriberStatus>();
		Map<UserDto, SubscriberStatus>  subscribersDto = coursWithSubscribersDto.getSubscribers();		
		for (Map.Entry<UserDto,SubscriberStatus>  e : subscribersDto.entrySet()) {
			UserDto userDto = e.getKey();		
			User auditor = userDao.find(userDto.getId());		
			SubscriberStatus status = e.getValue();	
			subscribers.put( auditor,status);		
			}	
		cours.setSubscribers(subscribers);
		cours.checkData();
		coursDao.update(cours);
	}
	

	
	
	@Override
	public void updateSuscriberStatus(long suscriberId, long coursId, SubscriberStatus status) throws CheckException {
		Cours cours = coursDao.find(coursId);
		User user = userDao.find(suscriberId);	
		cours.getSubscribers().put(user, status);
		cours.checkData();
		coursDao.save(cours );	
	}
	
	
	@Override
	public List<CoursWithStatusDto> findAllCoursWithAuditorStatus(long auditorId) {	
		List<Cours> listCours = coursDao.findActiveCours();
		User  auditor = userDao.find(auditorId);
		List<CoursWithStatusDto> listCoursDto = new ArrayList <CoursWithStatusDto> ();
		for (Cours cours :  listCours){
			CoursWithStatusDto coursDto = new CoursWithStatusDto ();
			coursDto.setId(cours.getId());
			coursDto.setActive(cours.isActive());	
			coursDto.setDescription(cours.getDescription());
			coursDto.setName(cours.getName());
			long userId = cours.getUser().getId();
			coursDto.setUserId(userId);
			SubscriberStatus status = cours.getSubscribers().get(auditor);
			coursDto.setStatus(status);
			listCoursDto.add(coursDto);
		}
		return listCoursDto;
	}
	
	@Override
	public PoolNumberDto getWebSocketMethodeNumber(long coursId,long  userId)throws NoWebSocketMethodToSubscribeException {	
		Cours cours = coursDao.find(coursId);
		User user = userDao.find(userId);
		PoolNumberDto poolNumber = new PoolNumberDto();
		poolNumber .setPoolNumber(-1);
		if (cours!=null && user !=null){
			SubscriberStatus subscriberStatus = cours.getSubscribers().get(user);
			if (subscriberStatus == SubscriberStatus.ACCEPTED || user.getAccountType() == AccountType.PROFESSOR)
				poolNumber .setPoolNumber(webSocketPoolManager.getWebSocketMethodeNumber( coursId,userId));			
		}
		//webSocketPoolManager.print();
		return  poolNumber ;
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
                if(cours == null)
                    return null;
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
