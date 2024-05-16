package com.duncancodes.algorithms.search;

import java.util.List;

public class LinearSearch implements SearchAlgorithm<String> {
	@Override
	public int search(List<String> data, String target) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).equals(target)) {
				return i;
			}
		}
		return -1;
	}
}
