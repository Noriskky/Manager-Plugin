package me.Noriskky.listener;

import me.Noriskky.Manager;
import me.Noriskky.utils.Config;
import me.Noriskky.utils.VanishManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.WeightNode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

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
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(player);
                }
            User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(player);
            Scoreboard scoreboard = player.getScoreboard();

            scoreboard.registerNewTeam(user.getPrimaryGroup() + player.getName());
            scoreboard.getTeam(user.getPrimaryGroup() + player.getName()).setPrefix(user.getCachedData().getMetaData().getPrefix() + " §r§l§8●§r ");
            scoreboard.getTeam(user.getPrimaryGroup() + player.getName()).setSuffix(user.getCachedData().getMetaData().getSuffix());
        scoreboard.getTeam(user.getPrimaryGroup() + player.getName()).setDisplayName(user.getCachedData().getMetaData().getSuffix() + player.getName());
            scoreboard.getTeam(user.getPrimaryGroup() + player.getName()).addEntry(player.getName());
        }
    }

