package com.duncancodes.algorithms.sort;

import java.util.List;

public class BubbleSort {
	public static <T extends Comparable<T>> void sort(List<T> data) {
		int n = data.size();
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				if (data.get(j).compareTo(data.get(j + 1)) > 0) {
					// Swap data[j] and data[j+1]
					T temp = data.get(j);
					data.set(j, data.get(j + 1));
					data.set(j + 1, temp);
				}
			}
		}
	}
}
