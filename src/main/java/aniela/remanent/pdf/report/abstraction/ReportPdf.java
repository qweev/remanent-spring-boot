package aniela.remanent.pdf.report.abstraction;

import aniela.remanent.pdf.report.abstraction.generator.ReportGenerator;
import aniela.remanent.pdf.report.abstraction.generator.ReportPage;
import aniela.remanent.pdf.report.api.ReportPdfApi;
import aniela.remanent.pdf.summary.SummaryGenerator;
import aniela.remanent.pozycje.BazaDAO;
import aniela.remanent.raport.raportDoDruku.PozycjaDoRaportuNetto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class ReportPdf implements ReportPdfApi {

    private static final String POLISH_CURRENCY = "zł";
    private static final int NUMBER_OF_COLUMNS_IN_REPORT = 6;
    private static final int NUMBER_OF_COLUMNS_IN_SUMMARY = 2;
    private static final int NUMBER_OF_HEADER_ROWS_AND_EMPTY_LINES = 1;
    private final Font FONT_HEADER;
    private final Font FONT_VALUE;
    protected BazaDAO bazaRaport;
    private Document document;
    private ReportGenerator reportGenerator;
    private SummaryGenerator summaryGenerator;
    private Queue<ReportPage> reportPages;
    private List<ReportPage> reportPagesForSummary = new ArrayList<>();

    public ReportPdf() {
        BaseFont baseFont = null;
        try {
            baseFont = BaseFont.createFont("arial.ttf", BaseFont.CP1250, BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e) {
            // e.printStackTrace();
        }
        FONT_HEADER = new Font(baseFont, 9, Font.BOLD);
        FONT_VALUE = new Font(baseFont, 9, Font.NORMAL);
    }

    @Override
    public String generateReport(List<PozycjaDoRaportuNetto> positions, String filePath) throws Exception {
        removeExisingReport(filePath);
        runReportGenerator();
        initializeReportPdf(filePath);
        generateHeader();
        generateFirstPage();
        generateInternalPages();
        generateEnding();
        generateSummary();
        closeDocument();
        return "Document created - this to be seet with wojti?";
    }

    private void removeExisingReport(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (path.toFile().exists()) {
            Files.delete(path);
        }
    }

    private void runReportGenerator() {
        reportGenerator = new ReportGenerator(bazaRaport.przygotujPozycjeDoRaportuNetto());
        reportPages = new LinkedList<>();
        reportPages.addAll(reportGenerator.generatePages());
    }

    private void initializeReportPdf(String fullFilePath) throws FileNotFoundException, DocumentException {
        document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(fullFilePath));
        PageNumerator pageNumerator = new PageNumerator(reportGenerator);
        pdfWriter.setPageEvent(pageNumerator);
        document.open();
    }

    private void generateHeader() throws DocumentException {
        Paragraph paragraph = new Paragraph("Spis z natury na dzień 31.12." + (LocalDateTime.now().getYear() - 1), FONT_HEADER);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        addEmptyLine(paragraph, 1);
        document.add(paragraph);
    }

    private void generateFirstPage() throws DocumentException {
        PdfPTable table = getPageTable();
        ReportPage page1 = reportPages.poll();
        fillPageWithPositions(table, page1);
        document.add(table);
        reportPagesForSummary.add(page1);
    }

    private void generateInternalPages() throws DocumentException {
        while (reportPages.peek() != null) {
            document.newPage();
            PdfPTable table = getPageTable();
            ReportPage reportPage = reportPages.poll();
            fillPageWithPositions(table, reportPage);
            document.add(table);
            reportPagesForSummary.add(reportPage);
        }
    }

    private void generateEnding() throws DocumentException {
        Paragraph paragraph = new Paragraph(String.format("Spis ukonczono na pozycji nr %d ", bazaRaport.obliczIloscPozycji()));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        addEmptyLine(paragraph, NUMBER_OF_HEADER_ROWS_AND_EMPTY_LINES);
        document.add(paragraph);
    }

    private void generateSummary() throws DocumentException {
        document.newPage();
        PdfPTable tableSumamry = getPageTableForSummary();
        summaryGenerator = new SummaryGenerator();
        fillTableWithReportPageNumberAndSum(tableSumamry, reportPagesForSummary, summaryGenerator.getTotalSum(reportPagesForSummary));
        document.add(tableSumamry);
    }

    private void closeDocument() {
        document.close();
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private PdfPCell getPdfCellHeader(String columnName) {
        PdfPCell pdfCell = new PdfPCell(new Phrase(columnName, FONT_HEADER));
        pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return pdfCell;
    }

    private PdfPCell getPdfCell(String value) {
        PdfPCell pdfCell = new PdfPCell(new Phrase(value, FONT_VALUE));
        pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return pdfCell;
    }

    private PdfPCell getPdfCellNazwaTowaru(String value) {
        PdfPCell pdfCell = new PdfPCell(new Phrase(value, FONT_VALUE));
        pdfCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        return pdfCell;
    }

    private PdfPCell getEmptyPdfCell() {
        PdfPCell pdfCell = new PdfPCell(new Phrase("", FONT_VALUE));
        pdfCell.setBorderWidth(0f);
        return pdfCell;
    }

    private PdfPTable getPageTable() throws DocumentException {
        PdfPTable table = new PdfPTable(NUMBER_OF_COLUMNS_IN_REPORT);
        table.setWidthPercentage(100f);
        table.setTotalWidth(new float[]{10f, 80f, 7f, 10f, 14f, 17f});

        PdfPCell pdfCellHeaderLp = getPdfCellHeader("LP");
        PdfPCell pdfCellHeaderNazwaTowaru = getPdfCellHeader("Nazwa towaru");
        PdfPCell pdfCellHeaderJm = getPdfCellHeader("j. m.");

        PdfPCell pdfCellHeaderIlosc = getPdfCellHeader("Ilość");
        PdfPCell pdfCellHeaderCenaNetto = getPdfCellHeader("Cena netto");
        PdfPCell pdfCellHeaderwartoścNetto = getPdfCellHeader("Wartość netto");
        table.addCell(pdfCellHeaderLp);
        table.addCell(pdfCellHeaderNazwaTowaru);
        table.addCell(pdfCellHeaderJm);
        table.addCell(pdfCellHeaderIlosc);
        table.addCell(pdfCellHeaderCenaNetto);
        table.addCell(pdfCellHeaderwartoścNetto);
        table.setHeaderRows(NUMBER_OF_HEADER_ROWS_AND_EMPTY_LINES);
        return table;
    }

    private void fillPageWithPositions(PdfPTable table, ReportPage reportPage) {
        reportPage.positions.forEach(element -> {
            table.addCell(getPdfCell(String.valueOf(element.getPozyzjaWRaporcie())));
            table.addCell(getPdfCellNazwaTowaru(element.getNazwaTowaru()));
            table.addCell(getPdfCell(element.getJednostka()));
            table.addCell(getPdfCell(AmountFormatter.formatAmount(element.getIlosc())));
            table.addCell(getPdfCell(element.getCenaNetto() + POLISH_CURRENCY));
            table.addCell(getPdfCell(element.getSumaNetto() + POLISH_CURRENCY));
        });
        table.addCell(getEmptyPdfCell());
        table.addCell(getEmptyPdfCell());
        table.addCell(getEmptyPdfCell());
        table.addCell(getEmptyPdfCell());
        table.addCell(getEmptyPdfCell());
        table.addCell(getPdfCell(reportPage.getSumOfPositions() + POLISH_CURRENCY));
    }

    private PdfPTable getPageTableForSummary() throws DocumentException {
        PdfPTable table = new PdfPTable(NUMBER_OF_COLUMNS_IN_SUMMARY);
        table.setWidthPercentage(20f);
        table.setTotalWidth(new float[]{10f, 10f});
        PdfPCell pdfCellPageNumber = getPdfCellHeader("Strona");
        PdfPCell pdfCellPageValue = getPdfCellHeader("Wartość");
        table.addCell(pdfCellPageNumber);
        table.addCell(pdfCellPageValue);
        table.setHeaderRows(1);
        return table;
    }

    private void fillTableWithReportPageNumberAndSum(PdfPTable table, List<ReportPage> reportPages, double totalSum) {
        reportPages.forEach(reportPage -> {
            table.addCell(getPdfCell(String.valueOf(reportPage.pageNumber)));
            table.addCell(getPdfCell(String.valueOf(reportPage.getSumOfPositions())));
        });

        table.addCell(getEmptyPdfCell());
        table.addCell(getPdfCell(totalSum + POLISH_CURRENCY));
    }


    private final static class AmountFormatter {

        private AmountFormatter() {
        }

        public static String formatAmount(double amount) {
            BigDecimal bigDecimal = new BigDecimal(amount);
            int intValue = bigDecimal.intValue();
            BigDecimal delimiter = bigDecimal.subtract(new BigDecimal(intValue));
            if (delimiter.equals(BigDecimal.valueOf(0))) {
                return String.valueOf(intValue);
            } else {
                return String.valueOf(amount);
            }
        }
    }
}
