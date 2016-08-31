package cnam.glg204.quiz.server.service.quiz;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import cnam.glg204.quiz.common.config.Constantes;
import cnam.glg204.quiz.common.dto.QuestionDto;
import cnam.glg204.quiz.common.dto.SequenceDto;
import cnam.glg204.quiz.common.dto.SequenceWithQuestionsDto;
import cnam.glg204.quiz.common.dto.SessionQuizDto;
import cnam.glg204.quiz.common.dto.TopicDto;
import cnam.glg204.quiz.common.enums.SessionStatus;
import cnam.glg204.quiz.common.exceptions.CheckException;
import cnam.glg204.quiz.common.exceptions.CoursNotActiveException;
import cnam.glg204.quiz.common.exceptions.NoCoursSelectedException;
import cnam.glg204.quiz.common.exceptions.SessionQuizAlreadyRunningException;
import cnam.glg204.quiz.server.domain.cours.Cours;
import cnam.glg204.quiz.server.domain.cours.CoursDao;
import cnam.glg204.quiz.server.domain.questions.Question;
import cnam.glg204.quiz.server.domain.questions.QuestionDao;
import cnam.glg204.quiz.server.domain.sequence.Sequence;
import cnam.glg204.quiz.server.domain.sequence.SequenceDao;
import cnam.glg204.quiz.server.domain.sessionquiz.SessionQuiz;
import cnam.glg204.quiz.server.domain.sessionquiz.SessionQuizDao;
import cnam.glg204.quiz.server.domain.topic.Topic;
import cnam.glg204.quiz.server.domain.topic.TopicDao;
import cnam.glg204.quiz.server.domain.user.User;
import cnam.glg204.quiz.server.domain.user.UserDao;

@Service("quizService")
@Transactional
@Rollback(true)
public class QuizServiceImpl implements  QuizService{ 
	@Autowired
	TopicDao topicDao;
	
	@Autowired
	QuestionDao questionDao;
	
	@Autowired
	SequenceDao sequenceDao;
	
	@Autowired
	SessionQuizDao sessionQuizDao;
	 
	@Autowired
	UserDao userDao;
	
	@Autowired
	CoursDao coursDao;
	
	@Override
	public void createTopic(TopicDto topicDto) throws CheckException {	
			Topic topic = topicDtoToTopic(topicDto) ;
			topic.checkData();
			topicDao.save ( topic );
			topicDto.setId(topic.getId());
		}
	 
	@Override
	public TopicDto findTopic(long id) {               
			Topic topic = topicDao.find(id);
			return this.topicToTopicDto(topic);
	}

	@Override
	public void updateTopic(TopicDto topicDto) throws CheckException {
				Topic topic = topicDtoToTopic(topicDto);
				topic.checkData();
				topicDao.update(topic);		
	}

	@Override
	public void deleteTopic(long id) {
			Topic topic = topicDao.find(id);
			topicDao.delete(topic);
	}

	@Override
	public List<TopicDto> findAllTopics() {
		List<Topic> topics = topicDao.findAll();
		List<TopicDto> topicsDto = this.listOfTopicsToListOfTopicsDto(topics);	
		return topicsDto ;
	}

	@Override
	public List<TopicDto> findTopicsByProfessor(long userId) {
		User user = userDao.find(userId);
		List<Topic> topics = topicDao.findByUser(user);
		List<TopicDto> topicsDto = this.listOfTopicsToListOfTopicsDto(topics);	
		return topicsDto;
	}

	public List <TopicDto> listOfTopicsToListOfTopicsDto (List <Topic> listTopics){
		ArrayList<TopicDto> listTopicsDto = new ArrayList<TopicDto>();
		for (Topic topic : listTopics)
			 listTopicsDto.add( topicToTopicDto(topic) );
		return	listTopicsDto;	
	}
	
