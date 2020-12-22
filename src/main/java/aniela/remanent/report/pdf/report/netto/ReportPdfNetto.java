package aniela.remanent.report.pdf.report.netto;

import aniela.remanent.report.pdf.report.abstraction.ReportPdf;
import aniela.remanent.report.type.ReportType;
import org.springframework.stereotype.Service;

@Service
public class ReportPdfNetto extends ReportPdf {

    public ReportPdfNetto() {
        super(ReportType.NETTO);
    }
}
