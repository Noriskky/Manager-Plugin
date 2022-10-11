package me.Noriskky.listener;

import me.Noriskky.Manager;
import me.Noriskky.utils.Config;
import me.Noriskky.utils.VanishManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    private static String prefix = ChatColor.translateAlternateColorCodes('&', Config.get().getString("Manager.Main.Prefix"));
    private static String errorprefix = ChatColor.translateAlternateColorCodes('&',Config.get().getString("Manager.Main.ErrorPrefix"));
    @EventHandler
    public void onjoin(PlayerJoinEvent e) {
        Player player = (Player) e.getPlayer();

        if (player.hasPermission("Manager.vanish")) {
            if (player.hasMetadata("autovanish")) {
                VanishManager vanishManager = Manager.getInstance().getVanishmanager();
                vanishManager.setVanished(player, true);
                player.sendMessage("§8Du bist im §9Vanish§8.");
            } else {
                VanishManager vanishManager = Manager.getInstance().getVanishmanager();
                vanishManager.setVanished(player, false);
                player.sendMessage("§8Auto Vanish ist §9Deaktiviert§8 Du kannst es mit §9/AutoVanish aktivieren§8.");
            }
        }
        if (player.hasPermission("Manager.cmdspy")) {
            if (player.hasMetadata("cmdspy")) {
                VanishManager vanishManager = Manager.getInstance().getVanishmanager();
                vanishManager.setVanished(player, true);
                player.sendMessage("§8Cmdspy ist §9Aktiviert§8.");
            } else {
                VanishManager vanishManager = Manager.getInstance().getVanishmanager();
                vanishManager.setVanished(player, false);
                player.sendMessage("§8Cmdspy ist §9Deaktiviert§8 Du kannst es mit §9/Cmdspy Aktivieren§8.");
            }
        }
    }
}
