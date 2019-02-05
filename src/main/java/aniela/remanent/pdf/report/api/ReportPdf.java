package aniela.remanent.pdf.report.api;

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
import java.util.List;

public abstract class ReportPdf implements ReportPdfApi {


    private static final Font FONT = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.BOLD);
    protected BazaDAO bazaRaport;
    private Document document;


    public ReportPdf() {
    }


    @Override
    public int countPostions() {
        return bazaRaport.obliczIloscPozycji();
    }

    @Override
    public String generateReport(List<PozycjaDoRaportuNetto> positions, String filePath) throws Exception {
        removeExisingReport(filePath);
        initializeReport(filePath);
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

    private void initializeReport(String fullFilePath) throws FileNotFoundException, DocumentException {
        document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fullFilePath));
        document.open();
    }

    private void generateHeader() throws DocumentException {
        Paragraph paragraph = new Paragraph("Spis z natury na dzien 31.12." + (LocalDateTime.now().getYear() - 1), FONT);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        addEmptyLine(paragraph, 1);
        document.add(paragraph);
    }

    private void generateFirstPage() throws DocumentException {
        PdfPTable table = new PdfPTable(6);
        PdfPCell pdfCellLp = getPdfCell("LP");
        PdfPCell pdfCellLpNazwaTowaru = getPdfCell("Nazwa towaru");
        PdfPCell pdfCellJm = getPdfCell("j. m.");
        PdfPCell pdfCellIlosc = getPdfCell("Ilość");
        PdfPCell pdfCellCenaNetto = getPdfCell("Cena netto");

        PdfPCell wartoścNetto = getPdfCell("Wartość netto");
        table.addCell(pdfCellLp);
        table.addCell(pdfCellLpNazwaTowaru);
        table.addCell(pdfCellIlosc);
        table.addCell(pdfCellJm);
        table.addCell(pdfCellCenaNetto);
        table.addCell(wartoścNetto);
        table.setHeaderRows(1);


        List<PozycjaDoRaportuNetto> positions = bazaRaport.przygotujPozycjeDoRaportuNetto();

        for (int x = 0; x < positions.size(); x++) {
            table.addCell(String.valueOf(x));
        }


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

    private PdfPCell getPdfCell(String columnName) {
        PdfPCell pdfCell = new PdfPCell(new Phrase(columnName));
        pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return pdfCell;
    }


}
