package sandromarques.advent.year2021;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class Day10 {
    public static void main(String[] args) {
        Day10 day10 = new Day10();
        day10.part1(FileManager.getFile(10, 2021)); //315693
        day10.part2(FileManager.getFile(10, 2021)); //1870887234
    }

    private void part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currLine;
            ArrayList<String> lines = new ArrayList<>();
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            HashMap<Character, Character> map = new HashMap<>();
            map.put('(', ')');
            map.put('[', ']');
            map.put('{', '}');
            map.put('<', '>');

            HashMap<Character, Integer> points = new HashMap<>();
            points.put(')', 3);
            points.put(']', 57);
            points.put('}', 1197);
            points.put('>', 25137);

            ArrayList<Character> wrong = aux1(lines, map);
            int totalPoints = 0;
            for (Character c : wrong) {
                totalPoints += points.get(c);
            }
            System.out.println(totalPoints);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Character> aux1(ArrayList<String> lines, HashMap<Character, Character> map) {
        ArrayList<Character> wrong = new ArrayList<>();
        for (String line : lines) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (map.containsKey(c)) {
                    stack.push(c);
                } else {
                    if (map.get(stack.pop()) != c) {
                        wrong.add(c);
                        break;
                    }
                }
            }

        }
        return wrong;
    }

    private ArrayList<ArrayList<Character>> aux2(ArrayList<String> lines, HashMap<Character, Character> map) {
        ArrayList<ArrayList<Character>> wrongAll = new ArrayList<>();
        for (String line : lines) {
            ArrayList<Character> wrongPerLine = new ArrayList<>();
            Stack<Character> stack = new Stack<>();
            boolean abort = false;
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (map.containsKey(c)) {
                    stack.push(c);
                } else {
                    if (map.get(stack.peek()) == c) {
                        stack.pop();
                    } else {
                        abort = true;
                    }
                }
            }
            if (!abort) {
                //line is imcomplete
                while (!stack.isEmpty()) {
                    wrongPerLine.add(map.get(stack.pop()));
                }
                wrongAll.add(wrongPerLine);
            }
        }
        return wrongAll;
    }

    private void part2(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currLine;
            ArrayList<String> lines = new ArrayList<>();
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            HashMap<Character, Character> map = new HashMap<>();
            map.put('(', ')');
            map.put('[', ']');
            map.put('{', '}');
            map.put('<', '>');

            HashMap<Character, Integer> points = new HashMap<>();
            points.put(')', 1);
            points.put(']', 2);
            points.put('}', 3);
            points.put('>', 4);

            ArrayList<ArrayList<Character>> wrong = aux2(lines, map);
            ArrayList<Long> pointsPerLine = new ArrayList<>();
            for (ArrayList<Character> list : wrong) {
                long totalPoints = 0;
                for (Character c : list) {
                    totalPoints = (totalPoints * 5) + points.get(c);
                }
                pointsPerLine.add(totalPoints);
            }
            Collections.sort(pointsPerLine);
            System.out.println("" + pointsPerLine.get(pointsPerLine.size() / 2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
