package com.surina.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.surina.dao.PSkiAnaRepAll_DAO;
import com.surina.entities.ViewSkiAnaRep;
import com.surina.repository.ViewSkiAnaRep_Repository;

@Service
public class Service_Impl_PSkiAnaRepAll {
	
	@Autowired
	private ViewSkiAnaRep_Repository repositoryViewSkiAnaRep;
	
	
	
	public List<ViewSkiAnaRep> getpSkiAnaRepAll(){
		
		return repositoryViewSkiAnaRep.proceduresSkiAnaRepAll();
	}
	
	
	
	
	
	
	//2. creazione dell-  oggetto pSkiAnaRepAll_DAO

	
//	public List<ViewSkiAnaRep> getpSkiAnaRepAll = repositoryViewSkiAnaRep.proceduresSkiAnaRepAll();
//	
//		if(ge)
//		
//	public List<ViewSkiAnaRep> getpSkiAnaRepAll (){
//		pSkiAnaRepAll_DAO.getId();
//		pSkiAnaRepAll_DAO.getCodice();
//		pSkiAnaRepAll_DAO.getCategoria();
//		pSkiAnaRepAll_DAO.getSubcategoria();
//		pSkiAnaRepAll_DAO.getDescrizione();
//		pSkiAnaRepAll_DAO.getStato();
//		pSkiAnaRepAll_DAO.getApprovazione();
//		pSkiAnaRepAll_DAO.getUtenti();
//		pSkiAnaRepAll_DAO.getPercorsi();
//		
//		return null ; 
//				
////		= repositoryViewSkiAnaRep.proceduresSkiAnaRepAll();
//	} 
	
	
	

}
