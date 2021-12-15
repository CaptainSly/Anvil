require 'anvilLib'
include Anvil

def getEventId
    return "eventFishing01"
end

def getEventName
    return "Fishing"
end

def onActivate(actor)
    info("Fishing Event Activated")
    caughtFish = getItem("item_Potion_HealthMinor")
    actor.getActorInventory().addItem(caughtFish)
    debug("Added " + caughtFish.getItemId() + " to the inventory")
end

