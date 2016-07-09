package com.cnam.quiz.server.service.quiz;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.cnam.quiz.common.dto.QuestionDto;
import com.cnam.quiz.common.dto.SequenceDto;
import com.cnam.quiz.common.dto.SessionQuizDto;
import com.cnam.quiz.common.dto.TopicDto;
import com.cnam.quiz.server.domain.cours.Cours;
import com.cnam.quiz.server.domain.cours.CoursDao;
import com.cnam.quiz.server.domain.questions.Question;
import com.cnam.quiz.server.domain.questions.QuestionDao;
import com.cnam.quiz.server.domain.sequence.Sequence;
import com.cnam.quiz.server.domain.sequence.SequenceDao;
import com.cnam.quiz.server.domain.sessionquiz.SessionQuiz;
import com.cnam.quiz.server.domain.sessionquiz.SessionQuizDao;
import com.cnam.quiz.server.domain.topic.Topic;
import com.cnam.quiz.server.domain.topic.TopicDao;
import com.cnam.quiz.server.domain.user.User;
import com.cnam.quiz.server.domain.user.UserDao;

@Service("quizService")
@Transactional
@Rollback(true)
public class QuizServiceImpl implements  QuizService{
	private static List<Topic> topics;
	
	 
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
	public void createTopic(TopicDto topicDto) {	
			Topic topic = topicDtoToTopic(topicDto) ;
			topicDao.save ( topic );
			topicDto.setId(topic.getId());
		}
	 
	@Override
	public TopicDto findTopic(long id) {
			Topic topic = topicDao.find(id);
			return this.topicToTopicDto(topic);
	}

