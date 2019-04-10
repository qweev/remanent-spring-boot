package aniela.remanent.pdf.report.brutto;


import aniela.remanent.pdf.report.abstraction.ReportPdf;
import aniela.remanent.type.ReportType;
import org.springframework.stereotype.Service;

@Service
public class ReportPdfBrutto extends ReportPdf {

    public ReportPdfBrutto() {
        super(ReportType.BRUTTO);
    }
}
