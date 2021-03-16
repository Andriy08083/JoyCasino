package ua.PGFKCasino.dice;

import handlers.IOHandler;
import ua.PGFKCasino.interfaces.ICasinoGame;
import java.util.Random;

public class Dice extends IOHandler implements ICasinoGame{
    int bet;
    int money = 0;
    int bonus = 100;

    public Dice() {
        money += bonus;
        System.out.println("Вітаю. Ви отримали бонус: "+ bonus + " гривень!");
        startGame();
    }
    
    public void spinDice(){
        int diceone = 1 + new Random().nextInt(6);
        int dicetwo = 1 + new Random().nextInt(6);
        int dicethree = 1 + new Random().nextInt(6);
        System.out.println("Значення кубиків: " + diceone + " | "  + dicetwo + " | "  + dicethree );
    }
    
    public void resultGame(int coef){
        if(coef == 4 || coef == 3 || coef == 2){
            money = money + bet * coef;
            System.out.println("Ваш виграш - " + bet * coef + " гривень!");
        }else if(coef == 0){
            System.out.println("Ви програли");
        }
        System.out.println("Ваш баланс - " + money + " гривень!");
    }

    @Override
    public void startGame() {
        System.out.println("Ви розпочали гру");
        
        System.out.print("Зробіть ставку: ");
            bet = getInput();
            money = money - bet;
        System.out.print("Ставка прийнята.");
        System.out.print("Введіть значення кубика: ");
            int dice = getInput();
            
        int diceone = 1 + new Random().nextInt(6);
        int dicetwo = 1 + new Random().nextInt(6);
        int dicethree = 1 + new Random().nextInt(6);
        System.out.println("Значення кубиків: " + diceone + " | "  + dicetwo + " | "  + dicethree );
        
        if(diceone == dice && dicetwo == dice && dicethree == dice){
            resultGame(4);
        }else if(diceone == dice && dicetwo == dice || diceone == dice && dicethree == dice || dicetwo == dice && dicethree == dice){
            resultGame(3);
        }else if(diceone == dice || dicetwo == dice || dicethree == dice){
            resultGame(2);
        }else {
            resultGame(0);
        }
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
