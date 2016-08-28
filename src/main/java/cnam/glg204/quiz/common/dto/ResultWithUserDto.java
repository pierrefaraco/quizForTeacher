package cnam.glg204.quiz.common.dto;

import cnam.glg204.quiz.common.dto.jsonserial.ResultDtoDeserializer;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cnam.glg204.quiz.server.domain.sessionquiz.SessionQuiz;
import cnam.glg204.quiz.server.domain.user.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
@JsonDeserialize(using = ResultDtoDeserializer .class)
public class ResultWithUserDto extends ResultDto implements Serializable {

	UserDto userDto;
		
	
	public UserDto getUserDto() {
		return userDto;
	}
	
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
		
	
}
