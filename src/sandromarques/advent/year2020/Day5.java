package sandromarques.advent.year2020;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public class Day5 {

    public static void main(String[] args) {
        Day5 day = new Day5();
        day.parts(FileManager.getFile(5, 2020), true); //correct answer : 911
        day.parts(FileManager.getFile(5, 2020), false); //correct answer : 629
    }

    private void parts(File file, boolean part1) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            LinkedList<Integer> ids = new LinkedList<>();
            for (String line : lines) {
                int rowLow = 0;
                int rowHigh = 127;
                int colLow = 0;
                int colHigh = 7;
                int row = -1;
                int col = -1;
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (c == 'F') { //lower half
                        rowHigh = rowHigh - (rowHigh - rowLow) / 2 - 1;
                    } else if (c == 'B') { //upper half
                        rowLow = rowHigh - (rowHigh - rowLow) / 2;
                    }
                    if (c == 'L') { //lower half
                        colHigh = colHigh - (colHigh - colLow) / 2 - 1;
                    } else if (c == 'R') { //upper half
                        colLow = colHigh - (colHigh - colLow) / 2;
                    }
                    if (rowLow == rowHigh) {
                        row = rowLow;
                    }
                    if (colLow == colHigh) {
                        col = colLow;
                        break;
                    }
                }
                int total = row * 8 + col;
                ids.add(total);
            }
            Collections.sort(ids);
            if (part1) {
                System.out.println(ids.getLast());
            } else {
                for (int i = 1; i < ids.size() - 1; i++) {
                    int last = ids.get(i - 1);
                    int curr = ids.get(i);
                    int after = ids.get(i + 1);
                    if (!(Math.abs(last - curr) == 1 && Math.abs(after - curr) == 1)) {
                        System.out.println(curr+1);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
