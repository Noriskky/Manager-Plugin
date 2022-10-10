package me.Noriskky.commands;

import me.Noriskky.Manager;
import me.Noriskky.utils.Config;
import me.Noriskky.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SpeedCMD implements CommandExecutor , TabCompleter {
    private static String prefix = ChatColor.translateAlternateColorCodes('&', Config.get().getString("Manager.Main.Prefix"));
    private static String errorprefix = ChatColor.translateAlternateColorCodes('&',Config.get().getString("Manager.Main.ErrorPrefix"));
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            MessageUtil.sendSenderTranslatedMessage(commandSender,Config.get().getString("Manager.Main.OnlyPlayers").replace("%ErrorPrefix%", errorprefix).replace("%Prefix%", prefix));
            return false;
        }
        Player p = (Player) commandSender;
        if (!p.hasPermission("Manager.speed")) {
            MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Main.NoPermission").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
            return false;
        }
        if (strings.length == 0) {
            MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Messages.Speed.error").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
            return false;
        }
        int speed;
        try {
            speed = Integer.parseInt(strings[0]);
        } catch (NumberFormatException e) {
            MessageUtil.sendPlayerMessage(p,Config.get().getString("Manager.Messages.Speed.error").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
            return false;
        }
        if (speed < 1 || speed > 10) {
            MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Messages.Speed.error").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
            return false;
        }
        if (p.isFlying()) {
            p.setFlySpeed((float) speed / 10);
        } else {
            p.setWalkSpeed((float) speed/ 10);
        }
        MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Messages.Speed.error").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 1) {
            for (int i = 1; i < 11; i++) {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }
}
