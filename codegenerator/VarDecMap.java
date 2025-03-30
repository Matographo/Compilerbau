import java.util.HashMap;
import java.util.Map;

public class VarDecMap {
	
	private static Map<String, String> globalVar = new HashMap<>();
	private static int globalVarCount = 5;
	private static int jumpAdress = 0;

	public static String getGlobal(String key) {
		if(!globalVar.containsKey(key)) {
			globalVar.put(key, "" + globalVarCount++);
		}
		return globalVar.get(key);
	}

	public static String getJumpAdress() {
		return "__jump" + jumpAdress++;
	}
}
