package sandromarques.advent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Day1 {

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        day1.part1(FileManager.getFile(1,1)); //correct answer : 1288
        day1.part2(FileManager.getFile(1,2)); //correct answer : 1311
    }

    private void part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            LinkedList<Integer> nums = new LinkedList<>();
            while ((line = br.readLine()) != null) {
                nums.add(Integer.parseInt(line));
            }
            System.out.println(numOfInc(nums));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void part2(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            LinkedList<Integer> sums = new LinkedList<>();
            LinkedList<Integer> A_list = new LinkedList<>();
            LinkedList<Integer> B_list = new LinkedList<>();
            LinkedList<Integer> C_list = new LinkedList<>();
            int count = 0;
            while ((line = br.readLine()) != null) {
                int num = Integer.parseInt(line);

                A_list.add(num);
                if (count != 0) {
                    B_list.add(num);
                    if (count != 1) {
                        C_list.add(num);
                    }
                }
                //code above works the same as :
                /*
                if (count == 0) {
                    A_list.add(num);
                } else if (count == 1) {
                    A_list.add(num);
                    B_list.add(num);
                } else {
                    A_list.add(num);
                    B_list.add(num);
                    C_list.add(num);
                }
                 */
                if (A_list.size() == 3) {
                    int sum = 0;
                    for (Integer i : A_list) {
                        sum += i;
                    }
                    sums.add(sum);
                    A_list.clear();
                }
                if (B_list.size() == 3) {
                    int sum = 0;
                    for (Integer i : B_list) {
                        sum += i;
                    }
                    sums.add(sum);
                    B_list.clear();
                }
                if (C_list.size() == 3) {
                    int sum = 0;
                    for (Integer i : C_list) {
                        sum += i;
                    }
                    sums.add(sum);
                    C_list.clear();
                }
                count++;
            }
            System.out.println(numOfInc(sums));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int numOfInc(LinkedList<Integer> nums) {
        int inc = 0;
        for (int i = 1; i < nums.size(); i++) {
            int currNum = nums.get(i);
            int lastNum = nums.get(i - 1);
            if (currNum - lastNum > 0) inc++;
        }
        return inc;
    }
}
