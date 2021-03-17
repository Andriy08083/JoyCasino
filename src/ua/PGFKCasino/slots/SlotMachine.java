package ua.PGFKCasino.slots;


import handlers.IOHandler;
import ua.PGFKCasino.interfaces.ICasinoGame;
import ua.PGFKCasino.profile.Profile;

import java.util.Random;



public class SlotMachine extends IOHandler implements ICasinoGame {
    public static final String RED = "\033[0;31m";
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    String name;
    Profile profile;
    int money;
    int coin;
    public static String getFruit() {
        String[] fruits = new String[]{RED+"Вишня"+RESET,"Апельсин",PURPLE+"Слива"+RESET,YELLOW+"Банан"+RESET,GREEN+"Гарбуз"+RESET,CYAN+"Бочка"+RESET};
        return fruits[new Random().nextInt(fruits.length)];
    }
    public SlotMachine(Profile pr) {
        profile = pr;
        loadGame();
        money += coin;

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
            System.out.println("Твій баланс складає:" +money);
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
        System.out.println("Гру завершено");
        saveGame();
    }

    @Override
    public void saveGame() {
        if (name != null) {
            writeJSON("profiles/" + name + ".json", name, money);
            System.out.println("Профiль успiшно збережено");
        }
        else
            System.out.println("Неможливо зберегти профiль");
    }

    @Override
    public void loadGame() {
        try {
            name = profile.getName();
            money = Integer.parseInt(profile.getBalance());
            startGame();
        }
        catch (Exception e) {
            stopGame();
        }
    }

    @Override
    public void getCurrentGameStats() {

    }
}
