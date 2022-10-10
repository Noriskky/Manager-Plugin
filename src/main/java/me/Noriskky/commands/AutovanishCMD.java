package me.Noriskky.commands;

import de.Herbystar.TTA.BossBar.TTA_BossBar;
import de.Herbystar.TTA.TTA_Methods;
import me.Noriskky.Manager;
import me.Noriskky.utils.Config;
import me.Noriskky.utils.MessageUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Painting;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

public class AutovanishCMD implements CommandExecutor {
    private static String prefix = ChatColor.translateAlternateColorCodes('&',Config.get().getString("Manager.Main.Prefix"));
    private static String errorprefix = ChatColor.translateAlternateColorCodes('&',Config.get().getString("Manager.Main.ErrorPrefix"));
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission("Vanish.autovanish")) {
                Player p = (Player) sender;
                if (p.hasMetadata("autovanish")) {
                    p.removeMetadata("autovanish", Manager.getInstance());
                    MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Messages.AutoVanish.deactivate").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));

                } else {
                    p.setMetadata("autovanish", new FixedMetadataValue(Manager.getInstance(), true));
                    MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Messages.AutoVanish.activate").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                }
            } else {
                MessageUtil.sendSenderTranslatedMessage(sender,Config.get().getString("Manager.Main.NoPermission").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
            }
        } else {
            MessageUtil.sendSenderTranslatedMessage(sender,Config.get().getString("Manager.Main.PlayerNotFound").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
        }
        return false;
    }
}
