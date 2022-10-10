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

public class InvSeeCMD implements CommandExecutor {
    private static String prefix = ChatColor.translateAlternateColorCodes('&', Config.get().getString("Manager.Main.Prefix"));
    private static String errorprefix = ChatColor.translateAlternateColorCodes('&',Config.get().getString("Manager.Main.ErrorPrefix"));
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (sender.hasPermission("Manager.invsee")) {
                if (args.length == 1) {
                    Player t = Bukkit.getPlayer(args[0]);
                    if (t == null) {
                        MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Main.PlayerNotFound").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                    } else {
                        p.openInventory(t.getInventory());
                    }
                } else {
                    p.sendMessage("§4Usage: /Invsee <Spieler>");
                }
            } else  {
                MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Main.NoPermssion").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
            }
        } else {
            MessageUtil.sendSenderTranslatedMessage(sender,Config.get().getString("Manager.Main.OnlyPlayers").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
        }
        return false;
    }
}
