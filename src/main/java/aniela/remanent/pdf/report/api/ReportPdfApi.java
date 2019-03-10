package aniela.remanent.pdf.report.api;

import aniela.remanent.position.netto.PozycjaDoRaportuNetto;

import java.util.List;

public interface ReportPdfApi {

    List<PozycjaDoRaportuNetto> getPostions();

    //TODO use interface in future to get generic positions
    String generateReport(List<PozycjaDoRaportuNetto> positions, String filePath) throws Exception;


}
