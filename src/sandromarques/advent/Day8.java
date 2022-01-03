package sandromarques.advent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day8 {
    private class Pair {
        private final LinkedList<Result> possibleResults;
        private final int idx;

        public Pair(int idx, LinkedList<Result> possibleResults) {
            this.idx = idx;
            this.possibleResults = possibleResults;
        }
    }

    private final String zero = "abcefg";
    private final String one = "cf"; //direct
    private final String two = "acdeg";
    private final String three = "acdfg";
    private final String four = "bcdf"; //direct
    private final String five = "abdfg";
    private final String six = "abdefg";
    private final String seven = "acf"; //direct
    private final String eight = "abcdefg"; //direct
    private final String nine = "abcdfg";

    private final String[] letters = {zero, one, two, three, four, five, six, seven, eight, nine};

    public static void main(String[] args) {
        Day8 day8 = new Day8();
        //day8.part2(FileManager.getFile(8));
    }

    private void part2(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currLine;
            while ((currLine = br.readLine()) != null) {
                String[] parts = currLine.split(" \\| ");
                List<String> list = Arrays.asList(parts[0].split(" "));
                LinkedList<String> sequence = new LinkedList<>(list);
                HashMap<Character, LinkedList<Character>> map = new HashMap<>();
                int[] numbers = new int[sequence.size()];
                firstCycle(sequence, map, numbers);
                LinkedList<Pair> lists = new LinkedList<>();
                for (int i = 0; i < sequence.size(); i++) {
                    String seq = sequence.get(i);
                    int length = seq.length();
                    if (length != 2 && length != 3 && length != 4 && length != 7) {
                        lists.add(new Pair(i, numberFromSequence(map, seq)));
                    }
                }
                findCorrectCombination(numbers, lists);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findCorrectCombination(int[] numbers, LinkedList<Pair> lists) {
        Pair p = lists.getFirst();
        for (int i = 0; i < p.possibleResults.size(); i++) {
            char[] currComb = p.possibleResults.get(i).comb;
            for (int j = 1; j < lists.size(); j++) {
                Pair curr = lists.get(j);
            }
        }
    }

    public LinkedList<Result> numberFromSequence(HashMap<Character, LinkedList<Character>> map, String seq) {
        LinkedList<Result> possibleNumbers = new LinkedList<>();
        char[][] combinations = getCombinations(map, seq);
        for (char[] comb : combinations) {
            for (int k = 0, lettersLength = letters.length; k < lettersLength; k++) {
                String numberSeq = letters[k];
                char[] numberChars = numberSeq.toCharArray();
                if (comb.length == numberChars.length) {
                    boolean found = false;
                    for (char c : comb) {
                        found = false;
                        for (char numberChar : numberChars) {
                            if (c != numberChar) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            break;
                        }
                    }
                    if (found) {
                        boolean alreadyHas = false;
                        for (Result r : possibleNumbers) {
                            if (r.num == k) {
                                alreadyHas = true;
                                break;
                            }
                        }
                        if (!alreadyHas) possibleNumbers.add(new Result(k, comb));
                    }
                }
            }
        }
        return possibleNumbers;
    }

    private void firstCycle(LinkedList<String> sequence, HashMap<Character, LinkedList<Character>> map, int[] numbers) {
        for (int idx = 0; idx < sequence.size(); idx++) {
            String seq = sequence.get(idx);
            switch (seq.length()) {
                case 2 -> {
                    aux(map, seq, one);
                    numbers[idx] = 1;
                }
                case 3 -> {
                    aux(map, seq, seven);
                    numbers[idx] = 7;
                }
                case 4 -> {
                    aux(map, seq, four);
                    numbers[idx] = 4;
                }
                case 7 -> {
                    aux(map, seq, eight);
                    numbers[idx] = 8;
                }
            }
        }
    }

    public char[][] getCombinations(HashMap<Character, LinkedList<Character>> map, String seq) {
        int totalCombinations = 1;
        for (Character c : seq.toCharArray()) {
            totalCombinations *= map.get(c).size();
        }
        char[][] combinations = new char[totalCombinations][seq.length()];
        int idx = 0;
        int lastAux = 1;
        for (Character c : seq.toCharArray()) {
            int currCombination = 0;
            LinkedList<Character> list = map.get(c);
            while (currCombination < totalCombinations) {
                for (Character character : list) {
                    for (int i = 0; i < totalCombinations / (list.size() * lastAux); i++) {
                        combinations[currCombination++][idx] = character;
                    }
                }
            }
            lastAux *= list.size();
            idx++;
        }
        //remove useless combinatiosn (duplicate chars bla bla)
        ArrayList<char[]> filteredCombinations = new ArrayList<>();
        for (char[] comb : combinations) {
            ArrayList<Character> occurrences = new ArrayList<>();
            boolean valid = true;
            for (Character c : comb) {
                if (occurrences.contains(c)) {
                    valid = false;
                    break;
                } else occurrences.add(c);
            }
            if (valid) {
                filteredCombinations.add(comb);
            }
        }
        char[][] filteredCombs = new char[filteredCombinations.size()][seq.length()];
        for (int i = 0; i < filteredCombinations.size(); i++) {
            filteredCombs[i] = filteredCombinations.get(i);
        }
        return filteredCombs;
    }

    private void aux(HashMap<Character, LinkedList<Character>> map, String seq, String numberSeq) {
        for (int j = 0; j < seq.length(); j++) {
            char currChar = seq.charAt(j);
            LinkedList<Character> list;
            if (map.containsKey(currChar)) {
                list = map.get(currChar);
                LinkedList<Character> aux = new LinkedList<>();
                for (char c : numberSeq.toCharArray()) {
                    if (list.contains(c)) {
                        aux.add(c);
                    }
                }
                list = aux;
            } else {
                list = new LinkedList<>();
                for (char c : numberSeq.toCharArray()) {
                    list.add(c);
                }
            }
            map.put(currChar, list);
        }
    }

    private class Result {
        private final int num;
        private final char[] comb;

        public Result(int num, char[] comb) {
            this.num = num;
            this.comb = comb;
        }
    }
}
