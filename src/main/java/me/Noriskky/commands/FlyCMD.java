package me.Noriskky.commands;

import me.Noriskky.Manager;
import me.Noriskky.utils.Config;
import me.Noriskky.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FlyCMD implements CommandExecutor {
    private static String prefix = ChatColor.translateAlternateColorCodes('&', Config.get().getString("Manager.Main.Prefix"));
    private static String errorprefix = ChatColor.translateAlternateColorCodes('&',Config.get().getString("Manager.Main.ErrorPrefix"));
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("Manger.fly")) {
                if (args.length == 0) {
                    if (p.getAllowFlight()) {
                        p.setAllowFlight(false);
                        MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Messages.Fly.deactivate").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                    } else {
                        p.setAllowFlight(true);
                        MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Messages.Fly.deactivate").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                    }
                } else if (args.length == 1) {
                    if (p.hasPermission("Manager.fly.other")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {

                        if (target.getAllowFlight()) {
                            target.setAllowFlight(false);
                            MessageUtil.sendPlayerTranslatedMessage(target,Config.get().getString("Manager.Messages.Fly.deactivate").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                            MessageUtil.sendPlayerTranslatedMessage(target,Config.get().getString("Manager.Messages.Fly.deactivateother").replace("%Target%",target.getName()).replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                        } else {
                            target.setAllowFlight(true);
                            MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Messages.Fly.activate").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                            MessageUtil.sendPlayerTranslatedMessage(target,Config.get().getString("Manager.Messages.Fly.activateother").replace("%Target%",target.getName()).replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                        }
                    } else {
                        MessageUtil.sendSenderTranslatedMessage(sender,Config.get().getString("Manager.Main.PlayerNotFound").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                    }

                    } else {
                        MessageUtil.sendSenderTranslatedMessage(sender,Config.get().getString("Manager.Main.NoPermission").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                    }
                } else {
                    p.sendMessage("§cUsage: /Fly <Spieler>");
                }
            } else {
                MessageUtil.sendSenderTranslatedMessage(sender,Config.get().getString("Manager.Main.NoPermission").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
            }
        } else {
            MessageUtil.sendSenderTranslatedMessage(sender,Config.get().getString("Manager.Main.OnlyPlayers").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
        }
        return false;
    }
}