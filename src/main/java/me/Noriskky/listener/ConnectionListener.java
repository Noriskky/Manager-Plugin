package me.Noriskky.listener;

import me.Noriskky.Manager;
import me.Noriskky.api.Api;
import me.Noriskky.utils.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;

public class ConnectionListener implements Listener {
    @EventHandler
    public void onjoin(PlayerJoinEvent e) {
        Player player = (Player) e.getPlayer();
        if (player.hasPermission("Manager.vanish")) {
            if (player.hasMetadata("autovanish")) {
                VanishManager vanishManager = Manager.getInstance().getVanishmanager();
                vanishManager.setVanished(player, true);
                player.sendMessage("§7You are in §9Vanish§7.");
            } else {
                VanishManager vanishManager = Manager.getInstance().getVanishmanager();
                vanishManager.setVanished(player, false);
                player.sendMessage("§7Auto Vanish is §9Deactivated§7 You can activate it with §9/AutoVanish§7.");
            }
        }
        if (player.hasPermission("Manager.cmdspy")) {
            if (player.hasMetadata("cmdspy")) {
                VanishManager vanishManager = Manager.getInstance().getVanishmanager();
                vanishManager.setVanished(player, true);
                player.sendMessage("§7Cmdspy is §9activated§7.");
            } else {
                VanishManager vanishManager = Manager.getInstance().getVanishmanager();
                vanishManager.setVanished(player, false);
                player.sendMessage("§7Cmdspy is §9Deactivated§7 You can activate it with §9/CmdSpy§7.");
            }
        }
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms") || Bukkit.getPluginManager().isPluginEnabled("PowerRanks")) {
            Scoreboard scoreboard = player.getScoreboard();
            scoreboard.registerNewTeam(Api.getPrefixTeam(player));
            scoreboard.getTeam(Api.getPrefixTeam(player)).setPrefix(Api.getPrefix(player) + " §r§l§8●§r ");
            scoreboard.getTeam(Api.getPrefixTeam(player)).setSuffix(Api.getSuffix(player));
            //scoreboard.getTeam(Api.getPrimaryRank(player) + player.getName()).setColor(ColorUtil.translate(Api.getNameColor(player)));
            scoreboard.getTeam(Api.getPrefixTeam(player)).addEntry(player.getName());
            ArrayList<String>
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms") || Bukkit.getPluginManager().isPluginEnabled("PowerRanks")) {
            Scoreboard scoreboard = player.getScoreboard();
            scoreboard.getTeam(Api.getPrefixTeam(player)).removeEntry(player.getName());
            scoreboard.getTeam(Api.getPrefixTeam(player)).unregister();
            Bukkit.getLogger().info(Api.getPrefixTeam(player) + " left & unregister Team: " + Api.getPrimaryRank(player) + player.getName());
            
        }
    }
    public static void deleteallTeams() {

    }
}

