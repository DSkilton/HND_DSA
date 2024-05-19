package com.duncancodes.algorithms.search;

import java.util.List;

public class BinarySearch {
	public static int search(List<String> data, String target) {
		return binarySearch(data, target, 0, data.size() - 1);
	}

	private static int binarySearch(List<String> data, String target, int low, int high) {
		if (low <= high) {
			int mid = low + (high - low) / 2;

			if (data.get(mid).equals(target)) {
				return mid;
			}

			if (data.get(mid).compareTo(target) > 0) {
				return binarySearch(data, target, low, mid - 1);
			} else {
				return binarySearch(data, target, mid + 1, high);
			}
		}
		return -1;
	}
}
