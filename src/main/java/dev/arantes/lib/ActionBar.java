package dev.arantes.lib;
import dev.arantes.lib.utils.NMSReflections;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ActionBar extends NMSReflections {
    private Object packet;

    public ActionBar(String message) {
        this.setMessage(message);
    }

    public void show(Player player) {
        sendPacket(player, this.packet);
    }

    public void setMessage(String message){
        try {
            Class<?> packetPlayOutChat = getNMSClass("PacketPlayOutChat");
            Constructor<?> constructor = packetPlayOutChat.getConstructor(
                    getNMSClass("IChatBaseComponent"), byte.class);

            Class<?> iChatBaseComponent = getNMSClass("IChatBaseComponent");
            Class<?> chatSerializer = iChatBaseComponent.getClasses()[0];
            Method a = chatSerializer.getMethod("a", String.class);
            Object component = a.invoke(chatSerializer, "{\"text\": \"" + message + "\"}");

            this.packet = constructor.newInstance(component, (byte) 2);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
