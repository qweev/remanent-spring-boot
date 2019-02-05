package aniela.remanent.pdf.report;

import aniela.remanent.raport.raportDoDruku.PozycjaDoRaportuNetto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class ReportPage {

    public final int pageNumber;
    private List<PozycjaDoRaportuNetto> positions;

    public ReportPage(int pageNumber) {
        this.pageNumber = pageNumber;
        positions = new ArrayList<>();
    }


    public void addPosition(PozycjaDoRaportuNetto position) {
        positions.add(position);
    }

    public double getSumOfPage() {
        BigDecimal result = new BigDecimal(2);
        return result.doubleValue();

    }


}
