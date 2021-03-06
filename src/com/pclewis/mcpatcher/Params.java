package com.pclewis.mcpatcher;

import java.util.HashMap;

public class Params {
	HashMap<String, String> map;
	MissHandler missHandler;
	static public abstract class MissHandler {
		public abstract String get(String key);
	}

	public Params() {
		this((MissHandler)null);
	}

	public Params(Params src) {
		this.missHandler = src.missHandler;
		this.map = new HashMap<String,String>();
		this.map.putAll(src.map);
	}

	public Params(MissHandler missHandler) {
		this.map = new HashMap<String,String>();
		this.missHandler = missHandler;
	}

	public void put(String key, String value) {
		map.put(key, value);
	}

	public String get(String key) {
		if (map.containsKey(key))
			return map.get(key);
		if(missHandler != null)
			return missHandler.get(key);
		return null;
	}

	public int getInt(String key) {
		return Integer.parseInt(get(key), 10);
	}

	public byte getByte(String key) {
		return (byte)(getInt(key) & 0xFF);
	}

	public boolean getBoolean(String key) {
		return Boolean.parseBoolean(get(key));
	}
}
