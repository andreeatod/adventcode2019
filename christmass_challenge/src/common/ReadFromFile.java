package common;

import java.io.*;
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

    public static List<Integer> readCharacterAsIntegers(String fileName) {
        List<Integer> integers = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            int c;
            while ((c = reader.read()) != -1) {
                integers.add(c - '0');
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        } catch (IOException ex) {
            System.out.println("Something went wrong while parsing file\n" + ex);
        }

        return integers;
    }
}
