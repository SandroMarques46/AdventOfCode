package sandromarques.advent.year2021;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Day18 {

    public static void main(String[] args) {
        Day18 day18 = new Day18();
        day18.part1(FileManager.getFile(18, 2021));
    }

    private void part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            ArrayList<String> lines = new ArrayList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            for (String line : lines) {
                int start = -1;
                StringBuilder explodePair = new StringBuilder();
                char[] charArray = line.toCharArray();
                Stack<Integer> stack = new Stack<>();
                for (int i = 0; i < charArray.length; i++) {
                    Character c = charArray[i];
                    if (c == '[') {
                        stack.push(i);
                    }
                    if (stack.size() == 5) {
                        if (start == -1) start = i;
                        explodePair.append(c);
                    }
                    if (c == ']') {
                        if(stack.size() == 5){
                            stack.pop();
                            String exp = explodePair.toString().replace("[", "").replace("]", "");
                            int l = Integer.parseInt(exp.split(",")[0]); //left pair number
                            int r = Integer.parseInt(exp.split(",")[1]); //right pair number
                            int left = findLeft(line, start);
                            //create new Pair with the correct additions
                            StringBuilder newPair = new StringBuilder();
                            newPair.append("[");
                            if (left == 0) newPair.append(0);
                            else newPair.append(l + left);
                            newPair.append(",");
                            int right = findRight(line, i);
                            if (right == 0) newPair.append(0);
                            else newPair.append(r + right);
                            newPair.append("]");
                            //replace the old stuff with the new pair
                            System.out.println(line);
                            line = line.replace(getPair(line, stack.pop()), newPair.toString());
                            System.out.println(line);
                            i = 0;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPair(String line, Integer from) {
        int end = from;
        int numOfPairs = 0;
        for (; end < line.length(); end++) {
            if (line.charAt(end) == '[') {
                numOfPairs++;
            }
            if (line.charAt(end) == ']') {
                numOfPairs--;
                if (numOfPairs == 0) break;
            }
        }
        return line.substring(from, end + 1);
    }

    private int findRight(String line, int start) {
        int i = start + 1;
        for (; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == ']') break;
        }
        if (i == start + 1) return 0;
        else return Integer.parseInt(line.substring(start + 2, i)); //removes the comma
    }

    private int findLeft(String line, int start) {
        int i = start - 1;
        for (; i > 0; i--) {
            char c = line.charAt(i);
            if (c == '[') break;
        }
        if (i == start - 1) return 0;
        else return Integer.parseInt(line.substring(i + 1, start - 1)); //removes the comma
    }
}
