package aniela.remanent.pdf.report.netto;

import aniela.remanent.pozycje.BazaDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ReportPdfNettoTest {


    private ReportPdfNetto reportPdfNettoTestedObj;

    @Mock
    private BazaDAO bazaDAO;

    @BeforeAll
    public void setupBeforeAll() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto()).thenReturn(PozycjeReportNettoFactory.generateListOfPozycjaDoRaportuNetto(34));
    }

    @BeforeEach
    public void setupBeforeEach() {
        reportPdfNettoTestedObj = new ReportPdfNetto(bazaDAO);
    }

    @Test
    public void generateReport() throws Exception {

        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), ".");
    }

}
