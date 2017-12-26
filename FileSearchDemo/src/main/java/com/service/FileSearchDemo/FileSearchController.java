package com.service.FileSearchDemo;


import java.util.List;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.search.bean.AppProperties;
import com.service.search.bean.SearchRequest;
import com.service.search.bean.SearchResponse;
import com.service.search.bean.ValidationError;
import com.service.search.constant.AppContextConfig;
import com.service.search.constant.SearchRestConstants;
import com.service.search.exception.WordNotFoundException;
import com.service.search.service.FileSearchService;

 
@RestController
@RequestMapping(SearchRestConstants.ROOT_CONTEXT)
public class FileSearchController {
	
	public static final Logger logger = LogManager.getLogger(FileSearchController.class);	
	 
	@RequestMapping(value=SearchRestConstants.SEARCH_WORDS, headers = "Accept=application/json",produces = MediaType.APPLICATION_JSON_VALUE, method=RequestMethod.POST)
    public @ResponseBody ResponseEntity<List<SearchResponse>> SearchMultiWords(@Valid @RequestBody List<SearchRequest> srchRequest, BindingResult bindResult) throws Exception {
		List<SearchResponse> searchMatchFiles = null;
		ValidationError validateError = new ValidationError();
		validateError.validate(srchRequest, bindResult);
		if(bindResult.hasErrors()){
			return new ResponseEntity<List<SearchResponse>>(searchMatchFiles, HttpStatus.BAD_REQUEST);	
		}
		
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppContextConfig.class);
		AppProperties appProperties = context.getBean(AppProperties.class);
		context.close();
		logger.info("Calling search controller to match words in files");
		long sTime = System.currentTimeMillis();
		FileSearchService fileSearchService = new FileSearchService();
		searchMatchFiles = fileSearchService.getMatchWordFiles(appProperties,srchRequest);
		
		 long eTime = System.currentTimeMillis();
		logger.info("Total time taken for search files : ", (eTime-sTime));
		if(searchMatchFiles.size() <= 0)
			throw new WordNotFoundException("words matches not found");
		return new ResponseEntity<List<SearchResponse>>(searchMatchFiles, HttpStatus.OK);
		 
	}
    
}  