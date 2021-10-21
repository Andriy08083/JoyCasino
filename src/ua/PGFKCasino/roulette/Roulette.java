package ua.PGFKCasino.roulette;

import org.fusesource.jansi.Ansi;
import ua.PGFKCasino.handlers.IOHandler;
import ua.PGFKCasino.interfaces.ICasinoGame;
import ua.PGFKCasino.menu.SoundPlayer;
import ua.PGFKCasino.profile.Profile;

import java.io.*;
import java.util.*;
import java.util.List;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;
import static ua.PGFKCasino.menu.Menu.printPGFK;

public class Roulette extends IOHandler implements ICasinoGame {
    String name;
    Profile profile;
    int money, bet, input;
    int num;
    Ansi inputColor;
    boolean isNumberBet;
    StringBuffer sb = new StringBuffer();
    OutputStreamWriter os;
    Ansi redColor(String text) {
        return ansi().bg(RED).a(text).reset();}
    Ansi greenColor(String text) {
        return ansi().bg(Ansi.Color.GREEN).a(text).reset();
    }

    Ansi blackColor(String text) {
        return ansi().bg(Ansi.Color.BLUE).a(text).reset();
    }

    ArrayList<Ansi> numbers = new ArrayList<>(Arrays.asList(
            greenColor("0"), redColor("1"), blackColor("2"), redColor("3"), blackColor("4"),
            redColor("5"), blackColor("6"), redColor("7"), blackColor("8"), redColor("9"),
            blackColor("10"), blackColor("11"), redColor("12"), blackColor("13"), redColor("14"),
            blackColor("15"), redColor("16"), blackColor("17"), redColor("18"), redColor("19"),
            blackColor("20"), redColor("21"), blackColor("22"), redColor("23"), blackColor("24"),
            redColor("25"), blackColor("26"), redColor("27"), blackColor("28"), blackColor("29"),
            redColor("30"), blackColor("31"), redColor("32"), blackColor("33"), redColor("34"),
            blackColor("35"), redColor("36")));


    public Roulette(Profile pr) {
        profile = pr;
        loadGame();
    }

    public String runRoulette() {
        try {
            if (osChecker().equals("Windows")) {
                os = new OutputStreamWriter(System.out, "CP866");
            } else {
                os = new OutputStreamWriter(System.out);
            }
            new SoundPlayer("rouletteRun").start();
            String border = "=====================================";
            String upperArrow = ansi().fg(CYAN).a("                        \\|/").reset().toString();
            String bottomArrow = ansi().fg(CYAN).a("                        /|\\").reset().toString();
            String borderLine = ansi().fg(YELLOW).a("|").reset().toString();
            StringBuffer values = new StringBuffer();
            Ansi space = ansi().fg(MAGENTA).a("--").reset();
            List<Ansi> allSpin = new ArrayList<>();
            List<Ansi> randomisedRoulette = new ArrayList<>(numbers);
            Collections.shuffle(randomisedRoulette);
            int lastSpin = 75;
            int i;
            for (i = 0; i <= lastSpin;) {
                Thread.sleep(100);
                clearConsole();
                if (i != 0) {
                    getCurrentGameStats();
                    sb.append(border)
                            .append("\n")
                            .append(upperArrow)
                            .append("\n")
                            .append(values.substring(0, (values.length() - 10)))
                            .append("\n")
                            .append(bottomArrow)
                            .append("\n")
                            .append(border)
                            .append("\n");
                    os.write(sb.toString());
                    os.flush();
                    values = new StringBuffer();
                    sb = new StringBuffer();
                }
                for (int k = 0; k < 12; k++) {
                    if (i == lastSpin) {
                        allSpin.add(randomisedRoulette.get((i + k) % 37));
                    }
                    values.append(borderLine).append(randomisedRoulette.get((i + k) % 37)).append(borderLine).append(space);
                }
                i++;
            }
            if (!isNumberBet) {
                Ansi color;
                if (String.valueOf(allSpin.get(allSpin.size() - 9)).contains("[41m")) {
                    color = ansi().bg(RED);
                    System.out.println("Ваш колiр: " + color + " " + ansi().reset());
                    return "2";
                }
                if (String.valueOf(allSpin.get(allSpin.size() - 9)).contains("[44m")) {
                    color = ansi().bg(BLUE);
                    System.out.println("Ваш колiр: " + color + " " + ansi().reset());
                    return "1";
                }
                if (String.valueOf(allSpin.get(allSpin.size() - 9)).contains("[42m")) {
                    color = ansi().bg(GREEN);
                    System.out.println("Ваш колiр: " + color + " " + ansi().reset());
                    return "0";
                }
            }
            else {
                System.out.println("Ваше число: " + allSpin.get(allSpin.size() - 9));
                return String.valueOf(allSpin.get(allSpin.size() - 9));
            }
        }
        catch (Exception e) { e.printStackTrace();}
        return "100";
    }


