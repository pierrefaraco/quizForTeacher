package cnam.glg204.quiz.common.dto;

import java.io.Serializable;
import java.util.Map;

import cnam.glg204.quiz.common.enums.SubscriberStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;



public class CoursWithStatusDto extends CoursDto implements Serializable {

	private SubscriberStatus status;
	
	public SubscriberStatus getStatus() {
		return status;
	}
	public void setStatus(SubscriberStatus status) {
		this.status = status;
	}   

}
