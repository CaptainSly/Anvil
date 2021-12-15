# Anvil

Anvil is a textbased game/engine/program that allows you to play a pseudo like dungeons and dragons like textbased game.
It is accompined with a WIP editor called Hammer that, on completion, will allow you to create adventures and games with
the editor. Allowing for countless possibilities.

The engine/editor/program combo uses the following libraries to do what it does

	* JavaFX - For it's UI
	* JRuby - For event, item, npc, etc scripting
	* GSON - For data serialization
	* FontAwesomeFX - for some of the graphics
	* SLF4J/Log4J - For Logging
	* DiceNotation - For random based dice rolls

The engine uses some DnD formula's to calculate some things in game, and the library DiceNotation really helps translate
the formula's into something that can be used in the game, while staying true to what's written in the manuals. 

# Build
========
To build an executable jar, run the build gradle task. It'll automatically compile a fat-jar. To Run the program, create either a bat file with the command, or run the commmand through a terminal

	- java -jar anvil.jar -s anvil

