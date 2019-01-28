package aniela.remanent.pdf.report.api;

import aniela.remanent.raport.raportDoDruku.PozycjaDoRaportuNetto;

import java.util.List;

public interface ReportPdfApi {

    int countPostions();

    List<PozycjaDoRaportuNetto> getPostionsNetto();

    //TODO use interface in future
    String generateReport(List<PozycjaDoRaportuNetto> positions);


}
