package com.duncancodes.parser;

import com.duncancodes.parser.Parser;

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
		System.out.println("MixedDataParser: Parse File");
		return firstParser.parseFile(file);
	}

	@Override
	public List<Object> parseLine(String line) {
		System.out.println("MixedDataParser: ParseLine");
		return firstParser.parseLine(line);
	}
}