	public List <Topic> listOftopicDtoToListOfTopic (List <TopicDto> listTopicDto){
		ArrayList<Topic> listTopic = new ArrayList<Topic>();
		for (TopicDto topic : listTopicDto)
			 listTopic.add( topicDtoToTopic(topic) );
		return	listTopic;	
	}

	public Topic topicDtoToTopic ( TopicDto topicDto){
		Topic topic = new Topic();
		topic.setId(topicDto.getId());
		User user = userDao.find(topicDto.getUserId());
		topic.setUser(user);
		topic.setName(topicDto.getName());
		topic.setDescription(topicDto.getDescription());		
		return topic;		
	}
	
	public TopicDto topicToTopicDto ( Topic topic){
		TopicDto topicDto = new TopicDto();
		topicDto.setId(topic.getId());
		long userId = topic.getUser().getId();
		topicDto.setUserId( userId );
		topicDto.setName(topic.getName());
		topicDto.setDescription(topic.getDescription());		
		return topicDto;		
	}

	@Override
	public void createQuestion(QuestionDto questionDto) throws CheckException {
		Question question = this.questionDtoToQuestion(questionDto);
		question.checkData();
		questionDao.save( question );
		questionDto.setId(question.getId());
		
	}

	@Override
	public QuestionDto findQuestion(long id) {		
		return this.questionToQuestionDto(questionDao.find(id));
	}

	@Override
	public void updateQuestion(QuestionDto questionDto) throws CheckException {
		Question  question = this.questionDtoToQuestion(questionDto);
		question.checkData();
		questionDao.update(question );		
	}

	@Override
	public void deleteQuestion(long id) {
		Question question = questionDao.find(id);
		questionDao.delete(question);		
		
	}

	@Override
	public List<QuestionDto> findQuestionsByTopic(long topicId) {
		Topic topic = topicDao.find(topicId);	
		List<Question> questions = questionDao.findByTopic(topic);		
		List<QuestionDto > questionsDto = this.listOfQuestionsToListOfQuestionsDto(questions);
		return questionsDto;
	}
	
	public List <QuestionDto> listOfQuestionsToListOfQuestionsDto (List <Question> listQuestions){
		ArrayList<QuestionDto> listQuestionsDto = new ArrayList<QuestionDto>();
		for (Question question : listQuestions)
			 listQuestionsDto.add( questionToQuestionDto(question) );
		return	listQuestionsDto;	
	}
	
	public List <Question> listOfquestionDtoToListOfQuestion (List <QuestionDto> listQuestionDto){
		ArrayList<Question> listQuestion = new ArrayList<Question>();
		for (QuestionDto question : listQuestionDto)
			 listQuestion.add( questionDtoToQuestion(question) );
		return	listQuestion;	
	}

	
	public Question questionDtoToQuestion ( QuestionDto questionDto){
		Question question = new Question();
		question.setId(questionDto.getId());
		Topic topic = topicDao.find(questionDto.getTopicId());
		question.setTopic(topic);
		question.setTitle( questionDto.getTitle());
		question.setPoints(questionDto.getPoints());
		question.setPosition(questionDto.getPosition());
		question.setAnswers(questionDto.getAnswers());
		question.setQuestion(questionDto.getQuestion());
		question.setQuestionType(questionDto.getQuestionType());
		question.setTimeToAnswer(questionDto.getTimeToAnswer());
		return question;		
	}
	
	public QuestionDto questionToQuestionDto ( Question question){
		QuestionDto questionDto = new QuestionDto();
		questionDto.setId(question.getId());
		long topicId = question.getTopic().getId();
		questionDto.setTopicId(topicId);
		questionDto.setTitle( question.getTitle());
		questionDto.setPoints(question.getPoints());
		questionDto.setPosition(question.getPosition());
		questionDto.setAnswers(question.getAnswers());
		questionDto.setQuestion(question.getQuestion());
		questionDto.setQuestionType(question.getQuestionType());
		questionDto.setTimeToAnswer(question.getTimeToAnswer());
		return questionDto;		
	}

