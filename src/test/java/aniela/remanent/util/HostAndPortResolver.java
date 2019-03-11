package aniela.remanent.util;

public final class HostAndPortResolver {

    private HostAndPortResolver() {

    }

    public final static String determineHostAndPort(int port) {
        return "http://localhost:" + Integer.valueOf(port).toString();

    }


}
