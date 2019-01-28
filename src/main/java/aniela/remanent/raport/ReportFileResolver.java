package aniela.remanent.raport;

public final class ReportFileResolver {

    private ReportFileResolver() {

    }

    private static String OS = System.getProperty("os.name").toLowerCase();

    public static String resolveFilePathForExcel(String fileName) {
        String commonFileNameWithExtension = fileName + ".xlsx";
        return resolveFullFileName(commonFileNameWithExtension);
    }

    public static String resolveFilePathForPdf(String fileName) {
        String commonFileNameWithExtension = fileName + ".pdf";
        return resolveFullFileName(commonFileNameWithExtension);
    }

    private static String resolveFullFileName(String commonFileNameWithExtnesion) {
        return OS.startsWith("win") ? "C:\\" + commonFileNameWithExtnesion : commonFileNameWithExtnesion;
    }




}
