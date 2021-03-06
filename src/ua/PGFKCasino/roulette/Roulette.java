package ua.PGFKCasino.roulette;

import ua.PGFKCasino.IO;
import ua.PGFKCasino.interfaces.ICasinoGame;

import java.util.Random;

public class Roulette extends IO implements ICasinoGame {
int money;

public Roulette(int balance) {
    this.money = balance;
    startGame();
}

    @Override
    public void startGame() {
        int random = new Random().nextInt(37);
        System.out.println("Ви розпочали гру");
        System.out.print("Зробiть ставку: ");
        int bet = getInput(true);
        System.out.print("Виберіть число: ");
        int input = getInput(false);
        if (input == random) {
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
