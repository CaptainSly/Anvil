package captainsly.anvil.mechanics.journal;

import java.util.ArrayList;
import java.util.List;

import captainsly.anvil.mechanics.player.Player;

/**
 * The Player Journal contains information about the player, what quests they
 * have and which ones they've completed, and also the player's inventory.
 * 
 * @author Zachary
 *
 */
public class PlayerJournal {

	private final Player player;
	private final List<JournalPage> journalPages;

	public PlayerJournal(Player player) {
		this.player = player;
		journalPages = new ArrayList<>();
	}

	public void addJournalPage(JournalPage journalPage) {
		journalPages.add(journalPage);
	}

	public Player getPlayer() {
		return player;
	}

	public List<JournalPage> getJournalPages() {
		return journalPages;
	}

}
