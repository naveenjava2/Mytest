package com.service.search.file;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileHandler extends SimpleFileVisitor<Path> {
	
	private  List<String> fileList = new ArrayList<>();
	//private  List<Path> pathList = new ArrayList<>();
	
	private final PathMatcher pathMatcher;
	public FileHandler(String globPattern) {
		pathMatcher = FileSystems.getDefault().getPathMatcher(
				"glob:" + globPattern);
	}

	@Override
	public FileVisitResult visitFile(Path filePath,
			BasicFileAttributes basicFileAttrib) {
		if (pathMatcher.matches(filePath.getFileName())) {
			fileList.add(filePath.toString());
			///pathList.add(filePath);
		}
		return FileVisitResult.CONTINUE;
	}

	
public List<String> getFileList(String basePath, FileHandler searchFileVisitor) throws IOException{
	Path rootPath = Paths.get(basePath);
	Files.walkFileTree(rootPath, searchFileVisitor);
    return fileList;
}
/*public List<Path> getpathList(String basePath, FileHandler searchFileVisitor) throws IOException{
	Path rootPath = Paths.get(basePath);
	Files.walkFileTree(rootPath, searchFileVisitor);
	return pathList;
}*/
	public static void main(String[] args) throws IOException {
		String globPattern = "*.txt";
		FileHandler f = new FileHandler(globPattern);
		f.getFileList("/home/scopus-1/workspace/spring-boot-example/.",f);
		System.out.println("Match Count: " + f.getFileList("/home/scopus-1/workspace/spring-boot-example/.",f));
	}
	}
