package sandromarques.advent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day11 {
    public static void main(String[] args) {
        Day11 day11 = new Day11();
        day11.part1(FileManager.getFile(11)); //1735
        day11.part2(FileManager.getFile(11)); //400
    }

    private void part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currLine;
            ArrayList<String> lines = new ArrayList<>();
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            int[][] matrix = new int[lines.size()][lines.get(0).length()];
            int idx = 0;
            for (String line : lines) {
                for (int i = 0; i < line.length(); i++) {
                    matrix[idx][i] = line.charAt(i) - '0';
                }
                idx++;
            }
            int x = add(matrix, 100);
            System.out.println(x);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void part2(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currLine;
            ArrayList<String> lines = new ArrayList<>();
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            int[][] matrix = new int[lines.size()][lines.get(0).length()];
            int idx = 0;
            for (String line : lines) {
                for (int i = 0; i < line.length(); i++) {
                    matrix[idx][i] = line.charAt(i) - '0';
                }
                idx++;
            }
            int i = 1;
            int totalCounter = i;
            do {
                add(matrix, i);
                totalCounter++;
            } while (!isAllZero(matrix));
            System.out.println(totalCounter - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isAllZero(int[][] matrix) {
        for (int[] m : matrix) {
            for (int k : m) {
                if (k != 0) return false;
            }
        }
        return true;
    }

    private int add(int[][] matrix, int steps) {
        int totalFlashes = 0;
        for (int k = 0; k < steps; k++) {

            for (int i = 0; i < matrix.length; i++) {
                int[] m = matrix[i];
                for (int j = 0; j < m.length; j++) {
                    m[j]++;
                }
            }
            //if there were cells with "10" and we incremented adjacent positions then there can be positions turning to 10 , so this
            //boolean makes sure this evaluation cycle keeps running until no more "10" cells to evaluate. Not the best way but def
            //the simplest visually
            boolean keepEvaluating = true;
            while (keepEvaluating) {
                keepEvaluating = false;
                for (int i = 0; i < matrix.length; i++) {
                    int[] m = matrix[i];
                    for (int j = 0; j < m.length; j++) {
                        if (m[j] >= 10) {
                            incAdjacent(matrix, i, j);
                            totalFlashes++;
                            m[j] = 0;
                            keepEvaluating = true;
                        }
                    }
                }
            }
        }
        return totalFlashes;
    }

    private void incAdjacent(int[][] matrix, int i, int j) {
        if (i - 1 >= 0 && matrix[i - 1][j] != 0)
            matrix[i - 1][j]++; //top
        if (i + 1 < matrix.length && matrix[i + 1][j] != 0)
            matrix[i + 1][j]++; //bottom
        if (j - 1 >= 0 && matrix[i][j - 1] != 0)
            matrix[i][j - 1]++; //left
        if (j + 1 < matrix.length && matrix[i][j + 1] != 0)
            matrix[i][j + 1]++; //right
        if (i - 1 >= 0 && j - 1 >= 0 && matrix[i - 1][j - 1] != 0)
            matrix[i - 1][j - 1]++; //top left
        if (i - 1 >= 0 && j + 1 < matrix.length && matrix[i - 1][j + 1] != 0)
            matrix[i - 1][j + 1]++; //top right
        if (i + 1 < matrix.length && j - 1 >= 0 && matrix[i + 1][j - 1] != 0)
            matrix[i + 1][j - 1]++; //bottom left
        if (i + 1 < matrix.length && j + 1 < matrix.length && matrix[i + 1][j + 1] != 0)
            matrix[i + 1][j + 1]++; //bottom right
    }
}
