package com.crozin.wykop.sdk.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class TestCollectionsUtils {
	private Collection<Object> emptyCollection;
	private Collection<Object> singleElementCollection;
	private Collection<Object> twoElementsCollection;
	private Collection<Object> multipleElementsCollection;
	
	private Map<Object, Object> emptyMap;
	private Map<Object, Object> singleElementMap;
	private Map<Object, Object> twoElementsMap;
	private Map<Object, Object> multipleElementsMap;
	
	@Before
	public void setUp() {
		emptyCollection = new ArrayList<Object>();
		
		singleElementCollection = new ArrayList<Object>();
		singleElementCollection.add("A");
		
		twoElementsCollection = new ArrayList<Object>();
		twoElementsCollection.add("A");
		twoElementsCollection.add("B");
		
		multipleElementsCollection = new ArrayList<Object>();
		multipleElementsCollection.add("A");
		multipleElementsCollection.add("B");
		multipleElementsCollection.add("C");
		
		emptyMap = new LinkedHashMap<Object, Object>();
		
		singleElementMap = new LinkedHashMap<Object, Object>();
		singleElementMap.put("A", 1);
		
		twoElementsMap = new LinkedHashMap<Object, Object>();
		twoElementsMap.put("A", 1);
		twoElementsMap.put("B", 2);
		
		multipleElementsMap = new LinkedHashMap<Object, Object>();
		multipleElementsMap.put("A", 1);
		multipleElementsMap.put("B", 2);
		multipleElementsMap.put("C", 3);
	}
	
	@Test
	public void joinCollection() {
		assertEquals("", CollectionsUtils.join(emptyCollection, ","));
		assertEquals("A", CollectionsUtils.join(singleElementCollection, ","));
		assertEquals("A,B", CollectionsUtils.join(twoElementsCollection, ","));
		assertEquals("A,B,C", CollectionsUtils.join(multipleElementsCollection, ","));
		assertEquals("ABC", CollectionsUtils.join(multipleElementsCollection, ""));
	}
	
	@Test
	public void joinCollectionLongSeparator() {
		assertEquals("", CollectionsUtils.join(emptyCollection, ",,,"));
		assertEquals("A", CollectionsUtils.join(singleElementCollection, ",,,"));
		assertEquals("A,,,B", CollectionsUtils.join(twoElementsCollection, ",,,"));
		assertEquals("A,,,B,,,C", CollectionsUtils.join(multipleElementsCollection, ",,,"));
	}

	@Test
	public void joinMap() {
		assertEquals("", CollectionsUtils.join(emptyMap, ",", "/"));
		assertEquals("A,1", CollectionsUtils.join(singleElementMap, ",", "/"));
		assertEquals("A,1/B,2", CollectionsUtils.join(twoElementsMap, ",", "/"));
		assertEquals("A,1/B,2/C,3", CollectionsUtils.join(multipleElementsMap, ",", "/"));
		assertEquals("A1B2C3", CollectionsUtils.join(multipleElementsMap, "", ""));
	}
	
	@Test
	public void joinMapLongSeparators() {
		assertEquals("", CollectionsUtils.join(emptyMap, ",,,", "///"));
		assertEquals("A,,,1", CollectionsUtils.join(singleElementMap, ",,,", "///"));
		assertEquals("A,,,1///B,,,2", CollectionsUtils.join(twoElementsMap, ",,,", "///"));
		assertEquals("A,,,1///B,,,2///C,,,3", CollectionsUtils.join(multipleElementsMap, ",,,", "///"));
	}
}
