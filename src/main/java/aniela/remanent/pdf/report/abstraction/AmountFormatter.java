package aniela.remanent.pdf.report.abstraction;

import java.math.BigDecimal;

public final class AmountFormatter {

    private AmountFormatter() {
    }

    public static String extractAmount(double amount) {
        // System.out.println("====================");
        // System.out.println("Amount: " + amount);
        BigDecimal bigDecimal = new BigDecimal(amount);
        int intValue = bigDecimal.intValue();
        BigDecimal delimiter = bigDecimal.subtract(new BigDecimal(intValue));
        // System.out.println("Double Number: " + bigDecimal.toPlainString());
        // System.out.println("Integer Part: " + intValue);
        // System.out.println("Delimiter Part: " + delimiter.toPlainString());
        if (delimiter.equals(BigDecimal.valueOf(0))) {
            //   System.out.println("Zwracam Inta");
            return String.valueOf(intValue);
        } else {
            //  System.out.println("Zwracam calosc");
            return String.valueOf(amount);
        }
    }
}
