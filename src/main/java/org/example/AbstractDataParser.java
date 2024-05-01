package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDataParser implements Parser {
	public abstract List<Object> parseLine(String line);

	protected Parser next;
	//This method is very important for the Chain of Responsibility pattern

	public void setNext(Parser next) {
		this.next = next;
	}

	@Override
	public List<List<Object>> parseFile(File file) throws IOException {
		System.out.println("Entering Abstract Data Parser");
		List<List<Object>> data = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				data.add(parseLine(line));
			}
		}
		return data;
	}

}
