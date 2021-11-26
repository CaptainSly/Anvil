package captainsly.anvil.ui.nodes;

import captainsly.anvil.mechanics.enums.EnumDirection;
import captainsly.anvil.mechanics.locations.Location;
import captainsly.anvil.ui.Anvil;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 * Allows the user to switch to different locations based on the current
 * location their currently in The name was cool so it stays
 * 
 * @author Zachary
 *
 */
public class DirectionSwatch extends Region {

	private GridPane rootGrid;
	private Button btnNorth, btnSouth, btnEast, btnWest;
	private Location currentLocation;

	private final Anvil anvil;

	public DirectionSwatch(Anvil anvil) {
		this.anvil = anvil;
		rootGrid = new GridPane();

		btnNorth = new Button();
		btnNorth.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.ARROW_UP));
		btnNorth.setDisable(true);
		btnNorth.setOnAction(e -> {
			setCurrentLocation(currentLocation.getNeighboringLocation(EnumDirection.NORTH));
		});

		btnSouth = new Button();
		btnSouth.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.ARROW_DOWN));
		btnSouth.setDisable(true);
		btnSouth.setOnAction(e -> {
			setCurrentLocation(currentLocation.getNeighboringLocation(EnumDirection.SOUTH));
		});

		btnEast = new Button();
		btnEast.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.ARROW_RIGHT));
		btnEast.setDisable(true);
		btnEast.setOnAction(e -> {
			setCurrentLocation(currentLocation.getNeighboringLocation(EnumDirection.EAST));
		});

		btnWest = new Button();
		btnWest.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.ARROW_LEFT));
		btnWest.setDisable(true);
		btnWest.setOnAction(e -> {
			setCurrentLocation(currentLocation.getNeighboringLocation(EnumDirection.WEST));
		});

		rootGrid.add(btnNorth, 1, 0);
		rootGrid.add(btnWest, 0, 1);
		rootGrid.add(btnEast, 2, 1);
		rootGrid.add(btnSouth, 1, 2);

		this.getChildren().add(rootGrid);
	}

	public String getTooltipString(EnumDirection dir) {
		Location tempLocation = currentLocation.getNeighboringLocation(dir);

		String text = "Currently moving to ";
		String locationText = "";

		if (tempLocation != null) {
			locationText = tempLocation.getLocationName();
		} else
			text = "";

		return text + locationText;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
		anvil.setCurrentLocation(currentLocation);
		anvil.clearConsole();

		// Movement Button Code
		btnNorth.setDisable(currentLocation.getNeighboringLocation(EnumDirection.NORTH) == null);
		btnNorth.setTooltip(new Tooltip(getTooltipString(EnumDirection.NORTH)));

		btnSouth.setDisable(currentLocation.getNeighboringLocation(EnumDirection.SOUTH) == null);
		btnSouth.setTooltip(new Tooltip(getTooltipString(EnumDirection.SOUTH)));

		btnEast.setDisable(currentLocation.getNeighboringLocation(EnumDirection.EAST) == null);
		btnEast.setTooltip(new Tooltip(getTooltipString(EnumDirection.EAST)));

		btnWest.setDisable(currentLocation.getNeighboringLocation(EnumDirection.WEST) == null);
		btnWest.setTooltip(new Tooltip(getTooltipString(EnumDirection.WEST)));

		// Write out description information:
		// The name of the Location
		// The description of the location
		anvil.writeToConsole("-=- " + currentLocation.getLocationName() + " -=-\n");
		anvil.writeToConsole(currentLocation.getLocationDescription() + "\n\n");

		// Write out the neighbor locations
		for (int i = 0; i < currentLocation.getNeighborLocations().length; i++) {
			Location location = currentLocation.getNeighborLocations()[i];

			if (location != null) {
				anvil.writeToConsole(location.getLocationName() + " is to the " + EnumDirection.values()[i].name() + "\n");
			}
		}

	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

}
