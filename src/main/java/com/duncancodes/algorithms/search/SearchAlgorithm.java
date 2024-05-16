package com.duncancodes.algorithms.search;

import java.util.List;

public interface SearchAlgorithm <T extends Comparable<T>> {

	int search(List<T> data, T target);
}
