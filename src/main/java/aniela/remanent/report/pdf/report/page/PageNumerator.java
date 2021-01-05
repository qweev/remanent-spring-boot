package aniela.remanent.report.pdf.report.page;

import aniela.remanent.report.pdf.report.generator.ReportGenerator;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;

public class PageNumerator  extends PdfPageEventHelper {

    private final static Logger LOG = Logger.getLogger(PageNumerator.class);
    private ReportGenerator reportGenerator;

    public PageNumerator(ReportGenerator reportGenerator) {
        this.reportGenerator = reportGenerator;
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        int numberOfGeneratedPages = reportGenerator.getNumberOfGeneratedPages();
        int currentPageNumber = writer.getCurrentPageNumber();
        String pageNumber = ( currentPageNumber <= numberOfGeneratedPages ?  String.format("Strona %s", String.valueOf(currentPageNumber)) : "");
        try {
            Rectangle pageSize = document.getPageSize();
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase(pageNumber),
                pageSize.getRight(30), pageSize.getTop(30), 0);

        } catch (Exception e) {
            LOG.warn("Cannot add page number to page: ", e);
        }
    }

}
