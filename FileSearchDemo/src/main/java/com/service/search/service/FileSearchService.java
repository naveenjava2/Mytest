package com.service.search.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.service.search.bean.AppProperties;
import com.service.search.bean.SearchRequest;
import com.service.search.bean.SearchResponse;
import com.service.search.exception.WordNotFoundException;
import com.service.search.file.FileHandler;

public class FileSearchService {
	public static final Logger logger = LogManager.getLogger(FileSearchService.class);	
    
public List<SearchResponse> getMatchWordFiles(AppProperties appProperties, List<SearchRequest> srchRequest) throws Exception{
	List<String> wordList= new ArrayList<>();
	List<SearchResponse> matchFileList = new ArrayList<>();
	try {
		
		srchRequest.forEach(word->{
			wordList.add(word.getSearchword());
		});
		logger.info("List of words to match in files : " + wordList);
		String globPattern = "*.txt";
		FileHandler fileHandler = new FileHandler(globPattern);
		List<String> fileList = fileHandler.getFileList(appProperties.getBasePath(), fileHandler);
		List<String> temList = new ArrayList<>();
		for(String file : fileList){
			FileReader fileIn = new FileReader(file);
	        BufferedReader reader = new BufferedReader(fileIn);
	        String line;
	        int i =0;
	        boolean isWordsMatch = false;
	        List<String> tempWordList = (List<String>) ((ArrayList)wordList).clone();
        	while((line = reader.readLine()) != null) {
	        	isWordsMatch = false;
	        	for(String word : tempWordList){
	        		if((line.contains(word) || line.startsWith(word) || line.endsWith(word))) {
	        			temList.add(word);
	        			 
		            }	        		
	        	}
	        	tempWordList.removeAll(temList);
	        	temList.clear();
	        	if(tempWordList.size()==0){
    				isWordsMatch = true;
            		break;
            	 }	
	        }
	        if(isWordsMatch){
	      	  SearchResponse matchFile = new SearchResponse();
	      	  matchFile.setFileName(file);
	      	  matchFile.setFilePath(file);
	  	  	  matchFileList.add(matchFile);
	      	    
	        }
		}
		
    }catch (IOException e){
    		throw new Exception("IO Exception occured");
	}
	 logger.info("Total files matched against words : "+  matchFileList.size());
	return matchFileList;
}

}

