package de.htwsaar.compiler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VarDecMap {

	private static Map<String, String> globalVar = new HashMap<>();
	private static Map<String, List<String>> functionVar = new HashMap<>();
	private static int globalVarCount = 0;
	private static int jumpAdress = 0;
	private static String currentFunction = null;

	public static String getGlobal(String key) {
		return globalVar.get(key);
	}

	public static String declarateGlobalVar(String key) {
		if (!globalVar.containsKey(key)) {
			globalVar.put(key, globalVarCount + "");
			globalVarCount++;
			return globalVar.get(key);
		} else {
			throw new IllegalArgumentException("Duplicate global variable definition: " + key);
		}
	}

	// public static String getVar(String function, String key) {
	// if (functionVar.containsKey(function)) {
	// if (functionVar.get(function).contains(key)) {
	// return key;
	// }
	// }
	// if (globalVar.containsKey(key)) {
	// return "_g" + key;
	// }
	// }

	public static String getLocal(String key) {
		return "_l" + key;
	}

	public static String getJumpAdress(String key) {
		return "_j" + key;
	}

	public static String getJumpAdress() {
		return "_j" + jumpAdress++;
	}

	public static String getFunctionAdress(String key, List<String> params) {
		String functionName = "_f" + key;
		if (!functionVar.containsKey(key)) {
			functionVar.put(key, params);
			if(functionVar.size() == 1) {
				functionName = "mark\nloadc _fmain\ncall\nhalt\n" + functionName;
			}
			currentFunction = key;
			return functionName;
		} else {
			throw new IllegalArgumentException("Duplicate function definition: " + key);
		}
	}

	public static String getCurrentFunction() {
		return currentFunction;
	}

	public static int getFunctionArgCount(String key) {
		if (functionVar.containsKey(key)) {
			return functionVar.get(key).size();
		} else {
			throw new IllegalArgumentException("Function not defined: " + key);
		}
	}

	public static int getLocalVar(String funcName, String varName) {
		if (functionVar.containsKey(funcName)) {
			List<String> params = functionVar.get(funcName);
			if (params.contains(varName)) {
				return params.indexOf(varName) + 1;
			} else {
				throw new IllegalArgumentException("Variable not found in function: " + varName);
			}
		} else {
			throw new IllegalArgumentException("Function not defined: " + funcName);
		}
	}

	public static String getVarDec(String key) {
		if(globalVar.get(key) != null) {
			return "loada " + globalVar.get(key) + "\n";
		} else {
			return "loadc " + getLocalVar(currentFunction, key) + "\nloadc SP\nadd\nload\n";
		}
	}
}
