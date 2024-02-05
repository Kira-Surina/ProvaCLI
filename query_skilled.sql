DROP DATABASE IF EXISTS skilled;
CREATE DATABASE IF NOT EXISTS skilled;

USE skilled;

set global sql_mode = 'STRICT_ALL_TABLES';
SET global character_set_client = utf8;
SET global character_set_results = utf8;
SET global character_set_connection = utf8;
set sql_mode = 'STRICT_ALL_TABLES';
SET character_set_client = utf8;
SET character_set_results = utf8;
SET character_set_connection = utf8;

drop user if exists skilled_app@'localhost';

CREATE USER skilled_app@'localhost'
  IDENTIFIED BY 'skilled_app';
  
SHOW GRANTS FOR skilled_app@'localhost';
SHOW GRANTS FOR CURRENT_USER;


  
SET FOREIGN_KEY_CHECKS=1;

USE skilled;

set global sql_mode = 'STRICT_ALL_TABLES';
SET global character_set_client = utf8;
SET global character_set_results = utf8;
SET global character_set_connection = utf8;
set sql_mode = 'STRICT_ALL_TABLES';
SET character_set_client = utf8;
SET character_set_results = utf8;
SET character_set_connection = utf8;


DROP TABLE IF EXISTS tSysLog;
CREATE TABLE tSysLog (
  id int unsigned NOT NULL AUTO_INCREMENT,
  tms DATETIME NOT NULL DEFAULT current_timestamp() COMMENT  'Timestamp', 
  cod varchar(30) DEFAULT 'undefined' COMMENT 'Codice elaborazione/programma',
  sub varchar(30) COMMENT 'Codice sottoelaborazione elaborazione',
  tip  ENUM ('D','I','W','E') DEFAULT 'D', -- tipo informazione: D-debug I-Informazione W-Warning E-Error
  msg varchar(4000),
  xai VARCHAR(30) NOT NULL COMMENT 'Autore inserimento', 
  xti DATETIME NOT NULL DEFAULT current_timestamp() COMMENT  'Timestamp inserimento', 
  PRIMARY KEY pk_tSysLog (id)
) ENGINE=MyIsam  DEFAULT CHARSET=utf8mb4;

USE skilled;

set global sql_mode = 'STRICT_ALL_TABLES';
SET global character_set_client = utf8;
SET global character_set_results = utf8;
SET global character_set_connection = utf8;
set sql_mode = 'STRICT_ALL_TABLES';
SET character_set_client = utf8;
SET character_set_results = utf8;
SET character_set_connection = utf8;

DROP TABLE IF EXISTS tSkiAna;
DROP TABLE IF EXISTS tSkiCat;

CREATE TABLE tSkiCat (
  id mediumint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id categoria',
  cod varchar(8)  COMMENT 'Codice categoria',
  des varchar(50)  COMMENT 'Descrizione breve',
  desExt varchar(400) COMMENT 'Descrizione estesa',
  kyw varchar(400) COMMENT 'Keywords',
  tip  ENUM ('N','F') DEFAULT 'F', -- tipo informazione: N-Nodo, F-Foglia
  sup mediumint unsigned COMMENT 'id categoria superiore',
  sts smallint unsigned NOT NULL DEFAULT 0 COMMENT  'Stato di attivazione del record 0/1 (disattivo/attivo)', 
  apr smallint unsigned NOT NULL DEFAULT 0 COMMENT  'Stato di approvazione del record 0/1/2 (da approvare/cassato/approvato)', 
  apu varchar(30) COMMENT 'Autore scelta approvazione', 
  apt datetime COMMENT  'Timestamp scelta approvazione', 
  ord smallint unsigned NOT NULL DEFAULT 50 COMMENT  'Priorità di ordinamento', 
  xai varchar(30) NOT NULL COMMENT 'Autore inserimento', 
  xti datetime NOT NULL DEFAULT current_timestamp() COMMENT  'Timestamp inserimento', 
  xam varchar(30) NOT NULL COMMENT 'Autore ultima modifica', 
  xtm datetime NOT NULL DEFAULT current_timestamp() COMMENT  'Timestamp ultima modifica', 
  PRIMARY KEY pk_tSkiCat (id)
) ENGINE=InnoDb  DEFAULT CHARSET=utf8mb4;

