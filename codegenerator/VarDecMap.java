import java.util.HashMap;
import java.util.Map;

public class VarDecMap {
	
	private static Map<String, String> map = new HashMap<>();
	private static int counter = 5;

	public static String getGlobal(String key) {
		if(!map.containsKey(key)) {
			map.put(key, "" + counter++);
		}
		return map.get(key);
	}
}
