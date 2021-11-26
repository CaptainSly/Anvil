package captainsly.anvil.mechanics.journal;

public abstract class JournalPage {

	protected final String journalPageId; // Journal Page's UUID

	private String pageTitle; // The Displayable page title
	private String pageDescription; // The Displayable Page Description

	public JournalPage(String journalPageId) {
		this.journalPageId = journalPageId;
	}

	public String getJournalPageId() {
		return journalPageId;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public String getPageDescription() {
		return pageDescription;
	}

}
