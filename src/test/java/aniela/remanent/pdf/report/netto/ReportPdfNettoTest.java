package aniela.remanent.pdf.report.netto;

import aniela.remanent.pozycje.BazaDAO;
import java.io.File;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ReportPdfNettoTest {

    private final static Logger LOGGER = Logger.getLogger(ReportPdfNettoTest.class);
    private ReportPdfNetto reportPdfNettoTestedObj;
    private BazaDAO bazaDAO;
    private PozycjeReportNettoFactory pozycjeReportNettoFactory;
    private static String filePath;
    private static String filePathSeparator = File.separator;
    private static String reportName = "Raport.pdf";
    private static String reportName10000 = "Raport10000.pdf";
    private static String reportNameRandom = "RaportRandom.pdf";
    private static String reportNameRandomMixed = "RaportMixed.pdf";
    private static String reportPath;
    private static String reportPath10000;
    private static String reportPathRandom;
    private static String reportPathMixed;
    private static int NUMBER_OF_POSITIONS_TO_GENERATE = 182;

    @BeforeAll
    public static void setupBeforeAll() {
        filePath = System.getProperty("user.home");
        reportPath = new StringBuilder().append(filePath).append(filePathSeparator).append(reportName).toString();
        reportPath10000 = new StringBuilder().append(filePath).append(filePathSeparator).append(reportName10000).toString();
        reportPathRandom = new StringBuilder().append(filePath).append(filePathSeparator).append(reportNameRandom).toString();
        reportNameRandomMixed = new StringBuilder().append(filePath).append(filePathSeparator).append(reportNameRandomMixed).toString();
    }

    @BeforeEach
    public void setupBeforeEach() {
        pozycjeReportNettoFactory = new PozycjeReportNettoFactory();
        bazaDAO = Mockito.mock(BazaDAO.class);
        reportPdfNettoTestedObj = new ReportPdfNetto(bazaDAO);
    }

    @Test
    public void generateReport() throws Exception {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
            .thenReturn(pozycjeReportNettoFactory.generateListOfPozycjaDoRaportuNettoContentSame(NUMBER_OF_POSITIONS_TO_GENERATE));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportPath);
        LOGGER.info(String.format("Report written to %s ", reportPath));
    }

    @Test
    public void generateReportFor10000() throws Exception {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
            .thenReturn(pozycjeReportNettoFactory.generateListOfPozycjaDoRaportuNettoContentSame(10000));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportPath10000);
        LOGGER.info(String.format("Report written to %s ", reportPath));
    }


    @Test
    public void generateReportRandomValues() throws Exception {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
            .thenReturn(pozycjeReportNettoFactory.generateListOfPozycjaDoRaportuNettoContentRandom(10));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportPathRandom);
        LOGGER.info(String.format("Report written to %s ", reportPathRandom));
    }

    @Test
    public void generateReportMixedValues() throws Exception {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
            .thenReturn(pozycjeReportNettoFactory.generateListOfPozycjaDoRaportuNettoContentMixed(10));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportNameRandomMixed);
        LOGGER.info(String.format("Report written to %s ", reportNameRandomMixed));
    }

}
