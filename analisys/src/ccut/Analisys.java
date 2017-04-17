package ccut;

import java.util.HashMap;
import java.util.Map;

public class Analisys {
	public static int AnalisysString(String string) {
		int length = string.length();
		if (length > 30000) {
			return -1;
		}
		if (length ==0) {
			return 0;
		}
		if (length ==1) {
			return 1;
		}
		Map<Character, Integer> cursor = new HashMap<Character, Integer>();
		cursor.put(string.charAt(0), 0);
		int[] lengthAt = new int[string.length()];
		lengthAt[0] = 1;
		int max = 0;
		for (int i = 1; i < length; i++) {
			char c = string.charAt(i);
			if (cursor.containsKey(c)) {
				lengthAt[i] = Math.min(lengthAt[i - 1] + 1, i - cursor.get(c));
			} else {
				lengthAt[i] = lengthAt[i - 1] + 1;
			}
			max = Math.max(max, lengthAt[i]);
			cursor.put(c, i);
		}
		return max;
	}
}