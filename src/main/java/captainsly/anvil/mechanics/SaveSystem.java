package captainsly.anvil.mechanics;

import captainsly.Main;
import captainsly.anvil.core.Registry;
import captainsly.anvil.mechanics.container.EquipmentSlot;
import captainsly.anvil.mechanics.container.Inventory;
import captainsly.anvil.mechanics.container.ItemSlot;
import captainsly.anvil.mechanics.enums.EnumAbility;
import captainsly.anvil.mechanics.enums.EnumSkill;
import captainsly.anvil.mechanics.items.Item;
import captainsly.anvil.mechanics.player.Player;
import captainsly.anvil.ui.Anvil;
import captainsly.utils.Utils;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Player Data Format:
 * <p>
 * {
 * "currentLocation": "locationId",
 * "player": {
 * "name": "playerName",
 * "exp": "playerExp",
 * "abilityScores": {
 * "strength": "playerStrength",
 * "dexterity": "playerDexterity",
 * "constitution": "playerConstitution",
 * "intelligence": "playerIntelligence",
 * "wisdom": "playerWisdom",
 * "charisma": "playerCharisma"
 * },
 * "skillScores": {
 * "One_Handed": "playerOneHanded",
 * "Two_Handed": "playerTwoHanded",
 * "Blunt": "playerBlunt",
 * "Lockpicking": "playerLockpicking",
 * "Pickpocketing": "playerPickpocketing",
 * "Smithing": "playerSmithing",
 * "Enchanting": "playerEnchanting",
 * "Fishing": "playerFishing",
 * "Wood_Cutting": "playerWoodcutting",
 * "Light_Magic": "playerLightMagic",
 * "Dark_Magic": "playerDarkMagic",
 * "Fire_Magic": "playerFireMagic",
 * "Earth_Magic": "playerEarthMagic",
 * "Frost_Magic": "playerFrostMagic",
 * "Water_Magic": "playerWaterMagic",
 * "Heavy_Armor": "playerHeavyArmor",
 * "Light_Armor": "playerLightArmor",
 * "Medium_Armor": "playerMediumArmor",
 * },
 * "inventory": {
 * "slot01": {
 * "itemId": "itemId",
 * "itemCount": "itemCount"
 * }
 * },
 * "equippedArmor": {
 * "headEquipment": "headEquipmentId",
 * "chestEquipment": "chestEquipmentId",
 * "legsEquipment": "legsEquipmentId",
 * "feetEquipment": "feetEquipmentId",
 * "handsEquipment": "handsEquipmentId"
 * },
 * "equippedWeapon": "equippedWeaponId",
 * "equippedShield": "equippedShieldId",
 * "equippedRings": {
 * "ring01": "ring01Id",
 * "ring02": "ring02Id"
 * },
 * "equippedAmulets": {
 * "amulet01": "amulet01Id",
 * "amulet02": "amulet02Id"
 * },
 * }
 * }
 */

public class SaveSystem {


