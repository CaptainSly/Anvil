package captainsly.anvil.mechanics.events;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import captainsly.Main;
import captainsly.anvil.mechanics.entities.Actor;

public class GameEvent {

	private String gameEventId;
	private String gameEventScript;

	private String gameEventName;

	public GameEvent(String gameEventScript) {
		this.gameEventScript = gameEventScript;

		// Setup game event name and id
		
		System.out.println("GameEventScript: " + gameEventScript);
		Main.globals.loadfile(gameEventScript).call();
		LuaValue getEventName = Main.globals.get("getEventName").call();
		LuaValue getEventId = Main.globals.get("getEventId").call();
		gameEventId = getEventId.tojstring();
		gameEventName = getEventName.tojstring();

	}

	public void onActivate(Actor actor) {
		Main.globals.loadfile(gameEventScript).call();
		Main.globals.get("onActivate").call(CoerceJavaToLua.coerce(actor));

	}

	public String getGameEventName() {
		return gameEventName;
	}

	public String getGameEventId() {
		return gameEventId;
	}

	public void setGameEventId(String gameEventId) {
		this.gameEventId = gameEventId;
	}

}
