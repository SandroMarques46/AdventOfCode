package sandromarques.advent.year2020;

import sandromarques.advent.FileManager;

import javax.management.MBeanRegistration;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Day2 {

    public static void main(String[] args) {
        Day2 day = new Day2();
        day.part1(FileManager.getFile(2, 2020)); //correct answer : 556
        day.part2(FileManager.getFile(2, 2020)); //correct answer : 605
    }

    private void part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currLine;
            int total = 0;
            while ((currLine = br.readLine()) != null) {
                String[] split = currLine.split(" ");
                int a = Integer.parseInt(split[0].split("-")[0]);
                int b = Integer.parseInt(split[0].split("-")[1]);
                char c = split[1].charAt(0);
                String seq = split[2];
                int count = 0;
                for (Character ch : seq.toCharArray()) {
                    if (ch == c) {
                        count++;
                    }
                    if (count > b) {
                        break;
                    }
                }
                if (count >= a && count <= b) {
                    total++;
                }
            }
            System.out.println(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void part2(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currLine;
            int total = 0;
            while ((currLine = br.readLine()) != null) {
                String[] split = currLine.split(" ");
                int a = Integer.parseInt(split[0].split("-")[0]) - 1;
                int b = Integer.parseInt(split[0].split("-")[1]) - 1;
                char c = split[1].charAt(0);
                String seq = split[2];
                char[] charArray = seq.toCharArray();
                boolean first = false;
                for (int i = 0; i < charArray.length; i++) {
                    char c1 = charArray[i];
                    if (i == a) {
                        if (c1 == c) {
                            first = true;
                        }
                    }
                    if (i == b) {
                        boolean second = c1 == c;
                        if ((!first && second) || (first && !second)) {
                            total++;
                            break;
                        }
                    }
                }
            }
            System.out.println(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
