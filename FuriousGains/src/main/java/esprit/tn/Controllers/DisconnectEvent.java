package esprit.tn.Controllers;

import javafx.event.Event;
import javafx.event.EventType;

public class DisconnectEvent extends Event {
    public static final EventType<DisconnectEvent> DISCONNECT_EVENT_TYPE = new EventType<>(Event.ANY, "DISCONNECT");

    public DisconnectEvent() {
        super(DISCONNECT_EVENT_TYPE);
    }
}
