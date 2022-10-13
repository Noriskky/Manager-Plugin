package me.Noriskky.listener;

import me.Noriskky.Manager;
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
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(player);
        }
        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(player);
        Scoreboard scoreboard = player.getScoreboard();

        scoreboard.getTeam(user.getPrimaryGroup() + player.getName()).removeEntry(player.getName());
    }
}

