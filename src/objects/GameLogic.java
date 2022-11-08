package objects;

import utils.ConsoleWorker;
import utils.FileUtils;
import utils.Permutator;
import utils.Validator;

import java.io.IOException;
import java.util.*;

public class GameLogic {

    public static String makeComputerString() {
        Set<Integer> numbers = new LinkedHashSet<>();
        Random random = new Random();
        while (numbers.size() != 4) {
            int numberRandom = random.nextInt(0, 10);
            numbers.add(numberRandom);
        }

        StringBuilder computerString = new StringBuilder();

        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            computerString.append(next);
            iterator.remove();
        }

        return computerString.toString();
    }

    public static String makeStringUser() {
        Scanner scanner = new Scanner(System.in);
        String userStr;
        while (true) {
            userStr = scanner.nextLine();//считывание с клавиатуры строки из чисел
            if (Validator.isCorrectInputUser(userStr)) {
                return userStr;//если подтвердилось что ввели 4 числа и они все разные, то выходим из цикла и возвращаем строку
            }
        }
    }

    public static int[] getScoreGame(String userStr, String computerStr) {
        int countBull = 0;      //счетчик количества быков
        int countCow = 0;       //счетчик количества коров
        for (int i = 0; i < userStr.length(); i++) {
            char c1 = userStr.charAt(i);

            for (int j = 0; j < computerStr.length(); j++) {
                char c2 = computerStr.charAt(j);

                if (c1 == c2) {
                    if (i == j) {
                        countBull++;
                    } else {
                        countCow++;
                    }
                    break;
                }
            }
        }
        return new int[]{countBull, countCow};
    }


    public static int predictUserNumber(String userString) throws IOException {
        int countAttempt = 1;

        List<Integer> userSet = new ArrayList<>(
                List.of(
                        Integer.parseInt(Character.toString(userString.charAt(0))),
                        Integer.parseInt(Character.toString(userString.charAt(1))),
                        Integer.parseInt(Character.toString(userString.charAt(2))),
                        Integer.parseInt(Character.toString(userString.charAt(3)))
                ));

        List<Integer> compNum = new ArrayList<>();
        Set<Integer> rightNumber = new HashSet<>();
        int[] score;

        Random random = new Random();

        while (rightNumber.size() != 4) {
            while (compNum.size() != 4) {
                int numberRandom = random.nextInt(0, 10);
                if (!compNum.contains(numberRandom)) {
                    compNum.add(numberRandom);
                }
            }
            String str = "" + compNum.get(0) + compNum.get(1) + compNum.get(2) + compNum.get(3);
            score = getScoreGame(userString, str);
            FileUtils.writeFileGameString(str, ConsoleWorker.getStringResult(score));

            for (int i = rightNumber.size(); i < compNum.size(); i++) {
                if (userSet.contains(compNum.get(i))) {
                    rightNumber.add(compNum.get(i));
                }
            }

            compNum.clear();
            compNum.addAll(rightNumber);
            countAttempt++;
        }

        Permutator<Integer> permutator = new Permutator<>(new Integer[]{compNum.get(0), compNum.get(1), compNum.get(2), compNum.get(3)});
        boolean isGuessedStr = false;
        while (permutator.hasNext() && !isGuessedStr) {
            Integer[] next = permutator.next();
            String str = "" + next[0] + next[1] + next[2] + next[3];
            score = getScoreGame(userString, str);
            FileUtils.writeFileGameString(str, ConsoleWorker.getStringResult(score));
            if (score[0] == 4) {
                isGuessedStr = true;
            }else {
                countAttempt++;
            }
        }
        return countAttempt;
    }

}
