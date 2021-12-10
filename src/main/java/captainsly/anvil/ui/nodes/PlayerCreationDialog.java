package captainsly.anvil.ui.nodes;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;

import captainsly.anvil.core.Registry;
import captainsly.anvil.mechanics.entities.actrace.ActorRace;
import captainsly.anvil.mechanics.entities.cclass.CharacterClass;
import captainsly.anvil.mechanics.enums.EnumAbility;
import captainsly.anvil.mechanics.enums.EnumSkill;
import captainsly.anvil.mechanics.player.Player;
import captainsly.anvil.ui.Anvil;
import captainsly.utils.Utils;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class PlayerCreationDialog extends Dialog<Player> {

	public PlayerCreationDialog(Anvil anvil) {
		Player player = new Player();

		GridPane statGridPane = new GridPane();
		GridPane skillGridPane = new GridPane();
		GridPane playerInfoGridPane = new GridPane();
		BorderPane playerBP = new BorderPane();

		ButtonType acceptButton = new ButtonType("Create", ButtonData.OK_DONE);

		this.setResizable(true);
		this.setTitle("Create Player");
		this.setHeaderText("Create your character to explore the world");
		this.getDialogPane().setContent(playerBP);
		this.getDialogPane().getButtonTypes().add(acceptButton);

		statGridPane.setPadding(new Insets(5f, 5f, 5f, 5f));
		skillGridPane.setPadding(new Insets(5f, 5f, 5f, 5f));
		playerInfoGridPane.setPadding(new Insets(5f, 5f, 5f, 5f));

		Node acceptButtonNde = this.getDialogPane().lookupButton(acceptButton);
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
		Set<Entry<String, ActorRace>> actorRaceEntrySet = Registry.getActorRaceRegistry().entrySet();
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
		Set<Entry<String, CharacterClass>> characterClassEntrySet = Registry.getCharacterClassRegistry().entrySet();
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
		Optional<Player> playerOptional = this.showAndWait();

		this.setResultConverter(dialogButton -> {

			if (dialogButton == acceptButton) {
				// Apply all the data to the player
				player.setActorName(playerNameField.getText());
				player.setActorRace(playerRaceBox.getValue());
				player.setActorCharacterClass(Registry.getCharacterClass(playerClassBox.getValue()));

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
	}

}
