package sandromarques.advent;

import java.io.*;
import java.util.*;

public class Day14 {
    private static class Pair {
        private final char first;
        private final char second;
        private final char replace;

        public Pair(char first, char second, char replace) {
            this.first = first;
            this.second = second;
            this.replace = replace;
        }

        public String getPairString() {
            return "" + first + second;
        }
    }

    private static class PairC {
        private final Long num;
        private final char c;

        public PairC(Long num, char c) {
            this.num = num;
            this.c = c;
        }

        public PairC(Long num) {
            this.num = num;
            this.c = '0';
        }
    }

    public static void main(String[] args) {
        Day14 day14 = new Day14();
        day14.part1(FileManager.getFile(14)); // K - H = 3587
        day14.part2(FileManager.getFile(14)); // K - N = 3906445077999
    }

    private void part1(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            ArrayList<Pair> pairs = new ArrayList<>();
            String polymer = br.readLine();
            br.readLine(); //empty line (2º line)
            String currLine;
            while ((currLine = br.readLine()) != null) {
                String[] split = currLine.split(" -> ");
                pairs.add(new Pair(split[0].charAt(0), split[0].charAt(1), split[1].charAt(0)));
            }
            LinkedList<Character> characters = new LinkedList<>();
            for (int i = 0; i < polymer.length(); i++) {
                characters.add(polymer.charAt(i));
            }

            LinkedList<PairC> aux1 = new LinkedList<>();
            LinkedList<Integer> toSort = new LinkedList<>();

            int steps = 10;
            for (int i = 1; i <= steps; i++) {
                aux1.clear();
                toSort.clear();
                for (Pair p : pairs) {
                    for (int j = 0; j < characters.size() - 1; j++) {
                        if (p.first == characters.get(j) && p.second == characters.get(j + 1)) {
                            aux1.add(new PairC((long) (j + 1), p.replace));
                            toSort.add(j + 1);
                        }
                    }
                }
                Collections.sort(toSort);
                for (int j = 0; j < toSort.size(); j++) {
                    int curr = toSort.get(j);
                    for (PairC p : aux1) {
                        if (p.num == curr) {
                            characters.add(curr + j, p.c);
                        }
                    }
                }
            }
            HashMap<Character, Integer> count = new HashMap<>();
            for (Character c : characters) {
                int x = count.getOrDefault(c, 0) + 1;
                count.put(c, x);
            }
            PairC min = new PairC(Long.MAX_VALUE);
            PairC max = new PairC(Long.MIN_VALUE);
            for (Character c : count.keySet()) {
                long x = count.get(c);
                if (x < min.num) min = new PairC(x, c);
                if (x > max.num) max = new PairC(x, c);
            }
            //System.out.println(max.c + " : " + max.num + "\n" + min.c + " : " + min.num);
            System.out.println(max.num - min.num);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void part2(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            ArrayList<Pair> pairs = new ArrayList<>();
            String polymer = br.readLine();
            br.readLine(); //empty line (2º line)
            String currLine;
            while ((currLine = br.readLine()) != null) {
                String[] split = currLine.split(" -> ");
                pairs.add(new Pair(split[0].charAt(0), split[0].charAt(1), split[1].charAt(0)));
            }
            HashMap<String, Long> pairCharsMap = new HashMap<>();
            for (int i = 1; i < polymer.length(); i++) {
                String str2 = "" + polymer.charAt(i - 1) + polymer.charAt(i);
                long prev = pairCharsMap.getOrDefault(str2, 0L);
                pairCharsMap.put(str2, prev + 1);
            }

            int steps = 40;
            for (int i = 1; i <= steps; i++) {
                HashMap<String, Long> newMap = new HashMap<>();
                for (Pair p : pairs) {
                    String pairString = p.getPairString();
                    if (pairCharsMap.containsKey(pairString)) {
                        long occurrences = pairCharsMap.get(pairString);
                        String str1 = "" + p.first + p.replace;
                        long prev1 = newMap.getOrDefault(str1, 0L);
                        newMap.put(str1, prev1 + occurrences);
                        String str2 = "" + p.replace + p.second;
                        long prev2 = newMap.getOrDefault(str2, 0L);
                        newMap.put(str2, prev2 + occurrences);
                    }
                }
                pairCharsMap = newMap;
                long length = 0;
                for (Long lng : pairCharsMap.values()) {
                    length += lng;
                }
                length = (length) + 1;
            }
            HashMap<Character, Long> count = new HashMap<>();
            for (String str : pairCharsMap.keySet()) {
                long occurrences = pairCharsMap.get(str);
                char first = str.charAt(0);
                char second = str.charAt(1);
                long aux1 = count.getOrDefault(first, 0L);
                count.put(first, aux1 + occurrences);
                if(first!=second) {
                    long aux2 = count.getOrDefault(second, 0L);
                    count.put(second, aux2 + 1);
                }
            }
            PairC min = new PairC(Long.MAX_VALUE);
            PairC max = new PairC(Long.MIN_VALUE);
            for (Character c : count.keySet()) {
                Long x = count.get(c);
                if (x < min.num) min = new PairC(x, c);
                if (x > max.num) max = new PairC(x, c);
            }
            System.out.println(max.num - min.num);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
