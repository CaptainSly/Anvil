require 'java'
java_import 'captainsly.Main'
java_import 'captainsly.anvil.core.Registry'

module Anvil 
    # AnvilLib is a ruby library for the anvil scripting engine
    
    # Anvil Engine Version String
    anvil_Engine_Version = "0.1.0"
    
    # Registry Methods
    def getLocation(locationId)
    	return Registry.getLocation(locationId)
    end
    
    def getActor(actorId)
    	return Registry.getActor(actorId)
    end
    
    def getItem(itemId)
    	return Registry.getItem(itemId)
    end
    	
    
    # Error Messages
    def info(message)
        Main.log.info("[ASE] - " + message)
    end

    def debug(message)
        Main.log.debug("[ASE] - " + message)
    end

    def error(message)
        Main.log.error("[ASE] - " + message)
    end

end