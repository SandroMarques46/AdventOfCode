package sandromarques.advent.year2022;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Day3 {
    public static void main(String[] args) {
        Day3 day = new Day3();
        System.out.println(day.part1(FileManager.getFile(3, 2022))); //correct answer : 8298
        System.out.println(day.part2(FileManager.getFile(3, 2022))); //correct answer : 2708
    }

    private int part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            //Setup priorities points
            HashMap<Character, Integer> points = getPointsMap();

            int sum = 0;
            for (String line : lines) {
                String part1 = line.substring(0, line.length() / 2);
                String part2 = line.substring(line.length() / 2);

                ArrayList<Character> list1 = new ArrayList<>();
                ArrayList<Character> list2 = new ArrayList<>();
                for (int i = 0; i < part1.length(); i++) {
                    char c1 = part1.charAt(i);
                    char c2 = part2.charAt(i);
                    if (c1 == c2 || list2.contains(c1)) {
                        sum += points.get(c1);
                        break;
                    } else if (list1.contains(c2)) {
                        sum += points.get(c2);
                        break;
                    } else {
                        list1.add(c1);
                        list2.add(c2);
                    }
                }
            }

            return sum;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private HashMap<Character, Integer> getPointsMap() {
        HashMap<Character, Integer> points = new HashMap<>();
        char ch = 'a';
        int p = 1;
        while (ch <= 'z') {
            points.put(ch, p++);
            ch += 1;
        }
        ch = 'A';
        while (ch <= 'Z') {
            points.put(ch, p++);
            ch += 1;
        }
        return points;
    }

    private int part2(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            //Setup priorities points
            HashMap<Character, Integer> points = getPointsMap();

            int sum = 0;
            for (int i = 0; i < lines.size(); i += 3) {
                String line1 = lines.get(i);
                String line2 = lines.get(i + 1);
                String line3 = lines.get(i + 2);

                HashMap<Character, Integer> map1 = getCharOccurrencesMap(line1);
                HashMap<Character, Integer> map2 = getCharOccurrencesMap(line2);
                HashMap<Character, Integer> map3 = getCharOccurrencesMap(line3);

                for (char c : map1.keySet()) {
                    if (map2.containsKey(c) && map3.containsKey(c)) {
                        sum += points.get(c);
                    }
                }
            }

            return sum;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private HashMap<Character, Integer> getCharOccurrencesMap(String string) {
        HashMap<Character, Integer> map = new HashMap<>();

        char[] stringChars = string.toCharArray();

        for (char c : stringChars) {
            int occurrences = map.getOrDefault(c, 0);
            map.put(c, occurrences + 1);
        }

        return map;
    }

}
