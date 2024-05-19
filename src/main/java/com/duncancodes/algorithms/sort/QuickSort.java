package com.duncancodes.algorithms.sort;

import java.util.List;

public class QuickSort {
	public static <T extends Comparable<T>> void sort(List<T> data) {
		quickSort(data, 0, data.size() - 1);
	}

	private static <T extends Comparable<T>> void quickSort(List<T> data, int low, int high) {
		if (low < high) {
			int pi = partition(data, low, high);

			quickSort(data, low, pi - 1);
			quickSort(data, pi + 1, high);
		}
	}

	private static <T extends Comparable<T>> int partition(List<T> data, int low, int high) {
		T pivot = data.get(high);
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			if (data.get(j).compareTo(pivot) <= 0) {
				i++;

				T temp = data.get(i);
				data.set(i, data.get(j));
				data.set(j, temp);
			}
		}

		T temp = data.get(i + 1);
		data.set(i + 1, data.get(high));
		data.set(high, temp);

		return i + 1;
	}
}
