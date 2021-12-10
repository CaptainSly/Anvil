package captainsly.anvil.mechanics.world;

import captainsly.anvil.mechanics.SaveSystem;
import captainsly.anvil.mechanics.locations.Location;
import captainsly.anvil.mechanics.player.Player;
import captainsly.anvil.ui.Anvil;

public class GameWorld {

    private final Anvil anvil; // Needs a reference to the main application for updating it's displays
    private final Player player; // Only needs one reference of the player, and the player should never change
    // unless it's being loaded upon creation

    private Location currentLocation;


    public GameWorld(Anvil anvil, Player player) {
        this.anvil = anvil;
        this.player = player;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
        anvil.setLocation(currentLocation);
    }

    /**
     * Updates the player involved views of the ui, anytime the player has triggered an event, or a player gets updated
     */
    public void updateGameWorld() {
    }
}
