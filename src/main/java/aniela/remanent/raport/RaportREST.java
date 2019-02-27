package aniela.remanent.raport;


import aniela.remanent.pozycje.BazaDAO;
import aniela.remanent.pozycje.bazaDanych.PozycjaBazy;
import aniela.remanent.raport.raportBrutto.RaportBrutto;
import aniela.remanent.raport.raportDoDruku.RaportNetto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RaportREST {
	final static Logger logger = Logger.getLogger(RaportREST.class);

	@Autowired
	BazaDAO bazaRaport;

	@Autowired
	RaportNetto raportNetto;

	//TODO
	//czym sie rozni raportNetto brutto od bazy DAO?

	@Autowired
	RaportBrutto raportBrutto;

	@GetMapping("/remanent/rest/raportNetto/excel/brutto/{sciezka}")
	public ResponseEntity utworzPlikRemanentBrutto(@PathVariable("sciezka") String sciezka) {
		logger.info("pobrana sciezka : " + sciezka);

		String fullSciezka = ReportFileResolver.resolveFilePathForExcel(sciezka);

		String status = raportBrutto.generujRaport(fullSciezka);
		if (status.equalsIgnoreCase("OK")){
			String statusDoWyslania = "plik zapisany w lokacji C:\\"+sciezka+".xlsx";
			logger.info("raportNetto zrobiony");
			return ResponseEntity.status(HttpStatus.OK).body(statusDoWyslania);
		}
		else{
			String statusDoWyslania = "plik NIE zapisany";
			logger.info("raportNetto NIE zrobiony");
			return ResponseEntity.status(HttpStatus.GONE).body(statusDoWyslania);
		}

	}


	@GetMapping(path = "/remanent/rest/raportNetto/excel/{sciezka}")
	public ResponseEntity utworzPlikRemanent(@PathVariable("sciezka") String sciezka) {
		logger.info("pobrana sciezka : " + sciezka);

		String fullSciezka = ReportFileResolver.resolveFilePathForExcel(sciezka);

		String status = raportNetto.generujRaport(fullSciezka);

		if (status.equalsIgnoreCase("OK")){
			String statusDoWyslania = "plik zapisany w lokacji C:\\"+sciezka+".xlsx";
			logger.info("raportNetto zrobiony");
			return ResponseEntity.status(HttpStatus.OK).body(statusDoWyslania);
		}else
		{
			String statusDoWyslania = "plik NIE zapisany";
			logger.info("raportNetto NIE zrobiony");
			return ResponseEntity.status(HttpStatus.GONE).body(statusDoWyslania);
		}

	}


	@GetMapping("/remanent/rest/raportNetto/statystyki")
	public ResponseEntity generujStatystyki() {

		String statystyka = bazaRaport.generujStatystyki();

		if (statystyka == null){
			return ResponseEntity.status(HttpStatus.GONE).body(statystyka);
		}
		else{
			return ResponseEntity.status(HttpStatus.OK).body(statystyka);
		}
	}


	@GetMapping(path = "/remanent/rest/raportNetto/zeroweCeny"
			,produces= MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity zeroweCeny() {

		List <PozycjaBazy> pozycje = bazaRaport.pobierzZeroweCeny();
		logger.info("raportNetto zerowe ceny ilosc  : " + pozycje.size());
		if ( (pozycje.size() == 0) || (pozycje == null) ){
			return ResponseEntity.status(HttpStatus.GONE).body("brak pozycji");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(pozycje);
		}

	}

	@GetMapping("/remanent/rest/raportNetto/zaloguj/{user}/{pass}")
	public ResponseEntity sprawdzLoginHaslo(@PathVariable("user") String user, @PathVariable("pass") String pass) {
		logger.info("pobrany login : " + user);
		logger.info("pobrany pass : " + pass);
		//TODO hash na usera i haslo w tabeli
		if ( user.equals("admin") && pass.equals("admin") ) { //hardcode na login i haslo !!!!
			logger.info("rest status logowania OK ");
			return ResponseEntity.status(HttpStatus.OK).body("OK");
		}
		else {
			logger.info("rest status logowania NOK ");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("NOK");
		}
	}

}
