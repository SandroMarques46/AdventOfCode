package sandromarques.advent.year2020;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Day11 {

    public static void main(String[] args) {
        Day11 day = new Day11();
        day.parts(FileManager.getFile(11, 2020),true); //correct answer : 2194
        day.parts(FileManager.getFile(11, 2020), false); //correct answer : 1944
    }

    private void parts(File file, boolean part1) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            char[][] matrix = new char[lines.size()][lines.getFirst().length()];
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                char[] chars = line.toCharArray();
                System.arraycopy(chars, 0, matrix[i], 0, chars.length);
            }
            matrix = cycle(matrix, 1000, part1);
            System.out.println(countOccupied(matrix));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int countOccupied(char[][] matrix) {
        int count = 0;
        for (char[] chars : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (chars[j] == '#') count++;
            }
        }
        return count;
    }

    private void print(char[][] matrix) {
        for (char[] chars : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(chars[j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private char[][] cycle(char[][] matrix, int cycles, boolean part1) {
        //char floor = '.';
        char occupiedSeat = '#';
        char emptySeat = 'L';
        //Observe the original matrix BUT change the newMatrix
        for (int n = 0; n < cycles; n++) {
            //copy arr
            char[][] newMatrix = new char[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix[0].length);
            }
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (part1) {
                        if (canBeOccupied(matrix, i, j)) {
                            newMatrix[i][j] = occupiedSeat;
                        } else if (canBeEmpty(matrix, i, j)) {
                            newMatrix[i][j] = emptySeat;
                        }
                    } else { //part2
                        if (canBeOccupiedPart2(matrix, i, j)) {
                            newMatrix[i][j] = occupiedSeat;
                        } else if (canBeEmptyPart2(matrix, i, j)) {
                            newMatrix[i][j] = emptySeat;
                        }
                    }
                }
            }
            if (areEqual(matrix, newMatrix)) { // stop cycle
                break;
            }
            //apply the last changes of the new matrix
            matrix = newMatrix;
        }
        return matrix;
    }

    private boolean areEqual(char[][] matrix, char[][] newMatrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != newMatrix[i][j]) return false;
            }
        }
        return true;
    }

    public boolean canBeEmpty(char[][] newMatrix, int i, int j) {
        if (newMatrix[i][j] == '#') {
            return countAdjacentOccupied(newMatrix, i, j, newMatrix.length, newMatrix[0].length) >= 4;
        }
        return false;
    }

    private int countAdjacentOccupied(char[][] newMatrix, int i, int j, int maxI, int maxJ) {
        int countAdjacentOccupied = 0; //top left
        if (i - 1 >= 0 && j - 1 >= 0 && newMatrix[i - 1][j - 1] == '#') countAdjacentOccupied++; //top left
        if (i - 1 >= 0 && newMatrix[i - 1][j] == '#') countAdjacentOccupied++; //top
        if (i - 1 >= 0 && j + 1 < maxJ && newMatrix[i - 1][j + 1] == '#') countAdjacentOccupied++; //top right
        if (j - 1 >= 0 && newMatrix[i][j - 1] == '#') countAdjacentOccupied++; //left
        if (j + 1 < maxJ && newMatrix[i][j + 1] == '#') countAdjacentOccupied++; //right
        if (i + 1 < maxI && j - 1 >= 0 && newMatrix[i + 1][j - 1] == '#') countAdjacentOccupied++; //bottom left
        if (i + 1 < maxI && newMatrix[i + 1][j] == '#') countAdjacentOccupied++; //bottom
        if (i + 1 < maxI && j + 1 < maxJ && newMatrix[i + 1][j + 1] == '#') countAdjacentOccupied++; //bottom right
        return countAdjacentOccupied;
    }

    private boolean canBeOccupied(char[][] newMatrix, int i, int j) {
        if (newMatrix[i][j] == 'L') {
            return countAdjacentOccupied(newMatrix, i, j, newMatrix.length, newMatrix[0].length) == 0;
        }
        return false;
    }

    /**
     * Following methods only used in part2 only
     */
    public boolean canBeEmptyPart2(char[][] newMatrix, int i, int j) {
        if (newMatrix[i][j] == '#') {
            return countFirstAdjacentOccupied(newMatrix, i, j, newMatrix.length, newMatrix[0].length) >= 5;
        }
        return false;
    }

    public boolean canBeOccupiedPart2(char[][] newMatrix, int i, int j) {
        if (newMatrix[i][j] == 'L') {
            return countFirstAdjacentOccupied(newMatrix, i, j, newMatrix.length, newMatrix[0].length) == 0;
        }
        return false;
    }

    public int countFirstAdjacentOccupied(char[][] newMatrix, int i, int j, int maxI, int maxJ) {
        int countAdjacentOccupied = 0;
        int auxI;
        int auxJ;
        //top left
        auxI = i - 1;
        auxJ = j - 1;
        while (auxI >= 0 && auxJ >= 0) {
            if (newMatrix[auxI][auxJ] == '#') {
                countAdjacentOccupied++;
                break;
            }
            if(newMatrix[auxI][auxJ] != '.') {
                break;
            }
            auxI--;
            auxJ--;
        }
        //top
        auxI = i - 1;
        auxJ = j;
        for (; auxI >= 0; auxI--) {
            if (newMatrix[auxI][auxJ] == '#') {
                countAdjacentOccupied++;
                break;
            }
            if(newMatrix[auxI][auxJ] != '.') {
                break;
            }
        }
        //top right
        auxI = i - 1;
        auxJ = j + 1;
        while (auxI >= 0 && auxJ < maxJ) {
            if (newMatrix[auxI][auxJ] == '#') {
                countAdjacentOccupied++;
                break;
            }
            if(newMatrix[auxI][auxJ] != '.') {
                break;
            }
            auxI--;
            auxJ++;
        }
        //left
        auxI = i;
        auxJ = j - 1;
        for (; auxJ >= 0; auxJ--) {
            if (newMatrix[auxI][auxJ] == '#') {
                countAdjacentOccupied++;
                break;
            }
            if(newMatrix[auxI][auxJ] != '.') {
                break;
            }
        }
        //right
        //already assigned warning ... : auxI = i;
        auxJ = j + 1;
        for (; auxJ < maxJ; auxJ++) {
            if (newMatrix[auxI][auxJ] == '#') {
                countAdjacentOccupied++;
                break;
            }
            if(newMatrix[auxI][auxJ] != '.') {
                break;
            }
        }
        //bottom left
        auxI = i + 1;
        auxJ = j - 1;
        while (auxI < maxI && auxJ >= 0) {
            if (newMatrix[auxI][auxJ] == '#') {
                countAdjacentOccupied++;
                break;
            }
            if(newMatrix[auxI][auxJ] != '.') {
                break;
            }
            auxI++;
            auxJ--;
        }
        //bottom
        auxI = i + 1;
        auxJ = j;
        while (auxI < maxI) {
            if (newMatrix[auxI][auxJ] == '#') {
                countAdjacentOccupied++;
                break;
            }
            if(newMatrix[auxI][auxJ] != '.') {
                break;
            }
            auxI++;
        }
        //bottom right
        auxI = i + 1;
        auxJ = j + 1;
        while (auxI < maxI && auxJ < maxJ) {
            if (newMatrix[auxI][auxJ] == '#') {
                countAdjacentOccupied++;
                break;
            }
            if(newMatrix[auxI][auxJ] != '.') {
                break;
            }
            auxI++;
            auxJ++;
        }
        return countAdjacentOccupied;
    }
}
