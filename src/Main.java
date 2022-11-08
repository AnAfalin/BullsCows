
import objects.BullsCows;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            new BullsCows().start();
        } catch (IOException e) {
            System.out.println("В игре возникла ошибка. Игра закрыта");
            e.printStackTrace();
        }

    }
}
