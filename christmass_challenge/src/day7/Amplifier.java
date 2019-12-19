package day7;

import common.ReadFromFile;

import java.util.ArrayList;
import java.util.List;

public class Amplifier {

    private static List<Integer> input = new ArrayList<>();

    public static void main(String[] args) {
        String fileName = "christmass_challenge/src/day7/daySeven.txt";
        input.addAll(ReadFromFile.readIntegers(fileName));

        List<List<Integer>> combos = generatePermutations();

        int max = Integer.MIN_VALUE;

        for (int j = 0; j < combos.size(); j++) {
            int previousOutput = 0;
            List<Integer> copy = copyInput(input);
            for (int i = 0; i < 5; i++) {
                previousOutput = runAlgorithm(copy, combos.get(j).get(i), previousOutput);
            }
            if (max < previousOutput) {
                max = previousOutput;
            }
        }

        System.out.println("Highest signal " + max);
    }

    private static void backtracking(List<List<Integer>> list, List<Integer> generated, int recursionDepth) {
        if (recursionDepth == 5) {
            list.add(new ArrayList<>(generated));
            return;
        }
        for (int i = 0; i < 5; ++i)
            if (!generated.contains(i)) {
                generated.add(i);
                backtracking(list, generated, recursionDepth + 1);
                generated.remove(recursionDepth);
            }
    }

    private static void backtracking(List<List<Integer>> list) {
        backtracking(list, new ArrayList<Integer>(), 0);
    }

    protected static List<List<Integer>> generatePermutations() {
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(ans);
        return ans;
    }

    private static List<Integer> copyInput(List<Integer> input) {
        return new ArrayList<>(input);
    }

    private static int runAlgorithm(List<Integer> input, int initialCode, int previousOutput) {
        boolean firstInput = true;
        for (int i = 0; i < input.size(); ) {
            Instruction instruction = decodeInstruction(input.get(i));

            switch (instruction.operation) {
                case 1:
                    int f = getNumber(instruction.firstParam, input.get(i + 1), input);
                    int s = getNumber(instruction.secondParam, input.get(i + 2), input);
                    int r = input.get(i + 3);

                    input.set(r, f + s);
                    i += 4;
                    break;
                case 2:
                    int ff = getNumber(instruction.firstParam, input.get(i + 1), input);
                    int ss = getNumber(instruction.secondParam, input.get(i + 2), input);
                    r = input.get(i + 3);

                    input.set(r, ff * ss);
                    i += 4;
                    break;
                case 3:
                    r = input.get(i + 1);
                    if (firstInput) {
                        input.set(r, initialCode);
                        firstInput = false;
                    } else {
                        input.set(r, previousOutput);
                    }
                    i += 2;
                    break;
                case 4:
                    System.out.println("Output " + getNumber(instruction.firstParam, input.get(i + 1), input));
                    return getNumber(instruction.firstParam, input.get(i + 1), input);
                case 5:
                    int fp = getNumber(instruction.firstParam, input.get(i + 1), input);
                    int sp = getNumber(instruction.secondParam, input.get(i + 2), input);
                    if (fp != 0) {
                        i = sp;
                    } else {
                        i += 3;
                    }
                    break;
                case 6:
                    int fpp = getNumber(instruction.firstParam, input.get(i + 1), input);
                    int spp = getNumber(instruction.secondParam, input.get(i + 2), input);
                    if (fpp == 0) {
                        i = spp;
                    } else {
                        i += 3;
                    }
                    break;
                case 7:
                    int ffpp = getNumber(instruction.firstParam, input.get(i + 1), input);
                    int sspp = getNumber(instruction.secondParam, input.get(i + 2), input);
                    r = input.get(i + 3);

                    if (ffpp < sspp) {
                        input.set(r, 1);
                    } else {
                        input.set(r, 0);
                    }
                    i += 4;
                    break;
                case 8:
                    int ffppp = getNumber(instruction.firstParam, input.get(i + 1), input);
                    int ssppp = getNumber(instruction.secondParam, input.get(i + 2), input);
                    r = input.get(i + 3);

                    if (ffppp == ssppp) {
                        input.set(r, 1);
                    } else {
                        input.set(r, 0);
                    }
                    i += 4;
                    break;
                case 99:
                    System.out.println("Halt with value " + input.get(i + 1));
                    break;
                default:
                    throw new RuntimeException("Operation code " + instruction.operation + " not supported yet!");
            }
        }
        return 0;
    }

    private static int getNumber(int mode, int number, List<Integer> input) {
        if (mode == 1) {
            return number;
        }
        return input.get(number);
    }

    private static Instruction decodeInstruction(int encodedInstruction) {
        Instruction instruction = new Instruction();
        instruction.operation = encodedInstruction % 100;
        instruction.thirdParam = encodedInstruction / 10000;
        instruction.secondParam = encodedInstruction / 1000 % 10;
        instruction.firstParam = encodedInstruction / 100 % 10;

        return instruction;
    }

    static class Instruction {
        public int operation;
        public int thirdParam;
        public int secondParam;
        public int firstParam;

        public Instruction() {
        }
    }
}