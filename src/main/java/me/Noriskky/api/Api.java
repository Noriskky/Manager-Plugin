package me.Noriskky.api;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import nl.svenar.PowerRanks.api.PowerRanksAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

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

    //GetNameColor
    public static String getNameColor(Player p) {
        if (Bukkit.getPluginManager().isPluginEnabled("PowerRanks")) {
            PowerRanksAPI PowerAPI = PowerRanksAPI.plugin.loadAPI();
            String Rank = PowerAPI.getPrimaryRank(p);
            return PowerAPI.getNameColor(Rank);
        }
        return null;
    }

    public static String getPrefixTeam(Player p) {
        String PlayerName = p.getName();
        String Weight = "1";
        String Rank = Api.getPrimaryRank(p);
        String PrefixTeam = Weight + "-" + Rank + "-" + PlayerName;
        return PrefixTeam;
    }

    /*public static Player getPlayerfromTeam(String Team) {
        Player player;
        Scoreboard scoreboard = player.getScoreboard();
        scoreboard.
        return
    }*/

    /*public static String getWeighttoString(Player p) {
        if (Bukkit.getPluginManager().isPluginEnabled("PowerRanks")) {
            PowerRanksAPI PowerAPI = PowerRanksAPI.plugin.loadAPI();
            return PowerAPI.
        } else if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
            return user.getCachedData().getMetaData().getPrimaryGroup();
        }
        return null;
    }*/
}