	@Override
	public void createSequence(SequenceWithQuestionsDto sequenceDto) throws CheckException {
		Sequence sequence = this.sequenceWithQuestionsDtoToSequence(sequenceDto);
		sequence .checkData();
		sequenceDao.save( sequence);		
		sequenceDto.setId(sequence.getId());		
	}
	
	
	@Override
	public SequenceWithQuestionsDto findSequence(long id) {
		return this.sequenceToSequenceWithQuestionsDto(sequenceDao.find(id));
	}

	@Override
	public void updateSequence(SequenceWithQuestionsDto sequenceDto) throws CheckException {
		Sequence sequence = this.sequenceWithQuestionsDtoToSequence(sequenceDto);
		sequence .checkData();
		sequenceDao.update(sequence);	
	}

	@Override
	public void deleteSequence(long id) {
		Sequence sequence =  sequenceDao.find(id);
		sequenceDao.delete( sequence );		
	}
	
	public List <SequenceDto> listOfSequencesToListOfSequencesDto (List <Sequence> listSequences){
		ArrayList<SequenceDto> listSequencesDto = new ArrayList<SequenceDto>();
		for (Sequence sequence : listSequences)
			 listSequencesDto.add( sequenceToSequenceDto(sequence) );
		return	listSequencesDto;	
	}
	
	public List <Sequence> listOfsequenceDtoToListOfSequence (List <SequenceDto> listSequenceDto){
		ArrayList<Sequence> listSequence = new ArrayList<Sequence>();
		for (SequenceDto sequence : listSequenceDto)
			 listSequence.add( sequenceDtoToSequence(sequence) );
		return	listSequence;	
	}

	@Override
	public List<SequenceDto> findSequenceByProfessor(long userId) {
		User user = userDao.find(userId);
		List<Sequence> sequences = sequenceDao.findByUser(user);
		return this.listOfSequencesToListOfSequencesDto(sequences);
	}

	
	@Override
	public int addQuestionToSequence(long sequenceId, long questionId ,int pos) throws CheckException {
		Sequence sequence = sequenceDao.find(sequenceId);
		Map<Integer, Question> questions  = sequence.getQuestions();
		if (questions.containsKey(pos)){
			Question question = questions.get(pos);
			pos = addQuestionToSequence( sequenceId, question.getId(), pos+1);
		}	
		Question question = questionDao.find(questionId);
		questions.put(pos, question );
		sequence .checkData();
		sequenceDao.save(sequence);
		System.out.println(pos + " !" );
		return pos;
	}

	@Override
	public void removeQuestionFromSequence(long sequenceId , int pos) throws CheckException {
		Sequence sequence = sequenceDao.find(sequenceId);
		Map<Integer, Question> questions  = sequence.getQuestions();
		questions.remove(pos);
		Map<Integer, Question> newQuestions  = new HashMap<Integer, Question> ();		
		for (Map.Entry<Integer,Question> e : questions .entrySet()) {
			Question question = e.getValue();	
			int newpos = e.getKey();
			if ( newpos > pos)
				 newQuestions.put(newpos - 1 , question);
			else 
				newQuestions.put(newpos , question);
				
		}	
		sequence.setQuestions(newQuestions);
		sequence .checkData();
		sequenceDao.save(sequence);		
	}

	
	public Sequence sequenceDtoToSequence ( SequenceDto sequenceDto){
		Sequence sequence = new Sequence();
		sequence.setId(sequenceDto.getId());
		User user = userDao.find(sequenceDto.getUserId());
		sequence.setUser(user);
		sequence.setName(sequenceDto.getName());
		sequence.setDescription(sequenceDto.getDescription());			
		return sequence;		
	}
	
