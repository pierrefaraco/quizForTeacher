package cnam.glg204.quiz.server.service.user;


import java.util.List;

import cnam.glg204.quiz.common.dto.UserDto;
import cnam.glg204.quiz.common.exceptions.CheckException;
import cnam.glg204.quiz.common.exceptions.CreateException;
import cnam.glg204.quiz.common.exceptions.ObjectNotFoundException;
import cnam.glg204.quiz.server.domain.user.User;


public interface UserService { 
	void createAccount(UserDto userDto)  throws CheckException , CreateException ;
	void updateProfil(UserDto userDto)  throws CheckException ;
	void deleteAccount(long id);
	UserDto findUser(long id) ;
}
