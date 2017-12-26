package com.service.search.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.service.search.bean.AppProperties;
import com.service.search.bean.SearchRequest;
import com.service.search.bean.SearchResponse;
import com.service.search.file.FileHandler;

public class FileSearch {
	
	public List<SearchResponse> getMatchWordFiles(List<SearchRequest> srchRequest) throws Exception{
		long sTime = System.currentTimeMillis();
		List<String> wordList= new ArrayList<>();
		List<SearchResponse> matchFileList = new ArrayList<>();
		try {
			
			srchRequest.forEach(word->{
				wordList.add(word.getSearchword());
			});
			String globPattern = "*.txt";
			FileHandler fileHandler = new FileHandler(globPattern);
			List<String> fileList = fileHandler.getFileList("/home/scopus-1/workspace/FileSearchDemo/", fileHandler);
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
			long eTime = System.currentTimeMillis();
			
			System.out.println(" total file :"+  (eTime-sTime));
	    }catch (IOException e){
	    		throw new Exception("IO Exception occured");
		}
		return matchFileList;
	}
	public List<SearchResponse> getNioMatchWordFiles(List<SearchRequest> srchRequest) throws Exception{
		long sTime = System.currentTimeMillis();
		List<String> wordList= new ArrayList<>();
		List<SearchResponse> matchFileList = new ArrayList<>();
		try {
			
			srchRequest.forEach(word->{
				wordList.add(word.getSearchword());
			});
			FileHandler fileHandler = new FileHandler("" );
			//List<Path> fileList = fileHandler.getpathList("/home/scopus-1/workspace/FileSearchDemo/", fileHandler);
			List<String> temList = new ArrayList<>();
			List<Path> temLst = new ArrayList<>();
			
			temList.add("india");
			temList.add("london");
			long wordCount = 0;

			for(Path file : temLst){
				 Stream<String> fileLines = Files.lines(file, Charset.defaultCharset());
				 	
		     }
			long eTime = System.currentTimeMillis();
			
			System.out.println("nio file :"+  (eTime-sTime));	
	    }catch (IOException e){
	    		throw new Exception("IO Exception occured");
		}
		return matchFileList;
	}
	 public static List<String> filterEmployees (List<String> employees, Predicate<String> predicate) {
	        return employees.stream().filter( predicate ).collect(Collectors.<String>toList());
	    }
	public static void main(String[] args) throws Exception {
		
		List<SearchRequest> srchRequest = new ArrayList<SearchRequest>();
		SearchRequest s = new SearchRequest();
		s.setSearchword("india");
		srchRequest.add(s);
		s.setSearchword("london");
		srchRequest.add(s);
		FileSearch fs = new FileSearch();
		
		List<SearchResponse> rs =fs.getMatchWordFiles(srchRequest);
		fs.getNioMatchWordFiles(srchRequest);
		 
		List <String> list = new ArrayList <String> ();
	     list.add("one");
	     list.add("two");
	     list.add("three");
	    
	     list.add("four");
	    
	     list.add("five");
	   
	     
	     List<String> a = new CopyOnWriteArrayList<String> ();
	     a.add("one");
	     a.add("two");
	     a.add("three");
	    
	     a.add("four");
	    
	     a.add("five");
	 	String line = "one sdgs vdff";
	 	

		boolean isWordsMatch = false;
		int c = 0;
	     	ListIterator<String> itr=a.listIterator();  
	     	while(itr.hasNext()){  
	     		System.out.println(c++);
	     			String word = itr.next();
	     		if((line.contains(word) || line.startsWith(word) || line.endsWith(word))) {
	     			a.remove(word);
	     			  
	     			if(a.size()==0){
		            		 isWordsMatch = true;
		            		 break;
		            	 }	 
		            }	        		
	     	
	     		}  

	}
}
