package captainsly.anvil.mechanics.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import captainsly.Main;
import captainsly.anvil.mechanics.container.EquipmentSlot;
import captainsly.anvil.mechanics.container.Inventory;
import captainsly.anvil.mechanics.entities.actrace.ActorRace;
import captainsly.anvil.mechanics.entities.cclass.CharacterClass;
import captainsly.anvil.mechanics.enums.EnumEquipmentSlotType;
import captainsly.anvil.mechanics.enums.EnumSkill;
import captainsly.anvil.mechanics.enums.EnumAbility;
import captainsly.anvil.mechanics.factions.Faction;
import captainsly.anvil.mechanics.items.equipment.Equipment;

public abstract class Actor implements Serializable {

	private static final long serialVersionUID = -1976893973670890905L;

	protected final String actorId; // Actor's UUID

	protected String actorName;

	private final List<Faction> actorFactionList;

	private ActorRace actorRace;
	private CharacterClass actorCharacterClass;

	private int[] actorAbilityScores;
	private int[] actorSkills, actorSkillsXp;
	private int actorArmorClass;

	// Equipment and Inventory
	private final Inventory actorInventory;

	private EquipmentSlot[] armorEquipmentSlots;
	private EquipmentSlot[] ringEquipmentSlots;
	private EquipmentSlot[] amuletEquipmentSlots;

	public Actor(String actorId, ActorRace actorRace, CharacterClass actorCharacterClass) {
		this.actorId = actorId;
		this.actorRace = actorRace;
		this.actorCharacterClass = actorCharacterClass;

		actorInventory = new Inventory();
		actorFactionList = new ArrayList<>();

		actorAbilityScores = new int[EnumAbility.values().length];
		actorSkills = new int[EnumSkill.values().length];
		actorSkillsXp = new int[EnumSkill.values().length];

		// Add The Actor's Racial Bonus to their stats and Skill Bonuses
		for (int i = 0; i < actorAbilityScores.length; i++) {
			actorAbilityScores[i] += actorRace.getActorRaceBenefits()[i];
			actorSkills[i] += actorCharacterClass.getCharacterClassSkillBonuses()[i];
		}

		actorArmorClass = 10 + getAbilityModifier(getActorAbilityScore(EnumAbility.DEXTERITY));

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

	public int getAbilityModifier(int abilityScore) {
		return (int) Math.floor((abilityScore - 10) / 2);
	}

	public void modifyArmorClass(int amount) {
		actorArmorClass += amount;
	}

	public void modifyActorAbilityScore(EnumAbility stat, int amount) {
		actorAbilityScores[stat.ordinal()] += amount;
	}

	public void modifyActorSkill(EnumSkill skill, int amount) {
		actorSkills[skill.ordinal()] += amount;
	}

	public void modifyActorSkillXp(EnumSkill skill, int amount) {
		actorSkillsXp[skill.ordinal()] += amount;
	}

	public void equipEquipment(Equipment equipment) {
		switch (equipment.getEquipSlotType()) {
		case NECK:
			Main.log.debug("Equiping a neck type item");

			// Find the first empty slot inside the neckEquipment array
			for (EquipmentSlot slot : amuletEquipmentSlots) {
				if (slot != null && slot.isEmpty()) // None of the slots should equal null, but just in case
					slot.addEquipment(equipment);
				else
					continue;
			}

			break;
		case RINGS:
			Main.log.debug("Equiping a ring type item");

			// Find the first empty slot inside the ringEquipment array
			for (EquipmentSlot slot : ringEquipmentSlots) {
				if (slot != null && slot.isEmpty())
					slot.addEquipment(equipment);
				else
					continue;
			}

			break;
		default:
			Main.log.debug("Equipping to slot type: " + equipment.getEquipSlotType());
			EquipmentSlot s = armorEquipmentSlots[equipment.getEquipSlotType().ordinal()];

			if (s.isEmpty())
				s.addEquipment(equipment);
			else {
				replaceSlot(s, equipment);
			}
			break;

		}

		equipment.onEquip(this);
	}

	private void replaceSlot(EquipmentSlot slot, Equipment equipment) {
		// Unequip and remove the current equipment from the slot
		slot.getSlotEquipment().onUnequip(this);
		slot.removeEquipment();

		// Add the new equipment then invoke the onEquip method with the current actor
		// as the parameter
		slot.addEquipment(equipment);
		slot.getSlotEquipment().onEquip(this);
	}

	public void unequipEquipment(EquipmentSlot slot) {
		slot.getSlotEquipment().onUnequip(this);
		slot.removeEquipment();
	}
	
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public void setActorAbilityScores(int[] actorAbilityScores) {
		this.actorAbilityScores = actorAbilityScores;
	}

	public void setActorSkills(int[] actorSkills) {
		this.actorSkills = actorSkills;
	}

	public void setActorRace(ActorRace actorRace) {
		this.actorRace = actorRace;
	}

	public void setActorCharacterClass(CharacterClass actorCharacterClass) {
		this.actorCharacterClass = actorCharacterClass;
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

	public int getActorArmorClass() {
		return actorArmorClass;
	}

	public int getActorTotalLevel() {
		// The Player's Level is a total of all there skills
		int level = 0;
		for (int i : actorSkills)
			level += i;

		return level;
	}

	public int getActorAbilityScore(EnumAbility stat) {
		return actorAbilityScores[stat.ordinal()];
	}

	public int getActorSkill(EnumSkill skill) {
		return actorSkills[skill.ordinal()];
	}

	public int getActorSkillXp(EnumSkill skill) {
		return actorSkillsXp[skill.ordinal()];
	}

	public int[] getActorAbilityScores() {
		return actorAbilityScores;
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
