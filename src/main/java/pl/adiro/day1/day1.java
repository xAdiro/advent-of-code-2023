package pl.adiro.day1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day1 {
    public static void part1() throws FileNotFoundException {
        var scanner = new Scanner(new File("src/main/resources/day1/input.txt"));

        List<Integer> values = new ArrayList<>();

        while(scanner.hasNext()){
            var line = scanner.nextLine();
            var number = "";

            for (int i =0; i<line.length();i++){
                var sign = line.charAt(i);
                if(Character.isDigit(sign)){
                    number += Character.getNumericValue(sign);
                    break;
                }
            }

            for (int i =line.length()-1; i>=0;i--){
                var sign = line.charAt(i);
                if(Character.isDigit(sign)){
                    number += Character.getNumericValue(sign);
                    break;
                }
            }

            values.add(Integer.parseInt(number));
        }

        System.out.print("Part1: " + values.stream().reduce(0, Integer::sum));
    }
}
