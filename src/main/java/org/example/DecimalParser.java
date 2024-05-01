package org.example;

import java.util.ArrayList;
import java.util.List;

class DecimalParser extends AbstractDataParser {
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