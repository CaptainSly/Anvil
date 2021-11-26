package captainsly.anvil.mechanics.player;

import captainsly.anvil.mechanics.entities.Actor;
import captainsly.anvil.mechanics.journal.PlayerJournal;

public class Player extends Actor {

	private static final long serialVersionUID = 2077666720416949312L;
	private PlayerJournal playerJournal;

	public Player() {
		super("actPlayer");
		playerJournal = new PlayerJournal(this);
	}

	public PlayerJournal getPlayerJournal() {
		return playerJournal;
	}

}
