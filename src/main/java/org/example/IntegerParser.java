package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IntegerParser extends AbstractDataParser {
	@Override
	public List<List<Object>> parseFile(File file) throws IOException {
		System.out.println("IntegerParser: Parse File");
		return super.parseFile(file);
	}

	@Override
	public List<Object> parseLine(String line) {
		System.out.println("IntegerParser: Parse Line");
		List<Object> row = new ArrayList<>();
		String[] values = line.split(",");

		for (String value : values) {
			try {
				row.add(Integer.parseInt(value.trim()));
			} catch (NumberFormatException e) {
				System.out.println("Passing to Next Parser");
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
