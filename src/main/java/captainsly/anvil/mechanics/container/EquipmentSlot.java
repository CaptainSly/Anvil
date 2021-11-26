package captainsly.anvil.mechanics.container;

import captainsly.anvil.mechanics.enums.EnumEquipmentSlotType;
import captainsly.anvil.mechanics.items.equipment.Equipment;

public class EquipmentSlot implements Comparable<EquipmentSlot> {

	private final EnumEquipmentSlotType slotType;
	private Equipment equipment;
	private boolean isEmpty;

	public EquipmentSlot(EnumEquipmentSlotType slotType) {
		this.slotType = slotType;
		isEmpty = true;
	}

	public void addEquipment(Equipment equipment) {
		this.equipment = equipment;
		isEmpty = false;
	}

	public void removeEquipment() {
		this.equipment = null;
		isEmpty = true;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public Equipment getSlotEquipment() {
		return equipment;
	}

	public EnumEquipmentSlotType getSlotType() {
		return slotType;
	}

	@Override
	public int compareTo(EquipmentSlot slot) {
		return Boolean.compare(this.isEmpty, slot.isEmpty);
	}

}
