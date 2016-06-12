package com.cnam.quiz.server.service.user;


import java.util.List;

import com.cnam.quiz.common.dto.UserDto;
import com.cnam.quiz.server.domain.user.User;


public interface UserService {
	UserDto login(String login,String password);
	void createAccount(UserDto userDto);
	void updateProfil(UserDto userDto);
	void deleteAccount(String id);
	UserDto findUser(long id);
	User findUserByMail(String mail);
	List <UserDto> getAllProfessors();  
}
