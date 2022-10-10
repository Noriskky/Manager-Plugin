
package me.Noriskky.utils;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
    public Config() {
    }

    public static FileConfiguration get() {
        File file = new File("plugins//Manager//config.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration;
    }

    public static FileConfiguration getRaw(String path) {
        File file = new File(path);
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration;
    }

    public static Boolean exist(String name) {
        File file = new File("plugins//Manager//" + name + ".yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return file.exists() ? true : false;
    }

    public static Boolean hasBooleanTrue(String name, String path) {
        File file = new File("plugins//Manager//" + name + ".yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration.getBoolean(path) ? true : false;
    }

    public static void save(String name) {
        File file = new File("plugins//Manager//" + name + ".yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        try {
            configuration.save(file);
        } catch (IOException var4) {
            System.out.println("Couldn't save file");
        }

    }

    public static void reload(String name) {
        File file = new File("plugins//Manager//" + name + ".yml");
        YamlConfiguration.loadConfiguration(file);
    }

    public static String set(String files, String option, Object value) {
        File file = new File("plugins//Manager//" + files + ".yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        if (!configuration.isSet(option)) {
            configuration.set(option, value);
        }

        try {
            configuration.save(file);
        } catch (IOException var6) {
            var6.printStackTrace();
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cError creating a player file."));
        }

        return files;
    }
}
