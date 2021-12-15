require 'anvilLib'
include "Anvil"

healAmount = 15

def onUse(actor)
	debug("Healing for: " + healAmount)
	actor.modifyActorHealth(healAmount)
end