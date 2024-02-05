package com.surina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.surina.entities.ViewSkiAnaRep;

@Repository
public interface ViewSkiAnaRep_Repository extends JpaRepository<ViewSkiAnaRep, Integer>{
	
	@Query(value="select\r\n"
			+ "a.id id, concat (c2.cod, c.cod, a.cod) codice, c2.des categoria, c.des subcategoria, a.des descrizione,\r\n"
			+ "a.sts stato, a.apr approvazione, \r\n"
			+ "0 utenti,\r\n"
			+ "0 percorsi\r\n"
			+ "from tskiana a join tskicat c on a.idcat = c.id join tskicat c2 on c.sup = c2.id\r\n"
			+ "order by c2.ord, c2.des, c.ord, c.des, a.ord, a.des", nativeQuery = true)
	public List<ViewSkiAnaRep> findAll();

//	@Procedure(name = "pSkiAnaRepAll")
//		public Integer proceduresSkiAnaRepAll();
	
	@Query(value="call pSkiAnaRepAll", nativeQuery = true)
	List<ViewSkiAnaRep> proceduresSkiAnaRepAll();
//	giusto utilizzare view o meglio creare una classe entity pSkiAnaRepAll o fare con DAO??
	
	
	@Query(value="call pSkiAnaRep (\"\",\"\");", nativeQuery = true)
	List<ViewSkiAnaRep> proceduresSkiAnaRer(Integer id, Double stato);

	

}
