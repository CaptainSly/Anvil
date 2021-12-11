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
	location = getLocation("locationCalinfor")
	debug(location.getLocationName())
    debug(actor.getActorId())
end

