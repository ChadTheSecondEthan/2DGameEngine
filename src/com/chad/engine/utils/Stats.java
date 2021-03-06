package com.chad.engine.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Stats {
	
	private static final HashMap<String, Stats> allStats = new HashMap<>();
	private static String defaultPath = "res/stats/";
	
	private final StatsMap stats;

	/**
	 * Creates a new file object for a given file. It will be read from
	 * the default path, which is normally set to "res/stats/"
	 * @param fileName the name of the file <strong>without the extension</strong>
	 */
	private Stats(String fileName) {
		stats = new StatsMap(defaultPath, fileName + ".txt");
		allStats.put(fileName, this);
	}

	public static Stats find(String fileName) {
		if (allStats.containsKey(fileName))
			return allStats.get(fileName);
		return new Stats(fileName);
	}
	
	public void write(String key, int value) {
		stats.remove(key);
		stats.put(key, value + "");
	}
	
	public void write(String key, float value) {
		stats.remove(key);
		stats.put(key, value + "");
	}
	
	public void write(String key, boolean value) {
		stats.remove(key);
		stats.put(key, value ? "true" : "false");
	}
	
	public void write(String key, String value) {
		stats.remove(key);
		stats.put(key, value);
	}
	
	public void write(String key, int[] values) {
		if (values.length == 0) return;
		StringBuilder all = new StringBuilder();
		for (int i : values)
			all.append(i).append(",");
		stats.remove(key);
		stats.put(key, all.toString());
	}
	
	public void write(String key, String[] values) {
		if (values.length == 0) return;
		StringBuilder all = new StringBuilder();
		for (String s : values)
			if (s != null)
				all.append(s).append(",");
		stats.remove(key);
		stats.put(key, all.toString());
	}
	
	public void write(String key, ReadableObject value) {
		stats.remove(key);
		stats.put(key, value.asString());
	}
	
	public String read(String key, String defaultValue) {
		if (stats.containsKey(key))
			return stats.get(key);
		return defaultValue;
	}
	
	public int readInt(String key, int defaultValue) {
		if (stats.containsKey(key))
			return Integer.parseInt(stats.get(key));
		return defaultValue;
	}
	
	public float readFloat(String key, float defaultValue) {
		if (stats.containsKey(key))
			return Float.parseFloat(stats.get(key));
		return defaultValue;
	}
	
	public boolean readBoolean(String key, boolean defaultValue) {
		if (stats.containsKey(key))
			return Boolean.parseBoolean(stats.get(key));
		return defaultValue;
	}
	
	public ReadableObject readObject(String key, ReadableObject defaultValue) {
		if (stats.containsKey(key)) 
			return defaultValue.fromString(key);
		return defaultValue;
	}
	
	public int[] readIntArray(String key) {
		if (stats.containsKey(key)) {
			String[] values = stats.get(key).split(",");
			int[] ints = new int[values.length];
			for (int i = 0; i < ints.length; i++) 
				ints[i] = Integer.parseInt(values[i]);
			return ints;
		}
		return new int[] {};
	}
	
	public String[] readStringArray(String key) {
		if (stats.containsKey(key)) 
			return stats.get(key).split(",");
		return new String[] {};
	}
	
	public void remove(String key) { stats.remove(key); }
	public void save() { stats.saveData(); }
	public void reset() { stats.reset(); }
	
	public static void saveAll() {
		for (String s : allStats.keySet())
			allStats.get(s).save();
	}

	public static void setDefaultStatsPath(String path) {
		defaultPath = path;
	}

}

class StatsMap extends HashMap<String, String> {
	
	private File file;
	
	public StatsMap(String dirName, String fileName) {
		super();
		
		try {

			File dir = new File(dirName);
			if (dir.exists() || dir.mkdir()) {

				file = new File(dirName + fileName);
				if (file.exists())
					readStats();
				else
					file.createNewFile();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void readStats() throws Exception {
		Scanner reader = new Scanner(new FileReader(file));
		while(reader.hasNextLine()) {
			String[] parts = reader.nextLine().split(":");
			
			put(parts[0], parts[1]);
		}
		reader.close();
	}

	void saveData() {
		try {
			if (file.delete() && file.createNewFile()) {
				FileWriter writer = new FileWriter(file);
				
				for (String key : keySet()) 
					writer.write(key + ":" + get(key) + "\n");
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void reset() {
		try {
			file.delete();
			clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
