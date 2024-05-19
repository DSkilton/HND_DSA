import com.duncancodes.ProcessingOptionsHandler;
import com.duncancodes.parser.Parser;
import com.duncancodes.parser.ParserHandler;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

	@Test
	public void getValidChoiceTest() {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("1".getBytes());

		Scanner scanner = new Scanner(byteArrayInputStream);

		assertEquals(ProcessingOptionsHandler.getValidatedChoice(scanner), 1);
	}

	@Test
	public void testParsingChain() throws IOException {
		ParserHandler parserHandler = new ParserHandler();
		// Create a temporary test file
		Path tempFile = Files.createTempFile("testFile", ".csv");

		try (BufferedWriter writer = Files.newBufferedWriter(tempFile)) {
			writer.write("Name,Age,Salary\n");
			writer.write("Alice,30,50000\n");
			writer.write("Bob,25,42000\n");
			writer.write("Charlie,35,55000\n");
			writer.write("David,28,48000\n");
			// Add more rows as needed
		}

		// Test parsing chain
		Parser parser = parserHandler.setupParserChain("mixed");
		List<List<Object>> data = parser.parseFile(tempFile.toFile());

		// Assertions to verify correctness
		assertNotNull(data, "Data should not be null");
		assertFalse(data.isEmpty(), "Data should not be empty");

		List<Object> firstRow = data.get(0);

		assertEquals("[Alice]", firstRow.get(0).toString(), "First entry should be Alice");
		assertEquals(30.0, firstRow.get(1), "Second entry should be 30");

		// Clean up afterward
		Files.delete(tempFile);
	}
}
