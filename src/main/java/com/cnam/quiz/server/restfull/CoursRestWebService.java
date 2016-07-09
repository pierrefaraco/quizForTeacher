package com.cnam.quiz.server.restfull;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cnam.quiz.common.dto.CoursDto;
import com.cnam.quiz.common.dto.CoursWithSubscribersDto;
import com.cnam.quiz.common.dto.UserDto;
import com.cnam.quiz.common.enums.SubscriberStatus;
import com.cnam.quiz.server.service.cours.CoursService;

@RestController
public class CoursRestWebService {

	@Autowired
	CoursService coursService;

	@RequestMapping(value = "/professor/cours/{coursid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CoursDto> findCours(
			@PathVariable("coursid") long coursId) {
		CoursDto cours = coursService.findCours(coursId);
		if (cours == null)
			return new ResponseEntity<CoursDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<CoursDto>(cours, HttpStatus.OK);
	}


	@RequestMapping(value = "/professor/cours/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CoursDto> createCours(@RequestBody CoursDto coursDto){
		coursDto.setId(0);
		coursService.createCours(coursDto);
		if ( coursDto.getId() == 0 )
			return new ResponseEntity<CoursDto>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<CoursDto>(coursDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/professor/cours/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity  updateCours(@RequestBody CoursDto coursDto) {
		coursService.updateCours(coursDto);
		CoursDto cours = coursService.findCours(coursDto.getId());
		if (!cours.equals( coursDto ))
			return new ResponseEntity<CoursDto>( HttpStatus.NOT_MODIFIED );
		return new ResponseEntity<CoursDto>( HttpStatus.OK);
	}

	@RequestMapping(value = "/professor/cours/{coursid}", method = RequestMethod.DELETE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteCours(@PathVariable("coursid") long coursId) {
		coursService.deleteCours(coursId);
		return new ResponseEntity( HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/user/{userid}/cours/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CoursDto>> getAllProfessorCours(@PathVariable("userid") long userId) {
		List<CoursDto> cours = coursService.getAllProfessorCours(userId);
		if (cours.isEmpty()) {
			return new ResponseEntity<List<CoursDto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CoursDto>>(cours, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all/active/cours/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CoursDto>>  getAllActiveCours() {
		List<CoursDto> cours = coursService.getAllActiveCours();
		if (cours.isEmpty()) {
				return new ResponseEntity<List<CoursDto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CoursDto>>(cours, HttpStatus.OK);
	}

	@RequestMapping(value = "/all/user/{userid}/cours/{coursid}/suscribe/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity  suscribe(@PathVariable("userid")long suscriberId,@PathVariable("coursid") long coursId) {
		coursService.suscribe(suscriberId, coursId);
		return new ResponseEntity  ( HttpStatus.OK);
	}

	@RequestMapping(value = "/all/user/{userid}/cours/{coursid}/unsuscribe/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity  unSuscribe(long suscriberId, long coursId) {
		coursService.unSuscribe(suscriberId, coursId);
		return new ResponseEntity  ( HttpStatus.OK);
	}

	@RequestMapping(value = "/professor/user/{userid}/cours/{coursid}/updatesuscriberstatus/{status} ", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity  updateSuscriberStatus(@PathVariable("userid")long suscriberId, 
			@PathVariable("coursid")long coursId,@PathVariable("status") SubscriberStatus status) {
		coursService.updateSuscriberStatus(suscriberId, coursId, status);
		return new ResponseEntity  ( HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/cours/{coursid}/sucribers/ ", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <CoursWithSubscribersDto> getAllSuscribers(@PathVariable("coursid")long coursId) {
		CoursWithSubscribersDto coursWithSubscribersDto = coursService.getAllSuscribers(coursId);
		if (coursWithSubscribersDto == null ) {
				return new ResponseEntity<CoursWithSubscribersDto>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<CoursWithSubscribersDto>(coursWithSubscribersDto, HttpStatus.OK);
	}

}
