package captainsly.anvil.mechanics.player;

import captainsly.anvil.core.Registry;
import captainsly.anvil.mechanics.entities.Actor;
import captainsly.anvil.mechanics.entities.actrace.ActorRace;
import captainsly.anvil.mechanics.entities.cclass.CharacterClass;
import captainsly.anvil.mechanics.enums.EnumAbility;
import captainsly.anvil.mechanics.enums.EnumSkill;
import captainsly.anvil.mechanics.journal.PlayerJournal;
import captainsly.utils.Utils;

public class Player extends Actor {

	private static final long serialVersionUID = 2077666720416949312L;
	private PlayerJournal playerJournal;

	public Player(ActorRace playerActorRace, CharacterClass playerCharacterClass) {
		super("actPlayer", playerActorRace, playerCharacterClass);
		playerJournal = new PlayerJournal(this);

		int[] scores = Utils.generateAbilityScores();
		for (int i = 0; i < EnumAbility.values().length; i++)
			modifyActorAbilityScore(EnumAbility.values()[i], scores[i]);

		scores = Utils.generateSkillScores();
		for (int i = 0; i < EnumSkill.values().length; i++)
			modifyActorSkill(EnumSkill.values()[i], scores[i]);

	}

	public Player() {
		this(Registry.actorRacesMap.get("raceDwarf"), Registry.characterClassMap.get("classBarbarian"));
	}

	public PlayerJournal getPlayerJournal() {
		return playerJournal;
	}

	@Override
	public void setActorDescription(String description) {}

	@Override
	public String getActorDescription() {
		return "The Player Character";
	}

}
