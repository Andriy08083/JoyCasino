package ua.PGFKCasino.dice;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

import handlers.IOHandler;
import ua.PGFKCasino.interfaces.ICasinoGame;
import ua.PGFKCasino.profile.Profile;
import java.util.Random;


public class Dice extends IOHandler implements ICasinoGame{
    String name;
    Profile profile;
    int bet, money; 

    public Dice(Profile pr) {
        profile = pr;
        loadGame();
    }
    
    public void spinDice(){
        int dice = getInput();
        int diceone, dicetwo, dicethree;   
        String diceonecolor, dicetwocolor, dicethreecolor;
            
        diceone = 1 + new Random().nextInt(6);
        dicetwo = 1 + new Random().nextInt(6);
        dicethree = 1 + new Random().nextInt(6);
        
        if(diceone == dice){
            diceonecolor = ansi().fg(GREEN).a(diceone).reset().toString();
        }else {
            diceonecolor = ansi().fg(RED).a(diceone).reset().toString();
        }
        
        if(dicetwo == dice){
            dicetwocolor = ansi().fg(GREEN).a(dicetwo).reset().toString();
        }else {
            dicetwocolor = ansi().fg(RED).a(dicetwo).reset().toString();
        }
        
        if(dicethree == dice){
            dicethreecolor = ansi().fg(GREEN).a(dicethree).reset().toString();
        }else {
            dicethreecolor = ansi().fg(RED).a(dicethree).reset().toString();
        }
        System.out.println("Значення кубиків: " + diceonecolor + " | "  + dicetwocolor + " | "  + dicethreecolor );
        
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
    
    public void resultGame(int coef){
        if(coef == 4 || coef == 3 || coef == 2){
            money = money + bet * coef;
            System.out.println("Ваш виграш - " + bet * coef);
        }else if(coef == 0){
            System.out.println("Ви програли.");
        }
        System.out.println("Ваш баланс - " + money);
    }

    @Override
    public void startGame() {
        System.out.println("Ви розпочали гру!");
        System.out.println("Ваш баланс: " + money);
        
        if (money <= 0) {
            System.out.println("Ви не можете грати, у вас нульовий баланс.");
            stopGame();
            return;
        }
        
        System.out.print("Зробіть ставку: ");
            bet = getInput();
            money = money - bet;
        System.out.println("Ставка прийнята!");
        System.out.print("Введіть значення кубика: ");
        spinDice();
        
        saveGame();
        
        System.out.println("Ставимо ще?");
        System.out.println("0. Нi, 1. Так");
        isGameContinue();
    }
    
    public void isGameContinue(){
        switch (handleInput().trim()) {
            case "1":
                startGame();
                break;
            case "2":
                stopGame();
                break;
            default:
                System.out.println("Незрозумiла команда. Введіть ще раз.");
                isGameContinue();
                break;
        }
    }

    public Integer getInput() {
        try {
            int input = Integer.parseInt(handleInput());
            if (money >= input && input > 0) {
                return input;
            } else
                throw new Exception("У вас недостатньо коштiв.");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return getInput();
        }
    }
    
    @Override
    public void stopGame() {
        System.out.println("Гру завершено.");
        saveGame();
    }

    @Override
    public void saveGame() {
        if (name != null) {
            writeJSON("profiles/" + name + ".json", name, money);
            System.out.println("Профiль успiшно збережено.");
        }
        else
            System.out.println("Неможливо зберегти профiль.");
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
