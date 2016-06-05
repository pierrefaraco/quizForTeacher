package com.cnam.quiz.server.service.user;


import java.util.List;

import com.cnam.quiz.common.dto.UserDto;


public interface UserService {
	UserDto login(String login,String password);
	void createAccount(UserDto userDto);
	void updateProfil(UserDto userDto);
	void deleteAccount(String id);
	UserDto findUser(long id);
	List <UserDto> getAllProfessors();  
}
