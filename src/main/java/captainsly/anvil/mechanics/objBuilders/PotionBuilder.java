package captainsly.anvil.mechanics.objBuilders;

import captainsly.Main;
import captainsly.anvil.mechanics.items.Potion;

public class PotionBuilder {

	private Potion potion;
	
	public PotionBuilder createPotion(String itemId, String itemName) {
		this.potion = new Potion(itemId, itemName);
		Main.log.debug(potion.getItemId());
		return this;
	}
	
	public PotionBuilder addScript(String script) {
		this.potion.setPotionScript(script);
		return this;
	}
	
	public Potion build() {
		return this.potion;
	}
	
}
