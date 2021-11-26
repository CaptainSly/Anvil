package captainsly.hammer.core.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import captainsly.anvil.mechanics.locations.Location;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class LocationTab extends Tab {

	private final Logger log = LoggerFactory.getLogger("[LocationTab]");

	private AnchorPane anchorPane;
	private BorderPane borderPane;
	private GridPane gridPane;

	private ListView<Location> currentLocations;
	private Label locationIdField, locationNameFiel;
	private TextArea locationDescriptionField;

	private Location location;

	public LocationTab() {
		super();

		anchorPane = new AnchorPane();
		borderPane = new BorderPane();
		gridPane = new GridPane();

		AnchorPane.setLeftAnchor(borderPane, 5d);
		AnchorPane.setRightAnchor(borderPane, 5d);
		AnchorPane.setTopAnchor(borderPane, 5d);
		AnchorPane.setBottomAnchor(borderPane, 5d);

		borderPane.setCenter(gridPane);
		anchorPane.getChildren().add(borderPane);

		this.setText("Location");
		this.setClosable(false);
		this.setContent(anchorPane);
	}

}
