package day8;

import common.ReadFromFile;

import java.util.List;

public class Decrypt {

    public static void main(String[] args) {
        String fileName = "christmass_challenge/src/day8/dayEight.txt";

        List<Integer> input = ReadFromFile.readCharacterAsIntegers(fileName);

        int width = 25;
        int height = 6;
        int perLayer = width * height;

        int layer = 0, product = 1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < input.size(); ) {
            int zeros = 0, ones = 0, twos = 0;

            while (i < perLayer * (layer + 1) && i <input.size()) {
                if (input.get(i) == 0) {
                    zeros++;
                }
                if (input.get(i) == 1) {
                    ones++;
                }
                if (input.get(i) == 2) {
                    twos++;
                }

                i++;
            }

            if (zeros < min) {
                min = zeros;
                product = ones * twos;
            }
            layer++;
        }

        System.out.println("The number " + product);
    }
}
