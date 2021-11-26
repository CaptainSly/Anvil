package captainsly.anvil.mechanics.events;

import java.io.File;

public class GameEvent {

	private String gameEventId;
	private File gameEventScript;

	public GameEvent(String gameEventId, String gameEventScript) {
		this.gameEventId = gameEventId;
		this.gameEventScript = new File(gameEventScript);
	}

	public String getGameEventId() {
		return gameEventId;
	}

	public void setGameEventId(String gameEventId) {
		this.gameEventId = gameEventId;
	}

	public File getGameEventScript() {
		return gameEventScript;
	}

	public void setGameEventScript(File gameEventScript) {
		this.gameEventScript = gameEventScript;
	}

}
