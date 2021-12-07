package captainsly.anvil.mechanics.entities.actrace;

import captainsly.anvil.mechanics.enums.EnumAbility;
import captainsly.anvil.mechanics.enums.EnumSkill;

public class ActorRace {

	private String actorRaceId; // The Actor's Race ID
	private String actorRaceName;
	private String actorRaceDesc;

	private int[] actorRaceAbilityBonuses;
	private int[] actorRaceSkillBonuses;
	private boolean isPlayable;

	public ActorRace(String actorRaceId, boolean isPlayable) {
		this.actorRaceId = actorRaceId;

		actorRaceAbilityBonuses = new int[EnumAbility.values().length];
		actorRaceSkillBonuses = new int[EnumSkill.values().length];
	}

	public ActorRace(String actorRaceId) {
		this(actorRaceId, true);
	}

	public ActorRace() {
		this("", true);
	}

	/**
	 * Set's the stat racial bonus
	 * 
	 * @param ability The Stat to modify
	 * @param amount  - The amount from 1 to 5
	 */
	public void modifyActorRaceBonusAbility(EnumAbility ability, int amount) {
		actorRaceAbilityBonuses[ability.ordinal()] += amount;

	}

	public void modifyActorRaceBonusSkill(EnumSkill stat, int amount) {
		actorRaceSkillBonuses[stat.ordinal()] += amount;
	}

	public void setActorRaceName(String actorRaceName) {
		this.actorRaceName = actorRaceName;
	}

	public void setActorRaceDesc(String actorRaceDesc) {
		this.actorRaceDesc = actorRaceDesc;
	}

	public void setActorRaceId(String actorRaceId) {
		this.actorRaceId = actorRaceId;
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
		return actorRaceAbilityBonuses[stat.ordinal()];
	}

	public int[] getActorRaceBenefitsAbility() {
		return actorRaceAbilityBonuses;
	}

	public int[] getActorRaceBenefitsSkill() {
		return actorRaceSkillBonuses;
	}

}
