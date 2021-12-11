package captainsly.anvil.mechanics.enums;

public enum EnumGameEventType {

    SCRIPT_EVENT, QUEST_EVENT, LOCATION_EVENT, MOVEMENT_EVENT, DIALOG_EVENT, BATTLE_EVENT;

    public static EnumGameEventType getEventTypeFromString(String eventType) {
        for (EnumGameEventType d : values())
            if (d.name().toLowerCase().equals(eventType.toLowerCase()))
                return d;

        return null;
    }

    }
