package simu.model;

public class PlatformInfo {

    private static PlatformInfo instance;
    private final boolean isMac;

    private PlatformInfo() {
        String OS = System.getProperty("os.name").toLowerCase();
        isMac = OS.contains("darwin") || OS.contains("mac");
    }

    public static PlatformInfo getInstance() {
        if (instance == null) {
            instance = new PlatformInfo();
        }
        return instance;
    }

    public boolean getIsMac() {
        return isMac;
    }
}
