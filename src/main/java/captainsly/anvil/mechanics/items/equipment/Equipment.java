package captainsly.anvil.mechanics.items.equipment;

import captainsly.anvil.mechanics.entities.Actor;
import captainsly.anvil.mechanics.enums.EnumEquipmentSlotType;
import captainsly.anvil.mechanics.enums.EnumEquipmentStat;
import captainsly.anvil.mechanics.enums.EnumStat;
import captainsly.anvil.mechanics.impl.IEquipable;
import captainsly.anvil.mechanics.items.Item;

public class Equipment extends Item implements IEquipable {

	private static final long serialVersionUID = -3897596030244524532L;
	private final EnumEquipmentSlotType equipSlotType;
	private int[] equipmentStats;

	public Equipment(String equipmentId, String equipmentName, EnumEquipmentSlotType equipSlotType) {
		super(equipmentId, equipmentName);
		this.equipSlotType = equipSlotType;
		equipmentStats = new int[EnumEquipmentStat.values().length];
	}

	@Override
	public void onEquip(Actor actor) {
		actor.modifyActorStat(EnumStat.ATK, getEquipmentStat(EnumEquipmentStat.ATK));
		actor.modifyActorStat(EnumStat.DEF, getEquipmentStat(EnumEquipmentStat.DEF));
		actor.modifyActorStat(EnumStat.SPD, getEquipmentStat(EnumEquipmentStat.SPD));
		actor.modifyActorStat(EnumStat.WIS, getEquipmentStat(EnumEquipmentStat.WIS));
		actor.modifyActorStat(EnumStat.MAX_HP, getEquipmentStat(EnumEquipmentStat.MAX_HP));
		actor.modifyActorStat(EnumStat.MAX_MP, getEquipmentStat(EnumEquipmentStat.MAX_MP));

	}

	@Override
	public void onUnequip(Actor actor) {
		actor.modifyActorStat(EnumStat.ATK, -1 * getEquipmentStat(EnumEquipmentStat.ATK));
		actor.modifyActorStat(EnumStat.DEF, -1 * getEquipmentStat(EnumEquipmentStat.DEF));
		actor.modifyActorStat(EnumStat.SPD, -1 * getEquipmentStat(EnumEquipmentStat.SPD));
		actor.modifyActorStat(EnumStat.WIS, -1 * getEquipmentStat(EnumEquipmentStat.WIS));
		actor.modifyActorStat(EnumStat.MAX_HP, -1 * getEquipmentStat(EnumEquipmentStat.MAX_HP));
		actor.modifyActorStat(EnumStat.MAX_MP, -1 * getEquipmentStat(EnumEquipmentStat.MAX_MP));
	}

	@Override
	public void onUse(Actor actor) {
		return; // Not used for Equipment
	}

	public void modifyEquipmentStat(EnumEquipmentStat stat, int amount) {
		equipmentStats[stat.ordinal()] += amount;
	}

	public int getEquipmentStat(EnumEquipmentStat stat) {
		return equipmentStats[stat.ordinal()];
	}

	public EnumEquipmentSlotType getEquipSlotType() {
		return equipSlotType;
	}

}
