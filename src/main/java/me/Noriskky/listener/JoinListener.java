package me.Noriskky.listener;

import me.Noriskky.Manager;
import me.Noriskky.api.Api;
import me.Noriskky.utils.ColorUtil;
import me.Noriskky.utils.Config;
import me.Noriskky.utils.VanishManager;
import net.kyori.adventure.text.format.NamedTextColor;
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
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms") || Bukkit.getPluginManager().isPluginEnabled("PowerRanks")) {
            Scoreboard scoreboard = player.getScoreboard();
            scoreboard.registerNewTeam(Api.getPrimaryRank(player) + player.getName());
            scoreboard.getTeam(Api.getPrimaryRank(player) + player.getName()).setPrefix(Api.getPrefix(player) + " §r§l§8●§r ");
            scoreboard.getTeam(Api.getPrimaryRank(player) + player.getName()).setSuffix(Api.getSuffix(player));
            //scoreboard.getTeam(Api.getPrimaryRank(player) + player.getName()).setColor(ColorUtil.translate(Api.getNameColor(player)));
            scoreboard.getTeam(Api.getPrimaryRank(player) + player.getName()).addEntry(player.getName());
        }
    }
}

