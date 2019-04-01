package aniela.remanent.pdf.report.formatter;

import java.math.BigDecimal;

public final class AmountFormatter {

    private AmountFormatter() {
    }

    public static String formatAmount(double amount) {
        BigDecimal bigDecimal = new BigDecimal(amount);
        int intValue = bigDecimal.intValue();
        BigDecimal delimiter = bigDecimal.subtract(new BigDecimal(intValue));
        if (delimiter.equals(BigDecimal.valueOf(0))) {
            return String.valueOf(intValue);
        } else {
            return String.valueOf(amount);
        }
    }

}
