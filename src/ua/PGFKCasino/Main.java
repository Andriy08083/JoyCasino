
package ua.PGFKCasino;

import org.fusesource.jansi.AnsiConsole;
import ua.PGFKCasino.menu.Menu;
import ua.PGFKCasino.handlers.MenuHandler;

/**
 *
 * @author Andrey
 */
public class Main {

    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        //new SoundPlayer("audio").start();
        Menu.printMain();
        MenuHandler.handleMain();
    }
}
