package ua.PGFKCasino.handlers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOHandler {
    static ProcessBuilder cls =  new ProcessBuilder("cmd", "/c", "cls");

    public static String handleInput() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "CP866"));
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

    public static void writeFile(String file, String content) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        }
        catch (Exception ignored) { }
    }

    public static String osChecker() {
        if (System.getProperty("os.name").contains("Windows")) {
            return "Windows";
        }
        return "Linux";
    }
    public static void writeJSON(String path, String name, int balance) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name.replace("І","I").replace("і","i"));
            jsonObject.put("balance", String.valueOf(balance));
            writeFile(path, jsonObject.toJSONString());
        }
        catch (Exception ignored) {
            System.out.println("Сталася помилка при збереженнi профiля");
        }
    }

    public static List<String> getFiles(String folderPath) {
        int i = 1;
        List<String> fileNames = new ArrayList<>();
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.getName().contains(".json")) {
                fileNames.add(i++ + ". "+ file.getName().replace(".json", ""));
            }
        }
        return fileNames;
    }

    public static void deleteFile(String filepath) {
        File file = new File(filepath);
        if (file.delete()) {
            System.out.println("Профiль успiшно видалено");
        }
        else {
            System.out.println("Не вдалося видалити профiль");
        }
    }

    public static String readFile(String filepath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String data = reader.readLine();
            reader.close();
            return data;
        }
        catch (Exception e) { }
        return "";
    }
}
