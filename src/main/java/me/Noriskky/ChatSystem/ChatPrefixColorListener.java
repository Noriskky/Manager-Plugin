package me.Noriskky.ChatSystem;

import me.Noriskky.Manager;
import me.Noriskky.utils.Config;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatPrefixColorListener implements Listener {
    @EventHandler
    public void ChatPrefix(AsyncPlayerChatEvent event) {
        if (Config.get().getBoolean("Manager.ChatSystem.active")) {
            Player p = event.getPlayer();
            User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
            final Player all = event.getPlayer();
            final String name = event.getPlayer().getName();
            final String Prefix = user.getCachedData().getMetaData().getPrefix();
            final String namecolor = user.getCachedData().getMetaData().getSuffix();
            final ChatColor chatColor = org.bukkit.ChatColor.DARK_GRAY;
            final String prefixseparator = " §r§l§8●§r ";
            final String chatseparator = " §r§8§l»§7 ";
            if (event.getMessage().contains("%")) {
                p.sendMessage("§4Bitte benutze kein §l§n%§r§4§l§r§4 ,weil sonst sehr viele Fehlermeldungend in der Console ausgestuckt werden Wir Bitten um Ihr Verständnis");
                event.setCancelled(true);
            } else {
                event.setFormat(Prefix + prefixseparator + namecolor + name + chatseparator + ChatColor.translateAlternateColorCodes('&', event.getMessage()));
            }
        }
    }
}

