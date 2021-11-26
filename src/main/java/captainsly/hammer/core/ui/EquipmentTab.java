package captainsly.hammer.core.ui;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import captainsly.anvil.mechanics.enums.EnumEquipmentSlotType;
import captainsly.anvil.mechanics.enums.EnumEquipmentStat;
import captainsly.anvil.mechanics.items.equipment.Equipment;
import captainsly.utils.FileHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class EquipmentTab extends Tab {

	private final Logger log = LoggerFactory.getLogger("[EquipmentTab]");

	private AnchorPane anchorPane;
	private BorderPane borderPane;
	private GridPane gridPane;

	private TextField equipmentIdField, equipmentNameField;
	private Label[] statLabels;
	private TextField[] statFields;

	private ChoiceBox<Equipment> equipmentChoiceBox;
	private ComboBox<EnumEquipmentSlotType> equipmentSlotChoiceBox;

	private Equipment currentEquipment;

	public EquipmentTab() {
		super();

		anchorPane = new AnchorPane();
		borderPane = new BorderPane();
		gridPane = new GridPane();

		HBox menuBar = new HBox();

		Button loadEquipmentBtn = new Button("Load Equipment");
		loadEquipmentBtn.setOnAction(e -> loadEquipment(FileHandler.WORKING_DIRECTORY + "/testEquipment.ser"));

		Button saveEquipmentBtn = new Button("Save Equipment");
		saveEquipmentBtn.setOnAction(e -> saveEquipment());

		equipmentChoiceBox = new ChoiceBox<>();
		equipmentSlotChoiceBox = new ComboBox<>();

		equipmentSlotChoiceBox.getItems().addAll(EnumEquipmentSlotType.values());

		equipmentIdField = new TextField();
		equipmentNameField = new TextField();

		AnchorPane.setLeftAnchor(borderPane, 5d);
		AnchorPane.setRightAnchor(borderPane, 5d);
		AnchorPane.setTopAnchor(borderPane, 5d);
		AnchorPane.setBottomAnchor(borderPane, 5d);

		menuBar.getChildren().addAll(equipmentChoiceBox, saveEquipmentBtn, loadEquipmentBtn);

		createStatNodes();

		gridPane.add(new Label("Equipment ID: "), 0, 0);
		gridPane.add(equipmentIdField, 1, 0);

		gridPane.add(new Label("Equipment Name: "), 0, 1);
		gridPane.add(equipmentNameField, 1, 1);

		gridPane.add(new Label("Equipment Type: "), 0, 2);
		gridPane.add(equipmentSlotChoiceBox, 1, 2);

		borderPane.setTop(menuBar);
		borderPane.setCenter(gridPane);
		anchorPane.getChildren().add(borderPane);

		setEquipment(new Equipment("", "", EnumEquipmentSlotType.CHEST));

		this.setText("Equipment");
		this.setClosable(false);
		this.setContent(anchorPane);

	}

	// Call this to change the current equipment being worked on
	public void setEquipment(Equipment equipment) {
		this.currentEquipment = equipment;

		equipmentIdField.setText(currentEquipment.getItemId());
		equipmentNameField.setText(currentEquipment.getItemName());
		equipmentSlotChoiceBox.setValue(currentEquipment.getEquipSlotType());

		for (int i = 0; i < EnumEquipmentStat.values().length; i++) {
			statFields[i].setText("" + currentEquipment.getEquipmentStat(EnumEquipmentStat.values()[i]));
		}

	}

	private void loadEquipment(String path) {
		Equipment equipment;
		try {
			equipment = (Equipment) FileHandler.deserializeObject(path);
			setEquipment(equipment);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}

	private void saveEquipment() {

		// TODO: Basically Check to see if the currentEquipment is an instance of one of
		// the different equipment classes

		currentEquipment = new Equipment(equipmentIdField.getText(), equipmentNameField.getText(),
				equipmentSlotChoiceBox.getValue());
		for (int i = 0; i < EnumEquipmentStat.values().length; i++) {
			currentEquipment.modifyEquipmentStat(EnumEquipmentStat.values()[i],
					Integer.parseInt(statFields[i].getText()));
		}

		log.debug("Writing current Equipment to file");
		FileHandler.serializeObject(currentEquipment,
				FileHandler.WORKING_DIRECTORY + "/" + currentEquipment.getItemId() + ".ser");

	}

	private void createStatNodes() {
		statLabels = new Label[EnumEquipmentStat.values().length];
		statFields = new TextField[EnumEquipmentStat.values().length];

		for (int i = 0; i < EnumEquipmentStat.values().length; i++) {
			statLabels[i] = new Label(EnumEquipmentStat.values()[i].name() + ": ");
			statFields[i] = new TextField();
			TextField textField = statFields[i];
			textField.textProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if (!newValue.matches("\\d*")) {
						textField.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}
			});
		}

		int row = 4;
		for (int i = 0; i < EnumEquipmentStat.values().length; i++) {
			gridPane.add(statLabels[i], 0, row);
			gridPane.add(statFields[i], 1, row);
			row++;
		}

	}

}
