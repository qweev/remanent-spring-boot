package aniela.remanent.raport;

public final class ReportFileResolver {

    private ReportFileResolver() {

    }

    public static String resolveFilePath(String fileName) {
        String OS = System.getProperty("os.name").toLowerCase();
        String commonFileName = fileName + ".xlsx";
        return OS.indexOf("win") >= 0 ? "C:\\" + commonFileName : commonFileName;
    }


}
