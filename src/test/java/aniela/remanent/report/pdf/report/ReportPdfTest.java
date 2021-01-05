package aniela.remanent.report.pdf.report;

import aniela.remanent.pozycje.BazaRepository;
import aniela.remanent.report.ReportFileResolver;
import aniela.remanent.report.pdf.report.abstraction.ReportPdf;
import aniela.remanent.report.pdf.report.brutto.ReportPdfBrutto;
import aniela.remanent.report.pdf.report.netto.ReportPdfNetto;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;

public class ReportPdfTest {

    private final static Logger LOGGER = Logger.getLogger(ReportPdfTest.class);
    private ReportPdf reportPdfNettoTestedObj;
    private ReportPdf reportPdfBruttoTestedObj;
    private BazaRepository bazaRepository;
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

    private Session session;

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
        bazaRepository = Mockito.mock(BazaRepository.class);
        reportPdfNettoTestedObj = new ReportPdfNetto();
        reportPdfBruttoTestedObj = new ReportPdfBrutto();
    }



    @Test
    public void generateReportNetto() {
        Mockito.when(bazaRepository.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentSame(NUMBER_OF_POSITIONS_TO_GENERATE));
        Mockito.when(bazaRepository.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE);
        reportPdfNettoTestedObj.generateReport(bazaRepository.przygotujPozycjeDoRaportuNetto(), reportNettoPath, NUMBER_OF_POSITIONS_TO_GENERATE);
        LOGGER.info(String.format("Report written to %s ", reportNettoPath));
    }

    @Test
    public void generateReportNettoFor10000() {
        Mockito.when(bazaRepository.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentSame(NUMBER_OF_POSITIONS_TO_GENERATE_10_K));
        Mockito.when(bazaRepository.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_10_K);
        reportPdfNettoTestedObj.generateReport(bazaRepository.przygotujPozycjeDoRaportuNetto(), reportNettoPath10000, NUMBER_OF_POSITIONS_TO_GENERATE_10_K);
        LOGGER.info(String.format("Report written to %s ", reportNettoPath10000));
    }


    @Test
    public void generateReportNettoRandomValues() {
        Mockito.when(bazaRepository.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentRandom(NUMBER_OF_POSITIONS_TO_GENERATE_20));
        Mockito.when(bazaRepository.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_20);
        reportPdfNettoTestedObj.generateReport(bazaRepository.przygotujPozycjeDoRaportuNetto(), reportNettoPathRandom, NUMBER_OF_POSITIONS_TO_GENERATE_20);
        LOGGER.info(String.format("Report written to %s ", reportNettoPathRandom));
    }

    @Test
    public void generateReportNettoMixedValues() {
        Mockito.when(bazaRepository.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentMixed(NUMBER_OF_POSITIONS_TO_GENERATE_20));
        Mockito.when(bazaRepository.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_20);
        reportPdfNettoTestedObj.generateReport(bazaRepository.przygotujPozycjeDoRaportuNetto(), reportNettoPathRandomMixed, NUMBER_OF_POSITIONS_TO_GENERATE_20);
        LOGGER.info(String.format("Report written to %s ", reportNettoPathRandomMixed));
    }

    @Test
    public void generateReportNettoNoEntriesInDB() {
        Mockito.when(bazaRepository.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentMixed(NUMBER_OF_POSITIONS_TO_GENERATE_0));
        Mockito.when(bazaRepository.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_0);
        reportPdfNettoTestedObj.generateReport(bazaRepository.przygotujPozycjeDoRaportuNetto(), reportNettoPathEmpty, NUMBER_OF_POSITIONS_TO_GENERATE_0);
        LOGGER.info(String.format("Report written to %s ", reportNettoPathEmpty));
    }

    //===========================================

    @Test
    public void generateReportBrutto() {
        Mockito.when(bazaRepository.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentSame(NUMBER_OF_POSITIONS_TO_GENERATE));
        Mockito.when(bazaRepository.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE);
        reportPdfBruttoTestedObj.generateReport(bazaRepository.przygotujPozycjeDoRaportuNetto(), reportBruttoPath, NUMBER_OF_POSITIONS_TO_GENERATE);
        LOGGER.info(String.format("Report written to %s ", reportBruttoPath));
    }

    @Test
    public void generateReportBruttoFor10000() {
        Mockito.when(bazaRepository.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentSame(NUMBER_OF_POSITIONS_TO_GENERATE_10_K));
        Mockito.when(bazaRepository.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_10_K);
        reportPdfBruttoTestedObj.generateReport(bazaRepository.przygotujPozycjeDoRaportuNetto(), reportBruttoPath10000, NUMBER_OF_POSITIONS_TO_GENERATE_10_K);
        LOGGER.info(String.format("Report written to %s ", reportBruttoPath10000));
    }


    @Test
    public void generateReportBruttoRandomValues() {
        Mockito.when(bazaRepository.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentRandom(NUMBER_OF_POSITIONS_TO_GENERATE_20));
        Mockito.when(bazaRepository.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_20);
        reportPdfBruttoTestedObj.generateReport(bazaRepository.przygotujPozycjeDoRaportuNetto(), reportBruttoPathRandom, NUMBER_OF_POSITIONS_TO_GENERATE_20);
        LOGGER.info(String.format("Report written to %s ", reportBruttoPathRandom));
    }

    @Test
    public void generateReportBruttoMixedValues() {
        Mockito.when(bazaRepository.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentMixed(NUMBER_OF_POSITIONS_TO_GENERATE_20));
        Mockito.when(bazaRepository.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_20);
        reportPdfBruttoTestedObj.generateReport(bazaRepository.przygotujPozycjeDoRaportuNetto(), reportBruttoPathRandomMixed, NUMBER_OF_POSITIONS_TO_GENERATE_20);
        LOGGER.info(String.format("Report written to %s ", reportBruttoPathRandomMixed));
    }

    @Test
    public void generateReportBruttoNoEntriesInDB() {
        Mockito.when(bazaRepository.przygotujPozycjeDoRaportuNetto())
                .thenReturn(pozycjeReportFactory.generateListOfPozycjaDoRaportuNettoContentMixed(NUMBER_OF_POSITIONS_TO_GENERATE_0));
        Mockito.when(bazaRepository.obliczIloscPozycji()).thenReturn(NUMBER_OF_POSITIONS_TO_GENERATE_0);
        reportPdfBruttoTestedObj.generateReport(bazaRepository.przygotujPozycjeDoRaportuNetto(), reportBruttoPathEmpty, NUMBER_OF_POSITIONS_TO_GENERATE_0);
        LOGGER.info(String.format("Report written to %s ", reportBruttoPathEmpty));
    }



}
