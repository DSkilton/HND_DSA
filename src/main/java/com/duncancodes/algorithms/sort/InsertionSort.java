package com.duncancodes.algorithms.sort;

import java.util.List;

public class InsertionSort {
	public static <T extends Comparable<T>> void sort(List<T> data) {
		int n = data.size();
		for (int i = 1; i < n; ++i) {
			T key = data.get(i);
			int j = i - 1;

			// Move elements of data[0..i-1], that are greater than key,
			// to one position ahead of their current position
			while (j >= 0 && data.get(j).compareTo(key) > 0) {
				data.set(j + 1, data.get(j));
				j = j - 1;
			}
			data.set(j + 1, key);
		}
	}
}
