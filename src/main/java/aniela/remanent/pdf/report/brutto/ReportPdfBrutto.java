package aniela.remanent.pdf.report.brutto;


import aniela.remanent.pdf.report.abstraction.ReportPdf;
import aniela.remanent.raport.raportDoDruku.PozycjaDoRaportuNetto;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO skip it now, focus on netto
@Service
public class ReportPdfBrutto extends ReportPdf {
    @Override
    public List<PozycjaDoRaportuNetto> getPostionsNetto() {
        return null;
    }
}
