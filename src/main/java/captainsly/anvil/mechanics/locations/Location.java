package captainsly.anvil.mechanics.locations;

import java.util.ArrayList;
import java.util.List;

import captainsly.anvil.mechanics.entities.Actor;
import captainsly.anvil.mechanics.enums.EnumDirection;
import captainsly.anvil.mechanics.events.GameEvent;

public class Location {

	private final String locationId; // Location's UUID

	private String locationName, locationDescription;

	private Location[] neighboringLocations;

	private List<Actor> locationActorsList;
	private List<GameEvent> locationActionsList;

	public Location(String locationId) {
		this.locationId = locationId;

		locationActorsList = new ArrayList<>();
		locationActionsList = new ArrayList<>();

		neighboringLocations = new Location[EnumDirection.values().length];
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public void addNeighborLocation(Location location, EnumDirection dir) {
		neighboringLocations[dir.ordinal()] = location;
		location.neighboringLocations[dir.opposite().ordinal()] = this;
	}

	public Location[] getNeighborLocations() {
		return neighboringLocations;
	}

	public Location getNeighboringLocation(EnumDirection dir) {
		return neighboringLocations[dir.ordinal()];
	}

	public List<Actor> getLocationActorsList() {
		return locationActorsList;
	}

	public List<GameEvent> getLocationActionsList() {
		return locationActionsList;
	}

	public String getLocationName() {
		return locationName;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public String getLocationId() {
		return locationId;
	}

}
