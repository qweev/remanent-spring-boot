package aniela.remanent.pdf.report.brutto;


import aniela.remanent.pdf.report.abstraction.ReportPdf;
import aniela.remanent.position.abstraction.PositionInterface;
import aniela.remanent.pozycje.BazaDAO;
import aniela.remanent.type.ReportType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO skip it now, focus on netto
@Service
public class ReportPdfBrutto extends ReportPdf {

    @Autowired
    public ReportPdfBrutto(BazaDAO bazaRaport) {
        super(ReportType.BRUTTO);
        // this.bazaRaport = bazaRaport;
    }


    @Override
    public List<PositionInterface> getPostions() {
        return null;
    }
}
