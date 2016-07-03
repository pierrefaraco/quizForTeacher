package com.cnam.quiz.server.restfull;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cnam.quiz.common.dto.CoursDto;
import com.cnam.quiz.common.dto.UserDto;
import com.cnam.quiz.common.enums.SubscriberStatus;
import com.cnam.quiz.server.service.cours.CoursService;

@RestController
public class CoursRestWebService {

	@Autowired
	CoursService coursService;

	@RequestMapping(value = "/professor/user/{userid}/cours/{coursid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CoursDto> findCours(@PathVariable("userid") long userId, @PathVariable("coursid") long coursId) {
		CoursDto cours = coursService.findCours(coursId);
		if (cours == null)
			return new ResponseEntity<CoursDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<CoursDto>(cours, HttpStatus.OK);
	}

	@RequestMapping(value = "/professor/cours/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CoursDto> createCours(CoursDto coursDto){
		coursDto.setId(-1);
		coursService.createCours(coursDto);
		if ( coursDto.getId()==-1 )
			return new ResponseEntity<CoursDto>(HttpStatus.valueOf("COURS NOT RECORDED"));
		return new ResponseEntity<CoursDto>(coursDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/professor/cours/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity  updateCours(CoursDto coursDto) {
		coursService.updateCours(coursDto);
		CoursDto cours = coursService.findCours(coursDto.getId());
		if (!cours.equals( coursDto ))
			return new ResponseEntity<CoursDto>( HttpStatus.NOT_MODIFIED );		
		return new ResponseEntity<CoursDto>( HttpStatus.OK);
	}

	@RequestMapping(value = "/professor/cours/{coursid}", method = RequestMethod.DELETE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteCours(@PathVariable("coursid") long coursId) {
		coursService.deleteCours(coursId);
		CoursDto cours = coursService.findCours(coursId);
		if(cours ==null)
			return new ResponseEntity( HttpStatus.OK);
		return new ResponseEntity( HttpStatus.NOT_MODIFIED );

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
	public ResponseEntity  updateSuscriberStatus(@PathVariable("userid")long suscriberId, @PathVariable("coursid")long coursId,@PathVariable("status") SubscriberStatus status) {
		coursService.updateSuscriberStatus(suscriberId, coursId, status);
		return new ResponseEntity  ( HttpStatus.OK);
	}
	
	@RequestMapping(value = "/professor/cours/{coursid}/sucribers/ ", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <Map<UserDto, SubscriberStatus>> getAllSuscribers(@PathVariable("coursid")long coursId) {
		Map<UserDto, SubscriberStatus>suscribers = coursService.getAllSuscribers(coursId);
		if (suscribers.isEmpty()) {
				return new ResponseEntity<Map<UserDto, SubscriberStatus>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Map<UserDto, SubscriberStatus>>(suscribers, HttpStatus.OK);
	}

}