	public SequenceDto sequenceToSequenceDto ( Sequence sequence){
		SequenceDto sequenceDto = new SequenceDto();
		sequenceDto.setId(sequence.getId());
		long userId = sequence.getUser().getId();
		sequenceDto.setUserId( userId );
		sequenceDto.setName(sequence.getName());
		sequenceDto.setDescription(sequence.getDescription());	
		return sequenceDto;		
	}
	
	
	public Sequence sequenceWithQuestionsDtoToSequence ( SequenceWithQuestionsDto sequenceDto){
		Sequence sequence = new Sequence();
		sequence.setId(sequenceDto.getId());
		User user = userDao.find(sequenceDto.getUserId());
		sequence.setUser(user);
		sequence.setName(sequenceDto.getName());
		sequence.setDescription(sequenceDto.getDescription());			
		Map <Integer,QuestionDto >   questionsDto =  sequenceDto.getQuestions();
		Map <Integer,Question> questions = new HashMap <Integer,Question > ();
                if (questionsDto != null)
                    for (Map.Entry<Integer,QuestionDto> e : questionsDto .entrySet()) {
                            Integer key = e.getKey();
                            Question question = this.questionDtoToQuestion(e.getValue());
                            questions.put(key, question );
                            }	
		sequence.setQuestions(questions);
		return sequence;		
	}
	
	
	public SequenceWithQuestionsDto sequenceToSequenceWithQuestionsDto ( Sequence sequence){
		SequenceWithQuestionsDto sequenceDto = new SequenceWithQuestionsDto();
		sequenceDto.setId(sequence.getId());
		long userId = sequence.getUser().getId();
		sequenceDto.setUserId( userId );
		sequenceDto.setName(sequence.getName());
		sequenceDto.setDescription(sequence.getDescription());	
		Map <Integer,Question>   questions =  sequence.getQuestions();
		Map <Integer,QuestionDto> questionsDto = new HashMap <Integer,QuestionDto > ();
		int i = 0 ;
                if (questions!= null)
                    for (Map.Entry<Integer,Question> e : questions .entrySet()) {
                            Integer key = e.getKey();		
                            Question question = e.getValue();
                            QuestionDto questionDto = this.questionToQuestionDto(e.getValue());
                            questionsDto.put(key, questionDto );
                            }	
		sequenceDto.setQuestions(questionsDto);
		return sequenceDto;		
	}

	@Override
	public SessionQuizDto findSessionQuiz(long id) {
		SessionQuiz sessionQuiz = sessionQuizDao.find(id);
		return this.sessionQuizToSessionQuizDto(sessionQuiz);
	}

	@Override
	public void startSessionQuiz(SessionQuizDto sessionQuizDto) throws SessionQuizAlreadyRunningException, CoursNotActiveException, CheckException, NoCoursSelectedException {           
		Cours cours =  coursDao.find(sessionQuizDto.getCoursId());
                if (cours == null)
                    throw new NoCoursSelectedException ("no cours selected");
		if ( !cours.isActive() )
			throw new CoursNotActiveException("The cours " +cours.getName()  + " is not active");
		
		for (SessionQuiz  session : sessionQuizDao.findByCours(cours))
			if (session.getStatus().equals(SessionStatus.RUNNING))
				throw new SessionQuizAlreadyRunningException("A session quiz already running for the cours " +cours.getName());
							
		SessionQuiz sessionQuiz = this.sessionQuizDtoToSessionQuiz(sessionQuizDto);
		sessionQuiz.setStatus(SessionStatus.RUNNING);
		sessionQuiz.setStartDate(Calendar.getInstance().getTime());
		sessionQuiz .checkData();
		sessionQuizDao.save(sessionQuiz );
		sessionQuizDto.setId(sessionQuiz.getId());
		sessionQuizDto.setStatus(sessionQuiz.getStatus());
		
		SimpleDateFormat formatter = new SimpleDateFormat(Constantes.DATE_FORMAT );
		sessionQuizDto.setStartDate(formatter.format (sessionQuiz.getStartDate()));
	}

