package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFromFile {

    public static List<Integer> readIntegers(String fileName) {
        List<Integer> integers = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {
                integers.add(scanner.nextInt());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }

        return integers;
    }

    public static List<String> readStrings(String fileName) {
        List<String> words = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {
                words.add(scanner.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }

        return words;
    }
}
