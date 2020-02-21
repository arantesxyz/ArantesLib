package dev.arantes.lib.bungeemessaging;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class MessageListener implements PluginMessageListener {
    private JavaPlugin plugin;

    public MessageListener(final JavaPlugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        plugin.getServer().getMessenger().registerIncomingPluginChannel(
                plugin,
                "BungeeCord",
                this
        );
    }

    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        final String subchannel = in.readUTF();

        BungeeMessageReceivedEvent event = new BungeeMessageReceivedEvent(subchannel, in);
        plugin.getServer().getPluginManager().callEvent(event);
    }

    public void sendMessage(Player player, String subchannel, String... arguments) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(subchannel);

        for (String arg : arguments) {
            out.writeUTF(arg);
        }

        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
}