    public static Player loadPlayerData(Anvil anvil) {
        Player player = new Player();
        try {
            File playerFile = new File(Utils.SAVE_DIRECTORY + "/azraein.json");
            JsonReader reader = new JsonReader(new FileReader(playerFile));
            reader.beginObject();
            while (reader.hasNext()) {
                String currentOp = reader.nextName();
                Main.log.debug("Current Op: " + currentOp);
                if (currentOp.equals("currentLocation")) {
                    anvil.setCurrentLocation(Registry.getLocation(reader.nextString()));
                } else if (currentOp.equals("player")) {
                    reader.beginObject();
                    while (reader.hasNext()) {
                        String playerOp = reader.nextName();
                        Main.log.debug("Player Op: " + playerOp);
                        if (playerOp.equals("name"))
                            player.setActorName(reader.nextString());
                        else if (playerOp.equals("exp"))
                            player.setActorExperience(reader.nextInt());
                        else if (playerOp.equals("currentHP"))
                            player.setActorCurrentHealth(reader.nextInt());
                        else if (playerOp.equals("currentMP"))
                            player.setActorCurrentMana(reader.nextInt());
                        else if (playerOp.equals("abilityScores")) {
                            reader.beginObject();
                            while (reader.hasNext()) {
                                String abilityOp = reader.nextName();
                                Main.log.debug("Ability Op: " + abilityOp);
                                player.setActorAbilityScore(EnumAbility.getAbilityFromString(abilityOp), reader.nextInt());
                            }
                            reader.endObject();
                        } else if (playerOp.equals("skillScores")) {
                            reader.beginObject();
                            while (reader.hasNext()) {
                                String skillOp = reader.nextName();
                                Main.log.debug("Skill Op: " + skillOp);
                                player.setActorSkillScore(EnumSkill.getSkillFromString(skillOp), reader.nextInt());
                            }
                            reader.endObject();
                        } else if (playerOp.equals("actorRace")) {
                            player.setActorRace(Registry.getActorRace(reader.nextString()));
                        } else if (playerOp.equals("actorClass")) {
                            player.setActorCharacterClass(Registry.getCharacterClass(reader.nextString()));
                        } else if (playerOp.equals("inventory")) {
                            reader.beginObject();
                            while (reader.hasNext()) {
                                String inventoryOp = reader.nextName();
                                Main.log.debug("Inventory Op: " + inventoryOp);
                                if (inventoryOp.contains("slot")) {
                                    reader.beginObject();
                                    while (reader.hasNext()) {
                                        ItemSlot slot = new ItemSlot();
                                        String inventoryItemOp = reader.nextName();
                                        Main.log.debug("Inventory Item Op: " + inventoryItemOp);
                                        if (inventoryItemOp.equals("itemId"))
                                            slot.addItem(Registry.getItem(reader.nextString()));
                                        else if (inventoryItemOp.equals("itemCount"))
                                            slot.setItemCount(reader.nextInt());

                                        player.getActorInventory().addItemSlot(slot);
                                    }
                                    reader.endObject();
                                }
                            }
                            reader.endObject();
                        } else
                            reader.skipValue();
                    }
                }
            }
            reader.endObject();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }


    public static void savePlayerData(Anvil anvil, Player player) {
        try {
            JsonWriter playerWriter = new JsonWriter(new FileWriter(Utils.WORKING_DIRECTORY + "saves/" + player.getActorName() + ".json"));
            playerWriter.setIndent("    ");
            playerWriter.beginObject();
            {
                playerWriter.name("currentLocation").value(anvil.getCurrentLocation().getLocationId());
                playerWriter.name("player");
                playerWriter.beginObject();
                {
                    playerWriter.name("name").value(player.getActorName());
                    playerWriter.name("exp").value(player.getActorExperience());
                    playerWriter.name("currentHP").value(player.getActorCurrentHealth());
                    playerWriter.name("currentMP").value(player.getActorCurrentMana());

                    playerWriter.name("abilityScores").beginObject();
                    {
                        for (EnumAbility ability : EnumAbility.values())
                            playerWriter.name(ability.name()).value(player.getActorAbilityScore(ability));
                    }
                    playerWriter.endObject();

                    playerWriter.name("skillScores").beginObject();
                    {
                        for (EnumSkill skill : EnumSkill.values())
                            playerWriter.name(skill.name()).value(player.getActorSkill(skill));
                    }
                    playerWriter.endObject();

                    playerWriter.name("actorRace").value(player.getActorRace().getActorRaceId());
                    playerWriter.name("actorClass").value(player.getActorCharacterClass().getCharacterClassId());

                    playerWriter.name("inventory").beginObject();
                    {
                        for (int i = 0; i < player.getActorInventory().getItemSlots().size(); i++) {
                            ItemSlot slot = player.getActorInventory().getItemSlots().get(i);
                            Item item = slot.getItemFromSlot();
                            String ext = "";
                            if (i <= 9) ext = "0" + i;
                            else ext = "" + i;
                            playerWriter.name("slot" + ext).beginObject();
                            {
                                playerWriter.name("itemId").value(item.getItemId());
                                playerWriter.name("itemCount").value(slot.getItemCount());
                            }
                        }
                        playerWriter.endObject();

                        playerWriter.name("equippedArmor").beginObject();
                        {
                            for (EquipmentSlot slot : player.getArmorEquipmentSlots()) {
                                if (slot.getSlotEquipment() != null)
                                    playerWriter.name(Utils.toNormalCase(slot.getSlotType().name() + "Equipment")).value(slot.getSlotEquipment().getItemId());
                            }
                        }
                        playerWriter.endObject();

                        if (player.getWeaponEquipmentSlot().getItemFromSlot() != null)
                            playerWriter.name("weaponEquipment").value(player.getWeaponEquipmentSlot().getItemFromSlot().getItemId());

                        if (player.getShieldEquipmentSlot().getItemFromSlot() != null)
                            playerWriter.name("shieldEquipment").value(player.getShieldEquipmentSlot().getItemFromSlot().getItemId());

                        playerWriter.name("equippedRings").beginObject();
                        {
                            for (int i = 0; i < player.getRingEquipmentSlots().length; i++) {
                                String ext = "";
                                if (i <= 9) ext = "0" + i;
                                else ext = "" + i;

                                if (player.getRingEquipmentSlots()[i].getSlotEquipment() != null) {
                                    playerWriter.name("ring" + ext).value(player.getRingEquipmentSlots()[i].getSlotEquipment().getItemId());
                                }
                            }
                        }
                        playerWriter.endObject();

                        playerWriter.name("equippedAmulets").beginObject();
                        {
                            for (int i = 0; i < player.getAmuletEquipmentSlots().length; i++) {
                                if (player.getAmuletEquipmentSlots()[i].getSlotEquipment() != null)
                                    playerWriter.name("amulet" + i).value(player.getAmuletEquipmentSlots()[i].getSlotEquipment().getItemId());
                            }
                        }
                    }
                }
            }
            playerWriter.endObject();
            playerWriter.endObject();
            playerWriter.endObject();
            playerWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
