Anvil
=====

Legend

	* [A] Added
	* [R] Removed
	* [F] Fixed
	* [P] Prototyping
	* [H] Hold
	* [B] Bug

Bugs
====

Bug01-UIEquipPop - 11/23/2021

	- The Console throws an error if either of the pop over events are open when the application is closed

 0.1 - The Beginnings Update
==============

* [A] Anvil Scripting Platform
* [A] ControlsFX - 11.1.0
* [A] FontAwesomeFX - 8.9
* [A] Gson - 2.8.9
* [A] LuaJ - 3.0.1
* [A] SLF4J - 1.7.32
* [A] The Ability to load in data through json
* [B] Bug01-UIEquipPop
* [P] The Equipment
* [P] Spells
* [P] Factions
* [P] Magic

Working on the basic functionality of the game/engine/program. The Scripting engine needs a lot of work and needs a lot more to be implemented. 

The actor functionality of the engine currently supports the following:
* [A] ActorRace - The ability to choose a race for an actor
* [A] CharacterClass - A class that the actor "specializes" in that gives them bonuses to abilities and skills
* [A] Ability Scores - Attributes that define the character's stats
* [A] Skills - Attributes that the character can use for certain types of checks, and that can be used to increase the character's stats
* [A] Inventory - The ability to store items in the character's inventory
* [A] Equipment - The ability to store items in the character's equipment
* [P-A] Saving, currently only saves player data and the current location

What's not added to the actor:
  * The ability to hold a quest
  * The ability to hold dialog
  * Npc Ai in general

The beginnings update will be finished when the following is done:
* The Registry system is fleshed out, and includes the ability to load in all the correct data from json
* A Dialog System is created
* Basic Combat System is created

The save system for now, should save the following data:
* Player data, including:
    * Location
    * Name
    * Race
    * Class
    * Inventory
    * Experience
    * Ability Scores
    * Skill Scores
    * Armor EquipmentSlots
    * Neck and Ring EquipmentSlots
    * Weapon and Shield EquipmentSlots

Anvil Scripting Platform
========================

Bugs
====

0.1
====


