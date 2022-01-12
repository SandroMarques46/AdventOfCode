package sandromarques.advent.year2021;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Day4 {
    private LinkedList<int[][]> bingoCards;
    private String[] sequence;

    public static void main(String[] args) {
        Day4 day4 = new Day4();
        day4.part1(FileManager.getFile(4, 2021), true); //correct answer : 60368
        day4.part1(FileManager.getFile(4, 2021), false); //correct answer : 17435
    }

    //find board who wins first
    private void duplicateCode(BufferedReader br) throws IOException {
        String line;
        LinkedList<String> lines = new LinkedList<>();
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        this.sequence = lines.getFirst().split(",");
        this.bingoCards = new LinkedList<>();
        //read bingo cards
        int idx = 2;
        do {
            int bingoIdx = 0;
            int i = idx;
            int[][] bingoCard = new int[5][5];
            for (; i < idx + 5; i++) {
                String[] split = lines.get(i).trim().replaceAll(" {2}", " ").split(" ");
                for (int j = 0; j < split.length; j++) {
                    String n = split[j];
                    int num = Integer.parseInt(n);
                    bingoCard[bingoIdx][j] = num;
                }
                bingoIdx++;
            }
            bingoCards.add(bingoCard);
            idx = i + 1;
        } while (idx < lines.size());
    }

    private void part1(File file, boolean first) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            duplicateCode(br);
            //execute bingo game
            for (int i = 0; i < sequence.length; i++) {
                int currNum = Integer.parseInt(sequence[i]);
                for (int[][] card : bingoCards) {
                    removeNumFromBingoCard(currNum, card);
                }
                if (i >= 4) {
                    LinkedList<int[][]> list = checkWin(bingoCards);
                    if (!list.isEmpty()) {
                        if (first) {
                            int sum = sumOfAllNumbers(list.getFirst());
                            System.out.println("Board that wins first score = " + sum * currNum);
                            return;
                        } else if (bingoCards.size() == 1) { //only show last
                            int sum = sumOfAllNumbers(list.getFirst());
                            System.out.println("Board that wins last score = " + sum * currNum);
                            return;
                        } else {
                            bingoCards.removeAll(list);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //find board who wins last
    private void part2(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            duplicateCode(br);
            //execute bingo game
            for (int i = 0; i < sequence.length; i++) {
                int currNum = Integer.parseInt(sequence[i]);
                for (int[][] card : bingoCards) {
                    removeNumFromBingoCard(currNum, card);
                }
                if (i >= 4) {
                    LinkedList<int[][]> list = checkWin(bingoCards);
                    if (bingoCards.size() != 1) {
                        bingoCards.removeAll(list);
                    } else {
                        if (!checkWin(bingoCards).isEmpty()) {
                            int sum = sumOfAllNumbers(list.getFirst());
                            System.out.println("Board that wins last score = " + sum * currNum);
                            return;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int sumOfAllNumbers(int[][] card) {
        int sum = 0;
        for (int line = 0; line < card.length; line++) {
            for (int column = 0; column < card.length; column++) {
                sum += card[line][column];
            }
        }
        return sum;
    }

    public LinkedList<int[][]> checkWin(LinkedList<int[][]> bingoCards) {
        LinkedList<int[][]> won = new LinkedList<>();
        for (int[][] card : bingoCards) {
            if (check(card, true) || check(card, false)) {
                won.add(card);
            }
        }
        return won;
    }

    private boolean check(int[][] card, boolean checkLines) {
        for (int line = 0; line < card.length; line++) {
            int sum = 0;
            for (int column = 0; column < card.length; column++) {
                if (checkLines)
                    sum += card[line][column];
                else
                    sum += card[column][line];
            }
            if (sum == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean removeNumFromBingoCard(int currNum, int[][] card) {
        for (int i = 0; i < card.length; i++) {
            for (int j = 0; j < card.length; j++) {
                if (card[i][j] == currNum) {
                    card[i][j] = 0;
                    return true;
                }
            }
        }
        return false;
    }
}
