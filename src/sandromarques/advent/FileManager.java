package sandromarques.advent;

import java.io.File;

public class FileManager {
    private static final String EXTENSION = ".txt";
    private static final String FOLDER = "inputfiles/";

    public static File getFile(int day, int year, int part) {
        return new File(FOLDER + year + "/" + "day" + day + "_part" + part + EXTENSION);
    }

    public static File getFile(int day, int year) {
        return new File(FOLDER + year + "/" + "day" + day + EXTENSION);
    }
}
