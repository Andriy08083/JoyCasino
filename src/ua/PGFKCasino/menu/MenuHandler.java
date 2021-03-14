package ua.PGFKCasino.menu;


import ua.PGFKCasino.Slots.SlotMachine;
import ua.PGFKCasino.dice.Dice;
import ua.PGFKCasino.profile.Profile;
import ua.PGFKCasino.roulette.Roulette;

import static ua.PGFKCasino.IO.handleInput;

public class MenuHandler extends Menu {

    public static void handleMain() {
        System.out.print("Введіть команду: ");
        switch (handleInput().trim()) {
            case "1":
                printGames();
                handleGames();
                break;
            case "2":
                printProfile();
                handleProfile();
                break;
            case "3":
                printRules();
                handleRules();
                break;
            case "4":
                printAuthors();
                handleAuthors();
                break;
            case "5":
                System.exit(0);
                break;
            default:
                System.out.println("Незрозумiла команда. Введiть ще раз");
                System.out.print("Введіть команду: ");
                handleMain();
        }
    }

    public static void handleGames() {
        System.out.print("Введіть команду: ");
        switch (handleInput().trim()) {
            case "0":
                printMain();
                handleMain();
                break;
            case "1":
                new Roulette(new Profile("andrey"));
                break;
            case "2":
                new SlotMachine();
                break;
            case "3":
                new Dice();
                break;
            case "4":
                break;
            default:
                System.out.println("Незрозумiла команда. Введiть ще раз");
                System.out.print("Введіть команду: ");
                handleGames();
        }
    }

    public static void handleProfile() {

    }

    public static void handleRules() {

    }

    public static void handleAuthors() {
        System.out.println("0. Повернутися в головне меню");
        System.out.print("Введіть команду: ");
        handleInput();
        printMain();
        handleMain();
    }
}
