package captainsly.anvil.mechanics.entities;

import captainsly.Main;
import captainsly.anvil.mechanics.container.EquipmentSlot;
import captainsly.anvil.mechanics.container.Inventory;
import captainsly.anvil.mechanics.entities.actrace.ActorRace;
import captainsly.anvil.mechanics.entities.cclass.CharacterClass;
import captainsly.anvil.mechanics.enums.EnumAbility;
import captainsly.anvil.mechanics.enums.EnumEquipmentSlotType;
import captainsly.anvil.mechanics.enums.EnumSkill;
import captainsly.anvil.mechanics.factions.Faction;
import captainsly.anvil.mechanics.items.equipment.Equipment;
import captainsly.anvil.mechanics.magic.Spell;
import captainsly.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Actor implements Serializable {

    private static final long serialVersionUID = -1976893973670890905L;

    protected final String actorId; // Actor's UUID

    protected String actorName;
    protected String actorDescription;

    private List<Faction> actorFactionList;

    private ActorRace actorRace;
    private CharacterClass actorCharacterClass;

    // Stats
    private int[] actorAbilityScores;
    private int[] actorSkills;

    private int actorCurrentHealth, actorMaxHealth, actorCurrentMana, actorMaxMana;
    private int actorLevel, actorExperience;

    // Equipment and Inventory
    private Inventory actorInventory;

    private EquipmentSlot[] armorEquipmentSlots;
    private EquipmentSlot[] ringEquipmentSlots;
    private EquipmentSlot[] amuletEquipmentSlots;

    private List<Spell> actorSpellList;

    public Actor(String actorId, ActorRace actorRace, CharacterClass actorCharacterClass) {
        this.actorId = actorId;
        this.actorRace = actorRace;
        this.actorCharacterClass = actorCharacterClass;

        actorInventory = new Inventory();
        actorFactionList = new ArrayList<>();
        actorSpellList = new ArrayList<>();

        // Instantiate the actor's skills, abilitity scores and health and mana

        actorAbilityScores = new int[EnumAbility.values().length];
        actorSkills = new int[EnumSkill.values().length];

        // Add The Actor's Racial Bonus to their stats and Skill Bonuses
        for (int i = 0; i < actorAbilityScores.length; i++) {
            actorAbilityScores[i] += actorRace.getActorRaceBenefitsAbility()[i];
            actorSkills[i] += actorCharacterClass.getCharacterClassSkillBonuses()[i];
            actorSkills[i] += actorRace.getActorRaceBenefitsSkill()[i];
        }

        actorExperience = 0;

        actorCurrentHealth = actorMaxHealth = getActorMaxHealth();
        actorCurrentMana = actorMaxMana = getActorMaxMana();

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

    public void modifyActorAbilityScore(EnumAbility stat, int amount) {
        actorAbilityScores[stat.ordinal()] += amount;
    }

    public void modifyActorSkill(EnumSkill skill, int amount) {
        actorSkills[skill.ordinal()] += amount;
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

    public void modifyActorExperience(int amount) {
        actorExperience += amount;
    }

    public void modifyActorHealth(int amount) {
        // The actor's health can not be greater than their maximum health or less than 0
        actorCurrentHealth = Math.max(0, Math.min(actorCurrentHealth + amount, actorMaxHealth));
    }

    public void modifyActorMana(int amount) {
        // The actor's mana can not be greater than their maximum mana or less than 0
        actorCurrentMana = Math.max(0, Math.min(actorCurrentMana + amount, actorMaxMana));
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public void setActorDescription(String actorDescription) {
        this.actorDescription = actorDescription;
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

    public void setActorInventory(Inventory actorInventory) {
        this.actorInventory = actorInventory;
    }

    public void setActorFactionList(List<Faction> actorFactionList) {
        this.actorFactionList = actorFactionList;
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

    public String getActorDescription() {
        return actorDescription;
    }

    public ActorRace getActorRace() {
        return actorRace;
    }

    public CharacterClass getActorCharacterClass() {
        return actorCharacterClass;
    }

    public int getActorArmorClass() {
        // The Actor's armor class is calculated by Dnd rules
        // The armor class is calculated by adding the armor class bonus from the equipment
        // and the dexterity modifier from the actor's ability scores
        int armorClass = 0;

        for (EquipmentSlot slot : armorEquipmentSlots) {
            if (slot.getSlotEquipment() != null)
                armorClass += slot.getSlotEquipment().getArmorClassModifier();
        }

        armorClass += actorAbilityScores[EnumAbility.DEXTERITY.ordinal()];

        return armorClass;
    }

    public int getActorMaxHealth() {
        // The Actor's max health is calculated by DnD rules
        // The formula is: (CON Modifier) + (Level * 5) + 10
        return (Utils.getAbilityModifier(getActorAbilityScore(EnumAbility.CONSTITUTION)) + (getActorLevel() * 5)) + 10;
    }

    public int getActorMaxMana() {
        // The Actor's max mana is calculated by DnD rules
        // The formula is: (WIS Modifier) + (Level * 5) + 10
        return (Utils.getAbilityModifier(getActorAbilityScore(EnumAbility.WISDOM)) + (getActorLevel() * 5)) + 10;
    }

    public int getActorLevel() {
        // The actorrs level is calculated with the following:
        // FLOOR(-2.5 + SQRT(8 * actorXp + 1225) / 10)
        return actorLevel = (int) Math.floor(-2.5 + Math.sqrt(8 * actorExperience + 1225) / 10);
    }

    public int getActorCurrentHealth() {
        return actorCurrentHealth;
    }

    public int getActorCurrentMana() {
        return actorCurrentMana;
    }

    public int getActorExperience() {
        return actorExperience;
    }

    public int getActorAbilityScore(EnumAbility stat) {
        return actorAbilityScores[stat.ordinal()];
    }

    public int getActorSkill(EnumSkill skill) {
        return actorSkills[skill.ordinal()];
    }

    public int[] getActorAbilityScores() {
        return actorAbilityScores;
    }

    public int[] getActorSkills() {
        return actorSkills;
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

    public List<Spell> getActorSpellList() {
        return actorSpellList;
    }

    public List<Faction> getActorFactionList() {
        return actorFactionList;
    }

}
