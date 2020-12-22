package aniela.remanent.report.pdf.report.formatter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PriceFormatterTest {

    @Test
    public void format23344() {
        double value = 1.23344d;
        Assertions.assertEquals("1.23", PriceFormatter.formatPrice(value), "Not working");
    }

    @Test
    public void format2() {
        double value = 1.2d;
        Assertions.assertEquals("1.20", PriceFormatter.formatPrice(value), "Not working");

    }

    @Test
    public void formatNone() {
        double value = 1d;
        Assertions.assertEquals("1.00", PriceFormatter.formatPrice(value), "Not working");

    }


    @Test
    public void format23844() {
        double value = 1.23844d;
        Assertions.assertEquals("1.24", PriceFormatter.formatPrice(value), "Not working");
    }


}
