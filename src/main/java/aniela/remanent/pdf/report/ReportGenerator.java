package aniela.remanent.pdf.report;

import aniela.remanent.raport.raportDoDruku.PozycjaDoRaportuNetto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class ReportGenerator {

    private static final int POSITIONS_MAX_PER_PAGE = 55;
    private int pagesToGenerate = 0;
    private Queue postions = new LinkedList();
    //private List<PozycjaDoRaportuNetto> postions;

    public ReportGenerator(List<PozycjaDoRaportuNetto> postionsList) {
        this.postions.addAll(postionsList);
    }

    public void countNumberOfPages() {
        pagesToGenerate = postions.size() / POSITIONS_MAX_PER_PAGE;
    }

    public List<ReportPage> generatePages() {
        for (int pageNumber = 1; pageNumber <= pagesToGenerate; pageNumber++) {

            for (int positionNumber = 1; positionNumber <= POSITIONS_MAX_PER_PAGE; positionNumber++) {

            }


        }


        return new ArrayList<>();

    }


}
