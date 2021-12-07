package captainsly.anvil.core.scripting;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LoadState;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.BaseLib;
import org.luaj.vm2.lib.Bit32Lib;
import org.luaj.vm2.lib.CoroutineLib;
import org.luaj.vm2.lib.PackageLib;
import org.luaj.vm2.lib.StringLib;
import org.luaj.vm2.lib.TableLib;
import org.luaj.vm2.lib.jse.JseIoLib;
import org.luaj.vm2.lib.jse.JseMathLib;
import org.luaj.vm2.lib.jse.LuajavaLib;

public class AnvilPlatform {

	public static final String ANVIL_SCRIPT_PLATFORM_VERSION = "0.1";

	// Classes that are referenced in lua
	public static final String EQUIPMENT_CLASS = "captainsly.anvil.mechanics.items.equipment.Equipment";
	public static final String EQUIPMENT_SLOT_TYPE_ENUM = "captainsly.anvil.mechanics.enums.EnumEquipmentSlotType";
	public static final String STAT_ENUM = "captainsly.anvil.mechanics.enums.EnumStat";
	public static final String ABILITY_ENUM = "captainsly.anvil.mechanics.enums.EnumAbility";
	public static final String DIRECTION_ENUM = "captainsly.anvil.mechanics.enums.EnumDirection";
	public static final String PROFESSION_ENUM = "captainsly.anvil.mechanics.enums.EnumProfession";
	public static final String GAME_EVENT_CLASS = "captainsly.anvil.mechanics.events.GameEvent";
	public static final String ACTOR_CLASS = "captainsly.anvil.mechanics.entities.Actor";
	public static final String LOCATION_CLASS = "captainsly.anvil.mechanics.locations.Location";
	public static final String ANVIL_CLASS = "captainsly.anvil.ui.Anvil";

	public static final String ITEM_CLASS = "captainsly.anvil.mechanics.items.Item";

	/**
	 * Create a standard set of globals for JSE and Anvil including all the
	 * libraries.
	 * 
	 * @return Table of globals initialized with the standard JSE libraries + The
	 *         Anvil Libraries
	 * 
	 * @see org.luaj.vm2.lib.jse.JsePlatform
	 * @see org.luaj.vm2.lib.jme.JmePlatform
	 */
	public static Globals standardGlobals() {
		Globals globals = new Globals();
		globals.load(new PackageLib());
		globals.load(new Bit32Lib());
		globals.load(new TableLib());
		globals.load(new StringLib());
		globals.load(new CoroutineLib());
		globals.load(new JseMathLib());
		globals.load(new JseIoLib());
		globals.load(new LuajavaLib());
		globals.load(new BaseLib());
		globals.load(new AnvilBaseLib());
		LoadState.install(globals);
		LuaC.install(globals);
		return globals;
	}

}
