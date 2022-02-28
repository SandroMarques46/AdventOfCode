package sandromarques.advent.year2020;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day13 {

    public static void main(String[] args) {
        Day13 day = new Day13();
        day.part1(FileManager.getFile(13, 2020)); //correct answer : 4135
        day.part2(FileManager.getFile(13, 2020)); //correct answer : 640856202464541
    }

    private void part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            int earliest_timestamp = Integer.parseInt(br.readLine());
            String line = br.readLine();
            String[] split = line.split(",");
            LinkedList<Integer> busIds = new LinkedList<>();
            for (String str : split) {
                try {
                    int num = Integer.parseInt(str);
                    busIds.add(num);
                } catch (Exception e) {
                    //is "x" = ignore for now
                }
            }
            //find first bus
            //Key -> Bus ID , Value -> First time since earliest timestamp
            int earliestTime = Integer.MAX_VALUE;
            int earliestBusId = -1;
            HashMap<Integer, Integer> map = new HashMap<>();
            for (Integer busID : busIds) {
                int time = earliest_timestamp;
                while (time % busID != 0) {
                    time++;
                }
                map.put(busID, time);
                if (time < earliestTime) {
                    earliestTime = time;
                    earliestBusId = busID;
                }
            }
            //System.out.println("Earliest time = " + earliestTime);
            System.out.println((earliestTime - earliest_timestamp) * earliestBusId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void part2(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            long time = Integer.parseInt(br.readLine());
            String buses = br.readLine();
            ArrayList<Long> ids = new ArrayList<>();
            for (String id : buses.split(",")) {
                ids.add(id.equals("x") ? -1 : Long.parseLong(id));
            }

            long lcm = -1; // least common multiple
            int increment = 0;
            while (true) {
                final long id = ids.get(increment);
                if (id == -1) { // is "x"
                    increment++;
                    continue;
                }

                if (lcm == -1) { //is first step
                    lcm = id;
                    time = id;
                    increment++;
                    continue;
                }

                if ((time + increment) % id == 0) {
                    if (++increment >= ids.size()) {
                        System.out.println(time);
                        return;
                    }
                    lcm *= id;
                    continue;
                }
                time += lcm;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}