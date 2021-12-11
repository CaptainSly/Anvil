package captainsly.anvil.ui.nodes;

import captainsly.Main;
import captainsly.anvil.core.SaveSystem;
import captainsly.anvil.mechanics.enums.EnumAbility;
import captainsly.anvil.mechanics.enums.EnumSkill;
import captainsly.anvil.mechanics.player.Player;
import captainsly.anvil.ui.Anvil;
import captainsly.utils.Utils;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import org.controlsfx.control.PopOver;

public class PlayerStatsView extends Region {

    private GridPane gridPane;
    private final Player player;

    private Label nameLbl, raceLbl, classLbl, levelLbl, expLbl, hpLbl, mpLbl;
    private Button playerPopoverBtn, saveGameBtn;
    private PopOver playerStatsPopOver;

    private Label[] abilityLbls, skillLbls;
    private Label abilityTotalLbl, skillTotalLbl;

    public PlayerStatsView(Anvil anvil, Player player) {
        this.player = player;
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        nameLbl = new Label("Name: " + player.getActorName());
        raceLbl = new Label("Race: " + player.getActorRace().getActorRaceName());
        classLbl = new Label("Class: " + player.getActorCharacterClass().getCharacterClassName());
        levelLbl = new Label("Level: " + player.getActorLevel());
        expLbl = new Label("Exp: " + player.getActorExperience());
        hpLbl = new Label("HP: " + player.getActorCurrentHealth() + "/" + player.getActorMaxHealth());
        mpLbl = new Label("MP: " + player.getActorCurrentMana() + "/" + player.getActorMaxMana());

        abilityLbls = new Label[EnumAbility.values().length];
        skillLbls = new Label[EnumSkill.values().length];

        for (int i = 0; i < EnumAbility.values().length; i++)
            abilityLbls[i] = new Label(Utils.toNormalCase(EnumAbility.values()[i].name()) + ": " + player.getActorAbilityScore(EnumAbility.values()[i]));

        for (int i = 0; i < EnumSkill.values().length; i++)
            skillLbls[i] = new Label(Utils.toNormalCase(EnumSkill.values()[i].name()) + ": " + player.getActorSkill(EnumSkill.values()[i]));

        // Add up all the ability scores and set the ability total label
        int abilityTotal = 0;
        for (int i = 0; i < EnumAbility.values().length; i++)
            abilityTotal += player.getActorAbilityScore(EnumAbility.values()[i]);
        abilityTotalLbl = new Label("Ability Total: " + abilityTotal);

        // Add up all the skill scores and set the skill total label
        int skillTotal = 0;
        for (int i = 0; i < EnumSkill.values().length; i++)
            skillTotal += player.getActorSkill(EnumSkill.values()[i]);
        skillTotalLbl = new Label("Skill Total: " + skillTotal);


        playerStatsPopOver = showPlayerPopover();

        saveGameBtn = new Button("Save Game");
        saveGameBtn.setOnAction(e -> {
            Main.log.debug("Saving game...");
            SaveSystem.savePlayerData(anvil, player);
        });

        playerPopoverBtn = new Button("Stats");
        playerPopoverBtn.setOnAction(e -> {
            updateStatsView();
            playerStatsPopOver.show(playerPopoverBtn);
        });

        gridPane.add(nameLbl, 0, 0);
        gridPane.add(raceLbl, 1, 0);
        gridPane.add(classLbl, 2, 0);
        gridPane.add(levelLbl, 0, 1);
        gridPane.add(expLbl, 1, 1);
        gridPane.add(hpLbl, 0, 2);
        gridPane.add(mpLbl, 1, 2);
        gridPane.add(playerPopoverBtn, 0, 3);
        gridPane.add(saveGameBtn, 1, 3);


        this.getChildren().add(gridPane);
    }

    public void updateStatsView() {
        levelLbl.setText("Level: " + player.getActorLevel());
        expLbl.setText("Exp: " + player.getActorExperience());
        hpLbl.setText("HP: " + player.getActorCurrentHealth() + "/" + player.getActorMaxHealth());
        mpLbl.setText("MP: " + player.getActorCurrentMana() + "/" + player.getActorMaxMana());

        for (int i = 0; i < EnumAbility.values().length; i++)
            abilityLbls[i].setText(Utils.toNormalCase(EnumAbility.values()[i].name()) + ": " + player.getActorAbilityScore(EnumAbility.values()[i]));

        for (int i = 0; i < EnumSkill.values().length; i++)
            skillLbls[i].setText(Utils.toNormalCase(EnumSkill.values()[i].name()) + ": " + player.getActorSkill(EnumSkill.values()[i]));

        // Add up all the ability scores and set the ability total label
        int abilityTotal = 0;
        for (int i = 0; i < EnumAbility.values().length; i++)
            abilityTotal += player.getActorAbilityScore(EnumAbility.values()[i]);
        abilityTotalLbl = new Label("Ability Total: " + abilityTotal);

        // Add up all the skill scores and set the skill total label
        int skillTotal = 0;
        for (int i = 0; i < EnumSkill.values().length; i++)
            skillTotal += player.getActorSkill(EnumSkill.values()[i]);
        skillTotalLbl = new Label("Skill Total: " + skillTotal);


    }

    private PopOver showPlayerPopover() {
        PopOver popOver = new PopOver();
        popOver.setTitle("Player Stats");
//        popOver.setDetachable(false);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));

        GridPane skillGridPane = new GridPane();
        skillGridPane.setHgap(10);
        skillGridPane.setVgap(10);

        GridPane abilityGridPane = new GridPane();
        abilityGridPane.setHgap(10);
        abilityGridPane.setVgap(10);

        popOver.setContentNode(borderPane);

        for (int i = 0; i < EnumAbility.values().length; i++) {
            abilityLbls[i].setText(Utils.toNormalCase(EnumAbility.values()[i].name()) + ": " + player.getActorAbilityScore(EnumAbility.values()[i]));
            abilityGridPane.add(abilityLbls[i], 0, i);
        }

        abilityGridPane.add(abilityTotalLbl, 0, EnumAbility.values().length + 1);

        for (int i = 0; i < EnumSkill.values().length; i++) {
            skillLbls[i].setText(Utils.toNormalCase(EnumSkill.values()[i].name()) + ": " + player.getActorSkill(EnumSkill.values()[i]));
            skillGridPane.add(skillLbls[i], 0, i);
        }

        skillGridPane.add(skillTotalLbl, 0, EnumSkill.values().length + 1);

        borderPane.setLeft(abilityGridPane);
        borderPane.setCenter(new Separator(Orientation.VERTICAL));
        borderPane.setRight(skillGridPane);

        return popOver;
    }

}
