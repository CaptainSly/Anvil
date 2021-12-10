package captainsly.anvil.mechanics.objBuilders;

import captainsly.anvil.core.Registry;
import captainsly.anvil.mechanics.container.Inventory;
import captainsly.anvil.mechanics.entities.Actor;
import captainsly.anvil.mechanics.enums.EnumAbility;
import captainsly.anvil.mechanics.enums.EnumSkill;
import captainsly.anvil.mechanics.factions.Faction;
import captainsly.anvil.mechanics.items.Item;

public class ActorBuilder {

    public Actor actor;

    public ActorBuilder createActor(String actorId, String actorRace, String actorClass) {
        actor = new Actor(actorId, Registry.getActorRace(actorRace), Registry.getCharacterClass(actorClass));
        return this;
    }

    public ActorBuilder setActorNameAndDescription(String name, String description) {
        actor.setActorName(name);
        actor.setActorDescription(description);
        return this;
    }

    public ActorBuilder setActorAbilityScore(EnumAbility ability, int score) {
        actor.getActorAbilityScores()[ability.ordinal()] = score;
        return this;
    }

    public ActorBuilder setActorSkillScore(EnumSkill skill, int score) {
        actor.getActorSkills()[skill.ordinal()] = score;
        return this;
    }

    public ActorBuilder setActorInventory(Item... items) {
        Inventory inventory = new Inventory();
        inventory.addItems(items);
        actor.setActorInventory(inventory);
        return this;
    }

    public ActorBuilder setActorFactions(Faction... factions) {
        for (Faction faction : factions)
            actor.getActorFactionList().add(faction);

        return this;
    }
}
