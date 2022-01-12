package sandromarques.advent.year2020;

import sandromarques.advent.FileManager;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

    public static void main(String[] args) {
        Day4 day = new Day4();
        day.parts(FileManager.getFile(4, 2020), true); //correct answer : 256
        day.parts(FileManager.getFile(4, 2020), false); //correct answer : 198
    }

    private void parts(File file, boolean part1) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            LinkedList<String> lines = new LinkedList<>();
            String currLine;
            while ((currLine = br.readLine()) != null) {
                lines.add(currLine);
            }
            lines.add("");
            int total = 0;
            HashMap<String, String> map = new HashMap<>();
            for (String line : lines) {
                if (line.isBlank() || line.isEmpty()) {
                    if (part1 && containsAllRequired(map)) {
                        total++;
                    } else if (!part1 && valid(map)) {
                        total++;
                    }
                    map.clear();
                } else {
                    String[] split = line.split(" ");
                    for (String sp : split) {
                        String[] split2 = sp.split(":");
                        map.put(split2[0], split2[1]);
                    }
                }
            }
            System.out.println(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean valid(HashMap<String, String> map) {
        boolean b = containsAllRequired(map);
        if (b) {
            if (!isValidNum(map.get("byr"), 1920, 2002)) return false;
            if (!isValidNum(map.get("iyr"), 2010, 2020)) return false;
            if (!isValidNum(map.get("eyr"), 2020, 2030)) return false;
            if (!isValidHeight(map.get("hgt"))) return false;
            if (!isValidHairColor(map.get("hcl"))) return false;
            if (!isValidEyeColor(map.get("ecl"))) return false;
            if (!isValidID(map.get("pid"))) return false;
            return true;
        }
        return false;
    }

    private boolean isValidID(String pid) {
        return pid.length()==9;
    }

    private boolean isValidEyeColor(String hcl) {
        return hcl.equals("amb")
                || hcl.equals("blu")
                || hcl.equals("brn")
                || hcl.equals("gry")
                || hcl.equals("grn")
                || hcl.equals("hzl")
                || hcl.equals("oth");
    }

    private boolean isValidHairColor(String hcl) {
        if (hcl.charAt(0) != '#') return false;
        else {
            String str = hcl.substring(1);
            return str.length() == 6 && str.matches("[0-9a-f]+");
        }
    }

    private boolean isValidHeight(String hgt) {
        try {
            if (hgt.contains("cm")) {
                hgt = hgt.replace("cm", "");
                int x = Integer.parseInt(hgt);
                return x >= 150 && x <= 193;
            } else if (hgt.contains("in")) {
                hgt = hgt.replace("in", "");
                int x = Integer.parseInt(hgt);
                return x >= 59 && x <= 76;
            } else return false;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isValidNum(String str, int low, int high) {
        try {
            if (str.length() != 4) return false;
            int x = Integer.parseInt(str);
            return x >= low && x <= high;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean containsAllRequired(HashMap<String, String> map) {
        return map.containsKey("byr")
                && map.containsKey("iyr")
                && map.containsKey("eyr")
                && map.containsKey("hgt")
                && map.containsKey("hcl")
                && map.containsKey("ecl")
                && map.containsKey("pid");
    }
}