    @Override
    public void startGame() {
        clearConsole();
        printPGFK();
        String random;
        System.out.println("Ви розпочали гру");
        System.out.println("Ваш баланс: " + money);
        if (money <= 0) {
            System.out.println("Ви не можете грати, у вас нульовий баланс");
            stopGame();
            return;
        }
        input = isColorOrNum();
        if (input == 100) {
            stopGame();
            return;
        }
        System.out.print("Зробiть ставку: ");
        bet = getInput(1);

        random = runRoulette();
        if (String.valueOf(input).equals(random)) {
            new SoundPlayer("moneyWin").start();
            if (isNumberBet) {
                System.out.println("Ви вгадали число");
                System.out.println("Ваш виграш становить: " + (bet * 35));
                money += (bet * 35);
            }
            else {
                System.out.println("Ви вгадали колiр");
                System.out.println("Ваш виграш становить: " + (bet * 2));
                money += bet;
            }
        }
        else {
            new SoundPlayer("moneyLose").start();
            System.out.println("Ви програли :(");
            money -= bet;

        }
        saveGame();
        System.out.println("Ваш баланс становить: " + money);
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

    public Integer isColorOrNum() {
        System.out.println("Ставите на колiр, чи на число?");
        System.out.println("0. Колiр, 1. Число, 2. Покинути гру");
        switch (handleInput().trim()) {
            case "0":
                isNumberBet = false;
                System.out.println("0. Зелений, 1. Синiй, 2. Червоний");
                System.out.print("Виберiть колiр: ");
                return getInput(2);
            case "1":
                isNumberBet = true;
                System.out.print("Виберiть число: ");
                return getInput(0);
            case "2":
                return 100;
            default:
                System.out.println("Незрозумiла команда, повторiть ще раз");
                return isColorOrNum();
        }
    }

    public Integer getInput(int isBet) {
        try {
            switch (isBet) {
                case 0:
                    num = Integer.parseInt(handleInput().trim());
                    if ((0 <= num) && (num <= 36)) {
                        new SoundPlayer("moneyAccept").start();
                        return num;
                    } else
                        throw new Exception("Введене вами число не в дiапазонi гри");
                case 1:
                    num = Integer.parseInt(handleInput().trim());
                    if (money >= num && num > 0) {
                        return num;
                    } else
                        throw new Exception("У вас недостатньо коштiв");
                case 2:
                    switch (handleInput().trim()) {
                        case "0":
                            inputColor = greenColor(" ");
                            return 0;
                        case "1":
                            inputColor = blackColor(" ");
                            return 1;
                        case "2":
                            inputColor = redColor(" ");
                            return 2;
                        default:
                            System.out.println("Незрозумiла команда, повторiть ще раз");
                            return getInput(2);
                    }
            }
        }
        catch (Exception e) {
            if (e.getMessage().equals("У вас недостатньо коштiв")) {
                new SoundPlayer("moneyDeny").start();
                System.out.println("У вас недостатньо коштiв. Зробіть меншу ставку");
                return getInput(1);
            }
            else {
                System.out.println("Введене вами число не в дiапазонi гри");
                System.out.println("Введiть число, яке потрапляє в дiапазон вiд 0 до 36 включно");
                new SoundPlayer("moneyDeny").start();
                return getInput(0);
            }
        }
        return 0;
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
        }
        else
            System.out.println("Неможливо зберегти профiль");
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
        try {
            if (isNumberBet) {
                sb.append("Ваш баланс: ").append((money - bet)).append("\n");
                sb.append("Ваша ставка: ").append(bet).append("\n");
                sb.append("Число, на яке ви поставили: ").append(input).append("\n");
            } else {
                sb.append("Ваш баланс: ").append((money - bet)).append("\n");
                sb.append("Ваша ставка: ").append(bet).append("\n");
                sb.append("Колiр, на який ви поставили: ").append(inputColor).append("\n");
            }
        }
        catch (Exception ignored) { }
    }
}
