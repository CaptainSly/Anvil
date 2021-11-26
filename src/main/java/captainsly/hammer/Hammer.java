package captainsly.hammer;

import captainsly.hammer.core.ui.EquipmentTab;
import captainsly.hammer.core.ui.LocationTab;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Hammer extends Application {

	private BorderPane rootBP;
	private TabPane tabPane;
	private MenuBar menuBar;

	@Override
	public void start(Stage primaryStage) throws Exception {
		rootBP = new BorderPane();
		tabPane = new TabPane();
		menuBar = new MenuBar();

		tabPane.getTabs().addAll(new EquipmentTab(), new LocationTab());

		rootBP.setTop(menuBar);
		rootBP.setCenter(tabPane);

		Scene scene = new Scene(rootBP, 640, 480);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("hammerlogo.png"));
		primaryStage.setTitle("Hammer");
		primaryStage.show();
	}

}
