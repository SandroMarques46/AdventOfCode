package sandromarques.advent;

import java.io.*;
import java.util.ArrayList;

public class Day13 {
    private static class Pair {
        private final int x;
        private final int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Day13 day13 = new Day13();
        day13.parts(FileManager.getFile(13),true);
        day13.parts(FileManager.getFile(13),false);
        //part1 : 770
        //part2 output : day13.txt (EPUELPBR)
    }


    private void parts(File file, boolean part1) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            ArrayList<Pair> pairs = new ArrayList<>();
            ArrayList<String> folds = new ArrayList<>();
            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;
            String currLine;
            boolean isFoldSection = false;
            while ((currLine = br.readLine()) != null) {
                if (currLine.isEmpty()) isFoldSection = true;
                else if (isFoldSection) folds.add(currLine.split(" ")[2]);
                else {
                    String[] split = currLine.split(",");
                    int x = Integer.parseInt(split[0]);
                    int y = Integer.parseInt(split[1]);
                    if (x > maxX) maxX = x;
                    if (y > maxY) maxY = y;
                    pairs.add(new Pair(x, y));
                }
            }
            maxX++;
            maxY++;
            char[][] matrix = new char[maxY][maxX];
            for (int y = 0; y < maxY; y++) {
                for (int x = 0; x < maxX; x++) {
                    Pair found = null;
                    for (Pair p : pairs) {
                        if (p.x == x && p.y == y) {
                            found = p;
                            break;
                        }
                    }
                    if (found != null) {
                        matrix[y][x] = '#';
                        pairs.remove(found);
                    } else {
                        matrix[y][x] = '.';
                    }
                }
            }
            //printMatrix(matrix);
            for (int i = 0; i < folds.size(); i++) {
                String fold = folds.get(i);
                char letter = fold.split("=")[0].charAt(0);
                int num = Integer.parseInt(fold.split("=")[1]);
                if (letter == 'y') {
                    matrix = fold(matrix, num, true, maxX, maxY);
                } else if (letter == 'x') {
                    matrix = fold(matrix, num, false, maxX, maxY);
                }
                maxX = matrix[0].length;
                maxY = matrix.length;
                if (part1) { //part1
                    System.out.println("Part1 : " + getDots(matrix));
                    return;
                } else if (i == folds.size() - 1) { //part2 , only print the last fold since it's the answer (EPUELPBR)
                    File outputFolder = new File("outputFiles/day13");
                    outputFolder.mkdirs();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(outputFolder.getPath() + "/day13.txt"));
                    for (int y = 0; y < matrix.length; y++) {
                        for (int x = 0; x < matrix[0].length; x++) {
                            bw.write(matrix[y][x]);
                        }
                        bw.write("\n");
                    }
                    bw.flush();
                    bw.close();
                    System.out.println("Part2 solution at outputFiles/day13/day13.txt");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getDots(char[][] matrix) {
        int count = 0;
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                if (matrix[y][x] == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    private char[][] fold(char[][] matrix, int num, boolean isHorizontal, int maxX, int maxY) {
        char[][] newMatrix;
        if (isHorizontal) {
            newMatrix = new char[num][maxX];
            for (int x = 0; x < maxX; x++) {
                for (int y = 0; y < maxY; y++) {
                    if (y < num) { //top part
                        newMatrix[y][x] = matrix[y][x];
                    } else { //bottom
                        if (matrix[y][x] == '#') {
                            newMatrix[y - (y - num) * 2][x] = '#';
                        }
                    }
                }
            }
        } else {
            newMatrix = new char[maxY][num];
            for (int x = 0; x < maxX; x++) {
                for (int y = 0; y < maxY; y++) {
                    if (x < num) { //left part
                        newMatrix[y][x] = matrix[y][x];
                    } else { //right
                        if (matrix[y][x] == '#') {
                            newMatrix[y][x - (x - num) * 2] = '#';
                        }
                    }
                }
            }
        }
        return newMatrix;
    }
}