ALTER TABLE tSkiCat ADD CONSTRAINT FOREIGN KEY fk1_tSkiCat (sup) REFERENCES tSkiCat (id);

CREATE TABLE tSkiAna (
  id mediumint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id skill',
  cod varchar(8)  COMMENT 'Codice skill',
  des varchar(50)  COMMENT 'Descrizione breve',
  desExt varchar(400) COMMENT 'Descrizione estesa',
  idCat mediumint unsigned COMMENT 'id categoria associata',
  kyw varchar(400) COMMENT 'Keywords',
  tip  ENUM ('V','P','A','C') NOT NULL DEFAULT 'P', -- tipo valutazione: V:voto numerico, P:Percentuale 0-100, A:Valore assoluto, C:Classificazione
  sts smallint unsigned NOT NULL DEFAULT 0 COMMENT  'Stato di attivazione del record 0/1 (disattivo/attivo)', 
  apr smallint unsigned NOT NULL DEFAULT 0 COMMENT  'Stato di approvazione del record 0/1/2 (da approvare/cassato/approvato)', 
  apu varchar(30) COMMENT 'Autore scelta approvazione', 
  apt datetime COMMENT  'Timestamp scelta approvazione', 
  ord smallint unsigned NOT NULL DEFAULT 50 COMMENT  'Priorità di ordinamento', 
  xai varchar(30) NOT NULL COMMENT 'Autore inserimento', 
  xti datetime NOT NULL DEFAULT current_timestamp() COMMENT  'Timestamp inserimento', 
  xam varchar(30) NOT NULL COMMENT 'Autore ultima modifica', 
  xtm datetime NOT NULL DEFAULT current_timestamp() COMMENT  'Timestamp ultima modifica', 
  PRIMARY KEY pk_tSkiAna (id)
) ENGINE=InnoDb  DEFAULT CHARSET=utf8mb4;

ALTER TABLE tSkiAna ADD CONSTRAINT FOREIGN KEY fk1_tSkiAna (idCat) REFERENCES tSkiCat (id);

USE skilled;

create or replace view vSkiAnaRep as
select
a.id id, concat (c2.cod, c.cod, a.cod) codice, c2.des categoria, c.des subcategoria, a.des descrizione,
a.sts stato, a.apr approvazione, 
0 utenti,
0 percorsi
from tskiana a join tskicat c on a.idcat = c.id join tskicat c2 on c.sup = c2.id
order by c2.ord, c2.des, c.ord, c.des, a.ord, a.des
;

create or replace view vSkiCatRep as
select -- c1.tip, c2.tip, c1.sup, c2.sup,
	   c1.id cat_id, c1.cod cat_codice, c1.des cat_descrizione, c1.sts cat_stato, c1.apr cat_approvazione,
       c2.id sub_id, c2.cod sub_codice, c2.des sub_descrizione, c2.sts sub_stato, c2.apr sub_approvazione,
case when c1.tip = 'F' then "Categoria assente" when c2.id is null then "Subcategoria assente" else "" end associazione,
sign((select count(1) from tSkiAna where idcat = coalesce(c2.id, c1.id))) skills
from tskicat c1 left join tskicat c2 on c2.sup = c1.id
 where c1.tip = 'N' or (c1.tip = 'F' and c1.sup is null)
order by c1.ord, c1.des, c2.ord, c2.des
;

USE skilled;

set global sql_mode = 'STRICT_ALL_TABLES';
SET global character_set_client = utf8;
SET global character_set_results = utf8;
SET global character_set_connection = utf8;
set sql_mode = 'STRICT_ALL_TABLES';
SET character_set_client = utf8;
SET character_set_results = utf8;
SET character_set_connection = utf8;

SET @usr_current = 'SYSTEM';
SET @tms_current = current_timestamp();

delete from tskiana WHERE ID > 0; 

delete from tskicat WHERE ID > 0 AND sup is not null; 
delete from tskicat WHERE ID > 0; 


