package aniela.remanent.raport;


import aniela.remanent.pdf.report.brutto.ReportPdfBrutto;
import aniela.remanent.pdf.report.netto.ReportPdfNetto;
import aniela.remanent.position.abstraction.Position;
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

    private final static String STATUS_OK = "OK";

    @Autowired
    BazaDAO bazaRaport;

    @Autowired
    RaportNetto raportNetto;

    @Autowired
    RaportBrutto raportBrutto;

    @Autowired
    ReportPdfNetto reportPdfNetto;

    @Autowired
    ReportPdfBrutto reportPdfBrutto;


    @GetMapping("/remanent/rest/raport/excel/brutto/{sciezka}")
    public ResponseEntity utworzPlikRemanentBrutto(@PathVariable("sciezka") String sciezka) {
        logger.info("pobrana sciezka : " + sciezka);
        //String fullSciezka = ReportFileResolver.resolveFilePathForExcel(sciezka);
        //stary excel brutto
        //String status = raportBrutto.generujRaport(fullSciezka);
        String fullSciezka = ReportFileResolver.resolveFilePathForPdf(sciezka);
        List<Position> positions = bazaRaport.przygotujPozycjeDoRaportuBrutto();
        String status = reportPdfBrutto.generateReport(positions, fullSciezka, positions.size());

        if (status.equalsIgnoreCase(STATUS_OK)) {
            logger.info("raportNetto zrobiony");
            return ResponseEntity.status(HttpStatus.OK).body(generateMessageOKStatus(fullSciezka));
        } else {
            String statusDoWyslania = "plik NIE zapisany";
            logger.info("raportNetto NIE zrobiony");
            return ResponseEntity.status(HttpStatus.GONE).body(statusDoWyslania);
        }

    }


    @GetMapping(path = "/remanent/rest/raport/excel/{sciezka}")
    public ResponseEntity utworzPlikRemanent(@PathVariable("sciezka") String sciezka) {
        logger.info("pobrana sciezka : " + sciezka);
        //String fullSciezka = ReportFileResolver.resolveFilePathForExcel(sciezka);
        //stary excel netto
        //String status = raportNetto.generujRaport(fullSciezka);
        String fullSciezka = ReportFileResolver.resolveFilePathForPdf(sciezka);
        List<Position> positions = bazaRaport.przygotujPozycjeDoRaportuNetto();
        String status = reportPdfNetto.generateReport(positions, fullSciezka, positions.size());
        if (status.equalsIgnoreCase(STATUS_OK)) {
            logger.info("raportNetto zrobiony");
            return ResponseEntity.status(HttpStatus.OK).body(generateMessageOKStatus(fullSciezka));
        } else {
            String statusDoWyslania = "plik NIE zapisany";
            logger.info("raportNetto NIE zrobiony");
            return ResponseEntity.status(HttpStatus.GONE).body(statusDoWyslania);
        }
    }

    @GetMapping("/remanent/rest/raport/statystyki")
    public ResponseEntity generujStatystyki() {
        String statystyka = bazaRaport.generujStatystyki();
        if (statystyka == null) {
            return ResponseEntity.status(HttpStatus.GONE).body(statystyka);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(statystyka);
        }
    }


    @GetMapping(path = "/remanent/rest/raport/zeroweCeny"
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity zeroweCeny() {

        List<PozycjaBazy> pozycje = bazaRaport.pobierzZeroweCeny();
        logger.info("raportNetto zerowe ceny ilosc  : " + pozycje.size());
        if ((pozycje.size() == 0) || (pozycje == null)) {
            return ResponseEntity.status(HttpStatus.GONE).body("brak pozycji");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(pozycje);
        }

    }


    @GetMapping("/remanent/rest/raport/zaloguj/{user}/{pass}")
    public ResponseEntity sprawdzLoginHaslo(@PathVariable("user") String user, @PathVariable("pass") String pass) {
        logger.info("pobrany login : " + user);
        logger.info("pobrany pass : " + pass);
        //TODO hash na usera i haslo w tabeli
        if (user.equals("admin") && pass.equals("admin")) { //hardcode na login i haslo !!!!
            logger.info("rest status logowania OK ");
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        } else {
            logger.info("rest status logowania NOK ");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("NOK");
        }
    }


    private String generateMessageOKStatus(String path) {
        return new StringBuilder().append("plik zapisany w lokacji: ").append(path).toString();
    }


}
