package aniela.remanent.raport;

public final class MessageFactory {

    private MessageFactory() {
    }

    public static String generateMessageOKStatus(String path) {
        return new StringBuilder().append("plik zapisany w lokacji: ").append(path).toString();
    }
}
