package sandromarques.advent.year2020;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Day14 {
    private class Pair {
        public final String mask;
        public final LinkedList<String> values;

        public Pair(String mask, LinkedList<String> values) {
            this.mask = mask.split(" = ")[1];
            this.values = values;
        }
    }

    public static void main(String[] args) {
        Day14 day = new Day14();
        day.part1(FileManager.getFile(14, 2020)); //correct answer : 17765746710228
        day.part2(FileManager.getFile(14, 2020)); //correct answer : 4401465949086
    }

    private void part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            LinkedList<Pair> pairs = new LinkedList<>();
            LinkedList<String> curr = new LinkedList<>();
            String currMask = "";
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
                if (currLine.startsWith("mask")) {
                    if (!curr.isEmpty()) {
                        pairs.add(new Pair(currMask, curr));
                    }
                    currMask = currLine;
                    curr = new LinkedList<>();
                } else {
                    curr.add(currLine);
                }
            }
            pairs.add(new Pair(currMask, curr));
            HashMap<Integer, String> map = new HashMap<>();

            for (Pair pair : pairs) {
                for (String l : pair.values) {
                    String mask = pair.mask;
                    String substring = l.substring(l.indexOf("=") + 2);
                    int memAddress = Integer.parseInt(l.substring(4, l.indexOf("]")));
                    int memValue = Integer.parseInt(substring);
                    String num = String.format("%36s", Integer.toBinaryString(memValue)).replace(' ', '0');
                    num = applyMask(num, mask);
                    map.put(memAddress, num);
                }
            }
            long sum = 0;
            for (String i : map.values()) {
                //binary to decimal :
                sum += Long.parseLong(i, 2);
            }
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void part2(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            LinkedList<Pair> pairs = new LinkedList<>();
            LinkedList<String> curr = new LinkedList<>();
            String currMask = "";
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
                if (currLine.startsWith("mask")) {
                    if (!curr.isEmpty()) {
                        pairs.add(new Pair(currMask, curr));
                    }
                    currMask = currLine;
                    curr = new LinkedList<>();
                } else {
                    curr.add(currLine);
                }
            }
            pairs.add(new Pair(currMask, curr));
            HashMap<String, Integer> hmap = new HashMap<>();
            for (Pair pair : pairs) {
                for (String l : pair.values) {
                    String mask = pair.mask;
                    String memAddress = String.format("%36s", Integer.toBinaryString(Integer.parseInt(l.substring(4, l.indexOf("]"))))).replace(' ', '0');
                    int num = Integer.parseInt(l.substring(l.indexOf("=") + 2));
                    ArrayList<String> addresses = applyMaskPart2(memAddress, mask, 0);
                    for (String address : addresses) {
                        hmap.put(address, num);
                    }
                }
            }
            long sum = 0;
            for (int i : hmap.values()) {
                sum += i;
            }
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String applyMask(String num, String mask) {
        char[] numArr = num.toCharArray();
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) != 'X') {
                numArr[i] = mask.charAt(i);
            }
        }
        return new String(numArr);
    }

    ArrayList<String> applyMaskPart2(String address, String mask, int index) {
        if (index == address.length()) {
            return new ArrayList<>(Collections.singleton(address));
        }
        if (mask.charAt(index) == '1') {
            //change only one character at "index" position on "address" string
            char[] arr = address.toCharArray();
            arr[index] = mask.charAt(index);
            return applyMaskPart2(new String(arr), mask, index + 1);
        } else if (mask.charAt(index) == 'X') {
            char[] arr = address.toCharArray();
            arr[index] = '1';
            ArrayList<String> one = applyMaskPart2(new String(arr), mask, index + 1);
            arr[index] = '0';
            ArrayList<String> two = applyMaskPart2(new String(arr), mask, index + 1);
            one.addAll(two);
            return one;
        }
        return applyMaskPart2(address, mask, index + 1);
    }
}
