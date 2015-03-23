package me.michaelkrauty.ServerSync.connection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.michaelkrauty.ServerSync.Main;

import javax.net.SocketFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;

/**
 * Created on 3/21/2015.
 *
 * @author michaelkrauty
 */
public class BungeeConnector implements Runnable {

    private final Main main;
    public PrintWriter out;
    private Thread t;

    public BungeeConnector(Main main) {
        this.main = main;
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    public void run() {
        try {
            Socket socket = SocketFactory.getDefault().createSocket(main.config.getBungeeHost(), main.config.getBungeePort());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            String input;
            while ((input = in.readLine()) != null) {
                JsonObject obj = new JsonParser().parse(input).getAsJsonObject();
                int action = obj.get("action").getAsInt();
                if (action == 0)
                    main.getServer().broadcastMessage(obj.get("message").getAsString());
            }
        } catch (Exception e) {
            main.getLogger().log(Level.SEVERE, "Could not connect to bungee! Make sure your bungee server is running ServerSync_Bungee.jar, and the host/port match.");
            e.printStackTrace();
            main.getLogger().log(Level.SEVERE, "Disabling plugin...");
            main.getServer().getPluginManager().disablePlugin(main);

        }
    }
}
