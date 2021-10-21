package ua.PGFKCasino.profile;

import org.json.simple.JSONObject;
import ua.PGFKCasino.handlers.IOHandler;

public class Profile extends IOHandler {
    String name, balance;
    public Profile(String jsonFile) {
        try {
            JSONObject jsonObject = (JSONObject) readJSON("profiles/" + jsonFile + ".json");
            name = (String) jsonObject.get("name");
            balance = (String) jsonObject.get("balance");
        }
        catch (ClassCastException e) {
            System.out.println("Вибраний вами профiль бiльше не iснує");
        }
    }

    public String getName() {
        return name;
    }

    public String getBalance() {
        return balance;
    }

    public void getProfile() {
        System.out.println("Iм'я: " + name);
        System.out.println("Баланс: " + balance);
    }

    public void setDefaultProfile() {
        IOHandler.writeFile("defaultProfile", this.name);
    }
}
