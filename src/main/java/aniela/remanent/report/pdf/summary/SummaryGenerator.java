package aniela.remanent.report.pdf.summary;

import aniela.remanent.report.pdf.report.generator.ReportPage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public final class SummaryGenerator {

    private BigDecimal totalSum;

    public double getTotalSum(List<ReportPage> reportPageList) {

        BigDecimal result = new BigDecimal(0);
        for (ReportPage report : reportPageList) {
            result = result.add(BigDecimal.valueOf(report.getSumOfPositions()));
        }
        totalSum = result.setScale(2, RoundingMode.HALF_UP);
        return totalSum.doubleValue();
    }
}
