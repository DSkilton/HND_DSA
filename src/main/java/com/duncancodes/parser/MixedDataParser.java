package com.duncancodes.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MixedDataParser implements Parser {
	private Parser firstParser;

	public void setFirstParser(Parser parser) {
		this.firstParser = parser;
	}

	@Override
	public List<List<Object>> parseFile(File file) throws IOException {
		return firstParser.parseFile(file);
	}

	@Override
	public List<Object> parseLine(String line) {
		return firstParser.parseLine(line);
	}

	@Override
	public void setNext(Parser next) {

	}
}
