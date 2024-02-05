package com.surina.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import org.springframework.web.bind.annotation.RestController;


import com.surina.entities.TabskiAna;
import com.surina.entities.ViewSkiAnaRep;

import com.surina.services.SkilledServiceImpl;



@Controller
@RequestMapping("/api")
//@RestController
public class SkilledController {

	@Autowired
	private SkilledServiceImpl serviceImp;

	
	@GetMapping("/vskianarep")
	public String control(Model m) {
		m.addAttribute("view", serviceImp.getView());
		return"vSkiAnaRep";
	}
	
	@GetMapping("/control")
public List<ViewSkiAnaRep> getAll(){
		return serviceImp.getView();
		
		}

	@GetMapping("/tab")
public List<TabskiAna> allTab(){
		return serviceImp.getTabl();
	}


//	@GetMapping("/pSkiAnaRepAll")
//	public List getAllProcedure() {
//		return serviceImp.getPSkiAnaRepAll();
//	}
//	
//	@GetMapping("/pSkiAnaRepAll")
//	public List <ViewSkiAnaRep> getProceduresSkiAnaRepAll() {
//		return serviceImp.getProceduresSkiAnaRepAll();
//	}
	


	
//	@GetMapping("/pSkiAnaRepAll")
//	public String getProceduresSkiAnaRepAll(Model m) {
//		
//		PSkiAnaRepAll_DAO pSkiAnaRepAll_DAO = servicePSkiAnaRepAll.getpSkiAnaRepAll();
//		m.addAttribute("procedure", pSkiAnaRepAll_DAO);
//		
//		System.out.println("Hello from /pSkiAnaRepAll " + pSkiAnaRepAll_DAO);
//		return "pSkiAnaRepAll";
//		
//	} 
	
//	@GetMapping("/pSkiAnaRepParam")
//	public String getProcedureSkiAnaRepParam(
//			@RequestParam(name="id") Integer id, 
//			@RequestParam(name="stato") Double stato, 
//			Model m
//			) {
//		m.addAttribute("procedureParametri", serviceImp.getProceduresSkiAnaRepAll());
//		System.out.println(id + "_____" + stato);
//		return "pSkiAnaRepParam";
//	}

	
}
