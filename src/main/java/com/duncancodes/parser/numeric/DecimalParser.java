package com.duncancodes.parser.numeric;

import com.duncancodes.parser.AbstractDataParser;
import com.duncancodes.parser.Parser;
import com.duncancodes.parser.ParserRegistry;

import java.util.ArrayList;
import java.util.List;

public class DecimalParser extends AbstractDataParser {

	@Override
	public void registerParser() {
		ParserRegistry.registerParser("decimal", DecimalParser::new);
	}

	@Override
	public List<Object> parseLine(String line) {
		List<Object> row = new ArrayList<>();
		String[] values = line.split(",");

		for (String value : values) {
			try {
				row.add(Double.parseDouble(value.trim()));
			} catch (NumberFormatException e) {
				if (next != null) {
					row.add(next.parseLine(value));
				} else {
					row.add(value.trim()); // Return as string if no other parser
				}
			}
		}
		return row;
	}
}