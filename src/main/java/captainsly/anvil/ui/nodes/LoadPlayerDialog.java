package captainsly.anvil.ui.nodes;

import captainsly.anvil.core.SaveSystem;
import captainsly.anvil.mechanics.player.Player;
import captainsly.anvil.ui.Anvil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;


public class LoadPlayerDialog extends Dialog<Player> {

    private StringProperty playerFileName;
    private ButtonType loadBtnType, createBtnType;

    private final Anvil anvil;

    public LoadPlayerDialog(Anvil anvil) {
        this.anvil = anvil;

        setTitle("Load Player");
        setHeaderText("Select a Player");

        playerFileName = new SimpleStringProperty();
        loadBtnType = new ButtonType("Load");
        createBtnType = new ButtonType("Create");

        getDialogPane().getButtonTypes().addAll(loadBtnType, createBtnType);
        Node loadPlayerBtn = this.getDialogPane().lookupButton(loadBtnType);
        loadPlayerBtn.setDisable(true);

        GridPane dialogGrid = new GridPane();
        dialogGrid.setPadding(new Insets(5, 5, 5, 5));


        // Create an array of buttons that when clicked sets the string property value to the button text
        Button[] fileBtns = new Button[SaveSystem.getSaveFiles().length];
        for (int i = 0; i < fileBtns.length; i++) {
            fileBtns[i] = new Button(SaveSystem.getSaveFiles()[i].getName());
            int finalI = i;
            fileBtns[i].setOnAction(e -> playerFileName.setValue(fileBtns[finalI].getText()));
            dialogGrid.add(fileBtns[i], 0, i);
        }

        playerFileName.addListener((observable, oldValue, newValue) -> {
            loadPlayerBtn.setDisable(newValue.isEmpty());
        });

        this.setResultConverter(buttonType -> {
            if (buttonType == loadBtnType)
                return SaveSystem.loadPlayerData(anvil, playerFileName.get());
            else if (buttonType == createBtnType)
                return anvil.createPlayer();
            else return anvil.createPlayer();
        });

        this.getDialogPane().setContent(dialogGrid);
    }


}
