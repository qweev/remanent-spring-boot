package aniela.remanent.pdf.report.api;

import aniela.remanent.position.abstraction.PositionInterface;

import java.util.List;

public interface ReportPdfApi {

    List<PositionInterface> getPostions();

    //TODO use interface in future to get generic positions
    String generateReport(List<PositionInterface> positions, String filePath) throws Exception;


}
