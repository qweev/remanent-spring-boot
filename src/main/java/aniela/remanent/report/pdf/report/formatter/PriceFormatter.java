package aniela.remanent.report.pdf.report.formatter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class PriceFormatter {

    private PriceFormatter() {
    }

    public static String formatPrice(double value) {
        BigDecimal bd = new BigDecimal(value);
        return bd.setScale(2, RoundingMode.HALF_UP).toString();
    }
}
