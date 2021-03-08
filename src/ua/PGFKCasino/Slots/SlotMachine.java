package ua.PGFKCasino.Slots;

import ua.PGFKCasino.IO;
import ua.PGFKCasino.interfaces.ICasinoGame;
import java.util.Random;

public class SlotMachine extends IO implements ICasinoGame {
    int money = 0;
    int coin = 50;
    public static String getFruit() {
        String[] fruits = new String[]{"Вишня", "Апельсин", "Слива","Банан","Гарбуз","Бочка"};
        return fruits[new Random().nextInt(fruits.length)];
    }
    public SlotMachine() {
        money += coin;
        startGame();
    }


    @Override
    public void startGame() {
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
            System.out.println("Твій баланс складає:" + money);
            System.out.println("Скільки ти хочеш поставити??");
            rate = getInput();
            doubl = rate * 2;
            tripl = rate * 3;
            jeckpot = rate * 10;
            System.out.println(slot1);
            System.out.println(slot2);
            System.out.println(slot3);
            if (slot1 != slot2 && slot1 != slot3 && slot2 != slot3) {
                System.out.println("Ти втратив гроші(");
                money -= rate;
            } else if (slot1 == slot2 || slot1 == slot3 || slot2 == slot3) {
                System.out.println("Вітаю ти виграв $" + doubl);
                money += doubl;

            } else if (slot1 == slot2 && slot3.equals("Вишня")) {
                System.out.println("Вітаю ти виграв $" + tripl);
                money += tripl;
            } else if (slot1.equals("Вишня") && slot2.equals("Вишня") && slot3.equals("Вишня")) {
                System.out.println("Вітаю! Ти зірвав джекпот $" + jeckpot);
            }
            System.out.println("Твій баланс складає " + money);
            System.out.println("Прожовжити? 1/2 ");
            ContinueSpin();

    }

    public Integer getInput() {
        try {
            int input = Integer.parseInt(handleInput());
            if (money >= input && input > 0) {
                return input;
            } else
                throw new Exception("У вас недостатньо коштiв");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return getInput();
        }
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
