package captainsly.anvil.mechanics.container;

import captainsly.anvil.mechanics.items.Item;

public class ItemSlot implements Comparable<ItemSlot> {

	private Item item;
	private int itemCount;
	private boolean isEmpty;

	public ItemSlot(Item item) {
		this(item, 1, false);
	}

	public ItemSlot() {
		this(null, 0, true);
	}

	public ItemSlot(Item item, int amount, boolean isEmpty) {
		this.item = item;
		itemCount = amount;
		this.isEmpty = isEmpty;
	}

	public void addItem(Item item) {
		this.item = item;
		itemCount = 1;
		isEmpty = false;
	}

	public void addItem(Item item, int amount) {
		addItem(item);
		addItem(amount);
	}

	public void addItem(int i) {
		itemCount += i;
		isEmpty = false;
	}

	public void removeItem(int i) {
		if (i >= itemCount)
			i = itemCount;

		itemCount -= i;

		if (itemCount == 0)
			isEmpty = true;
	}

	public void emptySlot() {
		removeItem(itemCount);
	}

	public Item getItemFromSlot() {
		return item;
	}

	public int getItemCount() {
		return itemCount;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public boolean doesContainItem(Item item) {
		return getItemFromSlot().getItemId().equals(item.getItemId());

	}

	@Override
	public int compareTo(ItemSlot o) {
		return Boolean.compare(isEmpty, o.isEmpty);
	}

}
