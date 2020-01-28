package dev.arantes.lib.exceptions;


import dev.arantes.lib.messages.MessagesBase;

public class CustomMessageException extends Exception {
    public CustomMessageException(String message) {
        super(message);
    }
    public CustomMessageException(MessagesBase message) {
        super(message.toString());
    }
}
