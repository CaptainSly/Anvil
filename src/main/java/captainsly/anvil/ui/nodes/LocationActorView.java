package captainsly.anvil.ui.nodes;

import captainsly.anvil.mechanics.entities.Actor;
import captainsly.anvil.mechanics.locations.Location;
import captainsly.anvil.ui.Anvil;
import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Callback;

public class LocationActorView extends Region {

    private ListView<Actor> locationActorListView;
    private Location currentLocation;

    public LocationActorView(Anvil anvil, Location currentLocation) {
        this.currentLocation = currentLocation;
        locationActorListView = new ListView<>();
        locationActorListView.setItems(FXCollections.observableArrayList(currentLocation.getLocationActorsList()));
        locationActorListView.setCellFactory(new Callback<ListView<Actor>, ListCell<Actor>>() {
            @Override
            public ListCell<Actor> call(ListView<Actor> param) {
                ListCell<Actor> cell = new ListCell<Actor>() {

                    @Override
                    protected void updateItem(Actor item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getActorName());
                        } else setText("");
                    }
                };
                return cell;
            }
        });
        this.getChildren().add(locationActorListView);
    }

    public void updateLocation(Location location) {
        currentLocation = location;
        locationActorListView.setItems(FXCollections.observableArrayList(currentLocation.getLocationActorsList()));
    }

}
