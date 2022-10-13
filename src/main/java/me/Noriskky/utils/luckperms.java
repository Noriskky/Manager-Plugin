package me.Noriskky.utils;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class luckperms {

    private static net.luckperms.api.LuckPerms luckPerms;
        public static String getPrefix(Player p) {
            User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
            return user.getCachedData().getMetaData().getPrefix();
        }

    }


