package cnam.glg204.quiz.server.rest;
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
import cnam.glg204.quiz.common.dto.CoursDto;
import cnam.glg204.quiz.common.dto.CoursWithStatusDto;
import cnam.glg204.quiz.common.dto.CoursWithSubscribersDto;
import cnam.glg204.quiz.common.dto.PoolNumberDto;
import cnam.glg204.quiz.common.dto.UserDto;
import cnam.glg204.quiz.common.enums.SubscriberStatus;
import cnam.glg204.quiz.common.exceptions.CheckException;
import cnam.glg204.quiz.common.exceptions.NoWebSocketMethodToSubscribeException;
import cnam.glg204.quiz.server.service.cours.CoursService;

/**
 * 
 * Controlleur rest,  donne un accés aux methodes qui permmettent de gérer les cours
 * @author Pierre Faraco
 *
 */

@RestController
public class CoursRestWebService {

	@Autowired
	CoursService coursService;
	
	/**
	 *
	 * Renvois un cours sans la liste des abonnés à partir de son Id<br/>
	 * @param coursId id du cours
	 * @return Instance du cours trouvé  avec status HTTP
	 */

	@RequestMapping(value = "/all/cours/{coursid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CoursDto> findCours(
			@PathVariable("coursid") long coursId) {
		CoursDto cours = coursService.findCours(coursId);
		if (cours == null)
			return new ResponseEntity<CoursDto>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<CoursDto>(cours, HttpStatus.OK);
	}
	
	/**
	 *
	 * Renvois un cours avec  les la liste des abonnés à partir de son Id<br/>
	 * @param coursId id du cours
	 * @return Instance du cours trouvé comprenant la liste des abonnés avec status HTTP
	 * 	
	 */
	
