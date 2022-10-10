package me.Noriskky;

import me.Noriskky.ChatSystem.ChatPrefixColorListener;
import me.Noriskky.ChatSystem.ProfanityFilterChat;
import me.Noriskky.commands.*;
import me.Noriskky.listener.CMDspyListener;
import me.Noriskky.listener.JoinListener;
import me.Noriskky.utils.Config;
import me.Noriskky.utils.VanishManager;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class Manager extends JavaPlugin {
    private static Manager plugin;
    private static Manager instance;
    private VanishManager vanishmanager;
    private Manager manager;

    @Override
    public void onEnable() {
        this.saveResource("config.yml", false);
        new Config();
        plugin = this;
        instance = this;
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
            if (provider != null) {
                LuckPerms api = provider.getProvider();
            }
        }
        command();
        listener();
        instance = this;
        this.vanishmanager = new VanishManager(this);
    }

    public static void main(@NotNull Player args) {
        ArrayList<Player> OnlinePlayers = new ArrayList<Player>();

        }

    public static Manager getPlugin() {
        return plugin;
    }

    public void command() {
        getCommand("msg").setExecutor(new MsgCMD());
        getCommand("Playerinfo").setExecutor(new PlayerInfo());
        getCommand("Vanish").setExecutor(new VanishCMD());
        getCommand("Fly").setExecutor(new FlyCMD());
        getCommand("AutoVanish").setExecutor(new AutovanishCMD());
        getCommand("CmdSpy").setExecutor(new CmdSpyCMD());
        getCommand("ChatClear").setExecutor(new ChatClearCMD());
        getCommand("help").setExecutor(new HelpCMD());
        getCommand("heal").setExecutor(new HealCMD());
        getCommand("speed").setExecutor(new SpeedCMD());
        getCommand("invsee").setExecutor(new InvSeeCMD());
    }
    public static Manager getInstance() {
        return instance;
    }

    public VanishManager getVanishmanager() { return vanishmanager; }

    public void listener() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ChatPrefixColorListener(), this);
        pm.registerEvents(new ProfanityFilterChat(), this);
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new CMDspyListener(), this);
    }

    @Override
    public void onDisable() {
    }
}
