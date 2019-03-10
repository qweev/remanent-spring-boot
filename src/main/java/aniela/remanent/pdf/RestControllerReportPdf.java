package aniela.remanent.pdf;

import aniela.remanent.pdf.report.brutto.ReportPdfBrutto;
import aniela.remanent.pdf.report.netto.ReportPdfNetto;
import aniela.remanent.raport.ReportFileResolver;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerReportPdf {

    final static Logger LOG = Logger.getLogger(RestControllerReportPdf.class);


    @Autowired
    ReportPdfBrutto reportPdfBrutto;

    @Autowired
    ReportPdfNetto reportPdfNetto;

    @GetMapping("/remanent/rest/raport/pdf/brutto/{sciezka}")
    public ResponseEntity utworzPlikRemanentBrutto(@PathVariable("sciezka") String sciezka) {
        String fullSciezka = ReportFileResolver.resolveFilePathForPdf(sciezka);
        return ResponseEntity.status(HttpStatus.GONE).body("Brutto still not implemented");
    }

    @GetMapping("/remanent/rest/raport/pdf/netto/{sciezka}")
    public ResponseEntity utworzPlikRemanentNetto(@PathVariable("sciezka") String sciezka) {
        String fullSciezka = ReportFileResolver.resolveFilePathForPdf(sciezka);
        try {
            reportPdfNetto.generateReport(reportPdfNetto.getPostions(), fullSciezka);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.GONE).body(String.format("Report generation has thrown error %s", e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.GONE).body("Netto still not implemented");
    }
}
