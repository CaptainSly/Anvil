package captainsly.anvil.ui;

import java.util.Iterator;
import java.util.Map.Entry;

import captainsly.anvil.core.Registry;
import captainsly.anvil.mechanics.entities.actrace.ActorRace;
import captainsly.anvil.mechanics.entities.cclass.CharacterClass;
import captainsly.anvil.mechanics.locations.Location;
import captainsly.anvil.mechanics.objBuilders.ActorRaceBuilder.ActorRaceStringConverter;
import captainsly.anvil.mechanics.objBuilders.CharacterClassBuilder.CharacterClassStringConverter;
import captainsly.anvil.mechanics.player.Player;
import captainsly.anvil.mechanics.world.GameWorld;
import captainsly.anvil.ui.nodes.DirectionSwatch;
import captainsly.anvil.ui.nodes.EquipmentSwatch;
import captainsly.anvil.ui.nodes.InventoryView;
import captainsly.anvil.ui.nodes.PlayerStatsView;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	private TextArea interactionTextArea;
	private PlayerStatsView playerStatView;
	private Location currentLocation;

	private GameWorld gameWorld;

	@Override
	public void start(Stage primaryStage) throws Exception {
		anchorPane = new AnchorPane();
		rootBP = new BorderPane();
		controlBP = new BorderPane();
		menuHBox = new HBox();
		playerOptionsVBox = new VBox();

		currentLocation = Registry.locationsMap.get("locationCalinfor");

		interactionTextArea = new TextArea();
		interactionTextArea.setWrapText(true);
		interactionTextArea.setEditable(false);
//		interactionTextArea.setStyle("-fx-font-size: 18");

		// Show the creation dialog, then create the gameworld
		// TODO: Fix player creation

		Player player = createPlayer();
		
		gameWorld = new GameWorld(this, player);

		inventoryView = new InventoryView(player);
		equipmentSwatch = new EquipmentSwatch(player);
		playerStatView = new PlayerStatsView(player);
		dirSwatch = new DirectionSwatch(this);

		setLocation(currentLocation);

		menuHBox.getChildren().addAll();

		Separator sep = new Separator(Orientation.HORIZONTAL);
		sep.setPadding(new Insets(1.5f, 0f, 1.5f, 0f));

		playerOptionsVBox.setPadding(new Insets(5, 5, 5, 5));
		playerOptionsVBox.getChildren().addAll(playerStatView, equipmentSwatch, sep, dirSwatch, inventoryView);

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

	private Player createPlayer() {
		Dialog<Player> dialog = new Dialog<>();
		ButtonType createPlayerType = new ButtonType("Create Player", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(createPlayerType);

		// Dialog Nodes
		BorderPane dialogRootPane = new BorderPane();
		ChoiceBox<ActorRace> raceChoiceBox = new ChoiceBox<>();
		ChoiceBox<CharacterClass> classChoiceBox = new ChoiceBox<>();
		TextField playerNameField = new TextField();
		TextArea descriptionArea = new TextArea();

		descriptionArea.setEditable(false);
		descriptionArea.setWrapText(true);

		raceChoiceBox.setConverter(new ActorRaceStringConverter());
		raceChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ActorRace>() {

			@Override
			public void changed(ObservableValue<? extends ActorRace> observable, ActorRace oldValue,
					ActorRace newValue) {
				if (newValue != null) {
					descriptionArea.clear();
					descriptionArea.setText(newValue.getActorRaceName() + "\n" + newValue.getActorRaceDesc());
				}
			}

		});

		classChoiceBox.setConverter(new CharacterClassStringConverter());
		classChoiceBox.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
			if (nv != null) {
				descriptionArea.clear();
				descriptionArea.setText(nv.getCharacterClassName() + "\n" + nv.getCharacterClassDesc());
			}
		});

		Iterator<Entry<String, ActorRace>> raceIterator = Registry.actorRacesMap.entrySet().iterator();
		while (raceIterator.hasNext()) {
			ActorRace race = raceIterator.next().getValue();
			raceChoiceBox.getItems().add(race);
		}

		Iterator<Entry<String, CharacterClass>> classIterator = Registry.characterClassMap.entrySet().iterator();
		while (classIterator.hasNext()) {
			CharacterClass cclass = classIterator.next().getValue();
			classChoiceBox.getItems().add(cclass);
		}

		Node createButton = dialog.getDialogPane().lookupButton(createPlayerType);
		createButton.setDisable(true);

		playerNameField.textProperty().addListener(e -> {
			createButton.setDisable(playerNameField.getText().isEmpty());
		});

		HBox hbox = new HBox();
		hbox.getChildren().addAll(playerNameField, raceChoiceBox, classChoiceBox);

		dialog.setResultConverter(dialogButton -> {
			Player player = new Player(raceChoiceBox.getValue(), classChoiceBox.getValue());
			player.setActorName(playerNameField.getText());

			return player;
		});

		dialogRootPane.setCenter(hbox);
		dialogRootPane.setBottom(descriptionArea);
		dialog.getDialogPane().setContent(dialogRootPane);
		return dialog.showAndWait().get();
	}

	public void setLocation(Location location) {
		// TODO: Any special Classes that need their location switched when this one is
		setCurrentLocation(location);
		dirSwatch.setCurrentLocation(location.getLocationId());

		// Update the GameWorld
		gameWorld.setCurrentLocation(location);
		gameWorld.updateView();
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	public void writeToConsole(String message) {
		interactionTextArea.appendText(message + "\n");
	}

	public void clearConsole() {
		interactionTextArea.clear();
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public GameWorld getGameWorld() {
		return gameWorld;
	}

	public TextArea getConsole() {
		return interactionTextArea;
	}

}
