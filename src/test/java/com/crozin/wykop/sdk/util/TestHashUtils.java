package com.crozin.wykop.sdk.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestHashUtils {
	@Test
	public void bytesToString() {
		byte[] abcdef = new byte[] { (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };
		
		assertEquals("", HashUtils.bytesToString(new byte[] { }));
		
		assertEquals("000000", HashUtils.bytesToString(new byte[] { 0, 0, 0 }));
		assertEquals("7f7f7f", HashUtils.bytesToString(new byte[] { 127, 127, 127 }));
		assertEquals("ff007f", HashUtils.bytesToString(new byte[] { -1, 0, 127 }));
		assertEquals("FF007F", HashUtils.bytesToString(new byte[] { -1, 0, 127 }, HashUtils.UPPERCASE_DIGITS));
		
		assertEquals(HashUtils.bytesToString(abcdef, HashUtils.LOWERCASE_DIGITS), HashUtils.bytesToString(abcdef));
		
		assertEquals("888f969da4abb2b9c0c7ced5dce3eaf1f8ff060d141b222930373e454c535a61686f76", HashUtils.bytesToString(new byte[] { -120, -113, -106, -99, -92, -85, -78, -71, -64, -57, -50, -43, -36, -29, -22, -15, -8, -1, 6, 13, 20, 27, 34, 41, 48, 55, 62, 69, 76, 83, 90, 97, 104, 111, 118 }));
	}
}
