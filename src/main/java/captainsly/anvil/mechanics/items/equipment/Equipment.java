package captainsly.anvil.mechanics.items.equipment;

import captainsly.anvil.mechanics.entities.Actor;
import captainsly.anvil.mechanics.enums.EnumEquipmentSlotType;
import captainsly.anvil.mechanics.impl.IEquipable;
import captainsly.anvil.mechanics.items.Item;

public class Equipment extends Item implements IEquipable {

	private static final long serialVersionUID = -3897596030244524532L;
	private final EnumEquipmentSlotType equipSlotType;
	private final int armorClassModifier;

	public Equipment(String equipmentId, String equipmentName, EnumEquipmentSlotType equipSlotType,
			int armorClassModifier) {
		super(equipmentId, equipmentName);
		this.equipSlotType = equipSlotType;
		this.armorClassModifier = armorClassModifier;
	}

	@Override
	public void onUse(Actor actor) {
		return; // Not used for Equipment
	}

	public EnumEquipmentSlotType getEquipSlotType() {
		return equipSlotType;
	}

	@Override
	public void onEquip(Actor actor) {
	}

	@Override
	public void onUnequip(Actor actor) {
	}

}
