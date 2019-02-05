package aniela.remanent.pdf.report.netto;

import aniela.remanent.pozycje.BazaDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.mockito.Mockito;

public class ReportPdfNettoTest {

    private ReportPdfNetto reportPdfNettoTestedObj;

    private BazaDAO bazaDAO;

    @BeforeAll
    public static void setupBeforeAll() {

    }

    @BeforeEach
    public void setupBeforeEach() {
        bazaDAO = Mockito.mock(BazaDAO.class);
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto()).thenReturn(PozycjeReportNettoFactory.generateListOfPozycjaDoRaportuNetto(189));
        reportPdfNettoTestedObj = new ReportPdfNetto(bazaDAO);
    }


    @EnabledOnOs(OS.WINDOWS)
    @Test
    public void generateReportWindows() throws Exception {
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), "C:\\development\\raportBrutto.pdf");
    }


    @EnabledOnOs(OS.LINUX)
    @Test
    public void generateReportLinux() throws Exception {
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), "C:\\development\\raportBrutto.pdf");
    }
}