	@Override
	public void stopSessionQuiz(SessionQuizDto sessionQuizDto) throws CheckException {
		SessionQuiz sessionQuiz = this.sessionQuizDtoToSessionQuiz(sessionQuizDto);
		sessionQuiz.setStatus(SessionStatus.NOT_RUNNING);
		sessionQuiz.setEndDate(Calendar.getInstance().getTime());
		sessionQuiz .checkData();
		sessionQuizDao.update( sessionQuiz );
		sessionQuizDto.setStatus(sessionQuiz.getStatus());
		sessionQuizDto.setEndDate(sessionQuiz.getEndDate().toString());
	}

	@Override
	public void deleteSessionQuiz(long id) {
		SessionQuiz sessionQuiz = sessionQuizDao.find(id);
		sessionQuizDao.delete(sessionQuiz);	
	}

	@Override
	public List<SessionQuizDto> findSessionQuizByCours(long coursId) {
		Cours cours = coursDao.find(coursId);
                if (cours ==null)
                    return null;
		List<SessionQuiz> listSessionQuiz =  sessionQuizDao.findByCours(cours);		
		return this.listOfSessionQuizToListOfSessionQuizDto(listSessionQuiz);
	}
	

	
	public List <SessionQuizDto> listOfSessionQuizToListOfSessionQuizDto (List <SessionQuiz> listSessionQuiz){
		ArrayList<SessionQuizDto> listSessionQuizDto = new ArrayList<SessionQuizDto>();
		for (SessionQuiz sessionQuiz : listSessionQuiz)
			listSessionQuizDto.add( sessionQuizToSessionQuizDto(sessionQuiz) );
		return	listSessionQuizDto;	
	}
	
	public List <SessionQuiz> listOfsessionQuizDtoToListOfSessionQuiz (List <SessionQuizDto> listSessionQuizDto){
		ArrayList<SessionQuiz> listSessionQuiz = new ArrayList<SessionQuiz>();
		for (SessionQuizDto sessionQuiz : listSessionQuizDto)
			 listSessionQuiz.add( sessionQuizDtoToSessionQuiz(sessionQuiz) );
		return	listSessionQuiz;	
	}

	public SessionQuiz sessionQuizDtoToSessionQuiz(SessionQuizDto sessionQuizDto){
		SessionQuiz sessionQuiz = new SessionQuiz();
		sessionQuiz.setId(sessionQuizDto.getId());
		Cours cours = coursDao.find(sessionQuizDto.getCoursId());
		Sequence sequence = sequenceDao.find(sessionQuizDto.getSequenceId());
		sessionQuiz.setCours(cours);
		sessionQuiz.setSequence(sequence);
		
		SimpleDateFormat formatter = new SimpleDateFormat(Constantes.DATE_FORMAT );
		if ( sessionQuizDto.getStartDate()!= null)
			try {
				sessionQuiz.setStartDate( formatter.parse(sessionQuizDto.getStartDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
		if ( sessionQuizDto.getEndDate()!= null)
			try {
				sessionQuiz.setStartDate( formatter.parse(sessionQuizDto.getEndDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 
		sessionQuiz.setStatus(sessionQuizDto.getStatus());
		return sessionQuiz;		
	}
	
	public SessionQuizDto  sessionQuizToSessionQuizDto (SessionQuiz sessionQuiz){
		SessionQuizDto sessionQuizDto = new SessionQuizDto();
		sessionQuizDto.setId(sessionQuiz.getId());
		sessionQuizDto.setCoursId( sessionQuiz.getCours().getId());
		sessionQuizDto.setSequenceId(sessionQuiz.getSequence().getId());
		SimpleDateFormat formatter = new SimpleDateFormat(Constantes.DATE_FORMAT );
		if (sessionQuiz.getStartDate() != null)
			sessionQuizDto.setStartDate( formatter.format(sessionQuiz.getStartDate()));
		if (sessionQuiz.getEndDate() != null)
			sessionQuizDto.setEndDate( formatter.format(sessionQuiz.getEndDate()));
		sessionQuizDto.setStatus(sessionQuiz.getStatus());
		return sessionQuizDto;		
	}


}
