package me.Noriskky.api;

import me.Noriskky.Manager;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import nl.svenar.PowerRanks.PowerRanks;
import nl.svenar.PowerRanks.api.PowerRanksAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Api {
    private static net.luckperms.api.LuckPerms luckPerms;

    //GetPrefix
    public static String getPrefix(Player p) {
        if (Bukkit.getPluginManager().isPluginEnabled("PowerRanks")) {
            PowerRanksAPI PowerAPI = PowerRanksAPI.plugin.loadAPI();
            String Rank = PowerAPI.getPrimaryRank(p);
            return ChatColor.translateAlternateColorCodes('&',PowerAPI.getPrefix(Rank));
        } else if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
            return ChatColor.translateAlternateColorCodes('&',user.getCachedData().getMetaData().getPrefix());
        }
        return null;
    }

    //GetSuffix
    public static String getSuffix(Player p) {
        if (Bukkit.getPluginManager().isPluginEnabled("PowerRanks")) {
            PowerRanksAPI PowerAPI = PowerRanksAPI.plugin.loadAPI();
            String Rank = PowerAPI.getPrimaryRank(p);
            return PowerAPI.getSuffix(Rank);
        } else if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
            return user.getCachedData().getMetaData().getSuffix();
        }
        return null;
    }

    //GetPrimaryGroup
    public static String getPrimaryRank(Player p) {
        if (Bukkit.getPluginManager().isPluginEnabled("PowerRanks")) {
            PowerRanksAPI PowerAPI = PowerRanksAPI.plugin.loadAPI();
            return PowerAPI.getPrimaryRank(p);
        } else if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
            return user.getCachedData().getMetaData().getPrimaryGroup();
        }
        return null;
    }
}


