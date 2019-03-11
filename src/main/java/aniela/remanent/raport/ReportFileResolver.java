package aniela.remanent.raport;

import java.io.File;

public final class ReportFileResolver {

    private ReportFileResolver() {

    }

    public static String resolveFilePathForExcel(String fileName) {
        String commonFileNameWithExtension = fileName + ".xlsx";
        return resolveFullFileName(commonFileNameWithExtension);
    }

    public static String resolveFilePathForPdf(String fileName) {
        String commonFileNameWithExtension = fileName + ".pdf";
        return resolveFullFileName(commonFileNameWithExtension);
    }

    private static String resolveFullFileName(String commonFileNameWithExtnesion) {
        String userHome = System.getProperty("user.home");
        return new StringBuilder().append(userHome).append(File.separator).append(commonFileNameWithExtnesion).toString();
    }

}
