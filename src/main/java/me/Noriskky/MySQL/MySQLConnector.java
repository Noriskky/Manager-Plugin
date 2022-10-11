package me.Noriskky.MySQL;

import me.Noriskky.Manager;
import me.Noriskky.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.sql.*;

public class MySQLConnector {

    private Connection connection;
    private String host = Config.get().getString("MySQL.host");
    private String port = Config.get().getString("MySQL.port");
    private String database = Config.get().getString("MySQL.database");
    private String username = Config.get().getString("MySQL.username");
    private String password = Config.get().getString("MySQL.password");

    public void connect() {
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true", username, password);
                System.out.println("SQL Connection opened");
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
                System.out.println("SQL Connection closed!");
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public boolean isConnected() {
        return !(connection == null);
    }

    public Connection getConnection() {
        return connection;
    }

    public void update(String query) {
        if (isConnected()) {
            Bukkit.getServer().getScheduler().runTaskAsynchronously((Plugin) Manager.getInstance(), new Runnable() {
                @Override
                public void run() {
                    try {
                        Statement statement = Manager.getInstance().getMySQLConnector().connection.createStatement();
                        statement.execute(query);
                        statement.close();
                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }
                }
            });
        }
    }

    public ResultSet getResult(String query) {
        try {
            return Manager.getInstance().getMySQLConnector().connection.createStatement().executeQuery(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }
}