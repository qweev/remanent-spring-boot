package aniela.remanent.pdf.report.api;

import aniela.remanent.position.abstraction.PositionInterface;

import java.util.List;

public interface ReportPdfApi {

    List<PositionInterface> getPostions();

    String generateReport(List<PositionInterface> positions, String filePath) throws Exception;


}
