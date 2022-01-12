package sandromarques.advent.year2021;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Day7 {
    public static void main(String[] args) {
        Day7 day7 = new Day7();
        //part1 : 348996 , part2 : 98231647
        day7.calculate(FileManager.getFile(7, 2021),true);
    }

    private void calculate(File file, boolean part2) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String[] seqStr = br.readLine().split(",");
            int[] sequence = new int[seqStr.length];
            int min = 0;
            int max = 0;
            HashMap<Integer, Integer> countMap = new HashMap<>();
            for (int i = 0, seqStrLength = seqStr.length; i < seqStrLength; i++) {
                sequence[i] = Integer.parseInt(seqStr[i]);
                int x = countMap.getOrDefault(sequence[i], 0);
                countMap.put(sequence[i], x + 1);
                if (i == 0) {
                    min = sequence[i];
                    max = sequence[i];
                } else if (sequence[i] < min) {
                    min = sequence[i];
                } else if (sequence[i] > max) {
                    max = sequence[i];
                }
            }
            int[] calcs = new int[max - min + 1];
            for (int i = min; i < max - min + 1; i++) {
                for (Integer num : countMap.keySet()) {
                    int count = countMap.get(num);
                    int aux = 0;
                    if(part2){
                        for (int j = 1; j <= Math.abs(i - num); j++) {
                            aux += j;
                        }
                    } else {
                        aux = Math.abs(i-num);
                    }
                    aux *= count;
                    calcs[i] += aux;
                }
                if (i != 0 && calcs[i - 1] < calcs[i]) {
                    System.out.println("Move to " + (i-1) + " total fuel is : " + calcs[i - 1]);
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
