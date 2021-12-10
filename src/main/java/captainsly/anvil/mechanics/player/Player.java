package captainsly.anvil.mechanics.player;

import captainsly.anvil.core.Registry;
import captainsly.anvil.mechanics.entities.Actor;
import captainsly.anvil.mechanics.entities.actrace.ActorRace;
import captainsly.anvil.mechanics.entities.cclass.CharacterClass;
import captainsly.anvil.mechanics.enums.EnumAbility;
import captainsly.anvil.mechanics.enums.EnumSkill;
import captainsly.anvil.mechanics.journal.PlayerJournal;
import captainsly.utils.Utils;

public class Player extends Actor {

    private static final long serialVersionUID = 2077666720416949312L;
    private PlayerJournal playerJournal;

    public Player(ActorRace playerActorRace, CharacterClass playerCharacterClass) {
        super("actPlayer", playerActorRace, playerCharacterClass);
        playerJournal = new PlayerJournal(this);
    }

    public Player() {
        this(Registry.getActorRace("raceDwarf"), Registry.getCharacterClass("classBarbarian"));
    }

    public PlayerJournal getPlayerJournal() {
        return playerJournal;
    }

    @Override
    public void setActorDescription(String description) {
    }

    @Override
    public String getActorDescription() {
        return "The Player Character";
    }

}
