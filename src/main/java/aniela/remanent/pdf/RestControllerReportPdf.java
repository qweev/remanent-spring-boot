package aniela.remanent.pdf;

import aniela.remanent.pdf.report.brutto.ReportPdfBrutto;
import aniela.remanent.pdf.report.netto.ReportPdfNetto;
import aniela.remanent.position.abstraction.Position;
import aniela.remanent.pozycje.BazaDAO;
import aniela.remanent.raport.ReportFileResolver;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestControllerReportPdf {

    @Autowired
    private BazaDAO bazaDAO;

    @Autowired
    private ReportPdfBrutto reportPdfBrutto;

    @Autowired
    private ReportPdfNetto reportPdfNetto;

    @GetMapping("/remanent/rest/raport/pdf/brutto/{nazwaPliku}")
    public ResponseEntity utworzPlikRemanentBrutto(@PathVariable("sciezka") String nazwaPliku) {
        String pathAfterResolve = ReportFileResolver.resolveFilePathForPdf(nazwaPliku);
        try {
            List<Position> positions = bazaDAO.przygotujPozycjeDoRaportuBrutto();
            reportPdfBrutto.generateReport(positions, pathAfterResolve, positions.size());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(String.format("Report generation has thrown error %s", e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    @GetMapping("/remanent/rest/raport/pdf/netto/{nazwaPliku}")
    public ResponseEntity utworzPlikRemanentNetto(@PathVariable("nazwaPliku") String nazwaPliku) {
        String pathAfterResolve = ReportFileResolver.resolveFilePathForPdf(nazwaPliku);
        try {
            List<Position> positions = bazaDAO.przygotujPozycjeDoRaportuNetto();
            reportPdfNetto.generateReport(positions, pathAfterResolve, positions.size());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(String.format("Report generation has thrown error %s", e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }


}
