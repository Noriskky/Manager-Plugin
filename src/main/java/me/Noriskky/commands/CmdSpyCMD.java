package me.Noriskky.commands;

import me.Noriskky.Manager;
import me.Noriskky.utils.Config;
import me.Noriskky.utils.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

public class CmdSpyCMD implements CommandExecutor {
    private static String prefix = ChatColor.translateAlternateColorCodes('&', Config.get().getString("Manager.Main.Prefix"));
    private static String errorprefix = ChatColor.translateAlternateColorCodes('&',Config.get().getString("Manager.Main.ErrorPrefix"));
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (sender.hasPermission("Manager.cmdspy")) {
                if (((Player) sender).hasMetadata("cmdspy")) {
                    p.removeMetadata("autovanish", Manager.getInstance());
                    MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Messages.CmdSpy.deactivate").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                } else {
                    p.setMetadata("cmdspy", new FixedMetadataValue(Manager.getInstance(), true));
                    MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Messages.CmdSpy.activate").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                }
            } else { MessageUtil.sendSenderTranslatedMessage(sender,Config.get().getString("Manager.Main.OnlyPlayers").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix"))); }
        } else { MessageUtil.sendSenderTranslatedMessage(sender,Config.get().getString("Manager.Main.OnlyPlayers").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix"))); }
        return false;
    }
}
