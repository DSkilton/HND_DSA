package com.duncancodes.parser;

import java.util.List;

public class JsonParser extends AbstractDataParser {
	@Override
	public void registerParser() {
		ParserRegistry.registerParser("json", JsonParser::new);
	}

	@Override
	public List<Object> parseLine(String line) {
		return null;
	}
}
