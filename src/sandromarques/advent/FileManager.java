package sandromarques.advent;

import java.io.File;

public class FileManager {
    private static final String EXTENSION = ".txt";
    private static final String FOLDER = "inputfiles/";

    public static File getFile(int day, int part) {
        return new File(FOLDER + "day" + day + "_part" + part + EXTENSION);
    }

    public static File getFile(int day) {
        return new File(FOLDER + "day" + day + EXTENSION);
    }
}
