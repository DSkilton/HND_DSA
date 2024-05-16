package com.duncancodes.algorithms.registry;

import com.duncancodes.algorithms.sort.SortAlgorithm;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SortAlgorithmRegistry {
	private static final Map<String, Supplier<SortAlgorithm<?>>> registry = new HashMap<>();

	public static void registerAlgorithm(String name, Supplier<SortAlgorithm<?>> algorithm) {
		registry.put(name.toLowerCase(), algorithm);
	}

	public static SortAlgorithm<?> getAlgorithm(String name) {
		Supplier<SortAlgorithm<?>> supplier = registry.get(name.toLowerCase());
		if (supplier != null) {
			return supplier.get();
		}
		throw new IllegalArgumentException("No sort algorithm found for name: " + name);
	}
}
