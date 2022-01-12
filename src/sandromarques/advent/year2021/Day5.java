package sandromarques.advent.year2021;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day5 {
    public static void main(String[] args) {
        Day5 day5 = new Day5();
        day5.part2(FileManager.getFile(5, 2021)); //correct answer : 19851
    }

    private void part2(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currLine;
            int[][] positions = new int[1000][1000];
            int overlappingPositions = 0;
            while ((currLine = br.readLine()) != null) {
                String[] split = currLine.replace(" -> ", ",").split(",");
                int x1 = Integer.parseInt(split[0]);
                int y1 = Integer.parseInt(split[1]);
                int x2 = Integer.parseInt(split[2]);
                int y2 = Integer.parseInt(split[3]);
                if (x1 == x2) {
                    int min = Math.min(y1, y2);
                    int max = Math.max(y1, y2);
                    for (; min <= max; min++) {
                        if (positions[min][x1] == 1) {
                            overlappingPositions++;
                        }
                        positions[min][x1]++;
                    }
                } else if (y1 == y2) {
                    int min = Math.min(x1, x2);
                    int max = Math.max(x1, x2);
                    for (; min <= max; min++) {
                        if (positions[y1][min] == 1) {
                            overlappingPositions++;
                        }
                        positions[y1][min]++;
                    }
                } else { //diagonal lines
                    int auxX = x1 - x2;
                    int auxY = y1 - y2;
                    if (auxX > 0 && auxY < 0 || auxX < 0 && auxY > 0) {
                        //e.g 10,5 -> 5,10
                        int line = Math.max(x1, x2); //5
                        int col = Math.min(y1, y2); //5
                        int maxLine = Math.max(x1, x2); //10
                        int maxCol = Math.max(y1, y2); //10
                        while (line <= maxLine && col <= maxCol) {
                            if (positions[col][line] == 1) {
                                overlappingPositions++;
                            }
                            positions[col][line]++;
                            col++;
                            line--;
                        }
                    } else if (auxX > 0 && auxY > 0 || auxX < 0 && auxY < 0) {
                        //e.g 1,2 -> 3,4
                        int line = Math.min(x1, x2); //1
                        int col = Math.min(y1, y2); //2
                        int maxLine = Math.max(x1, x2); //3
                        int maxCol = Math.max(y1, y2); //4
                        while (line <= maxLine && col <= maxCol) {
                            if (positions[col][line] == 1) {
                                overlappingPositions++;
                            }
                            positions[col][line]++;
                            col++;
                            line++;
                        }
                    }
                }
            }
            System.out.println("Overlapping Positions : " + overlappingPositions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
