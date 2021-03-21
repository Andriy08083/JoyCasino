package ua.PGFKCasino.lottery;

import ua.PGFKCasino.handlers.IOHandler;
import ua.PGFKCasino.interfaces.ICasinoGame;
import ua.PGFKCasino.profile.Profile;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.ansi;
import static ua.PGFKCasino.menu.Menu.printPGFK;

public class Lottery extends IOHandler implements ICasinoGame {
    public static final int NUMBERS = 6;
    public static final int MAX_NUMBER = 40;
    Profile profile;
    String name;
    double prize;
    int money;
    public Lottery(Profile pr) {
        profile = pr;
        loadGame();
    }

    // генерує набір виграшних номерів лото
    public static Set<Integer> createWinningNumbers() {
        Set<Integer> winningNumbers = new TreeSet<>();
        Random r = new Random();
        while (winningNumbers.size() < NUMBERS) {
            int number = r.nextInt(MAX_NUMBER) + 1;
            winningNumbers.add(number);
        }
        return winningNumbers;
    }

    // читає лотерейний квиток гравця з консолі
    public static Set<Integer> getTicket() {
        Set<Integer> ticket = new TreeSet<>();
        Scanner console = new Scanner(System.in);
        System.out.print("Введiть вашi "+ NUMBERS +
                " унiкальнi номера лото: ");
        while (ticket.size() < NUMBERS) {
            int number = console.nextInt();
            ticket.add(number);
        }
        return ticket;
    }

    @Override
    public void startGame() {
        clearConsole();
        printPGFK();
        if (money < 30) {
            System.out.println("Ви не можете грати, у вас нульовий баланс");
            stopGame();
            return;
        }
        money -= 30;
        System.out.println("З вашого рахунку знято 30 баксiв");
        System.out.println("Гра починається");
        // отримати виграшні номери та набори квитків
        Set<Integer> winningNumbers = createWinningNumbers();
        Set<Integer> ticket = getTicket();
        System.out.println();

        // зберігає лише виграшні номери з квитка користувача
        Set<Integer> intersection = new TreeSet<>(ticket);
        intersection.retainAll(winningNumbers);

        // Виворить результат
        System.out.println(ansi().fg(BLUE).a("Вашi номера: " + ticket).reset());
        System.out.println();
        System.out.println("Виграшнi номера: " + winningNumbers);
        System.out.println();
        System.out.println("Ви отримали: " + intersection.size() + " Виграшнi номери");
        if (intersection.size() > 0) {
            prize = 10 * Math.pow(2, intersection.size());
            System.out.println("Виграшний номер це: " + intersection);
            System.out.println("Ваш приз це: " + "$" + prize);
        }
        money += (int) prize;
        System.out.println("Ваш баланс: " + money);
        saveGame();
        isGameContinue();
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
            return;
        }
        else
            System.out.println("Неможливо зберегти профiль");
        return;

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
    public void isGameContinue() {
        System.out.println("0 - Завершити гру. 1 - Ставити ще");
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
    @Override
    public void getCurrentGameStats() {

    }
}
