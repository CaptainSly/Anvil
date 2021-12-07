package captainsly.anvil.mechanics.enums;

public enum EnumDirection {

	NORTH, SOUTH, EAST, WEST;

	public EnumDirection opposite() {
		switch (this) {
		case NORTH:
			return SOUTH;
		case EAST:
			return WEST;
		case SOUTH:
			return NORTH;
		case WEST:
			return EAST;
		default:
			return NORTH;
		}
	}


	public static EnumDirection getDirectionFromString(String direction) {
		for (EnumDirection d : values())
			if (d.name().toLowerCase().equals(direction.toLowerCase()))
				return d;

		return null;
	}

}
