package captainsly.anvil.ui;

import captainsly.anvil.mechanics.enums.EnumDirection;
import captainsly.anvil.mechanics.items.Potion;
import captainsly.anvil.mechanics.locations.Location;
import captainsly.anvil.mechanics.player.Player;
import captainsly.anvil.ui.nodes.DirectionSwatch;
import captainsly.anvil.ui.nodes.EquipmentSwatch;
import captainsly.anvil.ui.nodes.InventoryView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Anvil extends Application {

	// UI Nodes and Layout
	private AnchorPane anchorPane;
	private BorderPane rootBP, controlBP;
	private HBox menuHBox;
	private VBox playerOptionsVBox;

	private DirectionSwatch dirSwatch;
	private EquipmentSwatch equipmentSwatch;
	private InventoryView inventoryView;
	private static TextArea interactionTextArea;
	private Location currentLocation, testLoc;

	@Override
	public void start(Stage primaryStage) throws Exception {
		anchorPane = new AnchorPane();
		rootBP = new BorderPane();
		controlBP = new BorderPane();
		menuHBox = new HBox();
		playerOptionsVBox = new VBox();

		testLoc = new Location("testLoc2");
		testLoc.setLocationName("TEST 2");
		testLoc.setLocationDescription("CAW CAW");

		currentLocation = new Location("testLocation001");
		currentLocation.setLocationName("Test Location");
		currentLocation.setLocationDescription("TEST LOCATION DESCRIPTION");
		currentLocation.addNeighborLocation(testLoc, EnumDirection.NORTH);

		interactionTextArea = new TextArea();
		interactionTextArea.setEditable(false);
		interactionTextArea.setStyle("-fx-font-size: 18");

		Player player = new Player();
		Button btn = new Button("TEST");

		inventoryView = new InventoryView(player);
		equipmentSwatch = new EquipmentSwatch(player);
		dirSwatch = new DirectionSwatch(this);

		Potion i = new Potion("potionTest", "Test Potion");
		i.setPotionScript("potionTest.lua");

		btn.setOnAction(e -> i.onUse(player));

		player.getActorInventory().addItem(i, 1);

		setLocation(currentLocation);

		menuHBox.getChildren().addAll();

		Separator sep = new Separator(Orientation.HORIZONTAL);
		sep.setPadding(new Insets(1.5f, 0f, 1.5f, 0f));

		playerOptionsVBox.setPadding(new Insets(5, 5, 5, 5));
		playerOptionsVBox.getChildren().addAll(equipmentSwatch, sep, dirSwatch, inventoryView, btn);

		controlBP.setLeft(playerOptionsVBox);

		rootBP.setTop(menuHBox);
		rootBP.setLeft(controlBP);
		rootBP.setCenter(interactionTextArea);

		anchorPane.getChildren().add(rootBP);

		AnchorPane.setTopAnchor(rootBP, 5.0d);
		AnchorPane.setLeftAnchor(rootBP, 5.0d);
		AnchorPane.setBottomAnchor(rootBP, 5.0d);
		AnchorPane.setRightAnchor(rootBP, 5.0d);

		Scene scene = new Scene(anchorPane, 640, 480);
		primaryStage.getIcons().add(new Image("anvillogo.png"));
		primaryStage.setTitle("Anvil");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void setLocation(Location location) {
		// TODO: Any special Classes that need their location switched when this one is
		setCurrentLocation(location);
		dirSwatch.setCurrentLocation(location);
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	public static void writeToConsole(String message) {
		interactionTextArea.appendText(message + "\n");
	}

	public static void clearConsole() {
		interactionTextArea.clear();
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public TextArea getConsole() {
		return interactionTextArea;
	}

}
