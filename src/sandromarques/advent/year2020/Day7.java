package sandromarques.advent.year2020;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Day7 {

    public static void main(String[] args) {
        Day7 day = new Day7();
        day.part1(FileManager.getFile(7, 2020)); //correct answer :
    }

    private void part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            HashMap<String, LinkedList<Pair>> map = new HashMap<>();
            for (String line : lines) {
                String[] split = line.split(" bags contain ");
                String parentColor = split[0];
                String childs = split[1];
                LinkedList<Pair> childColors = new LinkedList<>();
                String[] strings = childs.split(",");
                for (String child : strings) {
                    String[] split2 = child.trim().split(" ");
                    int number;
                    if (split2[0].equals("no")) {
                        number = 0;
                    } else {
                        number = Integer.parseInt(split2[0]);
                    }
                    String childColor = split2[1] + " " + split2[2];
                    childColors.add(new Pair(number, childColor));
                    //split[3] == bags/bag
                }
                map.put(parentColor, childColors);
            }
            LinkedList<String> totalColors = new LinkedList<>();
            LinkedList<String> possibleColors = new LinkedList<>(Collections.singleton("shiny gold"));
            LinkedList<String> newPossible = new LinkedList<>();
            do {
                for (String pc : possibleColors) {
                    for (String key : map.keySet()) {
                        LinkedList<Pair> childColors = map.get(key);
                        Pair found = null;
                        for (Pair p : childColors) {
                            if (p.childColor.equals(pc)) {
                                found = p;
                                break;
                            }
                        }
                        if (found != null) {
                            //add parent color
                            if (!newPossible.contains(key)) {
                                newPossible.add(key);
                            }
                        }
                    }
                }
                for (String color : newPossible) {
                    if (!totalColors.contains(color)) {
                        totalColors.add(color);
                    }
                }
                possibleColors = new LinkedList<>(newPossible);
                newPossible.clear();
            } while (possibleColors.size() != 0);
            int lastSize = 0;
            System.out.println(totalColors.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //why does the example give "126" ?
    /*
    private void part2(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            HashMap<String, LinkedList<Pair>> map = new HashMap<>();
            for (String line : lines) {
                String[] split = line.split(" bags contain ");
                String parentColor = split[0];
                String childs = split[1];
                LinkedList<Pair> childColors = new LinkedList<>();
                String[] strings = childs.split(",");
                for (String child : strings) {
                    String[] split2 = child.trim().split(" ");
                    int number;
                    if (split2[0].equals("no")) {
                        number = 0;
                    } else {
                        number = Integer.parseInt(split2[0]);
                    }
                    String childColor = split2[1] + " " + split2[2];
                    childColors.add(new Pair(number, childColor));
                    //split[3] == bags/bag
                }
                map.put(parentColor, childColors);
            }
            LinkedList<String> totalColors = new LinkedList<>();
            LinkedList<Pair> oldList = new LinkedList<>(map.get("shiny gold"));
            //LinkedList<Pair> newList = new LinkedList<Pair>(map.get("shiny gold"));
            int count = 1;
            while (!oldList.isEmpty()) {
                for (Pair p : oldList) {

                }
            }
        } catch (IOException e) {
             e.printStackTrace();
        }
   }
*/

    private static class Pair {
        private final int number;
        private final String childColor;

        public Pair(int number, String childColor) {
            this.number = number;
            this.childColor = childColor;
        }
    }
}
