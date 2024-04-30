package org.example;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		displayMenu();
	}

	private static void displayMenu() {
		System.out.println("Welcome to the Data Processor");

		System.out.println("Usage: java Main <file_path> [parser_type]");
		System.out.println("file_path: Path to the file to process.");
		System.out.println("parser_type: (Optional) Specifies a parser type (mixed, integer, decimal).");

		Scanner scanner = new Scanner(System.in);

		while (true) {
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
			}
		}
	}

	private static int getValidatedChoice(Scanner scanner) {
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
		System.out.println("Enter file path:");
		String filePath = scanner.nextLine();

		File file = new File(filePath);
		if (file == null) {
			System.out.println("No valid file provided.");
			return;
		}

		System.out.println("Choose a processing option:");
		System.out.println("1. Parse data with a parser type.");
		System.out.println("2. Apply processing strategies.");
		int choice = scanner.nextInt();
		scanner.nextLine();

		switch (choice) {
			case 1:
				System.out.println("Case 1");
//				handleParsing(scanner, file);
				break;
			case 2:
				System.out.println("Case 2");
//				handleStrategies(scanner, file);
				break;
		}
	}

	private static File validateAndRetrieveFile(String initialPath) {
		Scanner scanner = new Scanner(System.in);
		String filePath = initialPath;

		while (true) {
			File file = new File(filePath);

			if (!file.exists()) {
				System.out.println("Error: The file does not exist: " + filePath);
			} else if (!file.canRead()) {
				System.out.println("Error: The file cannot be read: " + filePath);
			} else {
				return file;
			}

			System.out.println("Do you want to enter a new file path? (y/n)");
			String choice = scanner.nextLine().toLowerCase();

			if (choice.equals("n")) {
				return null;
			} else {
				System.out.println("Enter new file path:");
				filePath = scanner.nextLine();
			}
		}
	}

	private static Parser setupParserChain(String parserType) {
		return null;
	}

	private static void displayProcessingOptions(List<List<Object>> data) {

	}

	private static void sortData(List<List<Object>> data, int colIndex) {

	}

	private static void searchData(List<List<Object>> data, String str) {

	}


}