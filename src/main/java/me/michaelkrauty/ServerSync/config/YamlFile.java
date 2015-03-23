package me.michaelkrauty.ServerSync.config;

import me.michaelkrauty.ServerSync.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Created on 3/21/2015.
 *
 * @author michaelkrauty
 */
public class YamlFile {

    public final YamlConfiguration yaml = new YamlConfiguration();
    public final File yamlFile;
    private final Main main;

    public YamlFile(Main main, String name) {
        this.main = main;
        yamlFile = new File(main.getDataFolder(), name + ".yml");
        if (!yamlFile.exists()) {
            main.saveResource(name + ".yml", false);
        }
        load();

    }

    public void load() {
        try {
            yaml.load(yamlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            yaml.save(yamlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
