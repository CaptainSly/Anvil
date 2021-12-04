package captainsly.anvil.mechanics.entities.actrace;

import captainsly.anvil.mechanics.enums.EnumAbility;

public class ActorRace {

	private final String actorRaceId; // The Actor's Race ID
	private String actorRaceName;
	private String actorRaceDesc;

	private int[] actorRaceBonuses;
	private boolean isPlayable;

	public ActorRace(String actorRaceId, boolean isPlayable) {
		this.actorRaceId = actorRaceId;

		actorRaceBonuses = new int[EnumAbility.values().length];
	}

	public ActorRace(String actorRaceId) {
		this(actorRaceId, true);
	}

	/**
	 * Set's the stat racial bonus
	 * 
	 * @param stat   The Stat to modify
	 * @param amount - The amount from 1 to 5
	 */
	public void modifyActorRaceBonusStat(EnumAbility stat, int amount) {
		if (amount >= 5)
			amount = 5;

		if (amount <= -5)
			amount = -5;

		actorRaceBonuses[stat.ordinal()] += amount;

	}

	public void setActorRaceName(String actorRaceName) {
		this.actorRaceName = actorRaceName;
	}

	public void setActorRaceDesc(String actorRaceDesc) {
		this.actorRaceDesc = actorRaceDesc;
	}

	public boolean isPlayable() {
		return isPlayable;
	}

	public String getActorRaceId() {
		return actorRaceId;
	}

	public String getActorRaceName() {
		return actorRaceName;
	}

	public String getActorRaceDesc() {
		return actorRaceDesc;
	}

	public int getActorRaceBonusStat(EnumAbility stat) {
		return actorRaceBonuses[stat.ordinal()];
	}

	public int[] getActorRaceBenefits() {
		return actorRaceBonuses;
	}

}
