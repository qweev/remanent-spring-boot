package aniela.remanent.pdf.report.abstraction.generator;

import aniela.remanent.position.netto.PozycjaDoRaportuNetto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public final class ReportPage {

    private BigDecimal sumOfPositions;
    public final int pageNumber;
    public final List<PozycjaDoRaportuNetto> positions;

    ReportPage(int pageNumber) {
        this.pageNumber = pageNumber;
        positions = new ArrayList<>();
    }

    void addPosition(PozycjaDoRaportuNetto position) {
        positions.add(position);
    }

    void sumPositions() {
        BigDecimal result = new BigDecimal(0);
        for (PozycjaDoRaportuNetto position : positions) {
            double sumaNetto = position.getSumaNetto();
            BigDecimal sumaNettoAsBigDecimal = BigDecimal.valueOf(sumaNetto);
            result = result.add(sumaNettoAsBigDecimal);
        }
        sumOfPositions = result.setScale(2, RoundingMode.HALF_UP);
    }

    public double getSumOfPositions() {
        return sumOfPositions.doubleValue();
    }
}
