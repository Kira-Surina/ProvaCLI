package com.surina.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.http.HttpHeaders;
import java.nio.file.Path;

import org.jsoup.Jsoup;

import com.surina.services.FileExporter;
import com.surina.services.Service_Impl_PSkiAnaRepAll;

@Controller
@RequestMapping("")
public class Controller_pSkiAnaRepAll {
	
	@Autowired
	private Service_Impl_PSkiAnaRepAll servicePSkiAnaRepAll;
	
	@Autowired
	private FileExporter fileExporter;
	
	@GetMapping("")
	public String firstPage(Model m) {
	 m.addAttribute("title", "Benvenuto al Sito skills dei dipendenti, dei collaboratori e dei candidati");
		
		return "firstPage";
	}
	
	@GetMapping("/procedure/pSkiAnaRepAll1")
	public String getProceduresSkiAnaRepAll(Model m) {
		m.addAttribute("procedure", servicePSkiAnaRepAll.getpSkiAnaRepAll());
		return "pSkiAnaRepAll1";
	} 

	
//	@RequestMapping("/download1")
//	public ResponseEntity<InputStreamResource> downloadTextFileExample1() throws FileNotFoundException{
//		
//		String fileName = "example1.txt";
//		String fileContent = "Simple Solution \nDownload Example 1";
//		
//		//Create text file
//		Path exportedPath = fileExporter.export(fileContent, fileName);
//		
//		
//		//Download file with InputStreamResource
//		File exportedFile = exportedPath.toFile();
//		FileInputStream fileInputStream = new FileInputStream(exportedFile);
//		InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);
//		
//		return ResponseEntity.ok()
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName);
//	
//	}
	
	
	
}
