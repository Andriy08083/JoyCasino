package ua.PGFKCasino.dice;

import ua.PGFKCasino.interfaces.ICasinoGame;

import java.util.*;

public class Dice implements ICasinoGame {



    public Dice() {

    }

    @Override
    public void startGame() {
        int cash = 1000;
        Scanner scanner = new Scanner (System.in);
        System.out.println("Ви розпочали гру");
        System.out.print("Зробіть ставку: ");
        int bet = scanner.nextInt();
        System.out.print("Введіть значення кубика: ");
        int dice = scanner.nextInt();
        int diceone = 1 + new Random().nextInt(6);
        int dicetwo = 1 + new Random().nextInt(6);
        int dicethree = 1 + new Random().nextInt(6);
        System.out.println("Значення кубиків: " + diceone + " | "  + dicetwo + " | "  + dicethree );
        if(diceone == dice && dicetwo == dice && dicethree == dice){
            cash = cash + bet * 3;
            System.out.println("Ваш виграш - " + bet * 4);
            System.out.println("Ваш баланс - " + cash);
        }else if(diceone == dice && dicetwo == dice || diceone == dice && dicethree == dice || dicetwo == dice && dicethree == dice){
            cash = cash + bet * 2;
            System.out.println("Ваш виграш - " + bet * 3);
            System.out.println("Ваш баланс - " + cash);
        }else if(diceone == dice || dicetwo == dice || dicethree == dice){
            cash = cash + bet;
            System.out.println("Ваш виграш - " + bet * 2);
            System.out.println("Ваш баланс - " + cash);
        }else {
            cash = cash - bet;
            System.out.println("Ваш програли");
            System.out.println("Ваш баланс - " + cash);
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
