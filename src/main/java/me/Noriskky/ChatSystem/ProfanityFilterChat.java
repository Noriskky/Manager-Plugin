package me.Noriskky.ChatSystem;

import me.Noriskky.Manager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * JavaDoc this file!
 * Created: 30.09.2022
 *
 * @author WeLoveSpigotPlugins (welovespigotplugins@gmail.com)
 */
public class ProfanityFilterChat implements Listener{
    @EventHandler
    public void ProfanityFilter(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
    }
}
