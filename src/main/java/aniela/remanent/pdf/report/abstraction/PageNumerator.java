package aniela.remanent.pdf.report.abstraction;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class PageNumerator  extends PdfPageEventHelper {

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        try {
            Rectangle pageSize = document.getPageSize();
            //ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Title"), pageSize.getLeft(275), pageSize.getTop(30), 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase(String.format("Strona %s", String.valueOf(writer.getCurrentPageNumber()))),
                pageSize.getRight(30), pageSize.getTop(30), 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
