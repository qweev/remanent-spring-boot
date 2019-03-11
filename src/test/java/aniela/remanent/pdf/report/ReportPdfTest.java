package aniela.remanent.pdf.report;

import aniela.remanent.pdf.report.abstraction.ReportPdf;
import aniela.remanent.pdf.report.brutto.ReportPdfBrutto;
import aniela.remanent.pdf.report.netto.ReportPdfNetto;
import aniela.remanent.pozycje.BazaDAO;
import aniela.remanent.raport.ReportFileResolver;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;

public class ReportPdfTest {

    private final static Logger LOGGER = Logger.getLogger(ReportPdfTest.class);
    private ReportPdf reportPdfNettoTestedObj;
    private ReportPdf reportPdfBruttoTestedObj;
    private BazaDAO bazaDAO;
    private static String reportBruttoName = "RaportBrutto.pdf";
    private static String filePath;
    private static String filePathSeparator = File.separator;
    private static String reportNettoName = "RaportNetto";
    private static String reportNettoName10000 = "RaportNetto10000";
    private static String reportNettoNameRandom = "RaportNettoRandom";
    private static String reportNettoNameRandomMixed = "RaportNettoMixed";
    private static String reportNettoNameEmpty = "RaportNettoEmpty";
    private static String reportNettoPath;
    private static String reportNettoPath10000;
    private static String reportNettoPathRandom;
    private static String reportNettoPathRandomMixed;
    private static String reportNettoPathEmpty;
    private static String reportBruttoName10000 = "RaportBrutto10000";
    private static String reportBruttoNameRandom = "RaportBruttoRandom";
    private static String reportBruttoNameRandomMixed = "RaportBruttoMixed";
    private static String reportBruttoNameEmpty = "RaportBruttoEmpty";
    private static String reportBruttoPath;
    private static String reportBruttoPath10000;
    private static String reportBruttoPathRandom;
    private static String reportBruttoPathRandomMixed;
    private static String reportBruttoPathEmpty;
    private PozycjeReportFactory pozycjeReportFactory;
    private static int NUMBER_OF_POSITIONS_TO_GENERATE = 182;
    private static int NUMBER_OF_POSITIONS_TO_GENERATE_10_K = 10000;
    private static int NUMBER_OF_POSITIONS_TO_GENERATE_20 = 20;
    private static int NUMBER_OF_POSITIONS_TO_GENERATE_0 = 0;

    @BeforeAll
    public static void setupBeforeAll() {
        filePath = System.getProperty("user.home");
        reportNettoPath = ReportFileResolver.resolveFilePathForPdf(reportNettoName);
        reportNettoPath10000 = ReportFileResolver.resolveFilePathForPdf(reportNettoName10000);
        reportNettoPathRandom = ReportFileResolver.resolveFilePathForPdf(reportNettoNameRandom);
        reportNettoPathRandomMixed = ReportFileResolver.resolveFilePathForPdf(reportNettoNameRandomMixed);
        reportNettoPathEmpty = ReportFileResolver.resolveFilePathForPdf(reportNettoNameEmpty);
        reportBruttoPath = ReportFileResolver.resolveFilePathForPdf(reportBruttoName);
        reportBruttoPath10000 = ReportFileResolver.resolveFilePathForPdf(reportBruttoName10000);
        reportBruttoPathRandom = ReportFileResolver.resolveFilePathForPdf(reportBruttoNameRandom);
        reportBruttoPathRandomMixed = ReportFileResolver.resolveFilePathForPdf(reportBruttoNameRandomMixed);
        reportBruttoPathEmpty = ReportFileResolver.resolveFilePathForPdf(reportBruttoNameEmpty);
    }

    @BeforeEach
    public void setupBeforeEach() {
        pozycjeReportFactory = new PozycjeReportFactory();
        bazaDAO = Mockito.mock(BazaDAO.class);
        reportPdfNettoTestedObj = new ReportPdfNetto(bazaDAO);
        reportPdfBruttoTestedObj = new ReportPdfBrutto(bazaDAO);
    }

    //netto tests

    @Test
    public void generateReportNetto() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentSame(NUMBER_OF_POSITIONS_TO_GENERATE));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportNettoPath);
        LOGGER.info(String.format("Report written to %s ", reportNettoPath));
    }

    @Test
    public void generateReportNettoFor10000() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentSame(NUMBER_OF_POSITIONS_TO_GENERATE_10_K));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_10_K);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportNettoPath10000);
        LOGGER.info(String.format("Report written to %s ", reportNettoPath10000));
    }


    @Test
    public void generateReportNettoRandomValues() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentRandom(NUMBER_OF_POSITIONS_TO_GENERATE_20));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_20);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportNettoPathRandom);
        LOGGER.info(String.format("Report written to %s ", reportNettoPathRandom));
    }

    @Test
    public void generateReportNettoMixedValues() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentMixed(NUMBER_OF_POSITIONS_TO_GENERATE_20));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_20);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportNettoPathRandomMixed);
        LOGGER.info(String.format("Report written to %s ", reportNettoPathRandomMixed));
    }

    @Test
    public void generateReportNettoNoEntriesInDB() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentMixed(NUMBER_OF_POSITIONS_TO_GENERATE_0));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_0);
        reportPdfNettoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportNettoPathEmpty);
        LOGGER.info(String.format("Report written to %s ", reportNettoPathEmpty));
    }

    //===========================================

    @Test
    public void generateReportBrutto() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentSame(NUMBER_OF_POSITIONS_TO_GENERATE));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE);
        reportPdfBruttoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportBruttoPath);
        LOGGER.info(String.format("Report written to %s ", reportBruttoPath));
    }

    @Test
    public void generateReportBruttoFor10000() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentSame(NUMBER_OF_POSITIONS_TO_GENERATE_10_K));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_10_K);
        reportPdfBruttoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportBruttoPath10000);
        LOGGER.info(String.format("Report written to %s ", reportBruttoPath10000));
    }


    @Test
    public void generateReportBruttoRandomValues() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentRandom(NUMBER_OF_POSITIONS_TO_GENERATE_20));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_20);
        reportPdfBruttoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportBruttoPathRandom);
        LOGGER.info(String.format("Report written to %s ", reportBruttoPathRandom));
    }

    @Test
    public void generateReportBruttoMixedValues() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentMixed(NUMBER_OF_POSITIONS_TO_GENERATE_20));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_20);
        reportPdfBruttoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportBruttoPathRandomMixed);
        LOGGER.info(String.format("Report written to %s ", reportBruttoPathRandomMixed));
    }

    @Test
    public void generateReportBruttoNoEntriesInDB() {
        Mockito.when(bazaDAO.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentMixed(NUMBER_OF_POSITIONS_TO_GENERATE_0));
        Mockito.when(bazaDAO.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_0);
        reportPdfBruttoTestedObj.generateReport(bazaDAO.przygotujPozycjeDoRaportuNetto(), reportBruttoPathEmpty);
        LOGGER.info(String.format("Report written to %s ", reportBruttoPathEmpty));
    }

}
