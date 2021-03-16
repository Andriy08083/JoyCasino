package ua.PGFKCasino.menu;

import javazoom.jl.player.Player;

import java.io.FileInputStream;

public class SoundPlayer extends Thread {
    String audio;

    public SoundPlayer(String audio) {
        this.audio = audio;
    }

    public void run() {
        try {
            Player player = new Player(new FileInputStream("music/" + audio + ".mp3"));
            player.play();
        }
        catch (Exception ignored) { }
    }
}
