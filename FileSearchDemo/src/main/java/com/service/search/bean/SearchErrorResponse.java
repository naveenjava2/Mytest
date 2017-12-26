package com.service.search.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchErrorResponse {
	private int errorCode;
	private String errorMessage;
	private String errorDesc;
	private List<String> errors;

	public SearchErrorResponse(){
		super();
	}
	public SearchErrorResponse(int errorCode, String errorMessage, String errorDesc){
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorDesc = errorDesc;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	

}
