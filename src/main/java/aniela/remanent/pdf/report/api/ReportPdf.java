package aniela.remanent.pdf.report.api;

import aniela.remanent.pdf.report.ReportGenerator;
import aniela.remanent.pdf.report.ReportPage;
import aniela.remanent.pozycje.BazaDAO;
import aniela.remanent.raport.raportDoDruku.PozycjaDoRaportuNetto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class ReportPdf implements ReportPdfApi {


    private static final Font FONT_HEADER = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.BOLD);

    private static final Font FONT_VALUE = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.NORMAL);

    protected BazaDAO bazaRaport;
    private Document document;

    private ReportGenerator reportGenerator;
    private Queue<ReportPage> reportPages;

    public ReportPdf() {
    }


    @Override
    public int countPostions() {
        return bazaRaport.obliczIloscPozycji();
    }

    @Override
    public String generateReport(List<PozycjaDoRaportuNetto> positions, String filePath) throws Exception {
        removeExisingReport(filePath);
        runReportGenerator();
        initializeReportPdf(filePath);
        generateHeader();
        generateFirstPage();
        generateInternalPages();
        generateLastPage();
        generateSummary();
        closeDocument();
        return null;
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
        PdfWriter.getInstance(document, new FileOutputStream(fullFilePath));
        document.open();
    }

    private void generateHeader() throws DocumentException {
        Paragraph paragraph = new Paragraph("Spis z natury na dzien 31.12." + (LocalDateTime.now().getYear() - 1), FONT_HEADER);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        addEmptyLine(paragraph, 1);
        document.add(paragraph);
    }

    private void generateFirstPage() throws DocumentException {
        PdfPTable table = new PdfPTable(6);
        table.setTotalWidth(new float[]{10f, 80f, 10f, 10f, 10f, 10f});
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
        table.setHeaderRows(1);
        ReportPage page1 = reportPages.poll();

        page1.positions.forEach(element -> {
            table.addCell(getPdfCell(String.valueOf(element.getPozyzjaWRaporcie())));
            table.addCell(getPdfCellNazwaTowaru(element.getNazwaTowaru()));
            table.addCell(getPdfCell(element.getJednostka()));
            table.addCell(getPdfCell(String.valueOf(element.getIlosc())));
            table.addCell(getPdfCell(String.valueOf(element.getCenaNetto())));
            table.addCell(getPdfCell(String.valueOf(element.getSumaNetto())));
        });

        document.add(table);
    }

    private void generateInternalPages() {


    }

    private void generateInternalPage() {
    }

    private void generateLastPage() {
    }

    private void generateSummary() {
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


}
