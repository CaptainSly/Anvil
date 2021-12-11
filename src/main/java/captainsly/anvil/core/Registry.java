package captainsly.anvil.core;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import captainsly.anvil.mechanics.items.equipment.weapons.Weapon;
import com.google.gson.stream.JsonReader;

import captainsly.Main;
import captainsly.anvil.mechanics.entities.Actor;
import captainsly.anvil.mechanics.entities.actrace.ActorRace;
import captainsly.anvil.mechanics.entities.cclass.CharacterClass;
import captainsly.anvil.mechanics.enums.EnumAbility;
import captainsly.anvil.mechanics.enums.EnumDirection;
import captainsly.anvil.mechanics.enums.EnumSkill;
import captainsly.anvil.mechanics.events.GameEvent;
import captainsly.anvil.mechanics.items.Item;
import captainsly.anvil.mechanics.locations.Location;
import captainsly.anvil.mechanics.objBuilders.ActorRaceBuilder;
import captainsly.anvil.mechanics.objBuilders.CharacterClassBuilder;
import captainsly.anvil.mechanics.objBuilders.LocationBuilder;
import captainsly.utils.Utils;

public class Registry {

    private static final Map<String, Item> itemRegistry = new HashMap<>();
    private static final Map<String, Weapon> weaponRegistry = new HashMap<>();
    private static final Map<String, ActorRace> actorRaceRegistry = new HashMap<>();
    private static final Map<String, Location> locationRegistry = new HashMap<>();
    private static final Map<String, CharacterClass> characterClassRegistry = new HashMap<>();
    private static final Map<String, Actor> actorRegistry = new HashMap<>();
    private static final Map<String, GameEvent> gameEventRegistry = new HashMap<>();

    private static final File DATA_DIRECTORY_FILE = new File(Utils.WORKING_DIRECTORY + "data/");

    public static void register() {
        Main.log.debug("Registering Objects to respective lists");

        registerClasses();
        registerRaces();
        registerItems();
        registerEquipments();
        registerFactions();
        registerActors();
//        registerEvents();
        registerLocations();
    }

    private static void registerItems() {
        // Register All of the different types of items here
    }

    private static void registerEquipments() {
    }

    private static void registerFactions() {
    }

    private static void registerActors() {
    }

