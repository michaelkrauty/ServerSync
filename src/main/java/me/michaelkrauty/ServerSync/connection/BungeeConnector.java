package me.michaelkrauty.ServerSync.connection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.michaelkrauty.ServerSync.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import javax.net.SocketFactory;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created on 3/21/2015.
 *
 * @author michaelkrauty
 */
public class BungeeConnector implements Runnable {

    private final Main main;
    public PrintWriter out;
    private Thread t;
    private boolean error = false;

    public BungeeConnector(Main main) {
        this.main = main;
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    public void run() {
        while (!t.isInterrupted()) {
            Socket socket;
            try {
                socket = SocketFactory.getDefault().createSocket(main.config.getBungeeHost(), main.config.getBungeePort());
                error = false;
            } catch (Exception e) {
                if (!error) {
                    main.getLogger().severe("Could not connect to bungee! Make sure your bungee server is running ServerSync_Bungee.jar, and the host/port match.");
                    error = true;
                }
                continue;
            }
            try {
                main.getLogger().info("Established connection to bungee server.");
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                String input;
                while ((input = in.readLine()) != null) {
                    main.getLogger().info("DATA: " + input);
                    JsonObject obj = new JsonParser().parse(input).getAsJsonObject();
                    String action = obj.get("action").getAsString();
                    if (action.equalsIgnoreCase("chat")) {
                        for (Player player : main.getServer().getOnlinePlayers())
                            player.sendMessage(obj.get("message").getAsString().replace('&', ChatColor.COLOR_CHAR));
                        main.getLogger().info("[CHAT] " + obj.get("message").getAsString());
                    } else if (action.equalsIgnoreCase("realname")) {
                        for (Player player : main.getServer().getOnlinePlayers())
                            if (player.getName().equals(obj.get("player").getAsString()))
                                player.sendMessage(obj.get("target").getAsString() + "'s real name is " + obj.get("realname").getAsString());
                    } else {
                        Player player = main.getServer().getPlayer(obj.get("player").getAsString());
                    }
                }
            } catch (SocketException e) {
                main.getLogger().severe("Lost connection to bungee server! Attempting to reconnect...");
                error = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
