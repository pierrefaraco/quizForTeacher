package cnam.glg204.quiz.common.dto;

import cnam.glg204.quiz.common.dto.jsonserial.CoursWithSubscribersDtoDeserializer;
import cnam.glg204.quiz.common.dto.jsonserial.CoursWithSubscribersDtoSerializer;
import java.util.HashMap;
import java.util.Map;

import cnam.glg204.quiz.common.enums.SubscriberStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CoursWithSubscribersDtoSerializer.class)
@JsonDeserialize(using = CoursWithSubscribersDtoDeserializer.class)
public class CoursWithSubscribersDto extends CoursDto {

	private HashMap <UserDto,SubscriberStatus> subscribers;
	
	
	public Map <UserDto, SubscriberStatus> getSubscribers() {
		return subscribers;
	}
        
	public void setSubscribers(HashMap<UserDto, SubscriberStatus> subscribers) {
		this.subscribers = subscribers;
	}
	
}