    private static void registerClasses() {
        // Loop through the data directory and search for the classes.json file
        for (File file : DATA_DIRECTORY_FILE.listFiles()) {
            if (file.getName().equals("classes.json")) { // THE FILE NAME SHOULD NEVER CHANGE

                // Create a new json reader and read the file
                // All Classes are located in one single file
                try {
                    JsonReader classReader = new JsonReader(new FileReader(file));
                    // Begin opening the file
                    classReader.beginObject();
                    while (classReader.hasNext()) {
                        // Inside the file, each class is it's own object with the name being the id
                        // Create a classBuilder to create the class from the file and create dummy
                        // variables to hold data
                        CharacterClassBuilder classBuilder = new CharacterClassBuilder();
                        String classId = classReader.nextName(), className = "", classDescription = "";
                        int[] classAbilityBonuses = new int[EnumAbility.values().length];
                        int[] classSkillBonuses = new int[EnumSkill.values().length];

                        Main.log.debug("Creating CharacterClass: " + classId);
                        classReader.beginObject();
                        {
                            while (classReader.hasNext()) {
                                // Get the current "operation" that needs to be done
                                String currentOp = classReader.nextName();
                                Main.log.debug("Current CharacterClass Opeartion: " + currentOp);

                                if (currentOp.equals("name")) // The Race Name
                                    className = classReader.nextString();
                                else if (currentOp.equals("description")) // The Race Description
                                    classDescription = classReader.nextString();
                                else if (currentOp.equals("abilityBonuses")) { // The Ability Bonuses
                                    classReader.beginObject();
                                    {
                                        while (classReader.hasNext()) {
                                            EnumAbility ability = EnumAbility
                                                    .getAbilityFromString(classReader.nextName());
                                            int abilityBonus = classReader.nextInt();

                                            Main.log.debug("Ability: " + ability.name() + ", +" + abilityBonus);
                                            classAbilityBonuses[ability.ordinal()] = abilityBonus;
                                        }
                                    }
                                    classReader.endObject();
                                } else if (currentOp.equals("skillBonuses")) { // The Skill Bonuses
                                    classReader.beginObject();
                                    {
                                        while (classReader.hasNext()) {
                                            EnumSkill skill = EnumSkill.getSkillFromString(classReader.nextName());
                                            int skillBonus = classReader.nextInt();

                                            Main.log.debug("Skill: " + skill.name() + ", +" + skillBonus);
                                            classSkillBonuses[skill.ordinal()] = skillBonus;
                                        }
                                    }
                                    classReader.endObject();
                                }
                            }
                        }

                        // Build the race
                        classBuilder.createCharacterClass(classId, className, classDescription);
                        for (int i = 0; i < EnumAbility.values().length; i++)
                            classBuilder.modifyCharacterAbility(EnumAbility.values()[i], i);

                        for (int i = 0; i < EnumSkill.values().length; i++)
                            classBuilder.modifyCharacterSkill(EnumSkill.values()[i], i);

                        characterClassRegistry.put(classId, classBuilder.build());
                        classReader.endObject();

                    }
                    classReader.endObject();
                    classReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private static void registerRaces() {
        // Loop through the data directory and search for the races.json file
        for (File file : DATA_DIRECTORY_FILE.listFiles()) {
            if (file.getName().equals("races.json")) { // THE FILE NAME SHOULD NEVER CHANGE

                // Create a new json reader and read the file
                // All races are located in one single file
                try {
                    JsonReader raceReader = new JsonReader(new FileReader(file));
                    // Begin opening the file
                    raceReader.beginObject();
                    while (raceReader.hasNext()) {
                        // Inside the file, each race is it's own object with the name being the id
                        // Create a raceBuilder to create the race from the file and create dummy
                        // variables to hold data
                        ActorRaceBuilder raceBuilder = new ActorRaceBuilder();
                        String raceId = raceReader.nextName(), raceName = "", raceDescription = "";
                        int[] raceAbilityBonuses = new int[EnumAbility.values().length];
                        int[] raceSkillBonuses = new int[EnumSkill.values().length];

                        Main.log.debug("Creating race: " + raceId);
                        raceReader.beginObject();
                        {
                            while (raceReader.hasNext()) {
                                // Get the current "operation" that needs to be done
                                String currentOp = raceReader.nextName();
                                Main.log.debug("Current Race Opeartion: " + currentOp);

                                if (currentOp.equals("name")) // The Race Name
                                    raceName = raceReader.nextString();
                                else if (currentOp.equals("description")) // The Race Description
                                    raceDescription = raceReader.nextString();
                                else if (currentOp.equals("abilityBonuses")) { // The Ability Bonuses
                                    raceReader.beginObject();
                                    {
                                        while (raceReader.hasNext()) {
                                            EnumAbility ability = EnumAbility
                                                    .getAbilityFromString(raceReader.nextName());
                                            int abilityBonus = raceReader.nextInt();

                                            Main.log.debug("Ability: " + ability.name() + ", +" + abilityBonus);
                                            raceAbilityBonuses[ability.ordinal()] = abilityBonus;
                                        }
                                    }
                                    raceReader.endObject();
                                } else if (currentOp.equals("skillBonuses")) { // The Skill Bonuses
                                    raceReader.beginObject();
                                    {
                                        while (raceReader.hasNext()) {
                                            EnumSkill skill = EnumSkill.getSkillFromString(raceReader.nextName());
                                            int skillBonus = raceReader.nextInt();

                                            Main.log.debug("Skill: " + skill.name() + ", +" + skillBonus);
                                            raceSkillBonuses[skill.ordinal()] = skillBonus;
                                        }
                                    }
                                    raceReader.endObject();
                                }
                            }
                        }

                        // Build the race
                        raceBuilder.createActorRace(raceId, raceName, raceDescription);
                        for (int i = 0; i < EnumAbility.values().length; i++)
                            raceBuilder.modifyAbility(EnumAbility.values()[i], raceAbilityBonuses[i]);

                        for (int i = 0; i < EnumSkill.values().length; i++)
                            raceBuilder.modifySkills(EnumSkill.values()[i], raceSkillBonuses[i]);

                        actorRaceRegistry.put(raceId, raceBuilder.build());
                        raceReader.endObject();

                    }
                    raceReader.endObject();
                    raceReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private static void registerEvents() {
        // Look through the scripts/events folder and create an event for each script and load it into memory
        File[] eventScriptFiles = new File(Utils.SCRIPT_DIRECTORY + "events/").listFiles();
        for (int i = 0; i < eventScriptFiles.length; i++) {
            if (eventScriptFiles[i].isFile()) {
                Main.log.debug("Loading event script: " + eventScriptFiles[i].getName());
                GameEvent event = new GameEvent(Utils.SCRIPT_DIRECTORY + "events/" + eventScriptFiles[i].getName());
//                Main.log.debug("Event Script Loaded: " + event.getGameEventId());
            }
        }
    }

    private static void registerLocations() {
        // Loop through the data directory and find the locations file
        for (File file : DATA_DIRECTORY_FILE.listFiles()) {
            if (file.getName().equals("locations.json")) {
                // Found the file we're looking for

                try {
                    JsonReader locationReader = new JsonReader(new FileReader(file));

                    // Parse the file
                    locationReader.beginObject();
                    while (locationReader.hasNext()) {
                        // The First name should be the location id
                        LocationBuilder locationBuilder = new LocationBuilder();
                        String locationId = locationReader.nextName();
                        String locationName = "", locationDescription = "";

                        locationReader.beginObject();
                        while (locationReader.hasNext()) {
                            // Inside the location object begin it and get the actual location name
                            {
                                String currentOp = locationReader.nextName();

                                if (currentOp.equals("name"))
                                    locationName = locationReader.nextString();
                                else if (currentOp.equals("description"))
                                    locationDescription = locationReader.nextString();
                                else if (currentOp.equals("neighbors")) {
                                    // We have the name and description, throw it into the builder and get ready for
                                    // the neighbors
                                    locationBuilder.createLocation(locationId, locationName, locationDescription);

                                    locationReader.beginObject();
                                    while (locationReader.hasNext()) {
                                        String dir = locationReader.nextName();
                                        String neighborId = locationReader.nextString();

                                        locationBuilder.addNeighbor(neighborId,
                                                EnumDirection.getDirectionFromString(dir));

                                    }
                                    locationReader.endObject();
                                } else if (currentOp.equals("locationEvents")) {
                                    // Since all the events are already loaded, add them to the list
                                    locationReader.beginArray();
                                    while (locationReader.hasNext()) {
                                        locationBuilder.addEvent(locationReader.nextString());
                                    }
                                    locationReader.endArray();
                                } else if (currentOp.equals("locationActors")) {
                                    // Since all the actors are already loaded, add them to the list
                                    locationReader.beginArray();
                                    while (locationReader.hasNext()) {
                                        locationBuilder.addActor(locationReader.nextString());
                                    }
                                    locationReader.endArray();
                                }

                                // End of the Location Declaration add it to the list
                                Registry.locationRegistry.put(locationId, locationBuilder.build());
                            }
                        }
                        locationReader.endObject();
                    }
                    locationReader.endObject();

                    locationReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static Location getLocation(String locationId) {
        return locationRegistry.get(locationId);
    }

    public static Item getItem(String itemId) {
        return itemRegistry.get(itemId);
    }

    public static Weapon getWeapon(String weaponId) {
        return weaponRegistry.get(weaponId);
    }

    public static ActorRace getActorRace(String actorRaceId) {
        return actorRaceRegistry.get(actorRaceId);
    }

    public static CharacterClass getCharacterClass(String characterClassId) {
        return characterClassRegistry.get(characterClassId);
    }

    public static Map<String, Item> getItemRegistry() {
        return itemRegistry;
    }

    public static Map<String, Weapon> getWeaponRegistry() {
        return weaponRegistry;
    }

    public static Map<String, ActorRace> getActorRaceRegistry() {
        return actorRaceRegistry;
    }

    public static Map<String, Location> getLocationRegistry() {
        return locationRegistry;
    }

    public static Map<String, CharacterClass> getCharacterClassRegistry() {
        return characterClassRegistry;
    }

    public static Map<String, Actor> getActorRegistry() {
        return actorRegistry;
    }

    public static Map<String, GameEvent> getGameEventRegistry() {
        return gameEventRegistry;
    }

    public static Actor getActor(String actorId) {
        return actorRegistry.get(actorId);
    }

    public static GameEvent getGameEvent(String gameEventId) {
        return gameEventRegistry.get(gameEventId);
    }


}
