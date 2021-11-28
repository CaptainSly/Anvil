package captainsly.anvil.core.scripting;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaString;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;

import com.bernardomg.tabletop.dice.history.RollHistory;
import com.bernardomg.tabletop.dice.interpreter.DiceInterpreter;
import com.bernardomg.tabletop.dice.interpreter.DiceRoller;
import com.bernardomg.tabletop.dice.parser.DefaultDiceParser;
import com.bernardomg.tabletop.dice.parser.DiceParser;

import captainsly.Main;

public class AnvilLib extends TwoArgFunction {

	Globals globals;

	/**
	 * Perform one-time initialization on the library by adding base functions to
	 * the supplied environment, and returning it as the return value.
	 * 
	 * @param modname the module name supplied if this is loaded via 'require'.
	 * @param env     the environment to load into, which must be a Globals
	 *                instance.
	 */
	public LuaValue call(LuaValue modname, LuaValue env) {
		globals = env.checkglobals();

		// Scripting Platform Version number
		env.set("anvilVersion", "Anvil Scripting Platform Version: " + Main.APPLICATION_VERSION);

		// Enum Declaration Strings
		env.set("enumEquipmentStat", AnvilPlatform.EQUIPMENT_STAT_ENUM);
		env.set("enumEquipmentSlotType", AnvilPlatform.EQUIPMENT_SLOT_TYPE_ENUM);
		env.set("enumStat", AnvilPlatform.STAT_ENUM);
		env.set("enumSkill", AnvilPlatform.SKILL_ENUM);
		env.set("enumProfession", AnvilPlatform.PROFESSION_ENUM);
		env.set("enumDirection", AnvilPlatform.DIRECTION_ENUM);

		// Class Declaration Strings
		env.set("classEquipment", AnvilPlatform.EQUIPMENT_CLASS);
		env.set("classGameEvent", AnvilPlatform.GAME_EVENT_CLASS);
		env.set("classActor", AnvilPlatform.ACTOR_CLASS);
		env.set("classLocation", AnvilPlatform.LOCATION_CLASS);
		env.set("classAnvil", AnvilPlatform.ANVIL_CLASS);
		env.set("classItem", AnvilPlatform.ITEM_CLASS);

		// Dice Rolling Functions
		env.set("rollDice", new RollDice());

		// Util Functions
		env.set("anvilLog", new AnvilLog());
		return env;
	}

	final class AnvilLog extends OneArgFunction {

		@Override
		public LuaValue call(LuaValue arg) {
			LuaValue tostring = globals.get("tostring");
			for (int i = 1, n = arg.narg(); i <= n; i++) {
				if (i > 1)
					globals.STDOUT.print('\t');
				LuaString s = tostring.call(arg.arg(i)).strvalue();
				Main.log.debug("[Anvil Scripting]: " + s.tojstring());
			}
			return NONE;
		}
	}

	final class RollDice extends OneArgFunction {

		@Override
		public LuaValue call(LuaValue arg) {
			LuaValue tostring = globals.get("tostring");
			LuaString roll = tostring.call(arg.arg(1)).strvalue();
			final DiceParser parser = new DefaultDiceParser();
			final DiceInterpreter<RollHistory> roller = new DiceRoller();
			final RollHistory rolls = parser.parse(roll.tojstring(), roller);

			return LuaValue.valueOf(rolls.getTotalRoll());
		}

	}

}
