package captainsly.anvil.mechanics.items;

import captainsly.Main;
import captainsly.anvil.mechanics.entities.Actor;

public class Potion extends Item {

	private static final long serialVersionUID = 8881073780504989577L;

	private String potionScript;

	public Potion(String itemId, String itemName) {
		super(itemId, itemName);
	}

	public void setPotionScript(String potionScript) {
		this.potionScript = potionScript;
	}

	@Override
	public void onUse(Actor actor) {
		// TODO: Fix Script Usage
		// Run Script
		Main.scriptingEngine.loadScript("items/" + potionScript);
		Main.scriptingEngine.runMethod("onUse", new Object[] { actor }, Actor.class);
	}
}
