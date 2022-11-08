package objects;

import utils.ConsoleWorker;
import utils.FileUtils;

import java.io.IOException;
import java.util.Scanner;

public class BullsCows {

    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите тип игры: " + System.lineSeparator() +
                    "1. Компьютер загадывает, Вы - отгадываете" + System.lineSeparator() +
                    "2. Вы загадываете, компьютер - отгадываете"  + System.lineSeparator() +
                    "3. Выход из игры");
            String typeGame = scanner.nextLine();
            switch (typeGame) {
                case "1":
                    gameUserComputer();
                    break;
                case "2" :
                    gameComputerUser();
                    break;
                case "3":
                    System.out.println("Игра закрыта");
                    return;
                default:
                    System.out.println("Некорректный ввод. Повторите попытку.");
            }
        }
    }

    private void gameUserComputer() throws IOException {
        System.out.println("Игра \"Быки и коровы\" началась!");
        String computerString = GameLogic.makeComputerString();
        FileUtils.writeFileStarGame(computerString);
        System.out.println("Компьютер загадал число.");

        System.out.println("Отгадайте число, задуманное компьютером");
        String userString;
        int countAttempt = 1;
        while (true) {
            userString = GameLogic.makeStringUser();
            int[] scoreGame = GameLogic.getScoreGame(userString, computerString);

            String stringResult = ConsoleWorker.getStringResult(scoreGame);
            ConsoleWorker.printResul(stringResult);

            FileUtils.writeFileGameString(userString, stringResult);
            if (scoreGame[0] == 4) {
                System.out.println("Ура! Число отгадано!");
                break;
            }
            countAttempt++;
        }
        FileUtils.writeFileEndGame(countAttempt);
    }

    private void gameComputerUser() throws IOException {
        System.out.println("Загадайте число...");
        String userString = GameLogic.makeStringUser();
        FileUtils.writeFileStarGame(userString);

        int countAttempt = GameLogic.predictUserNumber(userString);
        FileUtils.writeFileEndGame(countAttempt);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Компьютера отгадал число за " + countAttempt + " попыток");

    }
}


