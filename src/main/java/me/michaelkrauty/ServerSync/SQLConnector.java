package me.michaelkrauty.ServerSync;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;

/**
 * Created on 3/21/2015.
 *
 * @author michaelkrauty
 */
public class SQLConnector {

    private final String jdbc;
    private final String user;
    private final String pass;
    private final String prefix;
    private Main main;
    private Connection connection;

    public SQLConnector(Main main) {
        this.main = main;
        jdbc = "jdbc:mysql://" + main.config.getDBHost() + ":" + main.config.getDBPort() + "/" + main.config.getDBName();
        user = main.config.getDBUser();
        pass = main.config.getDBPass();
        prefix = main.config.getDBPrefix();
        openConnection();
        checkTables();
    }

    public synchronized void openConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                main.getLogger().info("Connecting to database...");
                connection = DriverManager.getConnection(jdbc, user, pass);
                main.getLogger().info("Connected to database.");
            }
        } catch (Exception e) {
            main.getLogger().log(Level.SEVERE, "Connection to database failed!");
            e.printStackTrace();
            main.getLogger().log(Level.SEVERE, "Disabling plugin...");
            main.getServer().getPluginManager().disablePlugin(main);
        }
    }

    public synchronized void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    main.getLogger().info("Closed connection to database.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void checkTables() {
        openConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `" + prefix + "users` (uuid varchar(256) PRIMARY KEY, banned timestamp, ban_time int(255), muted timestamp, mute_time int(255), nickname varchar(256), admin tinyint(1));");
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
