package com.cnam.quiz.server.service.user;


import java.util.List;

import com.cnam.quiz.common.dto.UserDto;
import com.cnam.quiz.common.exceptions.ObjectNotFoundException;
import com.cnam.quiz.server.domain.user.User;


public interface UserService { 
	void createAccount(UserDto userDto);
	void updateProfil(UserDto userDto);
	void deleteAccount(long id);
	UserDto findUser(long id) ;
}
