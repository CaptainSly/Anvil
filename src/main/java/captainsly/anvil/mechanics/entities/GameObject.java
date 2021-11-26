package captainsly.anvil.mechanics.entities;

public abstract class GameObject {

	protected final String gameObjId; // Game Object's UUID

	public GameObject(String gameObjId) {
		this.gameObjId = gameObjId;
	}
	
	public abstract void onInteract(Actor actor);

	public String getObjectId() {
		return gameObjId;
	}

}
