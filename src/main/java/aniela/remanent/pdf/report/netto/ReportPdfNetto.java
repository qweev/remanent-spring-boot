package aniela.remanent.pdf.report.netto;

import aniela.remanent.pdf.report.abstraction.ReportPdf;
import aniela.remanent.position.netto.PozycjaDoRaportuNetto;
import aniela.remanent.pozycje.BazaDAO;
import aniela.remanent.type.ReportType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportPdfNetto extends ReportPdf {

    @Autowired
    public ReportPdfNetto(BazaDAO bazaRaport) {
        super(ReportType.NETTO);
        this.bazaRaport = bazaRaport;
    }


    @Override
    public List<PozycjaDoRaportuNetto> getPostions() {
        return bazaRaport.przygotujPozycjeDoRaportuNetto();
    }
}
