package com.service.search.bean;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SearchValidator implements Validator {
	
	@Override
	public boolean supports(Class clazz) {
		return SearchRequest.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		if (obj instanceof SearchRequest)
		{
			SearchRequest sechReq = (SearchRequest)obj;
			if(sechReq.getSearchword() ==null){
				errors.rejectValue("searchWord", "symbolsPresent",new Object[]{"'searchWord'"},"searchWord can't be null");	
			}else if(sechReq.getSearchword().trim().equalsIgnoreCase("")){
				errors.rejectValue("searchWord", "searchWord",new Object[]{"'searchWord'"},"searchWord can't be empty");
			}
		}
	}
	
}
