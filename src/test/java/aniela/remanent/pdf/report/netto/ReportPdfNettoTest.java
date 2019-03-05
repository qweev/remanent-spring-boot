package aniela.remanent.pdf.report.netto;

import aniela.remanent.pozycje.BazaDAO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;

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
    private static String reportNameEmpty = "Empty.pdf";
    private static String reportPath;
    private static String reportPath10000;
    private static String reportPathRandom;
    private static String reportPathRandomMixed;
    private static String reportPathEmpty;
    private static int NUMBER_OF_POSITIONS_TO_GENERATE = 182;
    private static int NUMBER_OF_POSITIONS_TO_GENERATE_10_K = 10000;
    private static int NUMBER_OF_POSITIONS_TO_GENERATE_20 = 20;
    private static int NUMBER_OF_POSITIONS_TO_GENERATE_0 = 0;

    @BeforeAll
    public static void setupBeforeAll() {
        filePath = System.getProperty("user.home");
        reportPath = new StringBuilder().append(filePath).append(filePathSeparator).append(reportName).toString();
        reportPath10000 = new StringBuilder().append(filePath).append(filePathSeparator).append(reportName10000).toString();
        reportPathRandom = new StringBuilder().append(filePath).append(filePathSeparator).append(reportNameRandom).toString();
        reportPathRandomMixed = new StringBuilder().append(filePath).append(filePathSeparator).append(reportNameRandomMixed).toString();
        reportPathEmpty = new StringBuilder().append(filePath).append(filePathSeparator).append(reportNameEmpty).toString();
    }

    @BeforeEach
    public void setupBeforeEach() {
        pozycjeReportNettoFactory = new PozycjeReportNettoFactory();
        bazaDAO = Mockito.mock(BazaDAO.class);
        reportPdfNettoTestedObj = new ReportPdfNetto(bazaDAO);
    }

    @Test
    public void generateReport() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportNettoFactory.generateListOfPozycjaDoRaportuNettoContentSame(NUMBER_OF_POSITIONS_TO_GENERATE));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportPath);
        LOGGER.info(String.format("Report written to %s ", reportPath));
    }

    @Test
    public void generateReportFor10000() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportNettoFactory.generateListOfPozycjaDoRaportuNettoContentSame(NUMBER_OF_POSITIONS_TO_GENERATE_10_K));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_10_K);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportPath10000);
        LOGGER.info(String.format("Report written to %s ", reportPath10000));
    }


    @Test
    public void generateReportRandomValues() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportNettoFactory.generateListOfPozycjaDoRaportuNettoContentRandom(NUMBER_OF_POSITIONS_TO_GENERATE_20));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_20);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportPathRandom);
        LOGGER.info(String.format("Report written to %s ", reportPathRandom));
    }

    @Test
    public void generateReportMixedValues() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportNettoFactory.generateListOfPozycjaDoRaportuNettoContentMixed(NUMBER_OF_POSITIONS_TO_GENERATE_20));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_20);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportPathRandomMixed);
        LOGGER.info(String.format("Report written to %s ", reportPathRandomMixed));
    }

    @Test
    public void generateReportNoEntriesInDB() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportNettoFactory.generateListOfPozycjaDoRaportuNettoContentMixed(NUMBER_OF_POSITIONS_TO_GENERATE_0));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_0);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportPathEmpty);
        LOGGER.info(String.format("Report written to %s ", reportPathEmpty));
    }
}
