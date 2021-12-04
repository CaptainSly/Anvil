package captainsly.anvil.core;

import java.util.HashMap;
import java.util.Map;

import captainsly.anvil.mechanics.entities.actrace.ActorRace;
import captainsly.anvil.mechanics.entities.cclass.CharacterClass;
import captainsly.anvil.mechanics.items.Item;

public class Registry {

	public static final Map<String, Item> itemDB = new HashMap<>();
	public static final Map<String, ActorRace> actorRacesMap = new HashMap<>();
	public static final Map<String, CharacterClass> characterClassMap = new HashMap<>();

}
