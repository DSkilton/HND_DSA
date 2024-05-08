package com.duncancodes.parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Parser {
	List<List<Object>> parseFile (File file) throws IOException;

	List<Object> parseLine(String line);
}
