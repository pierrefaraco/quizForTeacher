package com.cnam.quiz.server.service.quiz;

import java.util.List;
import java.util.Map;

import com.cnam.quiz.common.dto.CoursDto;
import com.cnam.quiz.common.dto.CoursWithStatusDto;
import com.cnam.quiz.common.dto.QuestionDto;
import com.cnam.quiz.common.dto.SequenceDto;
import com.cnam.quiz.common.dto.SequenceWithQuestionsDto;
import com.cnam.quiz.common.dto.SessionQuizDto;
import com.cnam.quiz.common.dto.TopicDto;
import com.cnam.quiz.common.enums.SubscriberStatus;
import com.cnam.quiz.common.exceptions.CheckException;
import com.cnam.quiz.common.exceptions.CoursNotActiveException;
import com.cnam.quiz.common.exceptions.NoCoursSelectedException;
import com.cnam.quiz.common.exceptions.SessionQuizAlreadyRunningException;


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
