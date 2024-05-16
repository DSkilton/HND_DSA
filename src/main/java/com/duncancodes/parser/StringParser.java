package com.duncancodes.parser;

import java.util.ArrayList;
import java.util.List;

public class StringParser extends AbstractDataParser {
	@Override
	public void registerParser(){
		ParserRegistry.registerParser("string", StringParser::new);
	}

	@Override
	public List<Object> parseLine(String line) {
		System.out.println("StringParser: Parse File");
		List<Object> row = new ArrayList<>();
		String[] values = line.split(",");

		for (String value : values) {
			row.add(value.trim());
		}
		return row;
	}
}
