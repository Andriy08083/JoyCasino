package ua.PGFKCasino.menu;

import ua.PGFKCasino.handlers.IOHandler;
import ua.PGFKCasino.profile.Profile;

import java.util.List;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

public class Menu {

    public static void printMain() {
        IOHandler.clearConsole();
        printPGFK();
        System.out.print(ansi().fg(RED));
        System.out.println("Вiтаю вас в нашому казино");
        System.out.println("Вибраний профiль: " + IOHandler.readFile("defaultProfile"));
        System.out.println("Баланс профiлю: " + new Profile(IOHandler.readFile("defaultProfile")).getBalance());
        System.out.println("1. Iгри");
        System.out.println("2. Профiль");
        System.out.println("3. Про авторiв");
        System.out.println("4. Вихiд");
        System.out.print(ansi().reset());
    }

    public static void printPGFK() {
        System.out.println(ansi().fg(RED).a(" ▐█▀█").toString() + ansi().fg(BLUE).a(" ▐█▀▀▀ ").toString() + ansi().fg(RED).a(" ▐█▀▀").toString() + ansi().fg(BLUE).a(" ▐█ ▐▀  ").reset().toString() + "▐█▀█  ▄█▀▄  ▄█▀▀█ ▐██ ██▄  █▌ ▐█▀▀█▌");
        System.out.println(ansi().fg(RED).a(" ▐█▄█").toString() + ansi().fg(BLUE).a(" ▐█ ▀█▌").toString() + ansi().fg(RED).a(" ▐█▀▀").toString() + ansi().fg(BLUE).a(" ▐██▌   ").reset().toString() + "▐█   ▐█▄▄▐█ ▀▀█▄▄  █▌ ▐█ █ █  ▐█  █▌" + "   " +  ansi().fg(BLUE)  .a(" █ █  ▄▀█").reset());
        System.out.println(ansi().fg(RED).a(" ▐█  ").toString() + ansi().fg(BLUE).a(" ▐██▄█▌").toString() + ansi().fg(RED).a(" ▐█  ").toString() + ansi().fg(BLUE).a(" ▐█ ▐▄  ").reset().toString() + "▐█▄█ ▐█  ▐█ █▄▄█▀ ▐██ ██  ██▌ ▐██▄█▌" + " █ " +  ansi().fg(YELLOW).a(" █▄█  █▀█").reset());
    }


    public static void printGames() {
        IOHandler.clearConsole();
        printPGFK();
        System.out.print(ansi().fg(RED));
        System.out.println("0. Повернутися в головне меню");
        System.out.println("1. Рулетка");
        System.out.println("2. Слот-машина");
        System.out.println("3. Кубики");
        System.out.println("4. Лото");
    }

    public static void printProfile() {
        IOHandler.clearConsole();
        printPGFK();
        System.out.print(ansi().fg(RED));
        System.out.println("0. Повернутися в головне меню");
        System.out.println("1. Отримати список профiлiв");
        System.out.println("2. Створити профiль");
    }

    public static void printProfileList() {
        IOHandler.clearConsole();
        printPGFK();
        System.out.print(ansi().fg(RED));
        List<String> profiles = IOHandler.getFiles("profiles");
        System.out.println("Наявнi профiлi: ");
        System.out.println("0. Повернутися в головне меню");
        for (String profile : profiles) {
            System.out.println(profile);
        }
        System.out.print("Виберiть профiль: ");
    }


    public static void printAuthors() {
        IOHandler.clearConsole();
        printPGFK();
        System.out.print(ansi().fg(RED));
        System.out.println("Автори:");
        System.out.println("Лабатiй Андрiй");
        System.out.println("Потiчний Олександр");
        System.out.println("Пятачук Олесандр");
        System.out.println("Маргiтич Станiслав");
        System.out.println("Цiккер Дмитро");
    }
}
