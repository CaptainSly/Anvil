package captainsly.anvil.ui.nodes;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import captainsly.anvil.mechanics.player.Player;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;

public class InventoryView extends Region {

	private FlowPane rootFlowPane;

	private List<Button> itemSlotBtnList;

	private final Player player;

	private final Logger log = LoggerFactory.getLogger("[Inventory View]");

	public InventoryView(Player player) {
		this.player = player;
		rootFlowPane = new FlowPane();
		itemSlotBtnList = new ArrayList<>();

		


		
	}
	
}
