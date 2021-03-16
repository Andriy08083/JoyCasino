package ua.PGFKCasino.lottery;

import ua.PGFKCasino.interfaces.ICasinoGame;

import java.util.*;

public class Lottery implements ICasinoGame {
    public static final int NUMBERS = 6;
    public static final int MAX_NUMBER = 40;

    public Lottery() {
        // отримати виграшні номери та набори квитків
        Set<Integer> winningNumbers = createWinningNumbers();
        Set<Integer> ticket = getTicket();
        System.out.println();
        
        // зберігає лише виграшні номери з квитка користувача
        Set<Integer> intersection = new TreeSet<Integer>(ticket);
        intersection.retainAll(winningNumbers);
        
        // Виворить результат 
        System.out.println("Номера рашого білету " + ticket);
        System.out.println("Виграшні номера " + 
                           winningNumbers);
        System.out.println();
        System.out.println("Ви отримали " + intersection.size() + 
                           " Виграшні номери");
        if (intersection.size() > 0) {
            double prize = 10 * Math.pow(2, intersection.size());
            System.out.println("Виграшний номер це " + 
                               intersection);
            System.out.println("Ваш приз це $" + prize);
        }
    }
    
    // генерує набір виграшних номерів лото
    public Set<Integer> createWinningNumbers() {
        Set<Integer> winningNumbers = new TreeSet<Integer>();
        Random r = new Random();
        while (winningNumbers.size() < NUMBERS) {
            int number = r.nextInt(MAX_NUMBER) + 1;
            winningNumbers.add(number);
        }
        return winningNumbers;
    }
    
    // читає лотерейний квиток гравця з консолі
    public Set<Integer> getTicket() {
        Set<Integer> ticket = new TreeSet<Integer>();
        Scanner console = new Scanner(System.in);
        System.out.print("Введіть свої " + NUMBERS + 
                         " унікальні номера лото: ");
        while (ticket.size() < NUMBERS) {
            int number = console.nextInt();
            ticket.add(number);
        }
        return ticket;
    }

    @Override
    public void startGame() {

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
