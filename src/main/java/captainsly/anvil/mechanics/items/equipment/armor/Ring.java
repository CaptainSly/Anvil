package captainsly.anvil.mechanics.items.equipment.armor;

import captainsly.anvil.mechanics.enums.EnumEquipmentSlotType;
import captainsly.anvil.mechanics.items.equipment.Equipment;

public class Ring extends Equipment {

	private static final long serialVersionUID = 3515438482089285851L;

	public Ring(String equipmentId, String equipmentName) {
		super(equipmentId, equipmentName, EnumEquipmentSlotType.RINGS);
	}

}
