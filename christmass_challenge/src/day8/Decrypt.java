package day8;

import common.ReadFromFile;

import java.util.ArrayList;
import java.util.List;

public class Decrypt {

    private static int width = 25;
    private static int height = 6;
    private static int perLayer = width * height;

    public static void main(String[] args) {
        String fileName = "christmass_challenge/src/day8/dayEight.txt";

        List<Integer> input = ReadFromFile.readCharacterAsIntegers(fileName);

        int layer = 0, product = 1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < input.size(); ) {
            int zeros = 0, ones = 0, twos = 0;

            while (i < perLayer * (layer + 1) && i < input.size()) {
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

        List<Integer> image = new ArrayList<>(perLayer);
        for (int i = 0; i < perLayer; i++) {
            image.add(input.get(i));
        }

        completeImage(input, image);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!image.get(i * width + j).equals(0)) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    private static void completeImage(List<Integer> input, List<Integer> image) {
        for (int i = 0; i < image.size(); i++) {
            if (image.get(i) == 2) {
                int k = 0;
                while (input.get(perLayer * k + i) == 2) {
                    k++;
                }
                image.set(i, input.get(perLayer * k + i));
            }
        }
    }
}
