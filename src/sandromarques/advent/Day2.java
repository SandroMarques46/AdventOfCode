package sandromarques.advent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {
    public static void main(String[] args) {
        Day2 day2 = new Day2();
        day2.part1(FileManager.getFile(2, 1)); //correct answer : 2147104
        day2.part2(FileManager.getFile(2, 2)); //correct answer : 2044620088
    }

    private void part1(File file) {
        int depth = 0;
        int horizontal = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(" ");
                String oper = split[0];
                int num = Integer.parseInt(split[1]);
                switch (oper) {
                    case "forward" -> horizontal += num;
                    case "up" -> depth -= num;
                    case "down" -> depth += num;
                    default -> System.out.println("Not implemented : " + oper);
                }
            }
            System.out.println("D: " + depth + " , H: " + horizontal + " , Total: " + depth * horizontal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void part2(File file) {
        int depth = 0;
        int horizontal = 0;
        int aim = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(" ");
                String oper = split[0];
                int num = Integer.parseInt(split[1]);
                switch (oper) {
                    case "forward" -> {
                        horizontal += num;
                        depth += aim * num;
                    }
                    case "up" -> aim -= num;
                    case "down" -> aim += num;
                    default -> System.out.println("Not implemented : " + oper);
                }
            }
            System.out.println("D: " + depth + " , H: " + horizontal + " , Total: " + depth * horizontal);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
