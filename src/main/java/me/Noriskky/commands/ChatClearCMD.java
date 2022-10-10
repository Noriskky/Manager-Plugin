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

public class ChatClearCMD implements CommandExecutor {
    private static String prefix = ChatColor.translateAlternateColorCodes('&',Config.get().getString("Manager.Main.Prefix"));
    private static String errorprefix = ChatColor.translateAlternateColorCodes('&',Config.get().getString("Manager.Main.ErrorPrefix"));
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            MessageUtil.sendSenderTranslatedMessage(sender, Config.get().getString("Manager.Main.OnlyPlayers").replace("%ErrorPrefix%",errorprefix).replace("%Prefix%",prefix));
        } else if (sender.hasPermission("Manager.ChatClear") || sender.isOp()) {
            if (args[0].equalsIgnoreCase("Global")) {
                for(int i = 0; i < 200; ++i) {
                    Bukkit.broadcastMessage("");
                }
                    MessageUtil.sendTranslatedBroadcast(Config.get().getString("Manager.Messages.ChatClear.Global").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")).replace("%Player%", sender.getName()));
            } else {
                if (args.length == 0) {
                    Player target = Bukkit.getPlayer(args[0]);
                    MessageUtil.sendPlayerTranslatedMessage(target,Config.get().getString("Manager.Messages.ChatClear.Target").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")).replace("%Target%",target.getName()));
                }
            }

        } else {
            MessageUtil.sendSenderTranslatedMessage(sender,Config.get().getString("Manager.Main.NoPermission").replace("%ErrorPrefix%", Config.get().getString("Manager.Main.ErrorPrefix")).replace("%Prefix%", Config.get().getString("Manager.Main.Prefix")));
        }
        return false;
    }
}
