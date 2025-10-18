package simu.model;

/**
 * Singleton Class to detect and store platform info
 */
public class PlatformInfo {

    private static PlatformInfo instance;
    private final boolean isMac;

    /**
     * Private constructor for singleton pattern.
     * <p>
     * Detects the operating system and sets the {@link #isMac} flag.
     * </p>
     */

    private PlatformInfo() {
        String OS = System.getProperty("os.name").toLowerCase();
        isMac = OS.contains("darwin") || OS.contains("mac");
    }

    /**
     * Returns the singleton instance of {@link PlatformInfo}.
     *
     * @return the {@link PlatformInfo} instance
     */

    public static PlatformInfo getInstance() {
        if (instance == null) {
            instance = new PlatformInfo();
        }
        return instance;
    }

    /**
     * Is a mac
     *
     * @return {@code true} if is Mac, {@code false} if not
     */
    public boolean getIsMac() {
        return isMac;
    }
}
