package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileUtils {
    private static final String pathLogFile = "resource/game-log.txt";

    public static void writeFileStarGame(String secretString) throws IOException {
        LocalDateTime localDateTime = LocalDateTime.now();
        String strCurrentDateTime = localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm"));
        int numberGame = readNumberGame();
        numberGame = numberGame + 1;
        String newWriteString = "Game №" + numberGame + " " + strCurrentDateTime + " " + "Загаданная строка " + secretString + System.lineSeparator();
        writeString(newWriteString);
    }

    public static void writeFileGameString(String query, String result) throws IOException {
        String newWriteString = "\tЗапрос: " + query + " Ответ: " + result + System.lineSeparator();
        writeString(newWriteString);
    }

    public static void writeFileEndGame(int countAttempt) throws IOException {
        String newWriteString = "\tСтрока была угадана за " + countAttempt + " попыток" + System.lineSeparator();
        writeString(newWriteString);
    }

    private static void writeString(String newWriteString) throws IOException {
        Files.writeString(Path.of(pathLogFile), newWriteString, StandardOpenOption.APPEND);
    }

    private static int readNumberGame() throws IOException {
        Path path = Path.of(pathLogFile);
        int numGame = 0;
        if(!Files.exists(path)){
            Files.createFile(path);
        }
        String string = Files.readString(path);
        if(string == null){
            return 0;
        }
        String[] split = string.split(System.lineSeparator());
        for (int i = split.length - 1; i >= 0 ; i--) {
            if(!split[i].startsWith("Game")){
                continue;
            }
            String number = split[i].split(" ")[1].substring(1);
            numGame = Integer.parseInt(number);
            break;
        }
        return numGame;
    }
}
