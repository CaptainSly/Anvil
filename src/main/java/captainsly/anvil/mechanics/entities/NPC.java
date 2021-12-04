package captainsly.anvil.mechanics.entities;

import captainsly.anvil.mechanics.entities.actrace.ActorRace;
import captainsly.anvil.mechanics.entities.cclass.CharacterClass;
import captainsly.anvil.mechanics.enums.EnumProfession;

public class NPC extends Actor {

	private static final long serialVersionUID = -9099073670614863151L;

	private EnumProfession npcProfession;

	public NPC(String actorId, ActorRace actorRace, CharacterClass actorCharacterClass) {
		super(actorId, actorRace, actorCharacterClass);
	}

	public void setNpcProfession(EnumProfession npcProfession) {
		this.npcProfession = npcProfession;
	}

	public EnumProfession getNpcProfession() {
		return npcProfession;
	}

}
