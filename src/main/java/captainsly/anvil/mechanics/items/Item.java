package captainsly.anvil.mechanics.items;

import java.io.Serializable;

import captainsly.anvil.mechanics.entities.Actor;

public abstract class Item implements Serializable {

	private static final long serialVersionUID = -248581493506412301L;
	private String itemId; // Item's UUID
	private String itemName; // Item's Displayable Name

	public Item(String itemId, String itemName) {
		this.itemId = itemId;
		this.itemName = itemName;
	}

	public Item() {
	}

	public abstract void onUse(Actor actor);

	public String getItemName() {
		return itemName;
	}

	public String getItemId() {
		return itemId;
	}

}
