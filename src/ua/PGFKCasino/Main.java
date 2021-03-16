/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.PGFKCasino;

import org.fusesource.jansi.AnsiConsole;
import ua.PGFKCasino.menu.Menu;
import handlers.MenuHandler;
import ua.PGFKCasino.menu.SoundPlayer;

/**
 *
 * @author Andrey
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //new SoundPlayer("audio").start();
        AnsiConsole.systemInstall();
        Menu.printMain();
        MenuHandler.handleMain();
    }

}
