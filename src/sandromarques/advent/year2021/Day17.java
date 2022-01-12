package sandromarques.advent.year2021;

public class Day17 {

    public static void main(String[] args) {
        Day17 day17 = new Day17();
        System.out.println(day17.part1()); //5565
        System.out.println(day17.part2()); //2163
        day17.parts();
    }

    private final int STEPS = 500;

    private int xStart = 143,
            xEnd = 177,
            yStart = -106,
            yEnd = -71;

    private int part1() {
        int n = Math.abs(yStart) - 1;
        return (((n * n) + n) / 2);
    }

    private int part2() {
        int sum = 0;
        for (int vx = -STEPS; vx < STEPS; vx++) {
            for (int vy = -STEPS; vy <= STEPS; vy++) {
                if (shoot(vx, vy)) sum++;
            }
        }
        return sum;
    }

    private boolean shoot(long initx, long inity) {
        long x = 0, y = 0;
        while (y > yEnd) {
            x += initx;
            y += inity;
            if (initx != 0) {
                initx = initx > 0 ? initx - 1L : initx + 1L;
            }
            inity -= 1;
        }
        while (y >= yStart) {
            if (x >= xStart && x <= xEnd) return true;
            x += initx;
            y += inity;
            if (initx != 0) {
                initx = initx > 0 ? initx - 1L : initx + 1L;
            }
        }
        return false;
    }

    /**
     * NEAL WU SOLUTIONS (part1 = 5151 , part2 = 947) :
     */
    private static final int[] X_TARGET = {143, 177};
    private static final int[] Y_TARGET = {-106, -71};

    private void parts() {
        long best = 0;
        long count = 0;
        for (long vx = -STEPS; vx <= STEPS; vx++) {
            for (long vy = -STEPS; vy <= STEPS; vy++) {
                long sim = simulate(vx, vy);
                best = Math.max(best, sim);
                if (sim >= 0) count += 1;
            }
        }
        System.out.println("Part1 : " + best);
        System.out.println("Part2 : " + count);
    }

    private long simulate(long vx, long vy) {
        long x = 0, y = 0;
        long max_y = 0;
        for (int s = 0; s < 1000; s++) {
            x += vx;
            y += vy;
            max_y = Math.max(max_y, y);

            if (X_TARGET[0] <= x && x <= X_TARGET[1] && Y_TARGET[0] <= y && y <= Y_TARGET[1])
                return max_y;

            vx -= Long.compare(x, 0);
            vy -= 1;
        }
        return -1;
    }
}
