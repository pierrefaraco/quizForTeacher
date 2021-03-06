package cnam.glg204.quiz.server.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import cnam.glg204.quiz.common.dto.UserDto;
import cnam.glg204.quiz.common.exceptions.CheckException;
import cnam.glg204.quiz.common.exceptions.CreateException;
import cnam.glg204.quiz.common.exceptions.UpdateException;
import cnam.glg204.quiz.server.service.user.UserService;
/**
 * 
 * Controlleur rest, donne un accés aux methodes qui permmettent de gérer les profils utilisateurs
 * @author Pierre Faraco
 *
 */
@RestController
public class UserRestWebService {

	@Autowired
	UserService userService;
	

	/**
	 *
	 * Renvois un objet user partir de son Id<br/>
	 * @param userId id de l'utilisateur
	 * @return Instance de l'utilisateur,  status HTTP
	 * 
	 */
	@RequestMapping(value = "/all/user/{userid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto>  findUser(@PathVariable("userid") long id) {
		UserDto userDto = userService.findUser(id);
		if (userDto == null) {
			return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
	/**
	 *
	 * Modifie un objet User à partir de son Id<br/>
	 * @param userDto Objet qui represente un utilisateur,
	 * @return Instance du cours modifé avec status HTTP
	 * 	
	 */
	
	@RequestMapping(value = "/all/user/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< UserDto > updateProfil(@RequestBody UserDto userDto,
			UriComponentsBuilder ucBuilder) throws CheckException, UpdateException{
		userService.updateProfil(userDto);
		UserDto user = userService.findUser(userDto.getId());
		if (!userDto.equals(user))
			return new ResponseEntity< UserDto >( HttpStatus.NOT_MODIFIED );		
		return new ResponseEntity< UserDto >(user, HttpStatus.ACCEPTED);
	}
	
	/**
	 *
	 * Permet de creer un utilisateur <br/>
	 * @param userDto Objet qui represente un utilisateur,
	 * @return Instance de l'utilisateur créé avec en plus le paramètre Id auto-généré avec status HTTP
	 * 	
	 */

	@RequestMapping(value = "/unsecured/user/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> createAccount(@RequestBody UserDto userDto ) throws CheckException, CreateException {
		userService.createAccount(userDto);
		if (userDto.getId() == 0 )
			return new ResponseEntity<UserDto>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	
	/**
	 *
	 * Permet de supprimer un utilisateur <br/>
	 * @param userId id de l'utilisateur
	 * @return status HTTP
	 * 	
	 */

	@RequestMapping(value = "/professor/user/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteAccount(long id) {
		userService.deleteAccount(id);
		return new ResponseEntity( HttpStatus.OK);	
	}



}
