package ua.PGFKCasino;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IO {
    public String handleInput() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        }
        catch (Exception e) { }
        return "";
    }
}
