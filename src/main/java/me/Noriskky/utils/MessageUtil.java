//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.Noriskky.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageUtil {
    public MessageUtil() {
    }

    public static void sendPlayerMessage(Player player, String message) {
        player.sendMessage(message);
    }

    public static void sendPlayerTranslatedMessage(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message).replace("%player%", player.getName()));
    }

    public static void sendSenderMessage(CommandSender sender, String message) {
        sender.sendMessage(message);
    }

    public static void sendSenderTranslatedMessage(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message).replace("%sender%", sender.getName()));
    }

    public static void sendBroadcast(String message) {
        Bukkit.broadcastMessage(message);
    }

    public static void sendTranslatedBroadcast(String message) {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void playSound(Player player, Location location, Sound sound, Integer volume, Integer pitch) {
        player.playSound(location, sound, (float)volume, (float)pitch);
    }
}