INSERT INTO tskicat (id,cod,des,desExt,kyw,tip,sup,sts,apr,apu,apt,ord,xai,xti,xam,xtm) VALUES
(1,'EDU','Educazione','Titoli, master e certificazioni',null,'N',null,1,1,@usr_current,@tms_current,30,@usr_current,@tms_current,@usr_current,@tms_current),
(2,'ITX','IT','Information technology',null,'N',null,1,1,@usr_current,@tms_current,10,@usr_current,@tms_current,@usr_current,@tms_current),
(3,'LNG','Lingue','Lingue scritte e parlate',null,'N',null,1,1,@usr_current,@tms_current,20,@usr_current,@tms_current,@usr_current,@tms_current),
(4,'GST','Gestione','Capacità amministrative e gestionali',null,'N',null,1,1,@usr_current,@tms_current,20,@usr_current,@tms_current,@usr_current,@tms_current),
(5,'SFT','Soft skills','Information technology',null,'N',null,1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(6,'DEV','Linguaggi per back-end','Linguaggi di programmazione per il back-end',null,'F',2,1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(7,'SRV','Gestione sistemi e network','Gestione sistemi e network',null,'F',2,1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(8,'SEC','Gestione sicurezza','Gestione sicurezza',null,'F',2,1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(9,'WEB','Linguaggi front-end','Linguaggi di programmazione e mark-up per il front end',null,'F',2,1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(10,'DBX','Database','SQL, progettazione, amministrazione, procedurale su DB',null,'F',2,1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(11,'REC','Recruiting','Recruiting',null,'F',4,1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(12,'FOR','Formazione','Docenza e formazione',null,'F',4,1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(13,'UNI','Laurea universitaria','Laurea universitaria',null,'F',1,1,1,@usr_current,@tms_current,10,@usr_current,@tms_current,@usr_current,@tms_current),
(14,'DIP','Diploma o simile','Diploma o simile',null,'F',1,1,1,@usr_current,@tms_current,20,@usr_current,@tms_current,@usr_current,@tms_current),
(15,'XXX','Altro','Altro titolo',null,'F',1,1,1,@usr_current,@tms_current,90,@usr_current,@tms_current,@usr_current,@tms_current),
(16,'MAD','Lingua madre','Lingua madre',null,'F',3,1,1,@usr_current,@tms_current,99,@usr_current,@tms_current,@usr_current,@tms_current),
(17,'STU','Lingua studiata','Lingua studiata',null,'F',3,1,1,@usr_current,@tms_current,99,@usr_current,@tms_current,@usr_current,@tms_current),
(18,'SIN','Personali','Soft skill personali',null,'F',5,1,1,@usr_current,@tms_current,99,@usr_current,@tms_current,@usr_current,@tms_current),
(19,'COL','Di gruppo','Soft skill di gruppo',null,'F',5,1,1,@usr_current,@tms_current,99,@usr_current,@tms_current,@usr_current,@tms_current),
(20,'XXX','Altro','Altra capacità non elencata',null,'F',2,1,1,@usr_current,@tms_current,99,@usr_current,@tms_current,@usr_current,@tms_current),
(21,'XXX','Altro','Altra capacità non elencata',null,'F',5,1,1,@usr_current,@tms_current,99,@usr_current,@tms_current,@usr_current,@tms_current);

INSERT INTO tskiana (id,cod,des,desExt,idCat,kyw,tip,sts,apr,apu,apt,ord,xai,xti,xam,xtm)VALUES
(1,'LMS','Laurea magistrale STEM','Laurea magistrale STEM',13,null,'V',1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(2,'LMN','Laurea magistrale non STEM','Laurea magistrale non STEM',13,null,'V',1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(3,'LSS','Laurea specialistica STEM','Laurea specialistica STEM',13,null,'V',1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(4,'LSNS','Laurea specialistica non STEM','Laurea specialistica STEM',13,null,'V',1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(5,'MSU','Master universitario','Master universitario',13,null,'V',1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(6,'MSA','Master non universitario','Master non univeersitario',15,null,'V',1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(7,'DIP','Diploma secondaria superiore ','Diploma secondaria superiore',14,null,'V',1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(8,'FRM','Diplona ente formazione','Diploma ente formazione',14,null,'V',1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(9,'CRT','Certificazione specialistica','Certficazione specialistica con esame c/o ente certificatore autorizzato',15,null,'V',1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(10,'CRN','Certificazione non specialistica','Certficazione non specialistica con attestato c/o ente certificatore autorizzato',15,null,'V',1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(11,'CRA','Certificazione auto-formazione','Certficazione con auto-formazione (udemy e simili)',15,null,'V',1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(12,'XXX','Altro titolo','Altro titolo',15,null,'V',1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current),
(13,'JAV','Java','Java',6,null,'P',1,1,@usr_current,@tms_current,50,@usr_current,@tms_current,@usr_current,@tms_current)
;

SELECT * FROM tskicat;
SELECT * FROM tskiana;
SELECT * FROM tsyslog;
SELECT * FROM vskianarep;
SELECT * FROM vskicatrep;

-- CASI ERRATI

INSERT INTO tskicat (id,cod,des,desExt,kyw,tip,sup,sts,apr,apu,apt,ord,xai,xti,xam,xtm) VALUES
(100,'???','???','???',null,'N',null,1,1,@usr_current,@tms_current,30,@usr_current,@tms_current,@usr_current,@tms_current),
(101,'###','###','###',null,'F',null,1,1,@usr_current,@tms_current,30,@usr_current,@tms_current,@usr_current,@tms_current);

drop function if exists fN2N;
drop function if exists fN2Z;
drop function if exists fCN;

DELIMITER $$
CREATE FUNCTION fN2N (tx VARCHAR(16383)) -- Converte stringhe nulle in NULL
RETURNS VARCHAR(16383)
	DETERMINISTIC
BEGIN
	if tx = '' then
		set tx = null;
    end if;
	RETURN tx;
END $$ -- fN2N

CREATE FUNCTION fCN (tx varchar(16383))-- Check nullo > boolean
RETURNS boolean 
	DETERMINISTIC
BEGIN
	if fN2N(tx) is null then
		return true;
	else
		return false;
    end if;
END $$ -- fCN

CREATE FUNCTION fN2Z (d varchar(32))-- converte NULL in 0
RETURNS varchar(32) 
	DETERMINISTIC
BEGIN
	if fCN(d) then
		set d = 0;
    end if;

	RETURN d;
END $$ -- fN2Z

DROP PROCEDURE IF EXISTS pSkiAnaRepAll;
DROP PROCEDURE IF EXISTS pSkiAnaRep;
DROP PROCEDURE IF EXISTS pSkiCatRepAll;
DROP PROCEDURE IF EXISTS pSkiCatRep;

DELIMITER $$
CREATE PROCEDURE pSkiAnaRep(
IN pi_Id varchar(32), pi_sts varchar(32))
	BEGIN
		select * 
		from  vSkiAnaRep
		where (fN2N(pi_Id) is null or id = pi_Id)
		and (fN2N(pi_sts) is null or stato = pi_sts)
		;
	END $$

CREATE PROCEDURE pSkiAnaRepAll()
	BEGIN
		call pSkiAnaRep (null,null);
	END $$


CREATE PROCEDURE pSkiCatRep(
IN pi_catId varchar(32), pi_catSts varchar(32), 
IN pi_subId varchar(32), pi_subSts varchar(32))
	BEGIN
		select * 
		from  vSkiCatRep
		where (fN2N(pi_catId) is null or cat_id = pi_catId)
		and (fN2N(pi_catSts) is null or cat_stato = pi_catSts)
        and (fN2N(pi_subId) is null or sub_id = pi_subId)
		and (fN2N(pi_subSts) is null or sub_stato = pi_subSts)
		;
	END $$

CREATE PROCEDURE pSkiCatRepAll()
	BEGIN
		call pSkiCatRep(null,null,null,null);
	END $$

DELIMITER ;

call pSkiAnaRep (2,"");
call pSkiAnaRep ('',0);
call pSkiAnaRep ('','');

call pSkiCatRep (2,1,10,1);
call pSkiCatRep ('',1,'',1);
call pSkiCatRep (2,'',10,'');
call pSkiCatRep (2,'','','');
call pSkiCatRep ('','','10','');
call pSkiCatRep ('','','','');

call pSkiAnaRepAll;
call pSkiCatRepAll;
select * from  vSkiAnaRep ;
select * from  vSkiCatRep;