	@Override
	public void updateTopic(TopicDto topicDto){
				Topic topic = topicDtoToTopic(topicDto);
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
	public void createQuestion(QuestionDto questionDto) {
		Question question = this.questionDtoToQuestion(questionDto);
		questionDao.save( question );
		questionDto.setId(question.getId());
		
	}

	@Override
	public QuestionDto findQuestion(long id) {		
		return this.questionToQuestionDto(questionDao.find(id));
	}

	@Override
	public void updateQuestion(QuestionDto questionDto) {
		questionDao.update(this.questionDtoToQuestion(questionDto));		
	}

	@Override
	public void deleteQuestion(long id) {
		Question question = questionDao.find(id);
		questionDao.delete(question);		
		
	}

	@Override
	public List<QuestionDto> findQuestionsByTopic(long topicId) {
		Topic topic = topicDao.find(topicId);	
		List<Question> questions = questionDao.findQuestionsByTopic(topic);
		return this.listOfQuestionsToListOfQuestionsDto(questions);
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
		question.setPoints(questionDto.getPoints());
		question.setPosition(questionDto.getPosition());
		question.setPropositions(questionDto.getPropositions());
		question.setQuestion(questionDto.getQuestion());
		question.setQuestionType(questionDto.getQuestionType());
		question.setAnswers(questionDto.getAnswers());
		question.setTimeToAnswer(questionDto.getTimeToAnswer());
		return question;		
	}
	
	public QuestionDto questionToQuestionDto ( Question question){
		QuestionDto questionDto = new QuestionDto();
		questionDto.setId(question.getId());
		long topicId = question.getTopic().getId();
		questionDto.setTopicId(topicId);
		questionDto.setPoints(question.getPoints());
		questionDto.setPosition(question.getPosition());
		questionDto.setPropositions(question.getPropositions());
		questionDto.setQuestion(question.getQuestion());
		questionDto.setQuestionType(question.getQuestionType());
		questionDto.setAnswers(question.getAnswers());
		questionDto.setTimeToAnswer(question.getTimeToAnswer());
		return questionDto;		
	}

	@Override
	public void createSequence(SequenceDto sequenceDto) {
		Sequence sequence = this.sequenceDtoToSequence(sequenceDto);
		sequenceDao.save( sequence);
		sequenceDto.setId(sequence.getId());		
	}
	
	
	@Override
	public SequenceDto findSequence(long id) {
		return this.sequenceToSequenceDto(sequenceDao.find(id));
	}

	@Override
	public void updateSequence(SequenceDto sequenceDto) {
		sequenceDao.update(this.sequenceDtoToSequence(sequenceDto));	
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
	public void addQuestionToSequence(long sequenceId, long questionId ,int pos) {
		Sequence sequence = sequenceDao.find(sequenceId);
		Map<Integer, Question> questions  = sequence.getQuestions();
		if (questions.containsKey(pos)){
			Question question = questions.get(pos);
			addQuestionToSequence( sequenceId, question.getId(), pos+1);
		}	
		Question question = questionDao.find(questionId);
		questions.put(pos, question );
		sequenceDao.save(sequence);
	}

	@Override
	public void removeQuestionFromSequence(long sequenceId , int pos) {
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
		sequenceDao.save(sequence);		
	}

	
	public Sequence sequenceDtoToSequence ( SequenceDto sequenceDto){
		Sequence sequence = new Sequence();
		sequence.setId(sequenceDto.getId());
		User user = userDao.find(sequenceDto.getUserId());
		sequence.setUser(user);
		sequence.setName(sequenceDto.getName());
		sequence.setDescription(sequenceDto.getDescription());			
		Map <Integer,QuestionDto >   questionsDto =  sequenceDto.getQuestions();
		Map <Integer,Question> questions = new HashMap <Integer,Question > ();
		for (Map.Entry<Integer,QuestionDto> e : questionsDto .entrySet()) {
			Integer key = e.getKey();
			Question question = this.questionDtoToQuestion(e.getValue());
			questions.put(key, question );
			}	
		sequence.setQuestions(questions);
		return sequence;		
	}
	
	public SequenceDto sequenceToSequenceDto ( Sequence sequence){
		SequenceDto sequenceDto = new SequenceDto();
		sequenceDto.setId(sequence.getId());
		long userId = sequence.getUser().getId();
		sequenceDto.setUserId( userId );
		sequenceDto.setName(sequence.getName());
		sequenceDto.setDescription(sequence.getDescription());	
		Map <Integer,Question>   questions =  sequence.getQuestions();
		Map <Integer,QuestionDto> questionsDto = new HashMap <Integer,QuestionDto > ();
		int i = 0 ;
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
	public void createSessionQuiz(SessionQuizDto sessionQuizDto) {
		SessionQuiz sessionQuiz = this.sessionQuizDtoToSessionQuiz(sessionQuizDto);
		sessionQuizDao.save(sessionQuiz );
		sessionQuizDto.setId(sessionQuiz.getId());
	}

	@Override
	public void updateSessionQuiz(SessionQuizDto sessionQuizDto) {
		SessionQuiz sessionQuiz = this.sessionQuizDtoToSessionQuiz(sessionQuizDto);
		sessionQuizDao.update( sessionQuiz );
	}

	@Override
	public void deleteSessionQuiz(long id) {
		SessionQuiz sessionQuiz = sessionQuizDao.find(id);
		sessionQuizDao.delete(sessionQuiz);	
	}

	@Override
	public List<SessionQuizDto> findSessionQuizByCours(long coursId) {
		Cours cours = coursDao.find(coursId);	
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
		sessionQuiz.setStartDate(sessionQuizDto.getStartDate());
		sessionQuiz.setEndDate(sessionQuizDto.getEndDate());
		sessionQuiz.setStatus(sessionQuizDto.getStatus());
		return sessionQuiz;		
	}
	
	public SessionQuizDto  sessionQuizToSessionQuizDto (SessionQuiz sessionQuiz){
		SessionQuizDto sessionQuizDto = new SessionQuizDto();
		sessionQuizDto.setId(sessionQuiz.getId());
		sessionQuizDto.setCoursId( sessionQuiz.getCours().getId());
		sessionQuizDto.setSequenceId(sessionQuiz.getSequence().getId());
		sessionQuizDto.setStartDate(sessionQuiz.getStartDate());
		sessionQuizDto.setEndDate(sessionQuiz.getEndDate());
		sessionQuizDto.setStatus(sessionQuiz.getStatus());
		return sessionQuizDto;		
	}
}
