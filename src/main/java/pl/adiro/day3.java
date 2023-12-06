package pl.adiro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class day3 {
    public static int part1(String file) throws IOException {
        int result = 0;

        var numberPattern = Pattern.compile("[0-9]+");

        List<String> lines = Files.readAllLines(Paths.get(file));

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Matcher matcher = numberPattern.matcher(line);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();

                if (isValidNum(i, start, end - 1, lines)) {
                    int number = Integer.parseInt(line.substring(start, end));
                    result += number;
                }
            }
        }
        return result;
    }

    public static boolean isValidNum(int lineNumber, int numberStart, int numberEnd, List<String> lines) {
        int start_i = numberStart > 0 ? numberStart - 1 : numberStart;
        int end_i = numberEnd < lines.size() - 1 ? numberEnd + 1 : numberEnd;

        String currLine = lines.get(lineNumber);
        if (isSymbol(currLine.charAt(start_i)) || isSymbol(currLine.charAt(end_i))) return true;

        if (lineNumber > 0) {
            String prevLine = lines.get(lineNumber - 1);

            for (int i = start_i; i <= end_i; i++) {
                if (isSymbol(prevLine.charAt(i))) return true;
            }
        }

        if (lineNumber < lines.size() - 1) {
            String nextLine = lines.get(lineNumber + 1);

            for (int i = start_i; i <= end_i; i++) {
                if (isSymbol(nextLine.charAt(i))) return true;
            }
        }

        return false;
    }

    private static boolean isSymbol(char character) {
        return character != '.' && !Character.isDigit(character);
    }


    public static int part2(String file) throws IOException {
        int result = 0;

        var numberPattern = Pattern.compile("[0-9]+");

        List<String> lines = Files.readAllLines(Paths.get(file));


        Map<Coords, List<Integer>> gearsNeighbours = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Matcher matcher = numberPattern.matcher(line);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();

                int number = Integer.parseInt(line.substring(start, end));

                for (Coords gear : gearNeighbours(i, start, end - 1, lines)) {
                    if (gearsNeighbours.containsKey(gear)) {
                        gearsNeighbours.get(gear).add(number);
                    } else {
                        var list = new ArrayList<Integer>();
                        list.add(number);
                        gearsNeighbours.put(gear, new ArrayList<>(list));
                    }
                }
            }
        }

        gearsNeighbours.values().removeIf(val -> (val.size() != 2));

        for (var entry : gearsNeighbours.entrySet()) {
            var value = entry.getValue();
            result += value.get(0) * value.get(1);
        }
        return result;
    }

    private static List<Coords> gearNeighbours(int lineNumber, int numberStart, int numberEnd, List<String> lines) {
        List<Coords> gears = new ArrayList<>();

        int start_i = numberStart > 0 ? numberStart - 1 : numberStart;
        int end_i = numberEnd < lines.size() - 1 ? numberEnd + 1 : numberEnd;

        String currLine = lines.get(lineNumber);
        if (currLine.charAt(start_i) == '*') gears.add(new Coords(start_i, lineNumber));
        if (currLine.charAt(end_i) == '*') gears.add(new Coords(end_i, lineNumber));

        if (lineNumber > 0) {
            String prevLine = lines.get(lineNumber - 1);

            for (int i = start_i; i <= end_i; i++) {
                if (prevLine.charAt(i) == '*') gears.add(new Coords(i, lineNumber - 1));
            }
        }

        if (lineNumber < lines.size() - 1) {
            String nextLine = lines.get(lineNumber + 1);

            for (int i = start_i; i <= end_i; i++) {
                if (nextLine.charAt(i) == '*') gears.add(new Coords(i, lineNumber + 1));
            }
        }

        return gears;
    }

    private static boolean isNear() {
        return false;
    }
}

class Coords {
    public int column;
    public int row;

    public Coords(int column, int row) {
        this.column = column;
        this.row = row;
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) return false;

        var coords2 = (Coords) obj;
        return this.column == coords2.column && this.row == coords2.row;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(column) - Integer.hashCode(row);
    }

    @Override
    public String toString() {
        return "[" + column + "," + row + "]";
    }
}


