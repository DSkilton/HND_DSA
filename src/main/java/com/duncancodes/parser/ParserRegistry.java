package com.duncancodes.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ParserRegistry {

	private static final Map<String, Supplier<Parser>> registry = new HashMap<>();

	public static void registerParser(String parserType, Supplier<Parser> parserSupplier) {
		registry.put(parserType.toLowerCase(), parserSupplier);
	}

	public static Parser getParser(String parserType) {
		Supplier<Parser> supplier = registry.get(parserType.toLowerCase());

		if (supplier != null) {
			return supplier.get();
		}

		throw new IllegalArgumentException("No parser found for type: " + parserType);
	}

}
