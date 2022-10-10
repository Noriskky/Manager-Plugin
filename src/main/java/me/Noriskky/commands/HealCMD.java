package me.Noriskky.commands;

import me.Noriskky.Manager;
import me.Noriskky.utils.Config;
import me.Noriskky.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HealCMD implements CommandExecutor {
    private static String prefix = ChatColor.translateAlternateColorCodes('&', Config.get().getString("Manager.Main.Prefix"));
    private static String errorprefix = ChatColor.translateAlternateColorCodes('&',Config.get().getString("Manager.Main.ErrorPrefix"));
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (sender.hasPermission("Manager.heal")) {
                if (args.length == 0) {
                    if (!(p.getHealth() == p.getMaxHealth())) {
                        double maxhealth = p.getMaxHealth();
                        double health = p.getHealth();
                        double msghealtdoubletoint = maxhealth - health;
                        int msghealthinttostring = (int) msghealtdoubletoint;
                        double executehealt = msghealtdoubletoint + health;
                        String msghealth = String.valueOf((int) msghealthinttostring);
                        p.setHealth(executehealt);
                        MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Messages.Heal.succelfully").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")).replace("%health%",msghealth));
                    } else {
                        MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Messages.Heal.unsuccesfully").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                    }
                } else if (args.length == 1) {
                    Player t = Bukkit.getPlayer(args[0]);
                    if (p.hasPermission("Manager.heal.other")) {
                    if (t != null) {
                        if (!(p.getHealth() == p.getMaxHealth())) {
                            double maxhealth = p.getMaxHealth();
                            double health = p.getHealth();
                            double msghealtdoubletoint = maxhealth - health;
                            int msghealthinttostring = (int) msghealtdoubletoint;
                            double executehealt = msghealtdoubletoint + health;
                            String msghealth = String.valueOf((int) msghealthinttostring);
                            t.setHealth(executehealt);
                            MessageUtil.sendPlayerTranslatedMessage(t,Config.get().getString("Manager.Messages.Heal.succesfully").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")).replace("%health%",msghealth));
                            MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Messages.Heal.succesfullyother").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")).replace("%health%",msghealth).replace("%Target%",t.getName()));
                        } else {
                            MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Messages.Heal.unsuccesfully").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                        }
                    } else {
                        MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Main.PlayerNotFound").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                    }
                    } else {
                        MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Main.NoPermssion").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
                    }
                } else {
                    p.sendMessage("§4Usage: /heal <Player>!");
                }
            } else {
                MessageUtil.sendPlayerTranslatedMessage(p,Config.get().getString("Manager.Main.NoPermssion").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
            }
        } else {
            MessageUtil.sendSenderTranslatedMessage(sender,Config.get().getString("Manager.Main.OnlyPlayers").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
        }
        return false;
    }
}
