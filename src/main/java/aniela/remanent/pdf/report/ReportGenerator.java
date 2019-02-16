package aniela.remanent.pdf.report;

import aniela.remanent.raport.raportDoDruku.PozycjaDoRaportuNetto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class ReportGenerator {

    private static final int POSITIONS_MAX_PER_PAGE = 54;
    private int pagesToGenerate = 0;
    private Queue<PozycjaDoRaportuNetto> postions = new LinkedList<>();
    public ReportGenerator(List<PozycjaDoRaportuNetto> postionsList) {
        this.postions.addAll(postionsList);
    }

    public List<ReportPage> generatePages() {

        pagesToGenerate = postions.size() / POSITIONS_MAX_PER_PAGE + 1;
        List<ReportPage> reportPages = new ArrayList<>();
        for (int pageNumber = 1; pageNumber <= pagesToGenerate; pageNumber++) {
            ReportPage reportPage = new ReportPage(pageNumber);
            for (int positionNumber = 1; positionNumber <= POSITIONS_MAX_PER_PAGE; positionNumber++) {
                PozycjaDoRaportuNetto position = postions.peek();
                if (position != null) {
                    reportPage.addPosition(postions.poll());
                }
            }
            reportPage.sumPositions();
            reportPages.add(reportPage);
        }
        return reportPages;
    }
}
