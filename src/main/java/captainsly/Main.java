package captainsly;

import org.luaj.vm2.Globals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import captainsly.anvil.core.scripting.AnvilPlatform;
import captainsly.anvil.ui.Anvil;
import captainsly.hammer.Hammer;
import captainsly.utils.FileHandler;
import javafx.application.Application;

public class Main {

	public static final String APPLICATION_VERSION = "0.0.1B";

	public static final Logger log = LoggerFactory.getLogger("[MAIN]");

	public static Globals globals;

	public static void main(String[] args) {

		// usage: anvilFX [options] required_input required_input2 options:
		// -h Shows this help text
		// -s, --argument Switches the Application that is launched on startup, Options
		// are Hammer or Anvil

		if (args.length >= 1) {
			// Find out which tag is being used in the arguments.
			switch (args[0]) {
			case "-h":
				// The Help Tag, Prints out help information
				log.info("HELP");
				printHelpInfo();
				System.exit(-1);
				break;
			case "-s":

				log.info("Switch mode. Argument: " + args[1]);
				log.info("Checking to see if the working directory exists");

				if (!FileHandler.doesWorkingDirExist())
					FileHandler.createDefaultWorkingDir();

				log.info("Creating LuaJ globals with the AnvilPlatform Globals");
				globals = AnvilPlatform.standardGlobals();

				// The Switch Tag, allows you to switch between anvil or hammer.
				switchApplication(args[1], args);
				break;
			default:
				printHelpInfo();
				System.exit(-1);
				break;
			}
		} else {
			printHelpInfo();
			System.exit(-1);
		}
	}

	private static void switchApplication(String key, String[] args) {
		if (key.equals("anvil"))
			Application.launch(Anvil.class, args);
		else if (key.equals("hammer"))
			Application.launch(Hammer.class, args);
		else {
			log.error("Use -h for help, unsupported switch tag argument");
			log.error("Loading Default Anvil");
			Application.launch(Anvil.class, args);
		}
	}

	private static void printHelpInfo() {
		log.error("anvilFx [options] argument argument");
		log.error("options:");
		log.error("-h Shows this help text");
		log.error("-s, argument Switches the application that is launched on Startup, options are hammer or anvil");
	}

}
