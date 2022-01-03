package sandromarques.advent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day9 {
    private static class Point {
        private final int num;
        private boolean isVisited;

        public Point(int num, boolean isVisited) {
            this.num = num;
            this.isVisited = isVisited;
        }
    }

    public static void main(String[] args) {
        Day9 day9 = new Day9();
        //day9.part1(FileManager.getFile(9));
        day9.part2(FileManager.getFile(9));
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
            ArrayList<Integer> basinsPoints = new ArrayList<>();
            for (int i = 0; i < matrix.length; i++) {
                int[] line = matrix[i];
                for (int j = 0; j < line.length; j++) {
                    ArrayList<Integer> nearbyPosition = new ArrayList<>();
                    int curr = matrix[i][j];
                    if (i - 1 >= 0) nearbyPosition.add(matrix[i - 1][j]);
                    if (i + 1 < matrix.length) nearbyPosition.add(matrix[i + 1][j]);
                    if (j - 1 >= 0) nearbyPosition.add(matrix[i][j - 1]);
                    if (j + 1 < line.length) nearbyPosition.add(matrix[i][j + 1]);
                    boolean aux = true;
                    for (Integer integer : nearbyPosition) {
                        if (curr >= integer) {
                            aux = false;
                            break;
                        }
                    }
                    if (aux) {
                        basinsPoints.add(getBasin(matrix, i, j));
                    }
                }
            }
            Collections.sort(basinsPoints);
            System.out.println(basinsPoints.get(basinsPoints.size()-1) * basinsPoints.get(basinsPoints.size()-2) * basinsPoints.get(basinsPoints.size()-3));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getBasin(int[][] matrix, int line, int col) {
        Point[][] matrix2 = new Point[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int[] l = matrix[i];
            for (int j = 0; j < l.length; j++) {
                matrix2[i][j] = new Point(l[j], false);
            }
        }
        return doWork(matrix2, line, col);
    }

    private int doWork(Point[][] matrix2, int i, int j) {
        if(matrix2[i][j].isVisited || matrix2[i][j].num==9){
            return 0;
        }
        matrix2[i][j].isVisited = true;
        int count = 1;
        if (i - 1 >= 0 && matrix2[i - 1][j].num > matrix2[i][j].num )
            count += doWork(matrix2, i - 1, j);
        if (i + 1 < matrix2.length && matrix2[i + 1][j].num > matrix2[i][j].num )
            count += doWork(matrix2, i + 1, j);
        if (j - 1 >= 0 && matrix2[i][j - 1].num > matrix2[i][j].num )
            count += doWork(matrix2, i, j - 1);
        if (j + 1 < matrix2[i].length && matrix2[i][j + 1].num > matrix2[i][j].num )
            count += doWork(matrix2, i, j + 1);
        return count;
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
            int count = 0;
            for (int i = 0; i < matrix.length; i++) {
                int[] line = matrix[i];
                for (int j = 0; j < line.length; j++) {
                    ArrayList<Integer> nearbyPosition = new ArrayList<>();
                    int curr = matrix[i][j];
                    if (i - 1 >= 0) nearbyPosition.add(matrix[i - 1][j]);
                    if (i + 1 < matrix.length) nearbyPosition.add(matrix[i + 1][j]);
                    if (j - 1 >= 0) nearbyPosition.add(matrix[i][j - 1]);
                    if (j + 1 < line.length) nearbyPosition.add(matrix[i][j + 1]);
                    boolean aux = true;
                    for (Integer integer : nearbyPosition) {
                        if (curr >= integer) {
                            aux = false;
                            break;
                        }
                    }
                    if (aux) {
                        count += curr + 1;
                    }
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
