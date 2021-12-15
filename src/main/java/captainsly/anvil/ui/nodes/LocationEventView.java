package captainsly.anvil.ui.nodes;

import captainsly.anvil.mechanics.events.GameEvent;
import captainsly.anvil.mechanics.locations.Location;
import captainsly.anvil.ui.Anvil;
import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Callback;

public class LocationEventView extends Region {

	private ListView<GameEvent> gameEventView;

	public LocationEventView(Anvil anvil, Location location) {
		gameEventView = new ListView<>();
		gameEventView.setCellFactory(new Callback<ListView<GameEvent>, ListCell<GameEvent>>() {

			@Override
			public ListCell<GameEvent> call(ListView<GameEvent> param) {
				ListCell<GameEvent> cell = new ListCell<GameEvent>() {
					@Override
					protected void updateItem(GameEvent item, boolean empty) {
						super.updateItem(item, empty);
						if (!empty) {
							if (item != null)
								this.setText(item.getGameEventName());
							else
								this.setText("");
						}
					}
				};
				return cell;
			}
		});

		gameEventView.setItems(FXCollections.observableArrayList(location.getLocationActionsList()));

		this.getChildren().add(gameEventView);
	}

	public void updateLocation(Location location) {
		gameEventView.getItems().clear();
		gameEventView.setItems(FXCollections.observableArrayList(location.getLocationActionsList()));
	}

}
