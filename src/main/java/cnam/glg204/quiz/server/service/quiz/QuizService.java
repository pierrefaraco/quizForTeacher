package cnam.glg204.quiz.server.service.quiz;

import java.util.List;
import java.util.Map;

import cnam.glg204.quiz.common.dto.CoursDto;
import cnam.glg204.quiz.common.dto.CoursWithStatusDto;
import cnam.glg204.quiz.common.dto.QuestionDto;
import cnam.glg204.quiz.common.dto.SequenceDto;
import cnam.glg204.quiz.common.dto.SequenceWithQuestionsDto;
import cnam.glg204.quiz.common.dto.SessionQuizDto;
import cnam.glg204.quiz.common.dto.TopicDto;
import cnam.glg204.quiz.common.enums.SubscriberStatus;
import cnam.glg204.quiz.common.exceptions.CheckException;
import cnam.glg204.quiz.common.exceptions.CoursNotActiveException;
import cnam.glg204.quiz.common.exceptions.NoCoursSelectedException;
import cnam.glg204.quiz.common.exceptions.SessionQuizAlreadyRunningException;


public interface QuizService {
	
        TopicDto findTopic(long id) ;
	     	     
	    void createTopic(TopicDto topicDto) throws CheckException ;
	     
	    void updateTopic(TopicDto topicDto) throws CheckException ;
	     
	    void deleteTopic(long id);
	 
	    List<TopicDto> findAllTopics() ;   
	    
	    List<TopicDto> findTopicsByProfessor(long userId) ;   
	    
        QuestionDto findQuestion(long id) ;
	     
	    void createQuestion(QuestionDto  questionDto) throws CheckException ;
	     
	    void updateQuestion(QuestionDto  questionDto)  throws CheckException ;
	     
	    void deleteQuestion(long id)  ;
	 
	    List<QuestionDto> findQuestionsByTopic(long topicId) ;   
	     
	    SequenceWithQuestionsDto findSequence(long id) ;
	     
	    void createSequence(SequenceWithQuestionsDto sequenceDto) throws CheckException ;
	     
	    void updateSequence(SequenceWithQuestionsDto sequenceDto)  throws CheckException ;
	     
	    void deleteSequence(long id)  ;
	    
	    List<SequenceDto> findSequenceByProfessor(long userId);   
	    
	    int addQuestionToSequence(long sequenceId, long questionId ,int pos) throws CheckException ;
	    
	    void removeQuestionFromSequence(long sequenceId,int pos) throws CheckException ;
	   
        SessionQuizDto findSessionQuiz(long id) ;
	     
	    void startSessionQuiz(SessionQuizDto sessionDto)throws SessionQuizAlreadyRunningException, CoursNotActiveException, CheckException, NoCoursSelectedException ;
	     
	    void stopSessionQuiz(SessionQuizDto sessionDto) throws CheckException ;
	     
	    void deleteSessionQuiz(long id);
	    
        List <SessionQuizDto>findSessionQuizByCours(long coursId);
        
        	    
}
