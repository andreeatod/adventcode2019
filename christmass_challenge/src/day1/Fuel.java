package day1;

import common.ReadFromFile;

import java.util.List;
import java.util.stream.Collectors;

public class Fuel {

    public static void main(String[] args) {
        String fileName = "christmass_challenge/src/day1/dayOne.txt";
        List<Integer> modules = ReadFromFile.readIntegers(fileName);

        List<Integer> requiredFuel = modules.stream()
                .map(Fuel::getRequiredFuel)
                .collect(Collectors.toList());

        int baseFuel = requiredFuel.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Required fuel so far " + baseFuel);

        int totalFuel = requiredFuel.stream()
                .mapToInt(Fuel::getExtraFuel)
                .sum();
        totalFuel = totalFuel + baseFuel;

        System.out.println("Entire fuel required " + totalFuel);
    }

    private static int getExtraFuel(int fuel) {
        int sum = 0;

        int extraFuel = getRequiredFuel(fuel);
        while (extraFuel > 0) {
            sum += extraFuel;
            extraFuel = getRequiredFuel(extraFuel);
        }

        return sum;
    }

    private static int getRequiredFuel(int module) {
        return module / 3 - 2;
    }
}
