package captainsly.anvil.mechanics.factions;

import java.util.ArrayList;
import java.util.List;

public abstract class Faction {

	protected final String factionId; // Faction's UUID

	private List<Faction> positiveFactions, neutralFactions, hostileFactions;

	private boolean isHostileByDefault;

	public Faction(String factionId) {
		this.factionId = factionId;
		positiveFactions = new ArrayList<>();
		neutralFactions = new ArrayList<>();
		hostileFactions = new ArrayList<>();
	}

	public void setIsHostileByDefault(boolean isHostileByDefault) {
		this.isHostileByDefault = isHostileByDefault;
	}

	public String getFactionId() {
		return factionId;
	}

	public List<Faction> getPositiveFactions() {
		return positiveFactions;
	}

	public List<Faction> getNeutralFactions() {
		return neutralFactions;
	}

	public List<Faction> getHostileFactions() {
		return hostileFactions;
	}

	public boolean isHostileByDefault() {
		return isHostileByDefault;
	}

}
