package me.michaelkrauty.ServerSync.commands;

import me.michaelkrauty.ServerSync.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by micha_000 on 4/3/2015.
 */
public class TempbanCommand implements CommandExecutor {

    private final Main main;

    public TempbanCommand(Main main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        return true;
    }
}
