package ua.PGFKCasino.slots;


import ua.PGFKCasino.handlers.IOHandler;
import ua.PGFKCasino.interfaces.ICasinoGame;
import ua.PGFKCasino.menu.SoundPlayer;
import ua.PGFKCasino.profile.Profile;

import java.util.Random;

import static ua.PGFKCasino.menu.Menu.printPGFK;


public class SlotMachine extends IOHandler implements ICasinoGame {
    public static final String RED = "\033[0;31m";
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    String name;
    Profile profile;
    int money;//гроші
    int coin;//внутрішня валюта
    //Масив для слот
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
    /*
        метод який починає гру
     */
    public void startGame() {
        if (money == 0) {
            stopGame();
            return;
        }
        clearConsole();
        printPGFK();
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
            System.out.println("Твiй баланс складає:" +money);
            System.out.println("Скiльки ти хочеш поставити??");
            rate = getInput();
            doubl = rate * 2;
            tripl = rate * 3;
            jeckpot = rate * 10;
            System.out.println(slot1);
            System.out.println(slot2);
            System.out.println(slot3);
            if (slot1 != slot2 && slot1 != slot3 && slot2 != slot3) {
                System.out.println("Ти втратив грошi(");
                new SoundPlayer("moneyLose").start();
                money -= rate;
            } else if (slot1 == slot2 || slot1 == slot3 || slot2 == slot3) {
                System.out.println("Вiтаю ти виграв $" + doubl);
                new SoundPlayer("moneyWin").start();
                money += doubl;

            } else if (slot1 == slot2 && slot3.equals("Вишня")) {
                System.out.println("Вітаю ти виграв $" + tripl);
                new SoundPlayer("moneyWin").start();
                money += tripl;
            } else if (slot1.equals("Вишня") && slot2.equals("Вишня") && slot3.equals("Вишня")) {
                System.out.println("Вiтаю! Ти зiрвав джекпот $" + jeckpot);
                new SoundPlayer("moneyWin").start();
            }
            System.out.println("Твiй баланс складає " + money);
            System.out.println("Прожовжити? 1.Так 2.Нi ");
            ContinueSpin();

    }
    /*
       Метод для перевірки балансу
     */
    public Integer getInput() {
        try {
            int input = Integer.parseInt(handleInput());
            new SoundPlayer("moneyAccept").start();
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

    /*
        Метод для продовження гри
     */
    public void ContinueSpin(){
        switch (handleInput().trim()) {
            case "1":
                startGame();
                break;
            case "2":
                stopGame();
                break;
            default:
                System.out.println("Незрозумiла команда. Введiть ще раз");
                ContinueSpin();
                break;
        }
    }
   /*
      метод який зупиняє гру
    */
    @Override
    public void stopGame() {
        System.out.println("Гру завершено");
        saveGame();
    }

    @Override
    /*
      Метод для збереження
     */
    public void saveGame() {
        if (name != null) {
            writeJSON("profiles/" + name + ".json", name, money);
            System.out.println("Профiль успiшно збережено");
        }
        else
            System.out.println("Неможливо зберегти профiль");
    }

    @Override
    /*
            Метод завантаження профілю
     */
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
