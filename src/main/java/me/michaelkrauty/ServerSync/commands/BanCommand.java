package me.michaelkrauty.ServerSync.commands;

import com.google.gson.JsonObject;
import me.michaelkrauty.ServerSync.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by micha_000 on 4/3/2015.
 */
public class BanCommand implements CommandExecutor {

    private Main main;

    public BanCommand(Main main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length == 2) {
            JsonObject obj = new JsonObject();
            obj.addProperty("action", "ban");
            obj.addProperty("player", args[1]);
            main.bungee.out.println(obj);
        } else if (args.length > 2) {
            JsonObject obj = new JsonObject();
            String reason = args[2];
            for (int i = 0; i < args.length; i++) {
                reason += args[i + 2] + " ";
            }
            reason = reason.trim();
            obj.addProperty("reason", reason);
            main.bungee.out.println(obj);
        } else
            sender.sendMessage(cmd.getUsage());
        return true;

    }
}
