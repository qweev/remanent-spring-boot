package aniela.remanent.pdf.report.netto;


import aniela.remanent.pdf.report.api.ReportPdf;
import aniela.remanent.pozycje.BazaDAO;
import aniela.remanent.raport.raportDoDruku.PozycjaDoRaportuNetto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportPdfNetto extends ReportPdf {


    private BazaDAO bazaRaport;

    @Autowired
    public ReportPdfNetto(BazaDAO bazaRaport) {
        this.bazaRaport = bazaRaport;
    }


    @Override
    public List<PozycjaDoRaportuNetto> getPostionsNetto() {
        return bazaRaport.przygotujPozycjeDoRaportuNetto();
    }
}
