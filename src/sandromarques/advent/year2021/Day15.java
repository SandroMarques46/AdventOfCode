package sandromarques.advent.year2021;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day15 {
    public static void main(String[] args) {
        Day15 day15 = new Day15();
        day15.part1(FileManager.getFile(15, 2021));
    }

    private void part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currLine = br.readLine();
            int[][] matrix = new int[currLine.length()][currLine.length()];
            int idx = 0;
            do {
                char[] charArray = currLine.toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    matrix[idx][i] = charArray[i] - '0';
                }
                idx++;
            } while ((currLine = br.readLine()) != null);
            System.out.println(findRisk(matrix, 0, 0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //ORDER of solution :
    //0,0
    //1,0
    //2,0
    //...2,6
    //3,6
    //3,7
    //4,7
    //5,7
    //5,8
    //6,8
    //7,8
    //8,8
    //8,9
    //9,9
    /*
    private int findRisk(int[][] matrix, int line, int col)  {
        if(line == 2 && col == 3){
            int xcswdf = 123;
        }
        if(line + 1 == matrix.length && col + 1 == matrix.length){
            return 0;
        } else if(line + 1 == matrix.length){ //can't go right , must go down
            return matrix[line][col + 1] + findRisk(matrix,line,col+1);
        } else if(col + 1 == matrix.length){ //can't go down , must go right
            return matrix[line + 1][col] + findRisk(matrix,line+1,col);
        } else {
            int right = matrix[line][col + 1];
            int down = matrix[line + 1][col];
            if (right < down) {
                return right + findRisk(matrix,line,col+1);
            } else if (down < right) {
                return down + findRisk(matrix,line+1,col);
            } else { //equals
                int a = right + findRisk(matrix,line,col+1);
                int b = down + findRisk(matrix,line+1,col);
                if(a < b){
                    return a;
                } else if (b < a){
                    return b;
                } else {
                    return -1;
                }
            }
        }
    }
     */
    private int findRisk(int[][] matrix, int line, int col) {
        if (line + 1 == matrix.length && col + 1 == matrix.length) {
            return 0;
        } else if (line + 1 == matrix.length) { //can't go right , must go down
            return matrix[line][col + 1] + findRisk(matrix, line, col + 1);
        } else if (col + 1 == matrix.length) { //can't go down , must go right
            return matrix[line + 1][col] + findRisk(matrix, line + 1, col);
        } else {
            int right = matrix[line][col + 1];
            int down = matrix[line + 1][col];
            int a = right + findRisk(matrix, line, col + 1);
            int b = down + findRisk(matrix, line + 1, col);
            if (a < b) {
                return a;
            } else if (b < a) {
                return b;
            } else {
                return -1;
            }
        }
    }
}
