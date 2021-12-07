package captainsly.anvil.mechanics.entities.cclass;

import captainsly.anvil.mechanics.enums.EnumAbility;
import captainsly.anvil.mechanics.enums.EnumSkill;

public class CharacterClass {

	// TODO: Fill out the Character Class and implement what is missing

	protected final String characterClassId;
	private String characterClassName;
	private String characterClassDesc;

	private int[] characterClassSkillBonuses;
	private int[] characterClassAbilityBonuses;

	public CharacterClass(String characterClassId, String characterClassName, String characterClassDesc) {
		this.characterClassId = characterClassId;
		this.characterClassName = characterClassName;
		this.characterClassDesc = characterClassDesc;

		characterClassAbilityBonuses = new int[EnumAbility.values().length];
		characterClassSkillBonuses = new int[EnumSkill.values().length];
	}

	/**
	 * Set's the stat racial bonus
	 * 
	 * @param stat   The Stat to modify
	 * @param amount - The amount from 1 to 5
	 */
	public void modifyCharacterClassAbilityBonuses(EnumAbility ability, int amount) {
		characterClassSkillBonuses[ability.ordinal()] += amount;
	}
	
	public void modifyCharacterClassSkillBonuses(EnumSkill skill, int amount) {
		characterClassSkillBonuses[skill.ordinal()] += amount;
	}

	public String getCharacterClassId() {
		return characterClassId;
	}

	public String getCharacterClassName() {
		return characterClassName;
	}

	public String getCharacterClassDesc() {
		return characterClassDesc;
	}

	public int[] getCharacterClassAbilityBonuses() {
		return characterClassAbilityBonuses;
	}
	
	public int[] getCharacterClassSkillBonuses() {
		return characterClassSkillBonuses;
	}

}
