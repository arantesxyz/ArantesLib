package dev.arantes.lib.objects;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class EventBase implements Listener {

    public EventBase(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
