package pl.adiro;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
//        day1.part1();
//        System.out.println(day2.part1(file(2)));
//        System.out.println(day2.part2(file(2)));
        System.out.println(day3.part2(file(3)));
    }

    public static String file(int dayN) {
        return String.format("src/main/resources/day%s/input.txt", dayN);
    }
}