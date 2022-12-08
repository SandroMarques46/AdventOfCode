package sandromarques.advent.year2022;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public class Day1 {
    public static void main(String[] args) {
        Day1 day = new Day1();
        System.out.println(day.part(true, FileManager.getFile(1, 2022))); //correct answer : 69281
        System.out.println(day.part(false, FileManager.getFile(1, 2022))); //correct answer : 201524
    }

    private int part(boolean part1, File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }

            LinkedList<Integer> sumCaloriesList = new LinkedList<>();
            int currElfCalories = 0;

            for (String line : lines) {
                if (line.isEmpty()) { //reset count to start a new elf
                    sumCaloriesList.add(currElfCalories);
                    currElfCalories = 0;
                } else {
                    currElfCalories += Integer.parseInt(line);
                }
            }

            Collections.sort(sumCaloriesList);
            Collections.reverse(sumCaloriesList);

            if (part1) {
                return sumCaloriesList.get(0);
            } else {
                return sumCaloriesList.get(0) + sumCaloriesList.get(1) + sumCaloriesList.get(2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
