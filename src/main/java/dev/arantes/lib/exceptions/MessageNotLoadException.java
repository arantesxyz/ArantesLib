package dev.arantes.lib.exceptions;

public class MessageNotLoadException extends CustomMessageException {
    public MessageNotLoadException() {
        super("Message wasn't loaded.");
    }
}