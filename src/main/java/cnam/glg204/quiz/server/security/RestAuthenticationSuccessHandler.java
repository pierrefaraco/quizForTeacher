package cnam.glg204.quiz.server.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import cnam.glg204.quiz.common.dto.UserDto;
import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.domain.user.UserDao;
import cnam.glg204.quiz.server.service.user.UserService;

@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
 
 @Autowired
 private UserDao userDao;
 
 
 @Override
 public void onAuthenticationSuccess(HttpServletRequest request, 
  HttpServletResponse response, Authentication authentication)
		  throws ServletException, IOException {
	 		User user =  userDao.findByMail(authentication.getName());
	 		SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, user);
 }
}