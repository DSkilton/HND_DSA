package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		displayWelcome();
	}

	private static void displayWelcome() {
		System.out.println("Welcome to the Data Processor");
		System.out.println("Usage: java Main <file_path> [parser_type]");
		System.out.println("file_path: Path to the file to process.");
		System.out.println("parser_type: (Optional) Specifies a parser type (mixed, integer, decimal).");

		displayMenu();
	}

	private static void displayMenu() {
//		System.out.println("Entering displayMenu...");

		Scanner scanner = new Scanner(System.in);

		System.out.println("Choose an option:");
		System.out.println("1. Enter file path and parser type");
		System.out.println("2. Exit");

		int choice = getValidatedChoice(scanner);
		scanner.nextLine();  // clear line

		switch (choice) {
			case 1:
				handleFileProcessing(scanner);
				break;
			case 2:
				System.exit(0);
			default:
				System.out.println("Invalid choice. Please try again.");
				displayMenu();
		}
//		System.out.println("Exiting displayMenu...");
	}

	public static int getValidatedChoice(Scanner scanner) {
//		System.out.println("Entering getValidatedChoice...");

		while (true) {
			System.out.println("Enter a number:" );
			try {
				while (!scanner.hasNextInt()) {
					System.out.println("Invalid choice. Please try again.");
					scanner.nextLine();
				}
				int choice = scanner.nextInt();
				return choice;
			} catch (InputMismatchException e) {
				scanner.nextLine();  // Consume invalid input
				System.out.println("Invalid input. Please enter a number.");
			}
		}
	}

	private static void handleFileProcessing(Scanner scanner) {
//		System.out.println("Entering handleFileProcessing...");

		while (true) {
			File file = getValidFile(scanner);
			if (file == null) {
				System.out.println("No valid file provided.");
			}

			System.out.println("Choose a processing option:");
			System.out.println("1. Parse data with a parser type.");
			System.out.println("2. Apply processing strategies.");

			int choice = getValidatedChoice(scanner);
			scanner.nextLine(); // Clear newline

			switch (choice) {
				case 1:
					System.out.println("Case 1: Parsing data...");
					 handleParsing(scanner, file);
					break;
				case 2:
					System.out.println("Case 2: Applying strategies...");
//					 handleStrategies(scanner, file);
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
					continue; // Retry options
			}
			break; // Exit after valid processing
		}
	}

	public static File getValidFile(Scanner scanner) {
//		System.out.println("Entering getValidFile...");

		while (true) {
			System.out.println("Enter file path:");
			String filePath = scanner.nextLine();

			try {
				File file = new File(filePath);
				if (file.exists() && file.canRead() && !file.isDirectory()) {
					System.out.println("Exiting getValidFile...");
					return file;
				} else {
					System.out.println("Invalid file path or directory: " + filePath);
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}

			System.out.println("Do you want to enter a new file path? (y/n)");
			String choice = scanner.nextLine().toLowerCase();

			if (choice.equals("n")) {
				return null;
			}
		}
	}

	public static void handleParsing(Scanner scanner, File file) {
//		System.out.println("Entering handleParsing...");

		System.out.println("Enter parser type (mixed, integer, decimal):");
		String parserType = scanner.nextLine().toLowerCase();

		Parser parser = setupParserChain(parserType);

		try {
			List<List<Object>> data = parser.parseFile(file);
			System.out.println("Parsed data: " + data);
			displayProcessingOptions(scanner, data, parser);
		} catch (IOException e) {
			System.out.println("Failed to process the file: " + e.getMessage());
		}

		System.out.println("Exiting handleParsing...");
	}

	private static void displayProcessingOptions(Scanner scanner, List<List<Object>> data, Parser parser) {
//		System.out.println("Entering displayProcessingOptions...");

		while (true) {
			System.out.println("Choose an operation:");
			System.out.println("1. Sort by specific column.");
			System.out.println("2. Search for a specific value.");
			System.out.println("3. Display headers.");
			System.out.println("4. Exit.");

			int choice = getValidatedChoice(scanner);

			switch (choice) {
				case 1:
					System.out.println("Enter column index to sort by:");
					displayHeaders(AbstractDataParser.headers);
					int colIndex = scanner.nextInt();
					sortData(data, colIndex);
					System.out.println("Sorted data: " + data);
					break;
				case 2:
					System.out.println("Enter value to search for:");
					String searchValue = scanner.next();
					int index = searchData(data, searchValue);
					System.out.println("Found at row: " + index);
					break;
				case 3:
					System.out.println("Display Headers Switch");
					displayHeaders(parser);
					break;
				case 4:
					return; //if we return, that will close our program
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
//		System.out.println("Exiting displayProcessingOptions...");
	}

	public static Parser setupParserChain(String parserType) {
		IntegerParser integerParser = new IntegerParser();
		DecimalParser decimalParser = new DecimalParser();
		StringParser stringParser = new StringParser();

		integerParser.setNext(decimalParser);
		decimalParser.setNext(stringParser);

		if (parserType.equals("integer")) {
			return integerParser;
		} else if (parserType.equals("decimal")) {
			return decimalParser;
		}

		MixedDataParser mixedParser = new MixedDataParser();
		mixedParser.setFirstParser(integerParser);
		System.out.println("returning parser from chain");
		return mixedParser;
	}

	public static void displayHeaders(Parser parser) {
		System.out.println("Display Headers -> ParserType: " + parser.getClass());

		System.out.println("Headers: ");
		List<String> headers = AbstractDataParser.headers;
		System.out.println("Headers: " + headers);

		if (headers != null) {
			displayHeaders(headers);
		} else {
			System.out.println("No headers available.");
		}
	}

	private static void displayHeaders(List<String> headers) {
		for (int i = 0; i < headers.size(); i++) {
			System.out.println(i + ": " + headers.get(i));
		}
	}

	public static void sortData(List<List<Object>> data, int colIndex) {
		data.sort(Comparator.comparing(a -> a.get(colIndex).toString()));
	}

	public static int searchData(List<List<Object>> data, String value) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).contains(value)) return i;
		}
		return -1;
	}
}