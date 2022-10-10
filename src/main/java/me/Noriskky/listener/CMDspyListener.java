package me.Noriskky.listener;

import me.Noriskky.utils.Config;
import me.Noriskky.utils.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class CMDspyListener implements Listener {
    private static String prefix = ChatColor.translateAlternateColorCodes('&', Config.get().getString("Manager.Main.Prefix"));
    private static String errorprefix = ChatColor.translateAlternateColorCodes('&',Config.get().getString("Manager.Main.ErrorPrefix"));
    @EventHandler
    public void cmdspy(PlayerCommandPreprocessEvent e) {
        Player p = (Player) e.getPlayer();
        if (p.hasMetadata("cmdspy")) {
            p.sendMessage("§9" + e.getPlayer().getName() + " §8» §9" + e.getMessage());
            MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Messages.CmdSpy.Format").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix").replace("%Player%",p.getName()).replace("%Command%",e.getMessage())));
        }
    }
}
