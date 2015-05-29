package me.michaelkrauty.ServerSync.commands;

import com.google.gson.JsonObject;
import me.michaelkrauty.ServerSync.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by micha_000 on 5/14/2015.
 */
public class RealnameCommand implements CommandExecutor {

    private final Main main;

    public RealnameCommand(Main main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        JsonObject obj = new JsonObject();
        obj.addProperty("sender", sender.getName());

        return true;
    }
}
