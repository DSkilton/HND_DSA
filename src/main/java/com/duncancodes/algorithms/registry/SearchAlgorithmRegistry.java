package com.duncancodes.algorithms.registry;

import com.duncancodes.algorithms.search.SearchAlgorithm;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SearchAlgorithmRegistry {
	private static final Map<String, Supplier<SearchAlgorithm<?>>> registry = new HashMap<>();

	public static void registerAlgorithm(String name, Supplier<SearchAlgorithm<?>> algorithm) {
		registry.put(name.toLowerCase(), algorithm);
	}

	public static SearchAlgorithm<?> getAlgorithm(String name) {
		Supplier<SearchAlgorithm<?>> supplier = registry.get(name.toLowerCase());
		if (supplier != null) {
			return supplier.get();
		}
		throw new IllegalArgumentException("No search algorithm found for name: " + name);
	}
}
