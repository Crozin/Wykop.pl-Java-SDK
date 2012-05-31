package com.crozin.wykop.sdk.util;

import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestRange {
	private Date lowerBound;
	private Date upperBound;
	private Boolean valid;
	
	public TestRange(Date lowerBound, Date upperBound, Boolean valid) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.valid = valid;
	}
	
	@Test
	public void constructor() {
		try {
			new Range<Date>(lowerBound, upperBound);
		} catch (IllegalArgumentException iae) {
			if (valid == true) {
				fail();
			}
		}	
	}
	
	@Parameters
	public static Collection<Object[]> data() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Object[][] dates = new Object[][] {
				{ null, sdf.parse("2011-12-12"), false },
				{ sdf.parse("2012-12-12"), sdf.parse("2011-12-12"), false },
				{ sdf.parse("2012-12-12"), sdf.parse("2012-12-12"), false },
				{ sdf.parse("2011-12-12"), sdf.parse("2012-12-12"), true }
		};
		
		return Arrays.asList(dates);
	}
}
