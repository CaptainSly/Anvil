package captainsly.anvil.mechanics.container;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import captainsly.anvil.mechanics.items.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    Logger logger = LoggerFactory.getLogger("[Inventory]");

    private ObservableList<ItemSlot> itemSlots;

    public Inventory() {
        itemSlots = FXCollections.observableArrayList();

    }

    public void addItem(Item item, int amount) {
        ItemSlot slot = getFreeSlot();
        slot.addItem(item, amount);
    }

    public void removeItem(Item item, int amount) {
        ItemSlot slot = getItemSlotFromItem(item);
        slot.removeItem(amount);
    }

    public ItemSlot getItemSlotFromItem(Item item) {
        // Create a dummy itemslot
        ItemSlot dummySlot = null;

        // Loop through the current itemslots
        for (ItemSlot slot : itemSlots) {

            // Check to see whether or not the current slot is empty
            if (!slot.isEmpty()) {

                // Check the item id to see if they match
                if (slot.getItemFromSlot().getItemId().equals(item.getItemId())) {
                    dummySlot = slot;
                    break;
                }

            } else {
                // The ItemSlot wasn't the right one, checking the next one
                continue;
            }

        }

        return dummySlot;
    }

    public void addItemSlot(ItemSlot slot) {
        itemSlots.add(slot);
    }

    public ItemSlot getFreeSlot() {
        // Check the itemSlots size; if 0 add a new empty itemslot and return
        if (itemSlots.size() == 0) {
            itemSlots.add(new ItemSlot());
            return itemSlots.get(0);
        }

        // Create a dummy itemslot to return and loop through the list of itemslots.
        ItemSlot itemSlot = null;
        for (int i = 0; i < itemSlots.size(); i++) {

            ItemSlot dummySlot = itemSlots.get(i);

            // Check the current dummy slot and see if the itemslot is empty
            if (dummySlot.isEmpty()) {
                itemSlot = dummySlot;
                break;
            } else {
                continue;
            }

        }

        if (itemSlot == null) {
            ItemSlot d = new ItemSlot();
            itemSlots.add(d);

            itemSlot = itemSlots.get(itemSlots.indexOf(d));
        }

        return itemSlot;
    }

    public ObservableList<ItemSlot> getItemSlots() {
        return itemSlots;
    }

    public void addItems(Item[] items) {
        for (Item item : items) {
            addItem(item, 1);
        }
    }
}
