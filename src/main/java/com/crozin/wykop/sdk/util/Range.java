package com.crozin.wykop.sdk.util;

public class Range<T extends Comparable<T>> {
	private final T lowerBound;
	private final T upperBound;
	
	public Range(T lowerBound, T upperBound) throws IllegalArgumentException {
		if (lowerBound == null || upperBound == null || lowerBound.compareTo(upperBound) > 0) {
			throw new IllegalArgumentException("The boundaries are null or the lower bound is greater than the upper bound");
		}
		
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	
	public T getLowerBound() {
		return lowerBound;
	}

	public T getUpperBound() {
		return upperBound;
	}
}
