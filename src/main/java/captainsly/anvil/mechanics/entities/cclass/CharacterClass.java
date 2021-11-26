package captainsly.anvil.mechanics.entities.cclass;

import captainsly.anvil.mechanics.enums.EnumSkill;

public class CharacterClass {

	// TODO: Fill out the Character Class and implement what is missing
	
	protected final String characterClassId;

	private int[] characterClassBonuses;

	public CharacterClass(String characterClassId) {
		this.characterClassId = characterClassId;

		characterClassBonuses = new int[EnumSkill.values().length];
	}

	/**
	 * Set's the stat racial bonus
	 * 
	 * @param stat   The Stat to modify
	 * @param amount - The amount from 1 to 5
	 */
	public void modifyCharacterClassBonuses(EnumSkill skill, int amount) {
		if (amount >= 5)
			amount = 5;

		if (amount <= -5)
			amount = -5;

		characterClassBonuses[skill.ordinal()] += amount;
	}

	public String getCharacterClassId() {
		return characterClassId;
	}

	public int getCharacterClassSkillBonus(EnumSkill skill) {
		return characterClassBonuses[skill.ordinal()];
	}

	public int[] getCharacterClassSkillBonuses() {
		return characterClassBonuses;
	}

}
