package com.service.search.bean;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

 
@XmlRootElement(name="searchRequest")
public class SearchRequest  {

	private static final long serialVersionUID = -7788619177798333712L;
	
	private int id;
	@NotNull()
	@NotEmpty()
	private String searchword;
	
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSearchword() {
		return searchword;
	}
	public void setSearchword(String searchword) {
		this.searchword = searchword;
	}
	 
}
