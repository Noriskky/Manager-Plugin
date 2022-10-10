package me.Noriskky.commands;

import me.Noriskky.Manager;
import me.Noriskky.utils.Config;
import me.Noriskky.utils.MessageUtil;
import me.Noriskky.utils.PlayerinfoUtils;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.haoshoku.nick.api.NickAPI;

import java.util.ArrayList;
import java.util.List;

public class MsgCMD implements CommandExecutor {
    private static String prefix = ChatColor.translateAlternateColorCodes('&',Config.get().getString("Manager.Main.Prefix"));
    private static String errorprefix = ChatColor.translateAlternateColorCodes('&',Config.get().getString("Manager.Main.ErrorPrefix"));
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {return false;}

        Player p = (Player) sender;
        if (args.length >= 1) {
            Player target = (Player) Bukkit.getPlayer(args[0]);
            if (target == null) {
                MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Main.PlayerNotFound").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                return true;
            }
            if (target instanceof Player) {
                String message = "";
                for (int i = 1; i < args.length; i++) {
                    message = message + " " + args[i];
                }
                if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
                    User Senderuser = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
                    User Targetuser = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(target);
                    String TRang = Targetuser.getCachedData().getMetaData().getPrefix() + Targetuser.getCachedData().getMetaData().getSuffix();
                    String PRang = Senderuser.getCachedData().getMetaData().getPrefix() + Senderuser.getCachedData().getMetaData().getSuffix();
                    MessageUtil.sendPlayerTranslatedMessage(p, Config.get().getString("Manager.Messages.Msg.sender").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")).replace("%Target%", target.getName()).replace("%Sender%", p.getName()).replace("%P-Rang%", PRang).replace("%T-Rang%", TRang).replace("%Message%", message));
                    MessageUtil.sendPlayerTranslatedMessage(target, Config.get().getString("Manager.Messages.Msg.target").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")).replace("%Target%", target.getName()).replace("%Sender%", p.getName()).replace("%P-Rang%", PRang).replace("%T-Rang%", TRang).replace("%Message%", message));
                    target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 5, 1);

                } else {
                    MessageUtil.sendPlayerTranslatedMessage(p, Config.get().getString("Manager.Messages.Msg.sender").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")).replace("%Target%", target.getName()).replace("%Sender%", p.getName()).replace("%Message%", message));
                    MessageUtil.sendPlayerTranslatedMessage(target, Config.get().getString("Manager.Messages.Msg.target").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")).replace("%Target%", target.getName()).replace("%Sender%", p.getName()).replace("%Message%", message));
                    target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 5, 1);
                }
            }
        } else {
            p.sendMessage("§4Usage: /msg <Player> <Message>!");
        }
        return false;
        }
}
