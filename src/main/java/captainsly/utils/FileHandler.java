package captainsly.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileHandler {

	public static final String WORKING_DIRECTORY = System.getProperty("user.dir") + "/anvil/";

	public static void createDefaultWorkingDir() {
		File workingDir = new File(WORKING_DIRECTORY);
		if (!workingDir.exists()) {
			workingDir.mkdir();
		}
	}

	public static void serializeObject(Object o, String path) {
		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(o);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object deserializeObject(String objectPath) throws ClassNotFoundException, IOException {
		FileInputStream fileIn = new FileInputStream(objectPath);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		Object o = in.readObject();
		in.close();
		fileIn.close();

		return o;
	}

	public static boolean doesWorkingDirExist() {
		return new File(WORKING_DIRECTORY).exists();
	}

}
