package captainsly.anvil.mechanics.entities.actrace;

import captainsly.anvil.mechanics.enums.EnumStat;

public class ActorRace {
	
	// TODO: Fill out the ActorRace Class and implement the rest of it

	private final String actorRaceId; // The Actor's Race ID

	private int[] actorRaceBonuses;

	public ActorRace(String actorRaceId) {
		this.actorRaceId = actorRaceId;

		actorRaceBonuses = new int[EnumStat.values().length];
	}

	/**
	 * Set's the stat racial bonus
	 * 
	 * @param stat   The Stat to modify
	 * @param amount - The amount from 1 to 5
	 */
	public void modifyActorRaceBonusStat(EnumStat stat, int amount) {
		if (amount >= 5)
			amount = 5;

		if (amount <= -5)
			amount = -5;

		actorRaceBonuses[stat.ordinal()] += amount;

	}

	public String getActorRaceId() {
		return actorRaceId;
	}

	public int getActorRaceBonusStat(EnumStat stat) {
		return actorRaceBonuses[stat.ordinal()];
	}

	public int[] getActorRaceBenefits() {
		return actorRaceBonuses;
	}

}
