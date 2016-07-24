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
import com.cnam.quiz.common.exceptions.CoursNotActiveException;
import com.cnam.quiz.common.exceptions.SessionQuizAlreadyRunningException;


public interface QuizService {
	
	   TopicDto findTopic(long id) ;
	     	     
	    void createTopic(TopicDto topicDto);
	     
	    void updateTopic(TopicDto topicDto);
	     
	    void deleteTopic(long id);
	 
	    List<TopicDto> findAllTopics() ;   
	    
	    List<TopicDto> findTopicsByProfessor(long userId) ;   
	    
        QuestionDto findQuestion(long id) ;
	     
	    void createQuestion(QuestionDto  questionDto);
	     
	    void updateQuestion(QuestionDto  questionDto);
	     
	    void deleteQuestion(long id)  ;
	 
	    List<QuestionDto> findQuestionsByTopic(long topicId) ;   
	     
	    SequenceWithQuestionsDto findSequence(long id) ;
	     
	    void createSequence(SequenceWithQuestionsDto sequenceDto);
	     
	    void updateSequence(SequenceWithQuestionsDto sequenceDto);
	     
	    void deleteSequence(long id)  ;
	    
	    List<SequenceDto> findSequenceByProfessor(long userId);   
	    
	    int addQuestionToSequence(long sequenceId, long questionId ,int pos);
	    
	    void removeQuestionFromSequence(long sequenceId,int pos);
	   
        SessionQuizDto findSessionQuiz(long id) ;
	     
	    void startSessionQuiz(SessionQuizDto sessionDto)throws SessionQuizAlreadyRunningException, CoursNotActiveException;
	     
	    void stopSessionQuiz(SessionQuizDto sessionDto);
	     
	    void deleteSessionQuiz(long id);
	    
        List <SessionQuizDto>findSessionQuizByCours(long coursId);
        
        
	    
}
