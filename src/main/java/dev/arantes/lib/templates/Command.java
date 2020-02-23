package dev.arantes.lib.templates;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.Arrays;
import java.util.List;

public abstract class Command extends BukkitCommand {

    protected Command(
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
    }

    @Override
    public boolean execute(CommandSender commandSender, String label, String[] args) {
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return super.tabComplete(sender, alias, args);
    }
}
