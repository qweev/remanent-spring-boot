package aniela.remanent.pdf.report.netto;

import aniela.remanent.pdf.report.abstraction.ReportPdf;
import aniela.remanent.position.abstraction.PositionInterface;
import aniela.remanent.type.ReportType;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReportPdfNetto extends ReportPdf {


    public ReportPdfNetto() {
        super(ReportType.NETTO);
    }


    @Override
    public List<PositionInterface> getPostions() {
        return null;
    }
}
