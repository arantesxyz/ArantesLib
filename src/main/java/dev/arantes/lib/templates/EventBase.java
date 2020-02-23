package dev.arantes.lib.templates;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class EventBase implements Listener {

    public EventBase(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
