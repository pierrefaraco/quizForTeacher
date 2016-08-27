package cnam.glg204.quiz.common.dto.jsonserial;

import cnam.glg204.quiz.common.dto.CoursWithSubscribersDto;
import cnam.glg204.quiz.common.dto.UserDto;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cnam.glg204.quiz.common.enums.SubscriberStatus;
import cnam.glg204.quiz.server.domain.user.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CoursWithSubscribersDtoSerializer extends JsonSerializer < CoursWithSubscribersDto >{

	@Override
	public void serialize(CoursWithSubscribersDto  coursWithSusbscribers, JsonGenerator jgen,  SerializerProvider provider)
			throws IOException, JsonProcessingException {
		   jgen.writeStartObject();
			   jgen.writeNumberField("id",coursWithSusbscribers.getId() );
			   jgen.writeStringField("name",coursWithSusbscribers.getName());
			   jgen.writeStringField("description",coursWithSusbscribers.getDescription());
			   jgen.writeBooleanField("active",coursWithSusbscribers.isActive());
			   jgen.writeNumberField("userId",coursWithSusbscribers.getUserId());
			   Map <UserDto,SubscriberStatus> subscribers =   coursWithSusbscribers.getSubscribers();
			   jgen.writeArrayFieldStart("subscribers");
				   for ( Map.Entry <UserDto,SubscriberStatus>  e : subscribers.entrySet()) {
					    UserDto  userDto = e.getKey();		
						SubscriberStatus status = e.getValue();	
						jgen.writeStartObject();
					    	jgen.writeNumberField("id",userDto.getId() );
					    	jgen.writeStringField("lastName", userDto.getLastName());
						    jgen.writeStringField("firstName",  userDto.getFirstName());
						    jgen.writeStringField("email", userDto.getEmail());
						    jgen.writeStringField("status",e.getValue().toString());
					    jgen.writeEndObject();		   
				   }
			   jgen.writeEndArray();
		   jgen.writeEndObject();				   
	}		
}


