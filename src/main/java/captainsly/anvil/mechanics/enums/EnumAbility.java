package captainsly.anvil.mechanics.enums;

public enum EnumAbility {

	STRENGTH, DEXTERITY, CONSTITUTION, INTELLIGENCE, WISDOM, CHARISMA;

	public static EnumAbility getAbilityFromString(String ability) {
		for (EnumAbility a : values())
			if (a.name().toLowerCase().equals(ability.toLowerCase()))
				return a;

		return null;
	}

}