	@RequestMapping(value = "/professor/coursAndSubscribers/{coursid} ", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <CoursWithSubscribersDto> getCoursWithSubscribersDto (@PathVariable("coursid")long coursId) {
		CoursWithSubscribersDto coursWithSubscribersDto = coursService.getCourWithSuscribers(coursId);
		if (coursWithSubscribersDto == null ) {
				return new ResponseEntity<CoursWithSubscribersDto>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<CoursWithSubscribersDto>(coursWithSubscribersDto, HttpStatus.OK);
	}
	
	/**
	 *
	 * Modifie un cours et sa liste d'abonnés à partir de son Id<br/>
	 * @param coursWithSubscribersDto Objet qui represente un cours et sa liste d'abonnés.
	 * @return Instance du cours modifé avec status HTTP
	 * 	
	 */
	
	@RequestMapping(value = "professor/coursAndSubscribers/ ", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <CoursWithSubscribersDto> updateCoursWithSubscribersDto (@RequestBody CoursWithSubscribersDto coursWithSubscribersDto) throws CheckException {
		coursService.updateCourWithSuscribers( coursWithSubscribersDto);
		return new ResponseEntity<CoursWithSubscribersDto>(coursWithSubscribersDto, HttpStatus.OK);
	}
	
	/**
	 *
	 * Créé un cours en base <br/>
	 * @param coursDto Objet qui represente le  cours à créer.
	 * @return Instance du cours créé avec en plus le paramètre Id auto-généré avec status HTTP
	 * 	
	 */

	@RequestMapping(value = "/professor/cours/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CoursDto> createCours(@RequestBody CoursDto coursDto) throws CheckException{
		coursDto.setId(0);
		coursService.createCours(coursDto);
		if ( coursDto.getId() == 0 )
			return new ResponseEntity<CoursDto>(HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<CoursDto>(coursDto, HttpStatus.OK);
	}
	
	/**
	 *
	 * Modifie un  cours <br/>
	 * @param coursDto Objet qui represente le cours à mettre à jour.
	 * @return Instance du cours modifé  avec status HTTP
	 * 	
	 */

	@RequestMapping(value = "/professor/cours/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity  updateCours(@RequestBody CoursDto coursDto) throws CheckException {
		coursService.updateCours(coursDto);
		CoursDto cours = coursService.findCours(coursDto.getId());
		if (!cours.equals( coursDto ))
			return new ResponseEntity<CoursDto>( HttpStatus.NOT_MODIFIED );
		return new ResponseEntity<CoursDto>( HttpStatus.OK);
	}
	
	/**
	 *
	 * Supprime un cours <br/>
	 * @param coursId id du cours à supprimer
	 * @return Status 
	 * 
	 */

	@RequestMapping(value = "/professor/cours/{coursid}", method = RequestMethod.DELETE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteCours(@PathVariable("coursid") long coursId) {
		coursService.deleteCours(coursId);
		return new ResponseEntity( HttpStatus.OK);
	}
	
	
	/**
	 *
	 * Retourne la liste des cours créé par un professeur <br/>
	 * @param userId id du professeur
	 * @return Liste des cours avec status HTTP
	 * 
	 */
	
	@RequestMapping(value = "/professor/user/{userid}/cours/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CoursDto>> getAllProfessorCours(@PathVariable("userid") long userId) {
		List<CoursDto> cours = coursService.findCoursByProfessor(userId);
		if (cours.isEmpty()) {
			return new ResponseEntity<List<CoursDto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CoursDto>>(cours, HttpStatus.OK);
	}
	
	/**
	 *
	 * Retourne la liste des cours actifs pour que les auditeurs puissent s'y abonner <br/>
	 * @return Liste des cours avec status HTTP
	 * 
	 */
	
	@RequestMapping(value = "/all/active/cours/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CoursDto>>  getAllActiveCours() {
		List<CoursDto> cours = coursService.getAllActiveCours();
		if (cours.isEmpty()) {
				return new ResponseEntity<List<CoursDto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CoursDto>>(cours, HttpStatus.OK);
	}
	
	/**
	 *
	 * Permet à un auditeur de s'incrire à un cours <br/>
	 * @param suscriberId id de l'auditeur qui veux s'inscrire
	 * @param coursId id du cours
	 * @return status HTTP
	 * 
	 */
	
	@RequestMapping(value = "/all/user/{userid}/cours/{coursid}/subscribe/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity  subscribe(@PathVariable("userid")long suscriberId,@PathVariable("coursid") long coursId) throws CheckException{
		coursService.suscribe(suscriberId, coursId);
		return new ResponseEntity  ( HttpStatus.OK );
	}
	
	
	/**
	 *
	 * Permet à un auditeur de se désincrire d'un cours <br/>
	 * @param suscriberId id de l'auditeur qui veux se désinscrire
	 * @param coursId id du cours
	 * @return status HTTP
	 * 
	 */

	@RequestMapping(value = "/all/user/{userid}/cours/{coursid}/unsubscribe/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity  unSubscribe(@PathVariable("userid")long suscriberId,@PathVariable("coursid") long coursId) throws CheckException{
		coursService.unSuscribe(suscriberId, coursId);
		return new ResponseEntity  ( HttpStatus.OK);
	}
	
	/**
	 *
	 * Permet à un professeur de changer le status d'un auditor abonnés à un cours () <br/>
	 * @deprecated utiliser plutot la méthode updateCoursWithSubscribersDto car elle peux traiter plusieurs changement de status en même temps. 
	 * @param suscriberId id de l'auditeur pour lequel on souhait emodifier le status
	 * @param coursId id du cours concerné
	 * @param status nouveau status de l'utilisateur 
	 * @return status HTTP
	 * 
	 */

	@RequestMapping(value = "/professor/user/{userid}/cours/{coursid}/updatesubscriberstatus/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity  updateSubscriberStatus(@PathVariable("userid")long suscriberId, 
			@PathVariable("coursid")long coursId,@PathVariable("status") SubscriberStatus status) throws CheckException{
		coursService.updateSuscriberStatus(suscriberId, coursId, status);
		return new ResponseEntity  ( HttpStatus.OK);
	}
	
	
	/**
	 *
	 * Permet à un auditeur de récupérer la liste des cours  avec pour chaque le status associé (WAITING_ANSWER , ACCEPTED , DENIED )
	 * @param auditorId id de l'auditeur
	 * @return liste des cours avec status,  status HTTP
	 * 
	 */
	
	@RequestMapping(value = "/all/user/{userid}/coursWithAuditorStatus/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<CoursWithStatusDto>> findAllCoursWithAuditorStatus(@PathVariable("userid")long auditorId){
		List<CoursWithStatusDto> listCours = coursService.findAllCoursWithAuditorStatus(auditorId);
		if (listCours.isEmpty()) {
			return new ResponseEntity<List<CoursWithStatusDto>>(HttpStatus.NO_CONTENT);
		}
		for (CoursWithStatusDto cours :listCours )
			System.out.println(cours.getName()+" "+cours.getStatus());
		return new ResponseEntity<List<CoursWithStatusDto>>(listCours, HttpStatus.OK);
		
	}

	/**
	 *
	 * Renvoit à l'utilisateur le n° de methode websocket qu'il doit utiliser en fonction du cours qu'il a sélectionné
	 * @param userId id de l'utilisateur
	 * @param coursId id du cours sélectionné
	 * @return numero de la methode websocket à utiliser pour le cours.
	 * 
	 */
	
	@RequestMapping(value = "/all/cours/{coursid}/user/{userid}/getPool/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PoolNumberDto> getWebSocketMethodeNumber(@PathVariable("coursid")long coursId,@PathVariable("userid")long  userId) throws NoWebSocketMethodToSubscribeException{
		PoolNumberDto poolNumber = coursService.getWebSocketMethodeNumber(coursId, userId);
		return new ResponseEntity<PoolNumberDto>(poolNumber, HttpStatus.OK);
	}
}
