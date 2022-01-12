package sandromarques.advent.year2021;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day6 {
    public static void main(String[] args) {
        Day6 day6 = new Day6();
        day6.part2(FileManager.getFile(6, 2021), 80);
    }

    final int CYCLE = 7;

    private void part2(File file, int daysToCount) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            ArrayList<Integer> days = new ArrayList<>();
            String currLine = br.readLine();
            String[] split = currLine.split(",");
            for (String n : split) {
                days.add(Integer.parseInt(n));
            }
            //MUST BE LONG instead of int type!!!
            long[] map = new long[9];
            for (int i = 0; i < days.size(); i++) {
                map[days.get(i)]++;
            }
            for (int day = 0; day < daysToCount; day++) {
                long parents = map[0];
                for (int n = 1; n < map.length; n++) {
                    map[n - 1] = map[n];
                }
                map[6] += parents;
                map[8] = parents;
            }
            long total2 = Arrays.stream(map).sum();
            System.out.println(daysToCount + " days: " + total2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //First version made (much slower than the part2 one)
    private void v1(File file, int daysToCount) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            ArrayList<Integer> days = new ArrayList<>();
            String currLine = br.readLine();
            String[] split = currLine.split(",");
            for (String n : split) {
                days.add(Integer.parseInt(n));
            }
            for (int i = 0; i < daysToCount; i++) {
                int newToAdd = 0;
                for (int j = 0; j < days.size(); j++) {
                    if (days.get(j) == 0) {
                        days.set(j, 6);
                        newToAdd++;
                    } else {
                        days.set(j, days.get(j) - 1);
                    }
                }
                for (int k = 0; k < newToAdd; k++) {
                    days.add(8);
                }
            }
            System.out.println(daysToCount + " days: " + days.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
