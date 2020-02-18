package dev.arantes.lib;
import dev.arantes.lib.utils.NMSReflections;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ActionBar extends NMSReflections {
    private static Class<?> packetPlayOutChat = getNMSClass("PacketPlayOutChat");
    private static Constructor<?> constructor;

    private Object packet;

    public ActionBar(String message) {
        this.setMessage(message);

        try {
            constructor = packetPlayOutChat.getConstructor(
                    getNMSClass("IChatBaseComponent"), byte.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void show(Player player) {
        sendPacket(player, this.packet);
    }

    // todo improve performace with static classes and constructors
    public void setMessage(String message){
        try {
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
