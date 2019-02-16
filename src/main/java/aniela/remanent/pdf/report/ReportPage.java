package aniela.remanent.pdf.report;

import aniela.remanent.raport.raportDoDruku.PozycjaDoRaportuNetto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public final class ReportPage {

    public final int pageNumber;
    public final List<PozycjaDoRaportuNetto> positions;

    public ReportPage(int pageNumber) {
        this.pageNumber = pageNumber;
        positions = new ArrayList<>();
    }

    public void addPosition(PozycjaDoRaportuNetto position) {
        positions.add(position);
    }

    public double getSumOfPositions() {
        BigDecimal result = new BigDecimal(0);
        for (PozycjaDoRaportuNetto position : positions) {
            double sumaNetto = position.getSumaNetto();
            BigDecimal sumaNettoAsBigDecimal = BigDecimal.valueOf(sumaNetto);
            result = result.add(sumaNettoAsBigDecimal);
        }
        result.setScale(2, RoundingMode.HALF_UP);
        return result.doubleValue();
    }
}
