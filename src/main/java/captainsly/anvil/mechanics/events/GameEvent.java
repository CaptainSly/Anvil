package captainsly.anvil.mechanics.events;

import captainsly.Main;
import captainsly.anvil.mechanics.entities.Actor;
import captainsly.anvil.mechanics.enums.EnumGameEventType;

public class GameEvent {

	private String gameEventId;
	private String gameEventScript;
	private EnumGameEventType gameEventType;

	private String gameEventName;

	public GameEvent(String gameEventScript) {
		this.gameEventScript = gameEventScript;
		Main.scriptingEngine.loadScript(gameEventScript);
		gameEventId = (String) Main.scriptingEngine.runMethod("getEventId");
		gameEventName = (String) Main.scriptingEngine.runMethod("getEventName");
	}

	public void onActivate(Actor actor) {
		Main.scriptingEngine.loadScript(gameEventScript);
		Main.scriptingEngine.runMethod("onActivate", new Object[] { actor }, Actor.class);
	}

	public String getGameEventName() {
		return gameEventName;
	}

	public String getGameEventId() {
		return gameEventId;
	}
	
	public EnumGameEventType getGameEventType() {
		return gameEventType;
	}

	public void setGameEventId(String gameEventId) {
		this.gameEventId = gameEventId;
	}

}
