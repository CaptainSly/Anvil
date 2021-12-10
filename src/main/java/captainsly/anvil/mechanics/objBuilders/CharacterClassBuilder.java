package captainsly.anvil.mechanics.objBuilders;

import captainsly.anvil.core.Registry;
import captainsly.anvil.mechanics.entities.cclass.CharacterClass;
import captainsly.anvil.mechanics.enums.EnumAbility;
import captainsly.anvil.mechanics.enums.EnumSkill;
import javafx.util.StringConverter;

public class CharacterClassBuilder {

	private CharacterClass characterClass;

	public CharacterClassBuilder createCharacterClass(String characterClassId, String characterClassName,
			String characterClassDesc) {
		this.characterClass = new CharacterClass(characterClassId, characterClassName, characterClassDesc);

		return this;
	}

	public CharacterClassBuilder modifyCharacterAbility(EnumAbility ability, int boost) {
		this.characterClass.modifyCharacterClassAbilityBonuses(ability, boost);

		return this;
	}

	public CharacterClassBuilder modifyCharacterSkill(EnumSkill skill, int boost) {
		this.characterClass.modifyCharacterClassSkillBonuses(skill, boost);

		return this;
	}

	public CharacterClass build() {
		return characterClass;
	}

	public static class CharacterClassStringConverter extends StringConverter<CharacterClass> {

		@Override
		public String toString(CharacterClass object) {
			if (!(object == null))
				return object.getCharacterClassName();
			else
				return "";
		}

		@Override
		public CharacterClass fromString(String string) {
			return Registry.getCharacterClass(string);
		}
	}

}
