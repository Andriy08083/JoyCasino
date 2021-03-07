package ua.PGFKCasino.Slots;

import ua.PGFKCasino.IO;
import ua.PGFKCasino.interfaces.ICasinoGame;
import java.util.Random;

public class SlotMachine extends IO implements ICasinoGame {
    public static String getFruit() {
        String[] fruits = new String[]{"Вишня", "Апельсин", "Слива","Банан","Гарбуз","Бочка"};
        return fruits[new Random().nextInt(fruits.length)];
    }
    @Override
    public void startGame() {
        String input = handleInput();
        Random random = new Random();
        int coin = 50;
        int rate;
        String slot1;
        String slot2;
        String slot3;
        int doubl;
        int tripl;
        int jeckpot;
            slot1 = getFruit();
            slot2 = getFruit();
            slot3 = getFruit();
            System.out.println("Твій баланс складає:" + coin);
            System.out.println("Скільки ти хочеш поставити??");
            rate = Integer.parseInt(handleInput());
            doubl = rate * 2;
            tripl = rate * 3;
            jeckpot = rate * 10;
            System.out.println(slot1);
            System.out.println(slot2);
            System.out.println(slot3);
            if (slot1 != slot2 && slot1 != slot3 && slot2 != slot3) {
                System.out.println("Ти втратив гроші(");
                coin -= rate;
                if (coin < 0) coin = 0;
            } else if (slot1 == slot2 || slot1 == slot3 || slot2 == slot3) {
                System.out.println("Вітаю ти виграв $" + doubl);
                coin += doubl;

            } else if (slot1 == slot2 && slot3.equals("Вишня")) {
                System.out.println("Вітаю ти виграв $" + tripl);
                coin += tripl;
            } else if (slot1.equals("Вишня") && slot2.equals("Вишня") && slot3.equals("Вишня")) {
                System.out.println("Вітаю! Ти зірвав джекпот $" + jeckpot);
            }
            System.out.println("Твій баланс складає " + coin);
            System.out.println("Прожовжити? 1/2 ");
            ContinueSpin();

    }

    public void ContinueSpin(){
        switch (handleInput().trim()) {
            case "1":
                startGame();
                break;
            case "2":
                stopGame();
                break;
            default:
                System.out.println("Незрозумiла команда. Введіть ще раз");
                ContinueSpin();
                break;
        }
    }

    @Override
    public void stopGame() {

    }

    @Override
    public void saveGame() {

    }

    @Override
    public void loadGame() {

    }

    @Override
    public void getCurrentGameStats() {

    }
}
