package me.Noriskky.listener;

import me.Noriskky.Manager;
import me.Noriskky.api.Api;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;

public class QuitListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e) throws InterruptedException {
        Player player = e.getPlayer();
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms") || Bukkit.getPluginManager().isPluginEnabled("PowerRanks")) {
            Scoreboard scoreboard = player.getScoreboard();
            scoreboard.getTeam(Api.getPrimaryRank(player) + player.getName()).removeEntry(player.getName());
            Thread.sleep(20);
            scoreboard.getTeam(Api.getPrimaryRank(player) + player.getName()).unregister();
            Bukkit.getLogger().info(Api.getPrefix(player) + player.getName() + " left & unregister Team: " + Api.getPrimaryRank(player) + player.getName());
        }
    }
}

