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
        if (args.length < 1) {
            sender.sendMessage(cmd.getUsage());
            return true;
        }
        if (!(sender instanceof Player))
            return true;
        Player player = (Player) sender;
        JsonObject obj = new JsonObject();
        obj.addProperty("action", "nickname");

        String name;
        if (main.getServer().getPlayer(args[0]) != null) {
            obj.addProperty("player", main.getServer().getPlayer(args[0]).getName());
            name = args[1] + " ";
            for (int i = 2; i < args.length; i++)
                name += args[i] + " ";
            name = name.trim();
        } else {
            obj.addProperty("player", player.getName());
            name = args[0] + " ";
            for (int i = 1; i < args.length; i++)
                name += args[i] + " ";
            main.getLogger().info(name);
            name = name.trim();
            main.getLogger().info(name);
        }

        obj.addProperty("nickname", name);
        main.bungee.out.println(obj);
        if (name.equalsIgnoreCase("off"))
            sender.sendMessage("You no longer have a nickname.");
        else
            sender.sendMessage("Your nickname is now " + name);
        return true;
    }
}
