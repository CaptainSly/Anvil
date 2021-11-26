package captainsly.anvil.mechanics.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import captainsly.anvil.mechanics.container.EquipmentSlot;
import captainsly.anvil.mechanics.container.Inventory;
import captainsly.anvil.mechanics.entities.actrace.ActorRace;
import captainsly.anvil.mechanics.entities.cclass.CharacterClass;
import captainsly.anvil.mechanics.enums.EnumEquipmentSlotType;
import captainsly.anvil.mechanics.enums.EnumSkill;
import captainsly.anvil.mechanics.enums.EnumStat;
import captainsly.anvil.mechanics.factions.Faction;
import captainsly.anvil.mechanics.items.equipment.Equipment;

public abstract class Actor implements Serializable {

	private static final long serialVersionUID = -1976893973670890905L;

	protected final String actorId; // Actor's UUID

	protected String actorName;

	private final List<Faction> actorFactionList;

	private final ActorRace actorRace;
	private final CharacterClass actorCharacterClass;

	private int[] actorStats;
	private int[] actorSkills, actorSkillsXp;

	// Equipment and Inventory
	private final Inventory actorInventory;

	private EquipmentSlot[] armorEquipmentSlots;
	private EquipmentSlot[] ringEquipmentSlots;
	private EquipmentSlot[] amuletEquipmentSlots;

	public Actor(String actorId) {
		this.actorId = actorId;
		actorInventory = new Inventory();
		actorFactionList = new ArrayList<>();

		actorRace = new ActorRace("testActorRace");
		actorCharacterClass = new CharacterClass("testCharacterClass");

		actorStats = new int[EnumStat.values().length];
		actorSkills = new int[EnumSkill.values().length];
		actorSkillsXp = new int[EnumSkill.values().length];

		// Add The Actor's Racial Bonus to their stats and Skill Bonuses
		for (int i = 0; i < actorStats.length; i++) {
			actorStats[i] += actorRace.getActorRaceBenefits()[i];
			actorSkills[i] += actorCharacterClass.getCharacterClassSkillBonuses()[i];
		}

		// Setup Equipment Slots
		amuletEquipmentSlots = new EquipmentSlot[5]; // Does your chain hang low?
		ringEquipmentSlots = new EquipmentSlot[10]; // There is ten fingers
		armorEquipmentSlots = new EquipmentSlot[EnumEquipmentSlotType.values().length - 2];

		// Create Equipment Slots
		for (int i = 0; i < armorEquipmentSlots.length; i++)
			armorEquipmentSlots[i] = new EquipmentSlot(EnumEquipmentSlotType.values()[i]);

		for (int i = 0; i < ringEquipmentSlots.length; i++)
			ringEquipmentSlots[i] = new EquipmentSlot(EnumEquipmentSlotType.RINGS);

		for (int i = 0; i < amuletEquipmentSlots.length; i++)
			amuletEquipmentSlots[i] = new EquipmentSlot(EnumEquipmentSlotType.NECK);

	}

	public void modifyActorStat(EnumStat stat, int amount) {
		actorStats[stat.ordinal()] += amount;
		if (actorStats[stat.ordinal()] < 0)
			actorStats[stat.ordinal()] = 0;
	}

	public void modifyActorSkill(EnumSkill skill, int amount) {
		actorSkills[skill.ordinal()] += amount;
		if (actorSkills[skill.ordinal()] < 0)
			actorSkills[skill.ordinal()] = 0;
	}

	public void modifyActorSkillXp(EnumSkill skill, int amount) {
		actorSkillsXp[skill.ordinal()] += amount;
		if (actorSkillsXp[skill.ordinal()] < 0)
			actorSkillsXp[skill.ordinal()] = 0;
	}

	public void equipEquipment(Equipment equipment) {
		// TODO: Equip equipment on actor
	}

	public void unequipEquipment(EquipmentSlot slot) {
		// TODO: Unequip equipment from actor
	}

	public String getActorId() {
		return actorId;
	}

	public String getActorName() {
		return actorName;
	}

	public ActorRace getActorRace() {
		return actorRace;
	}

	public CharacterClass getActorCharacterClass() {
		return actorCharacterClass;
	}

	public int getActorStat(EnumStat stat) {
		return actorStats[stat.ordinal()];
	}

	public int getActorSkill(EnumSkill skill) {
		return actorSkills[skill.ordinal()];
	}

	public int getActorSkillXp(EnumSkill skill) {
		return actorSkillsXp[skill.ordinal()];
	}

	public int[] getActorStats() {
		return actorStats;
	}

	public int[] getActorSkills() {
		return actorSkills;
	}

	public int[] getActorSkillsXp() {
		return actorSkillsXp;
	}

	public EquipmentSlot[] getArmorEquipmentSlots() {
		return armorEquipmentSlots;
	}

	public EquipmentSlot[] getRingEquipmentSlots() {
		return ringEquipmentSlots;
	}

	public EquipmentSlot[] getAmuletEquipmentSlots() {
		return amuletEquipmentSlots;
	}

	public Inventory getActorInventory() {
		return actorInventory;
	}

	public List<Faction> getActorFactionList() {
		return actorFactionList;
	}

}
