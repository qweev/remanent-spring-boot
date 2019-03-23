package aniela.remanent.raport;

import java.io.File;
import java.time.LocalDateTime;

public final class ReportFileResolver {

    private final static int year = LocalDateTime.now().getYear() - 1;


    private ReportFileResolver() {

    }

    public static String resolveFilePathForExcel(String fileName) {
        String commonFileNameWithExtension = fileName + Integer.valueOf(year).toString()+ ".xlsx";
        return resolveFullFileName(commonFileNameWithExtension);
    }

    public static String resolveFilePathForPdf(String fileName) {
        String commonFileNameWithExtension = fileName + Integer.valueOf(year).toString()+ ".pdf";
        return resolveFullFileName(commonFileNameWithExtension);
    }

    private static String resolveFullFileName(String commonFileNameWithExtnesion) {
        String userHome = System.getProperty("user.home");
        return new StringBuilder().append(userHome).append(File.separator).append(commonFileNameWithExtnesion).toString();
    }

}
