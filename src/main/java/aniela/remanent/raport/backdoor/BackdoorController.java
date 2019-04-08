package aniela.remanent.raport.backdoor;

import aniela.remanent.pdf.report.brutto.ReportPdfBrutto;
import aniela.remanent.pdf.report.netto.ReportPdfNetto;
import aniela.remanent.position.abstraction.Position;
import aniela.remanent.pozycje.BazaDAO;
import aniela.remanent.raport.MessageFactory;
import aniela.remanent.raport.ReportFileResolver;
import aniela.remanent.raport.raportBrutto.RaportBrutto;
import aniela.remanent.raport.raportDoDruku.RaportNetto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BackdoorController {

    private final static String STATUS_OK = "OK";
    private final static Logger LOGGER = Logger.getLogger(BackdoorController.class);

    @Autowired
    BazaDAO bazaDao;

    @Autowired
    RaportNetto raportNetto;

    @Autowired
    RaportBrutto raportBrutto;

    @Autowired
    private ReportPdfBrutto reportPdfBrutto;

    @Autowired
    private ReportPdfNetto reportPdfNetto;

    @GetMapping("/remanent/rest/backdoor/raport/pdf/brutto/{nazwaPliku}")
    public ResponseEntity createPdfGross(@PathVariable("nazwaPliku") String nazwaPliku) {
        String pathAfterResolve = ReportFileResolver.resolveFilePathForPdf(nazwaPliku);
        try {
            List<Position> positions = bazaDao.przygotujPozycjeDoRaportuBrutto();
            reportPdfBrutto.generateReport(positions, pathAfterResolve, positions.size());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(String.format("Report generation has thrown error %s", e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @GetMapping("/remanent/rest/raport/backdoor/pdf/netto/{nazwaPliku}")
    public ResponseEntity createPdfNet(@PathVariable("nazwaPliku") String nazwaPliku) {
        String pathAfterResolve = ReportFileResolver.resolveFilePathForPdf(nazwaPliku);
        try {
            List<Position> positions = bazaDao.przygotujPozycjeDoRaportuNetto();
            reportPdfNetto.generateReport(positions, pathAfterResolve, positions.size());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(String.format("Report generation has thrown error %s", e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @GetMapping("/remanent/rest/raport/backdoor/excel/brutto/{sciezka}")
    public ResponseEntity createExcelGross(@PathVariable("sciezka") String sciezka) {
        LOGGER.info("pobrana sciezka : " + sciezka);
        String fullSciezka = ReportFileResolver.resolveFilePathForExcel(sciezka);
        String status = raportBrutto.generujRaport(fullSciezka);
        if (status.equalsIgnoreCase(STATUS_OK)) {
            LOGGER.info("raportNetto zrobiony");
            return ResponseEntity.status(HttpStatus.OK).body(MessageFactory.generateMessageOKStatus(fullSciezka));
        } else {
            String statusDoWyslania = "plik NIE zapisany";
            LOGGER.info("raportNetto NIE zrobiony");
            return ResponseEntity.status(HttpStatus.GONE).body(statusDoWyslania);
        }
    }


    @GetMapping(path = "/remanent/rest/raport/backdoor/excel/netto{sciezka}")
    public ResponseEntity createExcelNet(@PathVariable("sciezka") String sciezka) {
        LOGGER.info("pobrana sciezka : " + sciezka);
        String fullSciezka = ReportFileResolver.resolveFilePathForExcel(sciezka);
        String status = raportNetto.generujRaport(fullSciezka);
        if (status.equalsIgnoreCase(STATUS_OK)) {
            LOGGER.info("raportNetto zrobiony");
            return ResponseEntity.status(HttpStatus.OK).body(MessageFactory.generateMessageOKStatus(fullSciezka));
        } else {
            String statusDoWyslania = "plik NIE zapisany";
            LOGGER.info("raportNetto NIE zrobiony");
            return ResponseEntity.status(HttpStatus.GONE).body(statusDoWyslania);
        }
    }


}
