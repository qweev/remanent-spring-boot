package aniela.remanent.pdf.report.brutto;


import aniela.remanent.pdf.report.abstraction.ReportPdf;
import aniela.remanent.position.abstraction.Position;
import aniela.remanent.type.ReportType;
import java.util.List;
import org.springframework.stereotype.Service;

//TODO skip it now, focus on netto
@Service
public class ReportPdfBrutto extends ReportPdf {


    public ReportPdfBrutto() {
        super(ReportType.BRUTTO);
        // this.bazaRaport = bazaRaport;
    }


    @Override
    public List<Position> getPostions() {
        return null;
    }
}
