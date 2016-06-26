package com.cnam.quiz.server.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnam.quiz.common.dto.UserDto;
import com.cnam.quiz.common.enums.AccountType;
import com.cnam.quiz.common.exceptions.ObjectNotFoundException;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.domain.user.UserDao;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;


	public UserServiceImpl() {

	}


	@Override
	public UserDto findUser(long id) {
		User user = userDao.find(id);
		return userToUserDto(user);
	}

	@Override
	public void createAccount(UserDto userDto) {
		System.out.println("create  " + userDto.getEmail() + " "
				+ userDto.getId() + " " + userDto.getBirthDay());
		User user = new User();
		user.setAccountType(AccountType.AUDITOR);
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setFirstName(userDto.getFirstName());
		;
		user.setLastName(userDto.getLastName());
		user.setBirthDay(userDto.getBirthDay());
		user.setPresentation(userDto.getPresentation());
		userDao.save(user);
		userDto.setAccountType(user.getAccountType());
		userDto.setId(user.getId());
		System.out.println("id " + user.getId());
	}

	@Override
	public void updateProfil(UserDto userDto) {
		System.out.println("update  " + userDto.getEmail() + " "
				+ userDto.getId() + " " + userDto.getBirthDay());
		User user = userDao.find(userDto.getId());

		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setBirthDay(userDto.getBirthDay());
		user.setPresentation(userDto.getPresentation());
		userDao.save(user);
	}

	@Override
	public void deleteAccount(String id) {
	

	}

	@Override
	public List<UserDto> getAllProfessors() {
		return null;
	}

	@Override
	public User findUserByMail(String email) {
		User user = userDao.findUserByMail(email);
		return user ;
	}

	public UserDto userToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setBirthDay(user.getBirthDay());
		userDto.setPresentation(user.getPresentation());
		userDto.setAccountType(user.getAccountType());
		return userDto;
	}

	public User userDtoToUser(User userDto) {
		User user = new User();
		user.setAccountType(userDto.getAccountType());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setBirthDay(userDto.getBirthDay());
		user.setPresentation(userDto.getPresentation());
		return user;
	}



}
