package captainsly.anvil.mechanics.entities;

import captainsly.anvil.mechanics.entities.actrace.ActorRace;
import captainsly.anvil.mechanics.entities.cclass.CharacterClass;
import captainsly.anvil.mechanics.items.Lootlist;

public class Enemy extends Actor {

	private Lootlist enemyLootList;

	private static final long serialVersionUID = -3651347256610812758L;

	public Enemy(String actorId, ActorRace actorRace, CharacterClass actorCharacterClass) {
		super(actorId, actorRace, actorCharacterClass);
	}

	public Lootlist getEnemyLootList() {
		return enemyLootList;
	}

}
