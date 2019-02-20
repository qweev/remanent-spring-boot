package aniela.remanent.pdf.report.abstraction;

import aniela.remanent.pdf.report.ReportGenerator;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PageNumerator  extends PdfPageEventHelper {

    private ReportGenerator reportGenerator;

    public PageNumerator(ReportGenerator reportGenerator){
        this.reportGenerator = reportGenerator;
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        int numberOfGeneratedPages = reportGenerator.getNumberOfGeneratedPages();
        int currentPageNumber = writer.getCurrentPageNumber();
        String pageNumber = numberOfGeneratedPages == currentPageNumber ?  String.format("Strona %s", String.valueOf(currentPageNumber)) : "";
        try {
            Rectangle pageSize = document.getPageSize();
            //ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Title"), pageSize.getLeft(275), pageSize.getTop(30), 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase(),
                pageSize.getRight(30), pageSize.getTop(30), 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
