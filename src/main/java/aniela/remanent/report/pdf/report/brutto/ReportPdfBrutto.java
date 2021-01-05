package aniela.remanent.report.pdf.report.brutto;


import aniela.remanent.report.pdf.report.abstraction.ReportPdf;
import aniela.remanent.report.type.ReportType;
import org.springframework.stereotype.Service;

@Service
public class ReportPdfBrutto extends ReportPdf {

    public ReportPdfBrutto() {
        super(ReportType.BRUTTO);
    }
}
