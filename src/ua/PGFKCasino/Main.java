/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.PGFKCasino;

import org.fusesource.jansi.AnsiConsole;
import ua.PGFKCasino.Slots.SlotMachine;
import ua.PGFKCasino.menu.Menu;
import ua.PGFKCasino.menu.MenuHandler;
import ua.PGFKCasino.profile.Profile;
import ua.PGFKCasino.roulette.Roulette;

/**
 *
 * @author Andrey
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        Menu.printMain();
        MenuHandler.handleMain();
    }

}
