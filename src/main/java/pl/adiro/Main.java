package pl.adiro;

import java.io.FileNotFoundException;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        day1.part1();
        System.out.println(day2.part1(file(2)));
        System.out.println(day2.part2(file(2)));
    }

    public static String file(int dayN) {
        return String.format("src/main/resources/day%s/input.txt", dayN);
    }
}