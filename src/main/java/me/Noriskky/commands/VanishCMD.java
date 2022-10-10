package me.Noriskky.commands;

import me.Noriskky.Manager;
import me.Noriskky.utils.Config;
import me.Noriskky.utils.MessageUtil;
import me.Noriskky.utils.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCMD implements CommandExecutor {
    private static String prefix = ChatColor.translateAlternateColorCodes('&', Config.get().getString("Manager.Main.Prefix"));
    private static String errorprefix = ChatColor.translateAlternateColorCodes('&',Config.get().getString("Manager.Main.ErrorPrefix"));
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        VanishManager vanishManager = Manager.getInstance().getVanishmanager();
        if (sender.hasPermission("Manager.Vanish")) {
            if (args.length == 1) {
                Player player = Bukkit.getPlayer(args[0]);
                if (player == null) {
                    MessageUtil.sendSenderTranslatedMessage(sender,Config.get().getString("Manager.Main.PlayerNotFound").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                    return true;
                }
                if (vanishManager.isVanished(player)) {
                    vanishManager.setVanished(player, false);
                    MessageUtil.sendSenderTranslatedMessage(sender,Config.get().getString("Manager.Messages.Vanish.deactivateother").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")).replace("%Target%",player.getName()));
                    MessageUtil.sendPlayerTranslatedMessage(player,Config.get().getString("Manager.Messages.Vanish.deactivate").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));

                } else {
                    vanishManager.setVanished(player, true);
                    MessageUtil.sendSenderTranslatedMessage(sender,Config.get().getString("Manager.Messages.Vanish.activateother").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")).replace("%Target%",player.getName()));
                    MessageUtil.sendPlayerTranslatedMessage(player,Config.get().getString("Manager.Messages.Vanish.activate").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                }
                return true;
            } else if (sender instanceof Player) {

                Player player = (Player) sender;

                if (vanishManager.isVanished(player)) {
                    vanishManager.setVanished(player, false);
                    MessageUtil.sendSenderTranslatedMessage(sender,Config.get().getString("Manager.Messages.Vanish.deactivate").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                } else {
                    vanishManager.setVanished(player, true);
                    MessageUtil.sendSenderTranslatedMessage(sender,Config.get().getString("Manager.Messages.Vanish.activate").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                }
                return true;
            }
        } else {
            MessageUtil.sendSenderTranslatedMessage(sender,Config.get().getString("Manager.Messages.Speed.error").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
        }
        return false;
    }
}
