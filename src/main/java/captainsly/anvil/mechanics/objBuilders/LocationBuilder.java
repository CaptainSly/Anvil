package captainsly.anvil.mechanics.objBuilders;

import captainsly.anvil.core.Registry;
import captainsly.anvil.mechanics.enums.EnumDirection;
import captainsly.anvil.mechanics.locations.Location;

public class LocationBuilder {
	
	public Location location;
	
	public LocationBuilder createLocation(String locationId, String locationName, String locationDescription) {
		location = new Location(locationId);
		location.setLocationName(locationName);
		location.setLocationDescription(locationDescription);
		
		return this;
	}
	
	public LocationBuilder addNeighbor(String neighbor, EnumDirection direction) {
		location.addNeighborLocation(neighbor, direction);
	
		return this;
	}
	
	public LocationBuilder addEvent(String event) {
		location.addLocationEvent(Registry.getGameEvent(event));
		
		return this;
	}
	
	public LocationBuilder addActor(String actor) {
		location.addLocationActor(Registry.getActor(actor));
		
		return this;
	}
	
	public Location build() {
		return location;
	}

}
