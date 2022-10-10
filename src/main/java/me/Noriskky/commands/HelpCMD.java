package me.Noriskky.commands;

import me.Noriskky.Manager;
import me.Noriskky.utils.Config;
import me.Noriskky.utils.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HelpCMD implements CommandExecutor {
    private static String prefix = ChatColor.translateAlternateColorCodes('&', Config.get().getString("Manager.Main.Prefix"));
    private static String errorprefix = ChatColor.translateAlternateColorCodes('&',Config.get().getString("Manager.Main.ErrorPrefix"));
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage(" ");
            p.sendMessage("§8/Gamemode - ändert dein Gamemode");
            p.sendMessage("§8/Fly - Aktiviert den Flugmodus");
            p.sendMessage("§8/Msg - Messenger also einfach normales msg.");
            p.sendMessage("§8/Cmdspy - zeigt dir was spieler für commands eingeben.");
            p.sendMessage("§8/vanish - machtdich unsichtbar für alle anderen.");
            p.sendMessage("§8/AutoVanish - aktiviert das wenn man joint automatisch im vanish ist.");
            p.sendMessage("§8/heal - Heilt dich oder einen anderen Spieler.");
            p.sendMessage(" ");
        } else {
            MessageUtil.sendSenderTranslatedMessage(sender, Config.get().getString("Manager.Main.OnlyPlayers").replace("%ErrorPrefix%",errorprefix).replace("%Prefix%",prefix));
        }
        return false;
    }
}
