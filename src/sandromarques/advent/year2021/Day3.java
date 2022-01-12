package sandromarques.advent.year2021;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Day3 {
    public static void main(String[] args) {
        Day3 day3 = new Day3();
        day3.part1(FileManager.getFile(3, 2021, 1)); //correct answer : 1092896
        day3.part2(FileManager.getFile(3, 2021, 2)); //correct answer : 4672151
    }

    private void part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            LinkedList<String> lines = new LinkedList<>();
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            int gamma = 0;
            int epsilon = 0;
            int lineLength = lines.getFirst().length();
            int totalNumber = lines.size();
            for (int i = 0; i < lineLength; i++) {
                int numOf1s = 0;
                for (String l : lines) {
                    if (l.charAt(i) == '1') numOf1s++;
                }
                gamma <<= 1;
                epsilon <<= 1;
                if (numOf1s > totalNumber - numOf1s) {
                    gamma += 1;
                } else {
                    epsilon += 1;
                }
            }
            System.out.println("G: " + gamma + " , E: " + epsilon + " , Power: " + gamma * epsilon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void part2(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            LinkedList<String> lines = new LinkedList<>();
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            //oxygen
            String binaryOxygen = find(lines, true, '1');
            int oxygen = Integer.parseInt(binaryOxygen,2);
            String binaryC02 = find(lines, false, '0');
            int c02 = Integer.parseInt(binaryC02,2);
            System.out.println("O: " + oxygen + " , C02: " + c02 + " , LifeSupport: " + oxygen * c02);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String find(LinkedList<String> lines, boolean isMostCommon, char defaultNum) {
        int lineLength = lines.getFirst().length();
        for (int i = 0; i < lineLength; i++) {
            LinkedList<String> aux = new LinkedList<>();
            int numOf1s = 0;
            for (String l : lines) {
                if (l.charAt(i) == '1') numOf1s++;
            }
            int totalNumber = lines.size();
            char toKeep;
            if (numOf1s > totalNumber - numOf1s) { //'1' appears more
                if (isMostCommon) toKeep = '1';
                else toKeep = '0';
            } else if (numOf1s < totalNumber - numOf1s) {//'0' appears more
                if (isMostCommon) toKeep = '0';
                else toKeep = '1';
            } else { //same for both
                toKeep = defaultNum;
            }
            for (String l : lines) {
                if (l.charAt(i) == toKeep) {
                    aux.add(l);
                }
            }
            lines = aux;
            if(lines.size()==1) return aux.getFirst();
        }
        return null;
    }
}
