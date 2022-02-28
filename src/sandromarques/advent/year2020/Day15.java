package sandromarques.advent.year2020;

import java.util.HashMap;

public class Day15 {

    public static void main(String[] args) {
        Day15 day = new Day15();
        day.part1(2020); //correct answer : 234
        day.part1(30000000); //correct answer : 8984 (takes 2/3 seconds to generate)
    }

    private void part1(int findTh) {
        String input = "0,13,1,16,6,17";
        HashMap<Integer, int[]> map = new HashMap<>();
        String[] split = input.split(",");
        int lastSpoken = -1;
        for (int i = 0; i < split.length; i++) {
            String curr = split[i];
            int[] arr = {i + 1, 0};
            int val = Integer.parseInt(curr);
            map.put(val, arr);
            lastSpoken = val;
        }
        int currRound = split.length + 1;
        while (true) {
            int[] lastList = map.get(lastSpoken);
            int num;
            if (lastList[1] == 0) { //was only spoken once
                num = 0;
            } else {
                num = lastList[1] - lastList[0];
            }
            addCurrentRound(map, currRound, num);
            lastSpoken = num;
            if (currRound == findTh) {
                System.out.println(lastSpoken);
                break;
            }
            currRound++;
        }
    }

    private void addCurrentRound(HashMap<Integer, int[]> map, int currRound, int num) {
        int[] array = map.get(num);
        if (array == null) {
            int[] arr = {currRound,0};
            map.put(num, arr);
        } else {
            if (array[1] != 0) { //overwrites the oldest round
                array[0] = array[1];
            }
            //puts the current round as the last item on the array
            array[1] = currRound;
            //no need to do map.put(num, array) because the reference to the array is the same
        }
    }
}
