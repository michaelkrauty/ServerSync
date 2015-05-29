package me.michaelkrauty.ServerSync.commands;

import com.google.gson.JsonObject;
import me.michaelkrauty.ServerSync.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by micha_000 on 4/3/2015.
 */
public class NicknameCommand implements CommandExecutor {

    private Main main;

    public NicknameCommand(Main main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length > 1) {
            sender.sendMessage(cmd.getUsage());
            return true;
        }
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;
        JsonObject obj = new JsonObject();
        obj.addProperty("action", "nickname");
        obj.addProperty("player", player.getName());

        String name = args[2];
        for (int i = 0; i < args.length; i++)
            name += args[i + 2] + " ";
        obj.addProperty("nickname", name.trim());
        return true;
    }
}
