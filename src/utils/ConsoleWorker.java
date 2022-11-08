package utils;

public class ConsoleWorker {
    public static String getStringResult(int[] arrayScoreBullCow) {
        int countBull = arrayScoreBullCow[0];
        int countCow = arrayScoreBullCow[1];
        StringBuilder stringResult = new StringBuilder();
        stringResult.append(countBull);
        if (countBull == 0) {
            stringResult.append(" быков ");
        } else if (countBull == 1) {
            stringResult.append(" бык ");
        } else {
            stringResult.append(" быка ");
        }
        stringResult.append(countCow);
        if (countCow == 0) {
            stringResult.append(" коров ");
        } else if (countCow == 1) {
            stringResult.append(" корова ");
        } else {
            stringResult.append(" коровы ");
        }
        return stringResult.toString();
    }

    public static void printResul(String stringResult){
        System.out.println(stringResult);
    }
}
