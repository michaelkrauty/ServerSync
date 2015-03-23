package me.michaelkrauty.ServerSync;

import com.google.gson.JsonObject;
import me.michaelkrauty.ServerSync.config.ConfigFile;
import me.michaelkrauty.ServerSync.connection.BungeeConnector;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created on 3/21/2015.
 *
 * @author michaelkrauty
 */
public class Main extends JavaPlugin implements Listener, CommandExecutor {

    public ConfigFile config;
    public BungeeConnector bungee;
    public SQLConnector sql;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        if (!getDataFolder().exists())
            try {
                getDataFolder().mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        config = new ConfigFile(this);
        bungee = new BungeeConnector(this);
        sql = new SQLConnector(this);
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        JsonObject obj = new JsonObject();
        obj.addProperty("player", event.getPlayer().getName());
        obj.addProperty("message", event.getMessage());
        bungee.out.println(obj);
        event.setCancelled(true);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
        if (commandLabel.equalsIgnoreCase("ss")) {
            JsonObject obj = new JsonObject();
            String message = "";
            if (args.length == 0)
                message = "hello there.";
            else
                for (int i = 0; i < args.length; i++) {
                    message += args[i] + " ";
                }
            obj.addProperty("message", message.trim());
            bungee.out.println(obj);
        }
        return true;
    }
}
