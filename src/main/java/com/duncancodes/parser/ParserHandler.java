package com.duncancodes.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ParserHandler {
	private Parser parser;

	public List<List<Object>> handleParsing(File file, String parserType) throws IOException {
		this.parser = ParserRegistry.getParser(parserType);
		return parser.parseFile(file);
	}

	public Parser getParser() {
		return parser;
	}
}