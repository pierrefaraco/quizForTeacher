package com.cnam.quiz.server.service.user;


import java.util.List;

import com.cnam.quiz.common.dto.UserDto;
import com.cnam.quiz.common.exceptions.CheckException;
import com.cnam.quiz.common.exceptions.CreateException;
import com.cnam.quiz.common.exceptions.ObjectNotFoundException;
import com.cnam.quiz.server.domain.user.User;


public interface UserService { 
	void createAccount(UserDto userDto)  throws CheckException , CreateException ;
	void updateProfil(UserDto userDto)  throws CheckException ;
	void deleteAccount(long id);
	UserDto findUser(long id) ;
}
