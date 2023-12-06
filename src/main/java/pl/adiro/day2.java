package pl.adiro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day2 {
    private static final int maxRed = 12;
    private static final int maxGreen = 13;
    private static final int maxBlue = 14;

    public static int part1(String file) throws FileNotFoundException {
        int score = 0;
        var scanner = new Scanner(new File(file));

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            String gameName = parts[0];
            int gameId = Integer.parseInt(gameName.substring(5));

            String gameContent = parts[1];
            gameContent = gameContent
                    .replace(" ", "")
                    .replace(",", "")
                    .replace(";", "");

            if (isPossible(gameContent)) {
                score += gameId;
            }
        }
        return score;
    }

    static public boolean isPossible(String gameContent) {
        var pattern = Pattern.compile("[0-9]{1,2}");
        Matcher matcher = pattern.matcher(gameContent);

        while (matcher.find()) {
            int count = Integer.parseInt(
                    gameContent.substring(matcher.start(), matcher.end())
            );
            char color = gameContent.charAt(matcher.end());

            switch (color) {
                case 'r':
                    if (count > maxRed) return false;
                    break;
                case 'g':
                    if (count > maxGreen) return false;
                    break;
                case 'b':
                    if (count > maxBlue) return false;
                    break;
            }
        }
        return true;
    }

    public static int part2(String file) throws FileNotFoundException {
        int score = 0;
        var scanner = new Scanner(new File(file));

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            String gameName = parts[0];
            int gameId = Integer.parseInt(gameName.substring(5));

            String gameContent = parts[1];
            gameContent = gameContent
                    .replace(" ", "")
                    .replace(",", "")
                    .replace(";", "");

            score += power(gameContent);
        }
        return score;
    }

    static public int power(String gameContent) {
        var pattern = Pattern.compile("[0-9]{1,2}");
        Matcher matcher = pattern.matcher(gameContent);

        int maxRed = 0;
        int maxGreen = 0;
        int maxBlue = 0;

        while (matcher.find()) {
            int count = Integer.parseInt(
                    gameContent.substring(matcher.start(), matcher.end())
            );
            char color = gameContent.charAt(matcher.end());

            switch (color) {
                case 'r':
                    if (count > maxRed) maxRed = count;
                    break;
                case 'g':
                    if (count > maxGreen) maxGreen = count;
                    break;
                case 'b':
                    if (count > maxBlue) maxBlue = count;
                    break;
            }
        }
        return maxRed * maxBlue * maxGreen;
    }
}
