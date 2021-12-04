package captainsly.anvil.mechanics.items;

import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import captainsly.Main;
import captainsly.anvil.mechanics.entities.Actor;
import captainsly.anvil.ui.Anvil;
import captainsly.utils.Utils;

public class Potion extends Item {

	private static final long serialVersionUID = 8881073780504989577L;

	private String potionScript;

	public Potion(String itemId, String itemName) {
		super(itemId, itemName);
	}

	public Potion() {
	}

	public void setPotionScript(String potionScript) {
		this.potionScript = potionScript;
	}

	@Override
	public void onUse(Actor actor) {
		Main.globals.loadfile(Utils.WORKING_DIRECTORY + "scripts/" + potionScript).call();
		Main.globals.get("onUse").invoke(CoerceJavaToLua.coerce(actor));
		Anvil.writeToConsole("Added hp to player");
	}
}
