package by.bsu.events;

import java.util.EventObject;

public class SettingsConfiguredEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public SettingsConfiguredEvent(Object source) {
        super(source);
    }
}
