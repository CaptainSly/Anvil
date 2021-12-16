package captainsly.anvil.mechanics.combat;

import captainsly.anvil.mechanics.entities.Enemy;
import captainsly.anvil.mechanics.player.Player;

public class BattleSystem {

	enum BattleState {
		PLAYER_STATE, ENEMY_STATE, NONE_STATE
	};

	BattleState currentBattleState;

	public BattleSystem() {
		currentBattleState = BattleState.NONE_STATE;
	}

	public void initiateBattle(Player player, Enemy enemy) {
		// Battles are in unclosable dialog boxes

	}

}
