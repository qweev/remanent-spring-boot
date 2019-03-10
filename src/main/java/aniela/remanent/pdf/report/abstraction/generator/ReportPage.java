package aniela.remanent.pdf.report.abstraction.generator;

import aniela.remanent.position.abstraction.PositionInterface;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public final class ReportPage {

    private BigDecimal sumOfPositions;
    public final int pageNumber;
    public final List<PositionInterface> positions;

    ReportPage(int pageNumber) {
        this.pageNumber = pageNumber;
        positions = new ArrayList<>();
    }

    void addPosition(PositionInterface position) {
        positions.add(position);
    }

    void sumPositions() {
        BigDecimal result = new BigDecimal(0);
        for (PositionInterface position : positions) {
            double sumaNetto = position.getSuma();
            BigDecimal sumaNettoAsBigDecimal = BigDecimal.valueOf(sumaNetto);
            result = result.add(sumaNettoAsBigDecimal);
        }
        sumOfPositions = result.setScale(2, RoundingMode.HALF_UP);
    }

    public double getSumOfPositions() {
        return sumOfPositions.doubleValue();
    }
}
