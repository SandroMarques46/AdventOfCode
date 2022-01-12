package sandromarques.advent.year2020;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public class Day1 {

    public static void main(String[] args) {
        Day1 day = new Day1();
        day.parts(FileManager.getFile(1, 2020), true); //correct answer : 157059
        day.parts(FileManager.getFile(1, 2020), false); //correct answer : 165080960
    }

    private void parts(File file, boolean part1) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currLine;
            LinkedList<Integer> list = new LinkedList<>();
            while ((currLine = br.readLine()) != null) {
                list.add(Integer.parseInt(currLine));
            }
            Collections.sort(list);
            if (part1) {
                String pair = find2num(list);
                int n1 = Integer.parseInt(pair.split(",")[0]);
                int n2 = Integer.parseInt(pair.split(",")[1]);
                System.out.println(n1 * n2);
            } else {
                String pair = find3num(list);
                int n1 = Integer.parseInt(pair.split(",")[0]);
                int n2 = Integer.parseInt(pair.split(",")[1]);
                int n3 = Integer.parseInt(pair.split(",")[2]);
                System.out.println(n1 * n2 * n3);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String find3num(LinkedList<Integer> list) {
        int i1 = 0;
        int im = 1; //idx middle , will always be between i1 and i2 , exclusive
        int i2 = list.size() - 1;
        while (true) {
            int value = list.get(i1) + list.get(im) + list.get(i2);
            if (value == 2020) return list.get(i1) + "," + list.get(im) + "," + list.get(i2);
            else if (value > 2020) {
                i2--;
            } else if (value < 2020) {
                if (im == i2 - 1) {
                    i1++;
                    im = i1 + 1;
                } else {
                    im++;
                }
            }
        }
    }

    private String find2num(LinkedList<Integer> list) {
        int i1 = 0;
        int i2 = list.size() - 1;
        while (true) {
            int value = list.get(i1) + list.get(i2);
            if (value == 2020) return list.get(i1) + "," + list.get(i2);
            else if (value > 2020) i2--;
            else if (value < 2020) i1++;
        }
    }
}
