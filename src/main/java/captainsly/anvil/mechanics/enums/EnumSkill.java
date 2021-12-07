package captainsly.anvil.mechanics.enums;

public enum EnumSkill {

	ONE_HANDED, TWO_HANDED, BLUNT, LOCKPICKING, PICKPOCKETING, SMITHING, ENCHANTING, FISHING, WOOD_CUTTING, LIGHT_MAGIC,
	DARK_MAGIC, FIRE_MAGIC, EARTH_MAGIC, FROST_MAGIC, WATER_MAGIC, HEAVY_ARMOR, LIGHT_ARMOR, MEDIUM_ARMOR;

	public static EnumSkill getSkillFromString(String skill) {
		for (EnumSkill s : values())
			if (s.name().toLowerCase().equals(skill.toLowerCase()))
				return s;

		return null;
	}

}
