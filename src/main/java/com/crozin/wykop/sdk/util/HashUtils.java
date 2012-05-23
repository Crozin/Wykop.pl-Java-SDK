package com.crozin.wykop.sdk.util;

public class HashUtils {
	public static final char[] LOWERCASE_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	public static final char[] UPPERCASE_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	
	public static String bytesToString(byte[] input) {
		return bytesToString(input, LOWERCASE_DIGITS);
	}
	
	public static String bytesToString(byte[] input, char[] digits) {
		char[] out = new char[input.length << 1];
		
		for (int i = 0, j = 0; i < input.length; i++) {
			out[j++] = digits[(0xF0 & input[i]) >>> 4];
			out[j++] = digits[0x0F & input[i]];
		}
		
		return new String(out);
	}
}
