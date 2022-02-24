package sandromarques.advent.year2020;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public class Day9 {

    public static void main(String[] args) {
        Day9 day = new Day9();
        //day.part1(FileManager.getFile(9, 2020)); //correct answer : 14360655
        day.part2(FileManager.getFile(9, 2020)); //correct answer :
    }

    private void part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            long[] nums = new long[lines.size()];
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                nums[i] = Long.parseLong(line);
            }
            int preamble = 25;
            for (int i = preamble; i < nums.length; i++) {
                if (!checkIfHasSum(i, preamble, nums)) {
                    System.out.println("First number to not have a sum : " + nums[i] + " (index " + i + ")");
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void part2(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            long[] nums = new long[lines.size()];
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                nums[i] = Long.parseLong(line);
            }
            int preamble = 25;
            //we already know this from part1
            long toFind = nums[501]; //nums[501] 14360655
            //
            int head = 0;
            int tail = head + preamble;
            int count = 0;
            int idx = head; //idx is always between head and tail , inclusive
            while (tail < nums.length) {
                while (count < toFind && idx < tail) {
                    count += nums[idx++];
                }
                int numbersAdded = idx - head;
                if (count == toFind && numbersAdded > 2) { // find a contiguous set of AT LEAST TWO NUMBERS
                    LinkedList<Long> listSort = new LinkedList<>();
                    for (int i = head; i < idx; i++) {
                        listSort.add(nums[i]);
                    }
                    Collections.sort(listSort);
                    System.out.println(listSort.getFirst() + listSort.getLast());
                }
                count -= nums[head];
                head++;
                tail++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkIfHasSum(int end, int preamble, long[] nums) {
        long toFind = nums[end];
        int start = end - preamble;
        for (int i = start; i < end; i++)
            for (int j = start; j < end; j++)
                if (i != j)
                    if (nums[i] + nums[j] == toFind)
                        return true;
        return false;
    }
}
