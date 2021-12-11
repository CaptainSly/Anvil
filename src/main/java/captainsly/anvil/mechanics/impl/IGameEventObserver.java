package captainsly.anvil.mechanics.impl;

import captainsly.anvil.mechanics.events.GameEvent;

/**
 * Observer Inteface for Game Events
 */
public interface IGameEventObserver {

    void update(GameEvent event);
}
