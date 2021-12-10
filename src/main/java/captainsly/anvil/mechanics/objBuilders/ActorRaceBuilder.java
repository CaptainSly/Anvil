package captainsly.anvil.mechanics.objBuilders;

import captainsly.anvil.core.Registry;
import captainsly.anvil.mechanics.entities.actrace.ActorRace;
import captainsly.anvil.mechanics.enums.EnumAbility;
import captainsly.anvil.mechanics.enums.EnumSkill;
import javafx.util.StringConverter;

public class ActorRaceBuilder {

	private ActorRace actorRace;

	public ActorRaceBuilder createActorRace(String actorRaceId, String actorRaceName, String actorRaceDesc) {
		this.setActorRace(new ActorRace(actorRaceId));
		this.getActorRace().setActorRaceName(actorRaceName);
		this.getActorRace().setActorRaceDesc(actorRaceDesc);

		return this;
	}

	public ActorRaceBuilder modifyAbility(EnumAbility stat, int amount) {
		this.getActorRace().modifyActorRaceBonusAbility(stat, amount);

		return this;
	}

	public ActorRaceBuilder modifySkills(EnumSkill skill, int amount) {
		this.getActorRace().modifyActorRaceBonusSkill(skill, amount);

		return this;
	}

	public ActorRace build() {
		return getActorRace();
	}

	private void setActorRace(ActorRace actorRace) {
		this.actorRace = actorRace;
	}

	private ActorRace getActorRace() {
		return actorRace;
	}

	public static class ActorRaceStringConverter extends StringConverter<ActorRace> {

		@Override
		public String toString(ActorRace object) {
			if (!(object == null))
				return object.getActorRaceName();
			else
				return "";
		}

		@Override
		public ActorRace fromString(String string) {
			return Registry.getActorRace(string);
		}

	}

}
