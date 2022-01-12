package sandromarques.advent.year2020;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day3 {

    public static void main(String[] args) {
        Day3 day = new Day3();
        day.parts(FileManager.getFile(3, 2020), true); //correct answer : 167
        day.parts(FileManager.getFile(3, 2020), false); //correct answer : 736527114
    }

    private void parts(File file, boolean part1) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currLine;
            ArrayList<String> lines = new ArrayList<>();
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            char[][] matrix = new char[lines.size()][lines.get(0).length()];
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                for (int j = 0; j < line.length(); j++) {
                    matrix[i][j] = line.charAt(j);
                }
            }
            if (part1) {
                System.out.println(countTrees(matrix, 0, 0, 3, 1));
            } else {
                int total = 1;
                total *= countTrees(matrix, 0, 0, 1, 1);
                total *= countTrees(matrix, 0, 0, 3, 1);
                total *= countTrees(matrix, 0, 0, 5, 1);
                total *= countTrees(matrix, 0, 0, 7, 1);
                total *= countTrees(matrix, 0, 0, 1, 2);
                System.out.println(total);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private int countTrees(char[][] matrix, int col, int line, int moveCol, int moveLine) {
        if (line >= matrix.length) return 0;
        if (col >= matrix[0].length) col -= matrix[0].length;
        int count = 0;
        if (matrix[line][col] == '#') count = 1;
        return count + countTrees(matrix, col + moveCol, line + moveLine, moveCol, moveLine);
    }
}
