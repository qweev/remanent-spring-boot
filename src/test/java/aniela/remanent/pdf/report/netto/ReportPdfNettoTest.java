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
    private static String reportNettoName = "RaportNetto.pdf";
    private static String reportNettoName10000 = "RaportNetto10000.pdf";
    private static String reportNettoNameRandom = "RaportNettoRandom.pdf";
    private static String reportNettoNameRandomMixed = "RaportNettoMixed.pdf";
    private static String reportNettoNameEmpty = "RaportNettoEmpty.pdf";
    private static String reportNettoPath;
    private static String reportNettoPath10000;
    private static String reportNettoPathRandom;
    private static String reportNettoPathRandomMixed;
    private static String reportNettoPathEmpty;
    private static int NUMBER_OF_POSITIONS_TO_GENERATE = 182;
    private static int NUMBER_OF_POSITIONS_TO_GENERATE_10_K = 10000;
    private static int NUMBER_OF_POSITIONS_TO_GENERATE_20 = 20;
    private static int NUMBER_OF_POSITIONS_TO_GENERATE_0 = 0;

    @BeforeAll
    public static void setupBeforeAll() {
        filePath = System.getProperty("user.home");
        reportNettoPath = new StringBuilder().append(filePath).append(filePathSeparator).append(reportNettoName).toString();
        reportNettoPath10000 = new StringBuilder().append(filePath).append(filePathSeparator).append(reportNettoName10000).toString();
        reportNettoPathRandom = new StringBuilder().append(filePath).append(filePathSeparator).append(reportNettoNameRandom).toString();
        reportNettoPathRandomMixed = new StringBuilder().append(filePath).append(filePathSeparator).append(reportNettoNameRandomMixed).toString();
        reportNettoPathEmpty = new StringBuilder().append(filePath).append(filePathSeparator).append(reportNettoNameEmpty).toString();
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
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportNettoPath);
        LOGGER.info(String.format("Report written to %s ", reportNettoPath));
    }

    @Test
    public void generateReportFor10000() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportNettoFactory.generateListOfPozycjaDoRaportuNettoContentSame(NUMBER_OF_POSITIONS_TO_GENERATE_10_K));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_10_K);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportNettoPath10000);
        LOGGER.info(String.format("Report written to %s ", reportNettoPath10000));
    }


    @Test
    public void generateReportRandomValues() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportNettoFactory.generateListOfPozycjaDoRaportuNettoContentRandom(NUMBER_OF_POSITIONS_TO_GENERATE_20));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_20);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportNettoPathRandom);
        LOGGER.info(String.format("Report written to %s ", reportNettoPathRandom));
    }

    @Test
    public void generateReportMixedValues() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportNettoFactory.generateListOfPozycjaDoRaportuNettoContentMixed(NUMBER_OF_POSITIONS_TO_GENERATE_20));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_20);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportNettoPathRandomMixed);
        LOGGER.info(String.format("Report written to %s ", reportNettoPathRandomMixed));
    }

    @Test
    public void generateReportNoEntriesInDB() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportNettoFactory.generateListOfPozycjaDoRaportuNettoContentMixed(NUMBER_OF_POSITIONS_TO_GENERATE_0));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_0);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportNettoPathEmpty);
        LOGGER.info(String.format("Report written to %s ", reportNettoPathEmpty));
    }
}
