package captainsly.anvil.mechanics.enums;

public enum EnumProfession {

	;

	public static EnumProfession getProfessionFromString(String profession) {
		for (EnumProfession enumProfession : values())
			if (enumProfession.name().equalsIgnoreCase(profession))
				return enumProfession;

		return null;
	}

}
