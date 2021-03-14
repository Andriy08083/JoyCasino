package ua.PGFKCasino;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class IO {
    static ProcessBuilder cls =  new ProcessBuilder("cmd", "/c", "cls");

    public static String handleInput() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        }
        catch (Exception ignored) { }
        return "";
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                cls.inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }
        catch (Exception ignored) { }
    }

    public static Object readJSON(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String data = reader.readLine();
            reader.close();
            JSONParser parser = new JSONParser();
            return parser.parse(data);
        }
        catch (Exception ignored) {
        }
        return new Object();
    }

    public static void writeJSON(String name, int balance) {
        try {
            JSONObject jsonObject = new JSONObject();
            FileWriter writer = new FileWriter("profiles/" + name + ".json");
            jsonObject.put("name", name);
            jsonObject.put("balance", String.valueOf(balance));
            writer.write(jsonObject.toJSONString());
            writer.close();
        }
        catch (IOException ignored) {
            System.out.println("Сталася поммилка при збереженнi профiля");
        }

    }
}
