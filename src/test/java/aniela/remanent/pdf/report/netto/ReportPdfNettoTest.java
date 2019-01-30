package aniela.remanent.pdf.report.netto;

import aniela.remanent.pozycje.BazaDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto()).thenReturn(PozycjeReportNettoFactory.generateListOfPozycjaDoRaportuNetto(34));
        reportPdfNettoTestedObj = new ReportPdfNetto(bazaDAO);
    }

    @Test
    public void generateReport() throws Exception {
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), "C:\\development\\raportBrutto.pdf");
    }
}
