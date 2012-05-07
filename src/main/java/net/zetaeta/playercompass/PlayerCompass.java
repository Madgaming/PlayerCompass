package net.zetaeta.playercompass;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerCompass extends JavaPlugin {
    public static Logger log;
    static Map<Player, Player> targets = new HashMap<Player, Player>();
    public void onDisable() {
        getLogger().info(this + " disabled!");
    }

    public void onEnable() {
        log = getLogger();
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getLogger().info(this + " enabled!");
    }

}

