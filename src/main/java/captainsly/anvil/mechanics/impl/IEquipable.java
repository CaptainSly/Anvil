package captainsly.anvil.mechanics.impl;

import captainsly.anvil.mechanics.entities.Actor;

public interface IEquipable {

	/**
	 * Determines what happens when the item is equipped
	 */
	void onEquip(Actor actor);

	void onUnequip(Actor actor);

}
