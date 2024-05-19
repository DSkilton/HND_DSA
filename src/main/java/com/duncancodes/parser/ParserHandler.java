package com.duncancodes.parser;

import com.duncancodes.parser.numeric.DecimalParser;
import com.duncancodes.parser.numeric.IntegerParser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ParserHandler  {
	private Parser parser;

	public List<List<Object>> handleParsing(File file, String parserType) throws IOException {
		this.parser = setupParserChain(parserType);
		return parser.parseFile(file);
	}

	public Parser getParser() {
		return parser;
	}

	public Parser setupParserChain(String parserType) {
		Parser integerParser = new IntegerParser();
		Parser decimalParser = new DecimalParser();
		Parser stringParser = new StringParser();

		integerParser.setNext(decimalParser);
		decimalParser.setNext(stringParser);

		if (parserType.equals("integer")) {
			return integerParser;
		} else if (parserType.equals("decimal")) {
			return decimalParser;
		}

		MixedDataParser mixedParser = new MixedDataParser();
		mixedParser.setFirstParser(integerParser);
		return mixedParser;
	}
}
