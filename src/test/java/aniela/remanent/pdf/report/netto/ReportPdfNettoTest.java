package aniela.remanent.pdf.report.netto;

import aniela.remanent.pozycje.BazaDAO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;

public class ReportPdfNettoTest {

    private ReportPdfNetto reportPdfNettoTestedObj;
    private BazaDAO bazaDAO;

    private final static Logger LOGGER = Logger.getLogger(ReportPdfNettoTest.class);
    private static String filePath;
    private static String filePathSeparator;
    private static String reportName = "Raport.pdf";
    private static String reportPathString;

    @BeforeAll
    public static void setupBeforeAll() {
        filePath = System.getProperty("user.home");
        filePathSeparator = File.separator;
        reportPathString = new StringBuilder().append(filePath).append(filePathSeparator).append(reportName).toString();
    }

    @BeforeEach
    public void setupBeforeEach() {
        bazaDAO = Mockito.mock(BazaDAO.class);
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto()).thenReturn(PozycjeReportNettoFactory.generateListOfPozycjaDoRaportuNetto(189));
        reportPdfNettoTestedObj = new ReportPdfNetto(bazaDAO);
    }

    @Test
    public void generateReportWindows() throws Exception {
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportPathString);
        LOGGER.info(String.format("Report written to %s ", reportPathString));
    }
}
