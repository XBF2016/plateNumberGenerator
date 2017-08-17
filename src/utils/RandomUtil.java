package utils;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RandomUtil {
	private static Random random;

	// 双重校验锁获取一个Random单例
	public static Random getRandom() {
		if (random == null) {
			synchronized (RandomUtil.class) {
				if (random == null) {
					random = new Random();
				}
			}
		}

		return random;
	}

	// 获得一个[0,max)之间的整数。
	public static int getRandomInt(int max) {
		return Math.abs(getRandom().nextInt()) % max;
	}

	// 获得一个[0,max)之间的整数。
	public static long getRandomLong(long max) {
		return Math.abs(getRandom().nextInt()) % max;
	}

	// 从list中随机取得一个元素
	public static String getRandomElement(List<String> list) {
		return list.get(getRandomInt(list.size()));
	}

	// 从set中随机取得一个元素
	public static String getRandomElement(Set<String> set) {
		int rn = getRandomInt(set.size());
		int i = 0;
		for (String s : set) {
			if (i == rn) {
				return s;
			}
			i++;
		}
		return null;
	}

	// 从map中随机取得一个key
	public static String getRandomKeyFromMap(Map<String, String> map) {
		int rn = getRandomInt(map.size());
		int i = 0;
		for (String key : map.keySet()) {
			if (i == rn) {
				return key;
			}
			i++;
		}
		return null;
	}

	// 从map中随机取得一个value
	public static String getRandomValueFromMap(Map<String, String> map) {
		int rn = getRandomInt(map.size());
		int i = 0;
		for (String value : map.values()) {
			if (i == rn) {
				return value;
			}
			i++;
		}
		return null;
	}

}
