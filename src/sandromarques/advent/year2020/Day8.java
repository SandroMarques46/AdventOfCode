package sandromarques.advent.year2020;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Day8 {

    public static void main(String[] args) {
        Day8 day = new Day8();
        day.part1(FileManager.getFile(8, 2020)); //correct answer : 2014
        day.part2(FileManager.getFile(8, 2020)); //correct answer : 2251
    }

    private void part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            System.out.println(calculateAccumulator(lines));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int calculateAccumulator(LinkedList<String> lines) {
        int count = 0, idx = 0;
        boolean[] visited = new boolean[lines.size()];
        //first condition is only for part2 (checks if it already passed through all instructions/lines)
        while (idx < lines.size() && !visited[idx]) {
            String line = lines.get(idx);
            String[] split = line.split(" ");
            if (split[0].equals("acc")) {
                count += Integer.parseInt(split[1]);
            } else if (split[0].equals("jmp")) {
                idx += Integer.parseInt(split[1]);
            }
            if (!split[0].equals("jmp")) {
                visited[idx] = true;
                idx++;
            }
        }
        return count;
    }

    private void part2(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            for (int idx = 0; idx < lines.size(); idx++) {
                String line = lines.get(idx);
                String s = line.split(" ")[0];
                if (!s.equals("acc")) {
                    LinkedList<String> newLines = new LinkedList<>(lines);
                    String oldLine = newLines.get(idx);
                    if (s.equals("nop")) {
                        newLines.set(idx, oldLine.replace("nop", "jmp"));
                    } else if (s.equals("jmp")) {
                        newLines.set(idx, oldLine.replace("jmp", "nop"));
                    }
                    if (!checkInfinite(newLines)) {
                        //System.out.println("Changed line \"" + oldLine + "\" to \"" + newLines.get(idx) + "\"");
                        System.out.println(calculateAccumulator(newLines));
                        return;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkInfinite(LinkedList<String> lines) {
        int idx = 0,instructionsCounter = 0;
        //idx must reach the end of the lines
        while (idx < lines.size()) {
            String line = lines.get(idx);
            String[] split = line.split(" ");
            if (split[0].equals("jmp")) {
                idx += Integer.parseInt(split[1]);
            } else {
                idx++;
            }
            if (instructionsCounter > lines.size()) return true;
            instructionsCounter++;
        }
        return instructionsCounter > lines.size();
    }
}
