package captainsly.anvil.core.scripting;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jruby.embed.PathType;
import org.jruby.embed.ScriptingContainer;

import captainsly.utils.Utils;

public class AnvilScriptingEngine {

	public static final String SCRIPTING_ENGINE_VERSION = "0.1.0";
	private final ScriptingContainer container;
	private Object runningScript;

	public AnvilScriptingEngine() {
		container = new ScriptingContainer();
		// Setup The Container
		List<String> loadPaths = new ArrayList<>();
		loadPaths.add(Utils.SCRIPT_DIRECTORY);
		container.setLoadPaths(loadPaths);
	}

	public void init() {
	}

	public void loadScript(String scriptPath) {
		runningScript = container.runScriptlet(Utils.loadFileToString(new File(scriptPath)));
	}

	public void loadScript(File file) {
		runningScript = container.runScriptlet(PathType.ABSOLUTE, file.getAbsolutePath());
	}

	public Object runMethod(String methodName, Object[] arguments, Object argumentClass) {
		Object result = container.callMethod(runningScript, methodName, arguments, argumentClass.getClass());
		return result;
	}
	
	public Object runMethod(String methodName) {
		Object object =  container.callMethod(runningScript, methodName);
		return object;
	}

	public static void main(String[] args) {
		AnvilScriptingEngine engine = new AnvilScriptingEngine();
		engine.init();
		
	}
	
}
