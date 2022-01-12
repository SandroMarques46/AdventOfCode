import org.junit.Assert;
import org.junit.Test;
import sandromarques.advent.year2021.Day8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Day8Test {
    Day8 day8 = new Day8();

    @Test
    public void test1() {
        HashMap<Character, LinkedList<Character>> map = new HashMap<>();
        String seq = "bgf";
        map.put('b', new LinkedList<>(Arrays.asList('a', 'd')));
        map.put('g', new LinkedList<>(Arrays.asList('b', 'c')));
        map.put('f', new LinkedList<>(List.of('e','f')));
        char[][] expected = {
                {'a', 'b', 'e'},
                {'a', 'b', 'f'},
                {'a', 'c', 'e'},
                {'a', 'c', 'f'},
                {'d', 'b', 'e'},
                {'d', 'b', 'f'},
                {'d', 'c', 'e'},
                {'d', 'c', 'f'}
        };

        char[][] actual = day8.getCombinations(map,seq);
        Assert.assertArrayEquals(expected,actual);
    }

    @Test
    public void test2() {
        HashMap<Character, LinkedList<Character>> map = new HashMap<>();
        String seq = "bg";
        map.put('b', new LinkedList<>(Arrays.asList('a', 'd')));
        map.put('g', new LinkedList<>(Arrays.asList('b', 'c')));
        char[][] expected1 = {
                {'a', 'b'},
                {'a', 'c'},
                {'d', 'b'},
                {'d', 'c'}
        };

        char[][] actual1 = day8.getCombinations(map,seq);
        Assert.assertArrayEquals(expected1,actual1);

        seq = "bgf";
        map.put('f', new LinkedList<>(List.of('e')));
        char[][] expected2 = {
                {'a', 'b','e'},
                {'a', 'c','e'},
                {'d', 'b','e'},
                {'d', 'c','e'}
        };

        char[][] actual2 = day8.getCombinations(map,seq);
        Assert.assertArrayEquals(expected2,actual2);
    }
}
