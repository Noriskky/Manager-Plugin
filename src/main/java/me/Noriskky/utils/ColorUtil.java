package me.Noriskky.utils;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public class ColorUtil {
    public ColorUtil() {
    }

    public static @NotNull ChatColor translate(String message) {
        return ChatColor.valueOf(ChatColor.translateAlternateColorCodes('&', message));
    }
}
