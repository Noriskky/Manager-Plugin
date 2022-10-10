package me.Noriskky.utils;

import org.bukkit.ChatColor;

public class ColorUtil {
    public ColorUtil() {
    }

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
