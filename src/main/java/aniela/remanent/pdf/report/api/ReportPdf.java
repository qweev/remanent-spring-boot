package aniela.remanent.pdf.report.api;

import aniela.remanent.pozycje.BazaDAO;
import aniela.remanent.raport.raportDoDruku.PozycjaDoRaportuNetto;

import java.util.List;

public abstract class ReportPdf implements ReportPdfApi {

    BazaDAO bazaRaport;

    public ReportPdf() {
    }


    @Override
    public int countPostions() {
        return bazaRaport.obliczIloscPozycji();
    }

    @Override
    public String generateReport(List<PozycjaDoRaportuNetto> positions) {


        return null;
    }
}
