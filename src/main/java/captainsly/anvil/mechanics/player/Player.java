package captainsly.anvil.mechanics.player;

import captainsly.anvil.mechanics.entities.Actor;
import captainsly.anvil.mechanics.entities.actrace.ActorRace;
import captainsly.anvil.mechanics.entities.cclass.CharacterClass;
import captainsly.anvil.mechanics.enums.EnumSkill;
import captainsly.anvil.mechanics.enums.EnumAbility;
import captainsly.anvil.mechanics.journal.PlayerJournal;
import captainsly.anvil.mechanics.objBuilders.ActorRaceBuilder;
import captainsly.anvil.mechanics.objBuilders.CharacterClassBuilder;

public class Player extends Actor {

	private static final long serialVersionUID = 2077666720416949312L;
	private PlayerJournal playerJournal;

	private final static ActorRace playerActorRace = new ActorRaceBuilder()
			.createActorRace("playerTestRace", "Player Test Race", "An in house, test race for testing")
			.modifyStats(EnumAbility.CHARISMA, 3).modifyStats(EnumAbility.CONSITITUION, 2)
			.modifyStats(EnumAbility.DEXTERITY, 2).modifyStats(EnumAbility.STRENGTH, 2)
			.modifyStats(EnumAbility.WISDOM, 1).modifyStats(EnumAbility.INTELLIGENCE, 2).build();
	private final static CharacterClass playerCharacterClass = new CharacterClassBuilder()
			.createCharacterClass("playerTestClass", "Player Test Class", "An in house, test class for testing")
			.modifyStats(EnumSkill.DARK_MAGIC, 5).modifyStats(EnumSkill.EARTH_MAGIC, 5)
			.modifyStats(EnumSkill.ENCHANTING, 5).modifyStats(EnumSkill.FIRE_MAGIC, 5)
			.modifyStats(EnumSkill.FROST_MAGIC, 5).modifyStats(EnumSkill.LIGHT_MAGIC, 5)
			.modifyStats(EnumSkill.LOCKPICKING, 5).modifyStats(EnumSkill.ONE_HANDED, 5)
			.modifyStats(EnumSkill.SMITHING, 5).modifyStats(EnumSkill.TWO_HANDED, 5)
			.modifyStats(EnumSkill.WATER_MAGIC, 5).build();

	public Player() {
		super("actPlayer", playerActorRace, playerCharacterClass);
		playerJournal = new PlayerJournal(this);
	}

	public PlayerJournal getPlayerJournal() {
		return playerJournal;
	}

}
