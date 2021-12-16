package captainsly.anvil.mechanics.items;

import java.util.ArrayList;
import java.util.List;

public class Lootlist {

	private List<Item> lootList;

	public Lootlist() {
		lootList = new ArrayList<>();
	}

	public void add(Item item) {
		lootList.add(item);
	}

	public List<Item> getLootList() {
		return lootList;
	}

}
