package org.example;

import java.io.File;
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
		System.out.println("Entering displayMenu...");

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

		System.out.println("Exiting displayMenu...");
	}

	private static int getValidatedChoice(Scanner scanner) {
		System.out.println("Exiting getValidatedChoice...");

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
		System.out.println("Entering handleFileProcessing...");

		while (true) {
			System.out.println("Enter file path:");
			File file = getValidFile(scanner);
			if (file == null) {
				System.out.println("No valid file provided.");
			}

			System.out.println("Choose a processing option:");
			System.out.println("1. Parse data with a parser type.");
			System.out.println("2. Apply processing strategies.");
			if (!scanner.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number.");
				scanner.nextLine(); // Clear invalid input
				continue; // Restart the loop
			}

			int choice = scanner.nextInt();
			scanner.nextLine(); // Clear newline

			switch (choice) {
				case 1:
					System.out.println("Case 1: Parsing data...");
//					 handleParsing(scanner, file);
					break;
				case 2:
					System.out.println("Case 2: Applying strategies...");
					// handleStrategies(scanner, file);
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
					continue; // Retry options
			}
			break; // Exit after valid processing
		}
	}

	public static File getValidFile(Scanner scanner) {
		System.out.println("Entering getValidFile...");

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