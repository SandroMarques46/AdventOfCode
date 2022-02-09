package sandromarques.advent.year2020;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Day6 {

    public static void main(String[] args) {
        Day6 day = new Day6();
        day.parts(FileManager.getFile(6, 2020),true); //correct answer : 6878
        day.parts(FileManager.getFile(6, 2020),false); //correct answer : 3464
    }

    private void parts(File file, boolean part1) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            lines.add("");
            int total = 0;
            HashMap<Character, Integer> map = new HashMap<>();
            int numPeopleByGroup = 0; //part2
            for (String line : lines) {
                if (line.isBlank() || line.isEmpty()) {
                    if(part1){
                        total += map.size();
                    } else { //part2
                        for (Character c : map.keySet()) {
                            if (map.get(c) == numPeopleByGroup) {
                                total++;
                            }
                        }
                        numPeopleByGroup = 0;
                    }
                    map.clear();
                } else {
                    numPeopleByGroup++; //part2
                    for (Character c : line.toCharArray()) {
                        int x = map.getOrDefault(c, 0);
                        map.put(c, x + 1);
                    }
                }
            }
            System.out.println(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
