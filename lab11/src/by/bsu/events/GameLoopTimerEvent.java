package by.bsu.events;

import java.util.EventObject;

public class GameLoopTimerEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public GameLoopTimerEvent(Object source) {
        super(source);
    }
}
