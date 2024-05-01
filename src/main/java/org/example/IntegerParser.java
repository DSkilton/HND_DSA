package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IntegerParser extends AbstractDataParser {
	@Override
	public List<List<Object>> parseFile(File file) throws IOException {
		return null;
	}

	@Override
	public List<Object> parseLine(String line) {
		List<Object> row = new ArrayList<>();
		String[] values = line.split(",");

		for (String value : values) {
			try {
				row.add(Integer.parseInt(value.trim()));
			} catch (NumberFormatException e) {
				if (next != null) {
					return next.parseLine(line);
				} else {
					row.add(value.trim());  // Default to string if no parser
				}
			}
		}
		return row;
	}
}
