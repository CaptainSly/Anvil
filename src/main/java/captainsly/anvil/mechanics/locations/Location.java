package captainsly.anvil.mechanics.locations;

import java.util.ArrayList;
import java.util.List;

import captainsly.anvil.core.Registry;
import captainsly.anvil.mechanics.entities.Actor;
import captainsly.anvil.mechanics.enums.EnumDirection;
import captainsly.anvil.mechanics.events.GameEvent;

public class Location {

	private final String locationId; // Location's UUID

	private String locationName, locationDescription;

	private String[] neighboringLocations;

	private List<Actor> locationActorsList;
	private List<GameEvent> locationActionsList;

	public Location(String locationId) {
		this.locationId = locationId;

		locationActorsList = new ArrayList<>();
		locationActionsList = new ArrayList<>();

		neighboringLocations = new String[EnumDirection.values().length];
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public void addNeighborLocation(String locationId, EnumDirection dir) {
		neighboringLocations[dir.ordinal()] = locationId;
	}

	public void addLocationEvent(GameEvent event) {
		locationActionsList.add(event);
	}
	
	public void addLocationActor(Actor actor) {
		locationActorsList.add(actor);
	}
	
	public String[] getNeighborLocationIds() {
		return neighboringLocations;
	}

	public String getNeighboringLocationId(EnumDirection dir) {
		return neighboringLocations[dir.ordinal()];
	}

	public Location getNeighboringLocation(EnumDirection dir) {
		return Registry.getLocation(getNeighboringLocationId(dir));
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
