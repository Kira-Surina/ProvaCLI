package com.surina.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TextFileExporter_Imp implements FileExporter {

	
	private static final String EXPORT_DIRECTORY = "C:\\\\Users\\\\Utente\\\\eclipse-workspace\\\\PrimoGiorno\\\\src\\\\prova";
	//Регистратор
	private Logger logger = LoggerFactory.getLogger(TextFileExporter_Imp.class);
	
	@Override
	public Path export(String fileContent, String fileName) {
	
		Path filePath = Paths.get(EXPORT_DIRECTORY, fileName);
		
		try {
			
			Path exportedFilePath = Files.write
					(
							filePath, 
							fileContent.getBytes(), 
							StandardOpenOption.CREATE);
			return exportedFilePath;
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
