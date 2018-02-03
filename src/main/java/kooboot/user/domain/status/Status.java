package kooboot.user.domain.status;

import java.io.NotActiveException;

import kooboot.user.domain.substatus.InitSubStatus;
import kooboot.user.domain.substatus.SubStatus;
import kooboot.user.domain.substatus.TranslateSubStatus;
import kooboot.user.domain.substatus.WeatherStatus;
import kooboot.user.exception.NotExistStatusException;

public class Status {
	
	private StatusCode status;
	private SubStatus subStatus;
	
	public Status(StatusCode statusCode){
		this.status = statusCode;
		initailizeSubStatus(statusCode);
	}
	
	public Status(StatusCode statusCode, String subStatusValue){
		this.status = statusCode;
		initailizeSubStatus(statusCode);
		setSubStatus(subStatusValue);
	}
	
	public void setStatusCode(StatusCode statusCode){
		this.status = statusCode;
		initailizeSubStatus(statusCode);
	}
	
	public StatusCode getStatusCode(){
		return this.status;
	}
	
	public void setSubStatus(String subStatusValue){
		this.subStatus.setStatusCode(subStatusValue);
	}
	
	public SubStatus getSubStatus(){
		return subStatus;
	}
	
	private void initailizeSubStatus(StatusCode statusCode){
		if(statusCode == StatusCode.INIT){
			this.subStatus = new InitSubStatus();
		}else if(statusCode == StatusCode.TRANSLATE){
			this.subStatus = new TranslateSubStatus();
		}else if(statusCode == StatusCode.WEATHER){
			this.subStatus = new WeatherStatus();
		}else{
			throw new NotExistStatusException("해당하는 하위 STATUS가 없습니다.");
		}
	}
	
}
