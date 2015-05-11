package me.michaelkrauty.ServerSync.commands;

import com.google.gson.JsonObject;
import me.michaelkrauty.ServerSync.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by micha_000 on 4/3/2015.
 */
public class MuteCommand implements CommandExecutor {

    private Main main;

    public MuteCommand(Main main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        JsonObject obj = new JsonObject();
        obj.addProperty("action", "mute");
        obj.addProperty("player", args[1]);
        if (args.length == 2) {
            main.bungee.out.println(obj);
        } else if (args.length > 2) {
            String reason = "";
            for (int i = 0; i < args.length - 2; i++) {
                reason += args[i + 2] + " ";
            }
            reason = reason.trim();
            obj.addProperty("reason", reason);
            System.out.println(obj);
            // TODO: main.bungee.out.println(obj);
        } else
            sender.sendMessage(cmd.getUsage());
        return true;
    }
}
