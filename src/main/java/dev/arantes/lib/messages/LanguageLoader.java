package dev.arantes.lib.messages;

import dev.arantes.lib.YamlConfig;
import dev.arantes.lib.exceptions.MessageNotLoadException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LanguageLoader {
    private Map<String, String> messages = null;

    private JavaPlugin plugin;
    private String path;

    public LanguageLoader(final JavaPlugin plugin, final String path) {
        this.plugin = plugin;
        this.path = path;
        messages = new HashMap<>();
    }

    public void load(final String filename, final File defaultFile) {
        YamlConfig messageFile = new YamlConfig(path + filename, plugin, defaultFile);

        messageFile.getConfig().getKeys(false).forEach(path ->
                messages.put(path.toLowerCase(), messageFile.getConfig().getString(path)));
    }

    public String getMessage(final String path) throws MessageNotLoadException {
        if (messages == null || !messages.containsKey(path.toLowerCase())){
            throw new MessageNotLoadException();
        }
        return messages.get(path.toLowerCase());
    }

}