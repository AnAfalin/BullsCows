package utils;

public class Validator {
    public static boolean isOtherNumbers(String str) {
        char char0 = str.charAt(0);
        char char1 = str.charAt(1);
        char char2 = str.charAt(2);
        char char3 = str.charAt(3);

        return char0 != char1 && char0 != char2 && char0 != char3
                && char1 != char2 && char1 != char3
                && char2 != char3;
    }

    public static boolean isCorrectInputUser(String userString){
        if (!userString.matches("[0-9]{4}")) {//проверка, что ввели НЕ 4 числа, а что-то с ошибкой (с пробелами, символами или буквами)
            System.out.println("Должно быть введено четырехзначное число");//если подтвердился неверный ввод, то выводим сообщение об ошибки и перезапускаем цикл
            return false;
        }
        if (!Validator.isOtherNumbers(userString)) {//проверка, что ввели какие-то одинаковые числа
            System.out.println("Цифры не должны повторяться");//если подтвердился неверный ввод, то выводим сообщение об ошибки и перезапускаем цикл
            return false;
        }
        return true;
    }
}
