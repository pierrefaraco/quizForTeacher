package com.cnam.quiz.server.restfull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.cnam.quiz.common.dto.UserDto;
import com.cnam.quiz.server.service.user.UserService;

@RestController
public class unsecuredRestWebService {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "unsecured/user/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> createAccount(@RequestBody UserDto userDto,
			UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + userDto.getFirstName());
		userService.createAccount(userDto);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/topic/{id}")
				.buildAndExpand(userDto.getId()).toUri());
		return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
	}
}
