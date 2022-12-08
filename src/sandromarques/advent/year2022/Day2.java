package sandromarques.advent.year2022;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Day2 {
    public static void main(String[] args) {
        Day2 day = new Day2();
        System.out.println(day.part1(FileManager.getFile(2, 2022))); //correct answer : 14827
        System.out.println(day.part2(FileManager.getFile(2, 2022))); //correct answer : 13889
    }

    private int part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            /*
            The score for a single round is :
            -The score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors)
            PLUS
            -The score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
             */
            LinkedList<Character> typesOfPlays = new LinkedList<>();
            typesOfPlays.add('A');
            typesOfPlays.add('B');
            typesOfPlays.add('C');

            HashMap<Character, Integer> pointsMap = new HashMap<>();
            int playPoints = 1;
            for (char c : typesOfPlays) {
                pointsMap.put(c, playPoints++);
            }

            /*
                A B -> Win
                B C -> Win
                C A -> Win

                A C -> Lose
                B A -> Lose
                C B -> Lose
             */
            int points = 0;
            for (String line : lines) {
                char c1 = line.charAt(0);
                char c2 = (char) (line.charAt(2) - 23); //Convert X,Y,Z letters to A,B,C

                points += getPoints(typesOfPlays, pointsMap, c1, c2);
            }
            return points;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getPoints(LinkedList<Character> typesOfPlays, HashMap<Character, Integer> pointsMap, char c1, char c2) {
        int currPoints = 0;
        int shapeSelectedPoints = pointsMap.get(c2);
        if (c1 == c2) { //Draw
            currPoints += shapeSelectedPoints; //shape you selected points
            currPoints += 3; //outcome of round points
        } else if (c2 - 1 == c1 || (c1 == typesOfPlays.getLast() && c2 == typesOfPlays.getFirst())) { //Win
            currPoints += shapeSelectedPoints;
            currPoints += 6; //outcome of round points
        } else { //Lose
            currPoints += shapeSelectedPoints;
            //points += 0; //outcome of round points
        }
        return currPoints;
    }

    private int part2(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            /*
            The score for a single round is :
            -The score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors)
            PLUS
            -The score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
             */
            LinkedList<Character> typesOfPlays = new LinkedList<>();
            typesOfPlays.add('A');
            typesOfPlays.add('B');
            typesOfPlays.add('C');

            HashMap<Character, Integer> pointsMap = new HashMap<>();
            int playPoints = 1;
            for (char c : typesOfPlays) {
                pointsMap.put(c, playPoints++);
            }

            /*
                A B -> Win (c1, c2+1)
                B C -> Win (c1, c1+1)
                C A -> Win (c1.last, c1.first)

                A C -> Lose (c1.first, c1.last)
                B A -> Lose (c1, c1-1)
                C B -> Lose (c1, c1-1)
             */
            int points = 0;
            for (String line : lines) {
                char c1 = line.charAt(0);
                char c2 = line.charAt(2); //Use X,Y,Z

                switch (c2) {
                    case 'X': //lose
                        if (c1 == typesOfPlays.getFirst()) {
                            c2 = typesOfPlays.getLast();
                        } else {
                            c2 = (char) (c1 - 1);
                        }
                        break;
                    case 'Y': //draw
                        c2 = c1;
                        break;
                    case 'Z': //win
                        if (c1 == typesOfPlays.getLast()) {
                            c2 = typesOfPlays.getFirst();
                        } else {
                            c2 = (char) (c1 + 1);
                        }
                        break;
                }
                points += getPoints(typesOfPlays, pointsMap, c1, c2);
            }
            return points;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
