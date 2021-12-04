package captainsly.anvil.mechanics.impl;

import captainsly.anvil.mechanics.entities.Actor;

public interface IEquipable {

	void onEquip(Actor actor);
	
	void onUnequip(Actor actor);
	
}
