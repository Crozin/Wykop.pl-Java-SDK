package com.crozin.wykop.sdk.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)
public class TestRange {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@DataProvider
	public static Object[][] validConstructorsProvider() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		return new Object[][] {
				{ sdf.parse("2011-12-12"), sdf.parse("2012-12-12") },
				{ sdf.parse("2012-01-01"), sdf.parse("2012-01-02") },
		};
	}
	
	@Test
	@UseDataProvider("validConstructorsProvider")
	public void validConstructor(Date lowerBound, Date upperBound) {
		new Range<Date>(lowerBound, upperBound);
	}
	
	@DataProvider
	public static Object[][] invalidConstructorsProvider() throws ParseException {
		return new Object[][] {
				{ null, sdf.parse("2011-12-12") },
				{ sdf.parse("2012-12-12"), sdf.parse("2011-12-12") },
				{ null, sdf.parse("2012-12-12") },
				{ sdf.parse("2012-12-12"), null },
		};
	}
	
	@Test(expected=IllegalArgumentException.class)
	@UseDataProvider("invalidConstructorsProvider")
	public void invalidConstructor(Date lowerBound, Date upperBound) {
		new Range<Date>(lowerBound, upperBound);
	}
	
	@Test
	public void getters() {
		Range<Integer> range = new Range<Integer>(12, 15);
		
		assertEquals("lower bound", new Integer(12), range.getLowerBound());
		assertEquals("upper bound", new Integer(15), range.getUpperBound());
	}
}
