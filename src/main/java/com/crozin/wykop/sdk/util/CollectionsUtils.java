package com.crozin.wykop.sdk.util;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionsUtils {
	public static String join(Collection<? extends Object> list, String separator) {
		StringBuilder sb = new StringBuilder();
		
		for (Object o : list) {
			sb.append(o.toString());
			sb.append(separator);
		}
		
		if (sb.length() > 0 && separator.length() > 0) {
			sb.delete(sb.length() - separator.length(), sb.length());
		}
		
		return sb.toString();
	}
	
	public static String join(Map<? extends Object, ? extends Object> map, String keyValueSeparator, String entrySeparator) {
		StringBuilder sb = new StringBuilder();
		
		for (Entry<? extends Object, ? extends Object> entry : map.entrySet()) {
			sb.append(entry.getKey().toString());
			sb.append(keyValueSeparator);
			sb.append(entry.getValue().toString());
			sb.append(keyValueSeparator);
		}
		
		if (sb.length() > 0 && entrySeparator.length() > 0) {
			sb.delete(sb.length() - entrySeparator.length(), sb.length());
		}
		
		return sb.toString();
	}
}
