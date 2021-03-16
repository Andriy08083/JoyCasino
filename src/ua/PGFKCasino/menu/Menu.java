package ua.PGFKCasino.menu;

import handlers.IOHandler;

import java.util.List;

public class Menu {

    public static void printMain() {
        System.out.println("Вiтаю вас в нашому казино");
        System.out.println("Вибраний профiль: " + IOHandler.readFile("defaultProfile"));
        System.out.println("1. Iгри");
        System.out.println("2. Профiль");
        System.out.println("3. Правила");
        System.out.println("4. Про авторiв");
        System.out.println("5. Вихiд");
    }

    public static void printGames() {
        System.out.println("0. Повернутися в головне меню");
        System.out.println("1. Рулетка");
        System.out.println("2. Слот-машина");
        System.out.println("3. Кубики");
        System.out.println("4. Лото");
    }

    public static void printProfile() {
        System.out.println("0. Повернутися в головне меню");
        System.out.println("1. Отримати список профiлiв");
        System.out.println("2. Створити профiль");
    }

    public static void printProfileList() {
        List<String> profiles = IOHandler.getFiles("profiles");
        System.out.println("Наявнi профiлi: ");
        System.out.println("0. Повернутися в головне меню");
        for (String profile : profiles) {
            System.out.println(profile);
        }
        System.out.print("Виберiть профiль: ");
    }

    public static void printRules() {

    }

    public static void printAuthors() {
        System.out.println("Автори:");
        System.out.println("Лабатiй Андрiй");
        System.out.println("Потiчний Олександр");
        System.out.println("Пятачук Олесандр");
        System.out.println("Маргiтич Станiслав");
        System.out.println("Цiккер Дмитро");
    }
}
