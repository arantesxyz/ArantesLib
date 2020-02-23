package dev.arantes.lib.templates;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public abstract class SelfRegisteredCommand extends BukkitCommand {
    private static Field bukkitCommandMap;

    public SelfRegisteredCommand(
            String name,
            String permission,
            String usageMessage,
            String permissionMessage,
            String description,
            String... aliases
    ) {
        super(name, description, usageMessage, Arrays.asList(aliases));
        setPermission(permission);
        setPermissionMessage(permissionMessage);

        register();
    }

    private void register() {
        try {
            if (bukkitCommandMap == null) {
                bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                bukkitCommandMap.setAccessible(true);
            }

            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            commandMap.register(this.getName(), this);
        } catch(NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public abstract boolean execute(CommandSender commandSender, String label, String[] args);

    @Override
    public abstract List<String> tabComplete(CommandSender sender, String alias, String[] args)
            throws IllegalArgumentException;
}
