package sandromarques.advent.year2020;

import sandromarques.advent.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Day12 {
    private enum Directions {
        NORTH, SOUTH, EAST, WEST
    }

    private Directions currDir = Directions.EAST;

    public static void main(String[] args) {
        Day12 day = new Day12();
        day.parts(FileManager.getFile(12, 2020), true); //correct answer : 2280
        day.parts(FileManager.getFile(12, 2020), false); //correct answer : 38693
    }

    private final char FORWARD = 'F';
    private final char NORTH = 'N';
    private final char SOUTH = 'S';
    private final char EAST = 'E';
    private final char WEST = 'W';
    private final char LEFT = 'L';
    private final char RIGHT = 'R';

    private void parts(File file, boolean part1) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            HashMap<Directions, Integer> count = new HashMap<>();
            count.put(Directions.NORTH, 0);
            count.put(Directions.SOUTH, 0);
            count.put(Directions.EAST, 0);
            count.put(Directions.WEST, 0);

            HashMap<Directions, Integer> waypoint = new HashMap<>();
            waypoint.put(Directions.NORTH, 1);
            waypoint.put(Directions.SOUTH, 0);
            waypoint.put(Directions.EAST, 10);
            waypoint.put(Directions.WEST, 0);

            for (String line : lines) {
                char action = line.charAt(0);
                int num = Integer.parseInt(line.substring(1));
                if (part1) {
                    if (action == FORWARD) {
                        count.put(currDir, count.get(currDir) + num);
                    } else if (action == NORTH) {
                        count.put(Directions.NORTH, count.get(Directions.NORTH) + num);
                    } else if (action == SOUTH) {
                        count.put(Directions.SOUTH, count.get(Directions.SOUTH) + num);
                    } else if (action == EAST) {
                        count.put(Directions.EAST, count.get(Directions.EAST) + num);
                    } else if (action == WEST) {
                        count.put(Directions.WEST, count.get(Directions.WEST) + num);
                    } else if (action == RIGHT) {
                        changeCurrDirRight(num);
                    } else if (action == LEFT) {
                        changeCurrDirLeft(num);
                    }
                } else {
                    if (action == FORWARD) {
                        forwardPart2(count, waypoint, num);
                    } else if (action == NORTH) {
                        waypoint.put(Directions.NORTH,waypoint.get(Directions.NORTH)+num);
                    } else if (action == SOUTH) {
                        waypoint.put(Directions.SOUTH,waypoint.get(Directions.SOUTH)+num);
                    } else if (action == EAST) {
                        waypoint.put(Directions.EAST,waypoint.get(Directions.EAST)+num);
                    } else if (action == WEST) {
                        waypoint.put(Directions.WEST,waypoint.get(Directions.WEST)+num);
                    } else if (action == RIGHT) { //rotate waypoint map
                        changeCurrDirRightPart2(waypoint,num);
                    } else if (action == LEFT) {
                        changeCurrDirLeftPart2(waypoint,num);
                    }
                }
            }
            int absoluteValues =
                    Math.abs(count.get(Directions.NORTH) - count.get(Directions.SOUTH))
                            + Math.abs(count.get(Directions.EAST) - count.get(Directions.WEST));
            System.out.println(absoluteValues);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeCurrDirRight(int degrees) {
        while (degrees != 0) {
            switch (currDir) {
                case NORTH -> currDir = Directions.EAST;
                case EAST -> currDir = Directions.SOUTH;
                case SOUTH -> currDir = Directions.WEST;
                case WEST -> currDir = Directions.NORTH;
            }
            degrees -= 90;
        }
    }

    private void changeCurrDirLeft(int degrees) {
        while (degrees != 0) {
            switch (currDir) {
                case NORTH -> currDir = Directions.WEST;
                case WEST -> currDir = Directions.SOUTH;
                case SOUTH -> currDir = Directions.EAST;
                case EAST -> currDir = Directions.NORTH;
            }
            degrees -= 90;
        }
    }

    /**
     * Part 2 methods
     */
    private void forwardPart2(HashMap<Directions, Integer> count, HashMap<Directions, Integer> waypoint, int num) {
        for (Directions dir : waypoint.keySet()) {
            count.put(dir, count.get(dir) + (waypoint.get(dir) * num));
        }
    }

    private void changeCurrDirRightPart2(HashMap<Directions, Integer> waypoint, int degrees) {
        LinkedList<Integer> values = new LinkedList<>();
        values.add(waypoint.get(Directions.NORTH));
        values.add(waypoint.get(Directions.EAST));
        values.add(waypoint.get(Directions.SOUTH));
        values.add(waypoint.get(Directions.WEST));
        LinkedList<Directions> list = new LinkedList<>();
        list.add(Directions.NORTH);
        list.add(Directions.EAST);
        list.add(Directions.SOUTH);
        list.add(Directions.WEST);
        while (degrees != 0) {
            list.addLast(list.removeFirst()); //rotate right
            degrees -= 90;
        }
        for (int i = 0; i < list.size(); i++) {
            waypoint.put(list.get(i), values.get(i));
        }
    }

    private void changeCurrDirLeftPart2(HashMap<Directions, Integer> waypoint, int degrees) {
        LinkedList<Integer> values = new LinkedList<>();
        values.add(waypoint.get(Directions.NORTH));
        values.add(waypoint.get(Directions.EAST));
        values.add(waypoint.get(Directions.SOUTH));
        values.add(waypoint.get(Directions.WEST));
        LinkedList<Directions> list = new LinkedList<>();
        list.add(Directions.NORTH);
        list.add(Directions.EAST);
        list.add(Directions.SOUTH);
        list.add(Directions.WEST);
        while (degrees != 0) {
            list.addFirst(list.removeLast()); //rotate left
            degrees -= 90;
        }
        for (int i = 0; i < list.size(); i++) {
            waypoint.put(list.get(i), values.get(i));
        }
    }
}
