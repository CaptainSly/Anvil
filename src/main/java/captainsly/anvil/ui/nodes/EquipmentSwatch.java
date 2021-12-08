package captainsly.anvil.ui.nodes;

import org.controlsfx.control.PopOver;

import captainsly.anvil.mechanics.container.EquipmentSlot;
import captainsly.anvil.mechanics.enums.EnumEquipmentSlotType;
import captainsly.anvil.mechanics.items.equipment.Equipment;
import captainsly.anvil.mechanics.player.Player;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class EquipmentSwatch extends Region {

	private final Player player;
	private Button btnHeadSlot, btnHandSlot, btnChestSlot, btnLegSlot, btnFootSlot, btnNeckSlot, btnRingSlot;
	private EquipmentSlot[] armorEquipmentSlots;
	private EquipmentSlot[] ringEquipmentSlots;
	private EquipmentSlot[] amuletEquipmentSlots;

	private PopOver ringPopOver, amuletPopOver;

	private GridPane rootGrid;

	public EquipmentSwatch(Player player) {
		this.player = player;

		amuletEquipmentSlots = player.getAmuletEquipmentSlots();
		ringEquipmentSlots = player.getRingEquipmentSlots();
		armorEquipmentSlots = player.getArmorEquipmentSlots();

		// Setup Root Pane
		rootGrid = new GridPane();
		rootGrid.setPadding(new Insets(1.5f, 1.5f, 1.5f, 1.5f));
		rootGrid.setHgap(5);
		rootGrid.setVgap(2.5f);

		// Setup Buttons
		btnNeckSlot = new Button();
		btnNeckSlot.setGraphic(new ImageView(new Image("icons/icon_necklace.png")));

		btnRingSlot = new Button();
		btnRingSlot.setGraphic(new ImageView(new Image("icons/icon_ring.png")));

		btnHeadSlot = new Button();
		btnHeadSlot.setGraphic(new ImageView(new Image("icons/icon_helmet.png")));
		btnHeadSlot.setTooltip(createEquipmentTooltip(armorEquipmentSlots[EnumEquipmentSlotType.HEAD.ordinal()]));

		btnHandSlot = new Button();
		btnHandSlot.setGraphic(new ImageView(new Image("icons/icon_hand.png")));
		btnHandSlot.setTooltip(createEquipmentTooltip(armorEquipmentSlots[EnumEquipmentSlotType.HANDS.ordinal()]));

		btnChestSlot = new Button();
		btnChestSlot.setGraphic(new ImageView(new Image("icons/icon_chest.png")));
		btnChestSlot.setTooltip(createEquipmentTooltip(armorEquipmentSlots[EnumEquipmentSlotType.CHEST.ordinal()]));

		btnLegSlot = new Button();
		btnLegSlot.setGraphic(new ImageView(new Image("icons/icon_legs.png")));
		btnLegSlot.setTooltip(createEquipmentTooltip(armorEquipmentSlots[EnumEquipmentSlotType.LEGS.ordinal()]));

		btnFootSlot = new Button();
		btnFootSlot.setGraphic(new ImageView(new Image("icons/icon_boots.png")));
		btnFootSlot.setTooltip(createEquipmentTooltip(armorEquipmentSlots[EnumEquipmentSlotType.FEET.ordinal()]));

		// TODO: Drag and Drop equipping, and necklace and ring popovers


		ringPopOver = new PopOver();
		ringPopOver.setContentNode(createPopOverFlow(ringEquipmentSlots));
		ringPopOver.setDetachable(false);

		// Setup the Amulet PopOver
		amuletPopOver = new PopOver();
		amuletPopOver.setContentNode(createPopOverFlow(amuletEquipmentSlots));
		amuletPopOver.setDetachable(false);

		btnRingSlot.setOnDragDetected(e -> ringPopOver.show(btnRingSlot));
		btnNeckSlot.setOnDragDetected(e -> amuletPopOver.show(btnNeckSlot));

		btnRingSlot.setOnAction(e -> ringPopOver.show(btnRingSlot));
		btnNeckSlot.setOnAction(e -> amuletPopOver.show(btnNeckSlot));

		// Add Nodes to Grid
		rootGrid.add(btnHeadSlot, 1, 0);
		rootGrid.add(btnNeckSlot, 2, 0);
		rootGrid.add(btnHandSlot, 0, 1);
		rootGrid.add(btnChestSlot, 1, 1);
		rootGrid.add(btnRingSlot, 2, 1);
		rootGrid.add(btnLegSlot, 1, 2);
		rootGrid.add(btnFootSlot, 1, 3);

		this.getChildren().add(rootGrid);
	}

	private FlowPane createPopOverFlow(EquipmentSlot[] equipmentSlots) {
		FlowPane flowPane = new FlowPane();
		flowPane.getChildren().clear();
		flowPane.setMaxSize(250, 250);
		flowPane.setPadding(new Insets(5f, 5f, 5f, 5f));

		for (int i = 0; i < equipmentSlots.length; i++) {
			// Create Dummy Button for the slot to interact with
			EquipmentSlot dummySlot = equipmentSlots[i];
			Button btnDummySlot = new Button();

			if (dummySlot.getSlotType().equals(EnumEquipmentSlotType.RINGS))
				btnDummySlot.setGraphic(new ImageView(new Image("icons/icon_ring.png")));
			else
				btnDummySlot.setGraphic(new ImageView(new Image("icons/icon_necklace.png")));

			// Create the Equipment Tooltip
			btnDummySlot.setTooltip(createEquipmentTooltip(equipmentSlots[i]));

			btnDummySlot.setOnAction(e -> {
				System.out.println("TEST: " + dummySlot.getSlotType().name());
			});

			// Add the slot to the flow pane
			flowPane.getChildren().add(btnDummySlot);
		}
		return flowPane;
	}

	private Tooltip createEquipmentTooltip(EquipmentSlot slot) {
		String equipmentSlotType = slot.getSlotType().name();
		String isEmpty = slot.isEmpty() ? "Empty" : slot.getSlotEquipment().getItemName();

		return new Tooltip(equipmentSlotType + "\n" + isEmpty + "\n");
	}

	/**
	 * Called when something is equipped through a context menu
	 * 
	 * @param equipment - The equipment to be equipped
	 */
	public void equipEquipment(Equipment equipment) {

		// If it's a necklace or ring handle it here
		if (equipment.getEquipSlotType() == EnumEquipmentSlotType.NECK) {

			// Loop through the necklace array
			for (EquipmentSlot slot : amuletEquipmentSlots) {
				// Check to see if the slot is empty
				if (slot.isEmpty()) {
					// Add the equipment to the slot
					slot.addEquipment(equipment);

				} else
					continue;
			}

		} else if (equipment.getEquipSlotType() == EnumEquipmentSlotType.RINGS) {

			// Loop through the ring array
			for (EquipmentSlot slot : ringEquipmentSlots) {
				// Check to see if the slot is empty
				if (slot.isEmpty()) {
					// Add the equipment to the slot
					slot.addEquipment(equipment);
				}
			}

		} else {

			// Not a ring or necklace, must be a piece of armor add it to the equipment
			getArmorSlot(equipment.getEquipSlotType()).addEquipment(equipment);

		}

	}

	public EquipmentSlot getArmorSlot(EnumEquipmentSlotType slotType) {
		EquipmentSlot slot = null;
		for (EquipmentSlot equip : armorEquipmentSlots) {
			if (equip.getSlotType().equals(slotType))
				slot = equip;
		}

		return slot;
	}

}
