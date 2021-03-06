package ua.PGFKCasino.roulette;

import org.fusesource.jansi.Ansi;
import ua.PGFKCasino.IO;
import ua.PGFKCasino.interfaces.ICasinoGame;

import java.util.*;

import static org.fusesource.jansi.Ansi.ansi;

public class Roulette extends IO implements ICasinoGame {
    int money;
    /*ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,
                                                            20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36));*/

    Ansi redColor(String text) {
        return ansi().bg(Ansi.Color.RED).a(text).reset();
    }

    Ansi greenColor(String text) {
        return ansi().bg(Ansi.Color.GREEN).a(text).reset();
    }

    Ansi blackColor(String text) {
        return ansi().bg(Ansi.Color.BLUE).a(text).reset();
    }

    ArrayList<Ansi> numbers = new ArrayList<Ansi>(Arrays.asList(
            greenColor("0"),redColor("1"),blackColor("2"),redColor("3"),blackColor("4"),
            redColor("5"),blackColor("6"),redColor("7"),blackColor("8"),redColor("9"),
            blackColor("10"),blackColor("11"),redColor("12"),blackColor("13"),redColor("14"),
            blackColor("15"),redColor("16"),blackColor("17"),redColor("18"),redColor("19"),
            blackColor("20"),redColor("21"),blackColor("22"),redColor("23"),blackColor("24"),
            redColor("25"),blackColor("26"),redColor("27"),blackColor("28"),blackColor("29"),
            redColor("30"),blackColor("31"),redColor("32"),blackColor("33"),redColor("34"),
            blackColor("35"),redColor("36")));
    public Roulette(int balance) {
        this.money = balance;
        startGame();
    }

    public String runRoulette() {
        try {
            String values = "";
            int lastSpin = new Random().nextInt(((100) + 50));
            int lastNum;
            int i = 0;
            int clearer = 0;
            int numOfSpins = 0;
            do {
                lastNum = i - 1;
                if (i == numbers.size()) {
                    i = 0;
                }
                if (clearer % 12 == 0) {

                    clearConsole();
                    System.out.println("=====================================");
                    System.out.println(values + " <-");
                    System.out.println("=====================================");
                    values = "";
                    clearer = 0;
                    numOfSpins++;
                }
                values += "|" + numbers.get(i) + "| ";
                i++;
                clearer++;
                Thread.sleep(4);
            }
            while (!(numOfSpins == lastSpin));
            System.out.println();
            System.out.println("=====================================");
            System.out.println("Ваше число: " + numbers.get(lastNum));
            return String.valueOf(lastNum);
        }
        catch (Exception e) { e.printStackTrace();}
        return "100";
    }


    @Override
    public void startGame() {
        String random;
        System.out.println("Ви розпочали гру");
        System.out.print("Зробiть ставку: ");
        int bet = getInput(true);
        System.out.print("Виберіть число: ");
        int input = getInput(false);
        random = runRoulette();
        if (String.valueOf(input).equals(random)) {
            System.out.println("Повезло-повезло");
            money += bet;
        }
        else {
            System.out.println("Не повезло-не повезло");
            money -= bet;
        }
        System.out.println("Ставимо ще?");
        System.out.println("0. Нi, 1. Так");
        isGameContinue();
    }

    public void isGameContinue() {
        switch (handleInput().trim()) {
            case "0":
                stopGame();
                break;
            case "1":
                startGame();
                break;
            default:
                System.out.println("Незрозумiла команда. Введіть ще раз");
                isGameContinue();
                break;
        }
    }

    public Integer getInput(boolean isBet) {
        try {
            int input = Integer.parseInt(handleInput().trim());
            switch (String.valueOf(isBet)) {
                case "true":
                    if (money >= input) {
                        return input;
                    }
                    else
                        throw new Exception("У вас недостатньо коштiв");
                case "false":
                    if ((0 <= input) && (input <= 36)) {
                        return input;
                    }
                    else
                        throw new Exception("Введене вами число не в дiапазонi гри");
            }
        }
        catch (Exception e) {
            if (e.getMessage().equals("У вас недостатньо коштiв")) {
                System.out.println("У вас недостатньо коштiв. Зробіть меншу ставку");
                return getInput(true);
            }
            else {
                System.out.println("Введене вами число не в дiапазонi гри");
                System.out.println("Введiть число, яке потрапляє в дiапазон вiд 0 до 36 включно");
                return getInput(false);
            }
        }
        return null;
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
