package me.michaelkrauty.ServerSync.config;

import me.michaelkrauty.ServerSync.Main;

/**
 * Created on 3/21/2015.
 *
 * @author michaelkrauty
 */
public class ConfigFile extends YamlFile {

    public ConfigFile(Main main) {
        super(main, "config");
    }

    public String getDBHost() {
        return yaml.getString("mysql.host");
    }

    public int getDBPort() {
        return yaml.getInt("mysql.port");
    }

    public String getDBUser() {
        return yaml.getString("mysql.user");
    }

    public String getDBPass() {
        return yaml.getString("mysql.pass");
    }

    public String getDBName() {
        return yaml.getString("mysql.database");
    }

    public String getDBPrefix() {
        return yaml.getString("mysql.table_prefix");
    }

    public String getBungeeHost() {
        return yaml.getString("bungee.host");
    }

    public int getBungeePort() {
        return yaml.getInt("bungee.port");
    }
}
