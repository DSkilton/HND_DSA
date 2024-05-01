package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractDataParser implements Parser {
	public abstract List<Object> parseLine(String line);

	protected Parser next;
	protected static List<String> headers;

	public void setNext(Parser next) {
		this.next = next;
	}

	@Override
	public List<List<Object>> parseFile(File file) throws IOException {
		System.out.println("Entering Abstract Data Parser");
		List<List<Object>> data = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line = reader.readLine();

			if (line != null) {
				headers = Arrays.asList(line.split(","));
				System.out.println("Headers: " + headers);

				while ((line = reader.readLine()) != null) {
					System.out.println("Reading Line: " + line);
					List<Object> parsedLine = parseLine(line);

					System.out.println("Parsed Line: " + parsedLine);
					data.add(parsedLine);
				}
			}
		}
		return data;
	}
}