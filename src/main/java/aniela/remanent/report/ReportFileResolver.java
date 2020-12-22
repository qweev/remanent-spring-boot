package aniela.remanent.report;

import java.io.File;
import java.time.LocalDateTime;

public final class ReportFileResolver {

    private final static int YEAR = LocalDateTime.now().getYear() - 1;

    private ReportFileResolver() {

    }

    public static String resolveFilePathForExcel(String fileName) {
        String commonFileNameWithExtension = fileName + Integer.valueOf(YEAR).toString() + ".xlsx";
        return resolveFullFileName(commonFileNameWithExtension);
    }

    public static String resolveFilePathForPdf(String fileName) {
        String commonFileNameWithExtension = fileName + Integer.valueOf(YEAR).toString() + ".pdf";
        return resolveFullFileName(commonFileNameWithExtension);
    }

    private static String resolveFullFileName(String commonFileNameWithExtnesion) {
        String userHome = System.getProperty("user.home");
        return new StringBuilder().append(userHome).append(File.separator).append(commonFileNameWithExtnesion).toString();
    }

}
