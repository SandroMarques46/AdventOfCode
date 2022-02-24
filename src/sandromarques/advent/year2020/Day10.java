package sandromarques.advent.year2020;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public class Day10 {

    public static void main(String[] args) {
        Day10 day = new Day10();
        //day.part1(FileManager.getFile(10, 2020)); //correct answer : 2059
        //The smaller example works (8) but the bigger example gives 10976 instead of the correct 19208
        day.part2(FileManager.getFile(10, 2020)); //correct answer : ___
    }

    private void part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            LinkedList<Integer> outputVoltages = new LinkedList<>();
            for (String line : lines) {
                outputVoltages.add(Integer.parseInt(line));
            }
            Collections.sort(outputVoltages);
            int totalVoltage = 0; //start
            int lastAdapter = 0;
            int[] diffArr = new int[4]; //[0] = NOT USED , [1] = diff of 1 , [2] = diff of 2 ...
            for (int ov : outputVoltages) {
                int diff = ov - lastAdapter;
                lastAdapter = ov;
                totalVoltage += ov;
                diffArr[diff]++;
            }
            int finalVal = lastAdapter + 3;
            diffArr[3]++;
            //1-jolt diffs x 3-jolt diffs
            System.out.println(diffArr[1] * diffArr[3]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void part2(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            LinkedList<Integer> outputVoltages = new LinkedList<>();
            for (String line : lines) {
                outputVoltages.add(Integer.parseInt(line));
            }
            Collections.sort(outputVoltages);

            LinkedList<String> sequences = new LinkedList<>();
            sequences.add("0-");
            System.out.println(rec(outputVoltages, 0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int rec(LinkedList<Integer> outputVoltages, int idx) {
        if (idx >= outputVoltages.size() - 1){ //if it's on position before the last one then it's guaranteed to complete
            return 1;
        }
        int lastAdapter;
        int count = 0;

        int ov = outputVoltages.get(idx);
        lastAdapter = ov;
        LinkedList<Integer> possibleNextVoltages = new LinkedList<>();
        for (int j = idx + 1; j < outputVoltages.size(); j++) {
            ov = outputVoltages.get(j);
            int diff = ov - lastAdapter;
            if (diff <= 3) {
                possibleNextVoltages.add(j);
            } else {
                break;
            }
        }
        for (int next : possibleNextVoltages) {
            count += rec(outputVoltages, next);
        }
        return count;
    }
    /*
    private int rec(LinkedList<Integer> outputVoltages,int idx) {
        if(idx >= outputVoltages.size()) return 1;
        int lastAdapter;
        int count = 0;
        for (; idx < outputVoltages.size(); idx++) {
            int ov = outputVoltages.get(idx);
            lastAdapter = ov;
            LinkedList<Integer> possibleNextVoltages = new LinkedList<>();
            for (int j = idx + 1; j < outputVoltages.size(); j++) {
                ov = outputVoltages.get(j);
                int diff = ov - lastAdapter;
                if (diff <= 3) {
                    possibleNextVoltages.add(j);
                } else {
                    break;
                }
            }
            if(possibleNextVoltages.size() >= 2){
                for (int next : possibleNextVoltages) {
                    count += rec(outputVoltages,next);
                }
                return count;
            }
        }
        return 1;
    }
     */
}
