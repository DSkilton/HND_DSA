package com.duncancodes.algorithms.search;

import java.util.List;

public class LinearSearch {
	public static <T> int search(List<T> data, T target) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).equals(target)) {
				return i;
			}
		}
		return -1;
	}
}
