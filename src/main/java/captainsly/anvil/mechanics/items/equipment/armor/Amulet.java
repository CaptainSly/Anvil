package captainsly.anvil.mechanics.items.equipment.armor;

import captainsly.anvil.mechanics.enums.EnumEquipmentSlotType;
import captainsly.anvil.mechanics.items.equipment.Equipment;

public class Amulet extends Equipment {

	private static final long serialVersionUID = 7658415531032718333L;

	public Amulet(String equipmentId, String equipmentName, int armorClassModifier) {
		super(equipmentId, equipmentName, EnumEquipmentSlotType.NECK, armorClassModifier);
	}

}
