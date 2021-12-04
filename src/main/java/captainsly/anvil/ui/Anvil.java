package captainsly.anvil.ui;

import captainsly.anvil.mechanics.enums.EnumDirection;
import captainsly.anvil.mechanics.items.Potion;
import captainsly.anvil.mechanics.locations.Location;
import captainsly.anvil.mechanics.player.Player;
import captainsly.anvil.mechanics.world.GameWorld;
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

	private GameWorld gameWorld;

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

		// Show the creation dialog, then create the gameworld
		// TODO: Fix player creation 
		
		Player player = new Player();
		Button btn = new Button("TEST");

		gameWorld = new GameWorld(this, player);

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
	/**
	private Player createPlayer() {
		Player player = new Player();

		Dialog<Player> playerCreationDialog = new Dialog<>();

		GridPane statGridPane = new GridPane();
		GridPane skillGridPane = new GridPane();
		GridPane playerInfoGridPane = new GridPane();
		BorderPane playerBP = new BorderPane();

		ButtonType acceptButton = new ButtonType("Create", ButtonData.OK_DONE);

		playerCreationDialog.setResizable(true);
		playerCreationDialog.setTitle("Create Player");
		playerCreationDialog.setHeaderText("Create your character to explore the world");
		playerCreationDialog.getDialogPane().setContent(playerBP);
		playerCreationDialog.getDialogPane().getButtonTypes().add(acceptButton);

		statGridPane.setPadding(new Insets(5f, 5f, 5f, 5f));
		skillGridPane.setPadding(new Insets(5f, 5f, 5f, 5f));
		playerInfoGridPane.setPadding(new Insets(5f, 5f, 5f, 5f));

		Node acceptButtonNde = playerCreationDialog.getDialogPane().lookupButton(acceptButton);
		acceptButtonNde.setDisable(true);

		Button reRollStats = new Button("Roll");
		Button reRollSkills = new Button("Roll");

		Label playerNameLbl = new Label("Player Name:");
		TextField playerNameField = new TextField();

		ChoiceBox<ActorRace> playerRaceBox = new ChoiceBox<>(); // Holds the references of the character
		ChoiceBox<String> playerClassBox = new ChoiceBox<>(); // Holds the ids of the character classes

		Label[] statLbls = new Label[EnumAbility.values().length];
		IncrementalIntegerField[] statFields = new IncrementalIntegerField[EnumAbility.values().length];

		Label[] skillLbls = new Label[EnumSkill.values().length];
		IncrementalIntegerField[] skillFields = new IncrementalIntegerField[EnumSkill.values().length];

		reRollStats.setOnAction(e -> {
			int[] adjustedScore = Utils.generateAbilityScores();
			for (int i = 0; i < statFields.length; i++)
				statFields[i].setFieldCount(adjustedScore[i]);
		});

		reRollSkills.setOnAction(e -> {
			int[] adjustedScore = Utils.generateSkillScores();
			for (int i = 0; i < skillFields.length; i++)
				skillFields[i].setFieldCount(adjustedScore[i]);
		});

		playerNameField.textProperty().addListener((obs, oV, nV) -> {
			acceptButtonNde.setDisable(nV.trim().isEmpty());
		});

		// Add the Actor Races that can be selected from the Registry map here
		Set<Entry<String, ActorRace>> actorRaceEntrySet = Registry.actorRacesMap.entrySet();
		Iterator<Entry<String, ActorRace>> actorRaceIterator = actorRaceEntrySet.iterator();

		while (actorRaceIterator.hasNext()) {

			// The Iterator has an entry, we want to pull the value
			ActorRace dummyRace = actorRaceIterator.next().getValue();

			// Check to see if the race is playable, if so add it to the choice box
			if (dummyRace.isPlayable()) {
				playerRaceBox.getItems().add(dummyRace);
			} else
				continue;

		}

		// Add all the Character Classes from the registry map to the choice box
		Set<Entry<String, CharacterClass>> characterClassEntrySet = Registry.characterClassMap.entrySet();
		Iterator<Entry<String, CharacterClass>> characterClassIterator = characterClassEntrySet.iterator();

		while (characterClassIterator.hasNext()) {
			playerClassBox.getItems().add(characterClassIterator.next().getKey()); // DEBUG WERE USING KEYS CURRENTLY
		}

		playerInfoGridPane.add(playerNameLbl, 0, 0);
		playerInfoGridPane.add(playerNameField, 1, 0);
		playerInfoGridPane.add(playerRaceBox, 2, 0);
		playerInfoGridPane.add(playerClassBox, 2, 1);

		int[] abscore = Utils.generateAbilityScores();
		for (int i = 0; i < statLbls.length; i++) {
			statLbls[i] = new Label(EnumAbility.values()[i].name());
			statFields[i] = new IncrementalIntegerField(false);

			statGridPane.add(statLbls[i], 0, i + 1);
			statGridPane.add(statFields[i], 1, i + 1);
			statFields[i].setFieldCount(abscore[i]);
		}

		statGridPane.add(reRollStats, 0, EnumAbility.values().length + 1);

		int[] sscore = Utils.generateSkillScores();
		for (int i = 0; i < skillLbls.length; i++) {
			skillLbls[i] = new Label(EnumSkill.values()[i].name());
			skillFields[i] = new IncrementalIntegerField(false);

			skillGridPane.add(skillLbls[i], 0, i + 1);
			skillGridPane.add(skillFields[i], 1, i + 1);
			skillFields[i].setFieldCount(sscore[i]);
		}

		skillGridPane.add(reRollSkills, 0, EnumSkill.values().length + 1);

		playerBP.setPadding(new Insets(1.5f, 1.5f, 1.5f, 1.5f));
		playerBP.setTop(playerInfoGridPane);
		playerBP.setLeft(statGridPane);
		playerBP.setRight(skillGridPane);

		Platform.runLater(() -> playerNameField.requestFocus());
		Optional<Player> playerOptional = playerCreationDialog.showAndWait();

		playerCreationDialog.setResultConverter(dialogButton -> {

			if (dialogButton == acceptButton) {
				// Apply all the data to the player
				player.setActorRace(playerRaceBox.getValue());
				player.setActorCharacterClass(Registry.characterClassMap.get(playerClassBox.getValue()));

				// Get the ability scores from the incremental fields
				int[] scores = new int[statFields.length];
				for (int i = 0; i < statFields.length; i++)
					scores[i] = statFields[i].getFieldCount();

				player.setActorAbilityScores(scores);

				int[] skills = new int[skillFields.length];
				for (int i = 0; i < skillFields.length; i++)
					skills[i] = skillFields[i].getFieldCount();

				player.setActorSkills(skills);

				return player;
			}

			return null;
		});

		Main.log.debug(playerOptional.get().getActorId());

		return playerOptional.get();
	}
	*/
	public void setLocation(Location location) {
		// TODO: Any special Classes that need their location switched when this one is
		setCurrentLocation(location);
		dirSwatch.setCurrentLocation(location);

		// Update the GameWorld
		gameWorld.setCurrentLocation(location);
		gameWorld.updateView();
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

	public GameWorld getGameWorld() {
		return gameWorld;
	}

	public TextArea getConsole() {
		return interactionTextArea;
	}

}
