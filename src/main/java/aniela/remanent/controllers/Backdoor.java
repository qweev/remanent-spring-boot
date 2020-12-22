package aniela.remanent.controllers;

import aniela.remanent.position.abstraction.Position;
import aniela.remanent.pozycje.BazaRepository;
import aniela.remanent.report.MessageFactory;
import aniela.remanent.report.ReportFileResolver;
import aniela.remanent.report.excel.brutto.RaportExcelBrutto;
import aniela.remanent.report.excel.netto.RaportExcelNetto;
import aniela.remanent.report.pdf.report.brutto.ReportPdfBrutto;
import aniela.remanent.report.pdf.report.netto.ReportPdfNetto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Backdoor {

    private final static String STATUS_OK = "OK";
    private final static Logger LOGGER = Logger.getLogger(Backdoor.class);

    @Autowired
    BazaRepository bazaAccess;

    @Autowired
    RaportExcelNetto raportExcelNetto;

    @Autowired
    RaportExcelBrutto raportExcelBrutto;

    @Autowired
    private ReportPdfBrutto reportPdfBrutto;

    @Autowired
    private ReportPdfNetto reportPdfNetto;

    @GetMapping("/remanent/rest/raport/backdoor/pdf/brutto/{nazwaPliku}")
    public ResponseEntity createPdfGross(@PathVariable("nazwaPliku") String nazwaPliku) {
        String pathAfterResolve = ReportFileResolver.resolveFilePathForPdf(nazwaPliku);
        try {
            List<Position> positions = bazaAccess.przygotujPozycjeDoRaportuBrutto();
            reportPdfBrutto.generateReport(positions, pathAfterResolve, positions.size());
        } catch (Exception e) {
            LOGGER.warn("Raport PDF brutto NIE zrobiony", e);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(String.format("Report generation has thrown error %s", e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(pathAfterResolve);
    }

    @GetMapping("/remanent/rest/raport/backdoor/pdf/netto/{nazwaPliku}")
    public ResponseEntity createPdfNet(@PathVariable("nazwaPliku") String nazwaPliku) {
        String pathAfterResolve = ReportFileResolver.resolveFilePathForPdf(nazwaPliku);
        try {
            List<Position> positions = bazaAccess.przygotujPozycjeDoRaportuNetto();
            reportPdfNetto.generateReport(positions, pathAfterResolve, positions.size());
        } catch (Exception e) {
            LOGGER.warn("Raport PDF netto NIE zrobiony", e);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(String.format("Report generation has thrown error %s", e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(pathAfterResolve);
    }

    @GetMapping("/remanent/rest/raport/backdoor/excel/brutto/{nazwaPliku}")
    public ResponseEntity createExcelGross(@PathVariable("nazwaPliku") String nazwaPliku) {
        LOGGER.info("nazwaPliku : " + nazwaPliku);
        String fullSciezka = ReportFileResolver.resolveFilePathForExcel(nazwaPliku);
        String status = raportExcelBrutto.generujRaport(fullSciezka);
        if (status.equalsIgnoreCase(STATUS_OK)) {
            LOGGER.info("Raport Excel brutto zrobiony");
            return ResponseEntity.status(HttpStatus.OK).body(MessageFactory.generateMessageOKStatus(fullSciezka));
        } else {
            String statusDoWyslania = "plik NIE zapisany";
            LOGGER.info("Raport Excel brutto NIE zrobiony");
            return ResponseEntity.status(HttpStatus.GONE).body(statusDoWyslania);
        }
    }


    @GetMapping("/remanent/rest/raport/backdoor/excel/netto/{nazwaPliku}")
    public ResponseEntity createExcelNet(@PathVariable("sciezka") String nazwaPliku) {
        LOGGER.info("pobrana sciezka : " + nazwaPliku);
        String fullSciezka = ReportFileResolver.resolveFilePathForExcel(nazwaPliku);
        String status = raportExcelNetto.generujRaport(fullSciezka);
        if (status.equalsIgnoreCase(STATUS_OK)) {
            LOGGER.info("Raport Excel netto zrobiony");
            return ResponseEntity.status(HttpStatus.OK).body(MessageFactory.generateMessageOKStatus(fullSciezka));
        } else {
            String statusDoWyslania = "plik NIE zapisany";
            LOGGER.info("Raport Excel netto NIE zrobiony");
            return ResponseEntity.status(HttpStatus.GONE).body(statusDoWyslania);
        }
    }


}
