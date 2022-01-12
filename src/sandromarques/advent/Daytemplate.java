package sandromarques.advent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Daytemplate {

    public static void main(String[] args) {
        Daytemplate day = new Daytemplate();
        day.part1(FileManager.getFile(1, 2021, 1)); //correct answer : 1288
    }

    private void part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currLine;
            while ((currLine = br.readLine()) != null) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
