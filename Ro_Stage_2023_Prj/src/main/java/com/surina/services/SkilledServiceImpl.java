package com.surina.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surina.entities.TabskiAna;
import com.surina.entities.ViewSkiAnaRep;
import com.surina.repository.TabskiAna_Repository;
import com.surina.repository.ViewSkiAnaRep_Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class SkilledServiceImpl implements SkilledService {

	@Autowired
	private ViewSkiAnaRep_Repository repositoryViewSkiAnaRep;
	
	@Autowired
	private TabskiAna_Repository repositoriTabskiAna;
	
	@Autowired
	@PersistenceContext
	private EntityManager em;
	

	@Override
	public List<ViewSkiAnaRep> getView() {
		return repositoryViewSkiAnaRep.findAll();
	}

	@Override
	public List<TabskiAna> getTabl() {
		// TODO Auto-generated method stub
		return repositoriTabskiAna.findAll();
	}

	//viene usato per solo per avere JSON o come? 
//	public List getPSkiAnaRepAll() {
//		return em.createNamedStoredProcedureQuery("pSkiAnaRepAll").getResultList();
//	}

	public List<ViewSkiAnaRep> getProceduresSkiAnaRepAll() {
		return repositoryViewSkiAnaRep.proceduresSkiAnaRepAll();
	}
	
	//return pricedure pSkiAnaRep con i parametri
//	public List<ViewSkiAnaRep> getProcedureSkiAnaRepParam(Integer id, Double stato){
//		return repositoryViewSkiAnaRep.proceduresSkiAnaRer(id, stato);
//	}
}
