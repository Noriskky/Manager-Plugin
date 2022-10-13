package me.Noriskky.utils;

import me.Noriskky.api.Api;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

/**
 * JavaDoc this file!
 * Created: 25.09.2022
 *
 * @author WeLoveSpigotPlugins (welovespigotplugins@gmail.com)
 */
public class PlayerinfoUtils {
    public static void openPlayerinfo(Player p) {
        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(p);
        String namecolor = user.getCachedData().getMetaData().getSuffix();
        ArrayList<Player> list = new ArrayList<>(p.getServer().getOnlinePlayers());
        Inventory Playerinfo = Bukkit.createInventory(p, 45, "§l§8» §r§9Player Info");

        for (Player player : list) {
            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3 );
            ArrayList<String> lore = new ArrayList<>();
            int lifePlayer = (int) player.getHealth();
            lore.add(ChatColor.DARK_GRAY + "Rang §l»§r " + Api.getPrefix(p));
            lore.add(ChatColor.DARK_GRAY + "Health §l»§r§c " + p.getHealth() + ChatColor.DARK_GRAY + " §l/§r §c" + p.getMaxHealth());
            lore.add(ChatColor.DARK_GRAY + "Ip §l»§r &9" + p.getAddress().getHostName());
            lore.add(ChatColor.DARK_GRAY + "Gamemode §l»§r §9" + p.getGameMode());
            SkullMeta itemMeta = (SkullMeta) playerHead.getItemMeta();
            itemMeta.setOwner(p.getName());
            itemMeta.setDisplayName(Api.getPrefix(p) + " §r§l§8●§r " + namecolor + p.getName());
            playerHead.setItemMeta(itemMeta);
            Playerinfo.addItem(playerHead);

        }
        p.openInventory(Playerinfo);
    }
}
