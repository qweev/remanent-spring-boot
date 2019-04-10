package aniela.remanent.pdf.report.api;

import aniela.remanent.position.abstraction.Position;

import java.util.List;

public interface ReportPdfApi {

    String generateReport(List<Position> positions, String filePath, int numberOfPositions) throws Exception;


}
