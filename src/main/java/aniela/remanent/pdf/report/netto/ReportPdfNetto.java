package aniela.remanent.pdf.report.netto;

import aniela.remanent.pdf.report.abstraction.ReportPdf;
import aniela.remanent.type.ReportType;
import org.springframework.stereotype.Service;

@Service
public class ReportPdfNetto extends ReportPdf {

    public ReportPdfNetto() {
        super(ReportType.NETTO);
    }
}
