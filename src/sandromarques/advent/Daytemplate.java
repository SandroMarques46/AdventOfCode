package sandromarques.advent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Daytemplate {

    public static void main(String[] args) {
        Daytemplate day = new Daytemplate();
        day.part1(FileManager.getFile(1, 2022)); //correct answer :
    }

    private void part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            for (String line : lines) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
