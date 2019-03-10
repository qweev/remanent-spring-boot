package aniela.remanent.pdf.report.abstraction.generator;

import aniela.remanent.position.netto.PozycjaDoRaportuNetto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class ReportGenerator {

    private static final int POSITIONS_MAX_PER_PAGE = 54;
    private int pagesToGenerate = 0;
    private Queue<PozycjaDoRaportuNetto> postions;

    public ReportGenerator() {
        postions = new LinkedList<>();
    }

    public List<ReportPage> generatePages(List<PozycjaDoRaportuNetto> postionsList) {
        postions.clear();
        this.postions.addAll(postionsList);
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

    public int getNumberOfGeneratedPages() {
        return pagesToGenerate;
    }

}
