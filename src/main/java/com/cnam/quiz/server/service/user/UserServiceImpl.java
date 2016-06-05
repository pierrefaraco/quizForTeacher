package com.cnam.quiz.server.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cnam.quiz.common.dto.UserDto;
import com.cnam.quiz.common.enums.AccountType;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.domain.user.UserDao;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	

	public UserServiceImpl(){
		
		
	}
	
	@Override
	public UserDto login(String login, String password) {	
		System.out.println(login +" "+password);
		User user = userDao.findUserByMailAndPassword(login, password);
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setAccountType(user.getAccountType());
		return userDto;		
	}



	@Override
	public UserDto findUser(long id) {
		User user = userDao.find(id);
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setFirstName(user.getFirstName());;
		userDto.setLastName(user.getLastName());
		userDto.setBirthDay(user.getBirthDay());
		userDto.setPresentation(user.getPresentation());
		userDto.setAccountType(user.getAccountType());
		return userDto;	
	}
	
	@Override
	public void createAccount(UserDto userDto) {
		System.out.println("create  "+ userDto.getEmail()+" " + userDto.getId()+ " " +userDto.getBirthDay());
		User user =new User();
		user.setAccountType(AccountType.AUDITOR);
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setFirstName(userDto.getFirstName());;
		user.setLastName(userDto.getLastName());
		user.setBirthDay(userDto.getBirthDay());
		user.setPresentation(userDto.getPresentation());
		userDao.save(user );	
		userDto.setAccountType(user.getAccountType());
		userDto.setId(user.getId());
		System.out.println("id " + user.getId());
	}

	@Override
	public void updateProfil(UserDto userDto) {
		System.out.println("update  "+ userDto.getEmail()+" " + userDto.getId()+ " " +userDto.getBirthDay());
		User user = userDao.find(userDto.getId());
		System.out.println("update  "+ user.getEmail()+" " + user.getId()+ " " +user.getBirthDay());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setFirstName(userDto.getFirstName());;
		user.setLastName(userDto.getLastName());
		user.setBirthDay(userDto.getBirthDay());
		user.setPresentation(userDto.getPresentation());	
		userDao.save(user );
	}

	@Override
	public void deleteAccount(String id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<UserDto> getAllProfessors() {
		// TODO Auto-generated method stub
		return null;
	}

}
