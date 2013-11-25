package net.sarigul.usermanager.util;

import java.util.HashMap;
import java.util.Map;

public class JSONObject {
	private final Map<String, Object> map;
	
	public JSONObject() {
		this(new HashMap<String, Object>());
	}
	
	public JSONObject(Map<String, Object> data) {
		this.map = data;
	}

	public JSONObject add(String key, Object value) {
		map.put(key, value);
		return this;
	}

	public String toJSON() {
		return toString();
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder("{ ");
		int i = 0, size = map.size();
		for(Map.Entry<String, Object> e : map.entrySet()) {
			b.append("\"").append(e.getKey()).append("\": ");
			Object val = e.getValue();
			if(val instanceof String) {
				b.append("\"").append(val).append("\"");
			} else {
				b.append(val.toString());
			}
			
			if(i++ < size - 1) {
				b.append(", ");
			}
		}
		return b.append("}").toString();
	}
}
