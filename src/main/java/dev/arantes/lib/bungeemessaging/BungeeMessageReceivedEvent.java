package dev.arantes.lib.bungeemessaging;

import com.google.common.io.ByteArrayDataInput;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BungeeMessageReceivedEvent extends Event {
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private String subChannel;
    private ByteArrayDataInput args;

    public BungeeMessageReceivedEvent(String subChannel, ByteArrayDataInput args) {
        this.subChannel = subChannel;
        this.args = args;
    }

    public String getSubChannel() {
        return subChannel;
    }

    public ByteArrayDataInput getArgs() {
        return args;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}