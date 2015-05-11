package me.michaelkrauty.ServerSync.commands;

import com.google.gson.JsonObject;
import me.michaelkrauty.ServerSync.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created on 5/9/2015.
 *
 * @author michaelkrauty
 */
public class RealnameCommand implements CommandExecutor {

    private Main main;

    public RealnameCommand(Main main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(cmd.getUsage());
            return true;
        }
        Player player = (Player) sender;
        JsonObject obj = new JsonObject();
        obj.addProperty("action", "realname");
        obj.addProperty("player", player.getName());
        obj.addProperty("target", args[0]);
        main.bungee.out.println(obj);
        return true;
    }
}
