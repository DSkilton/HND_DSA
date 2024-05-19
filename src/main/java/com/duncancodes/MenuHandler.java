package com.duncancodes;

import com.duncancodes.filehandling.FileHandler;
import com.duncancodes.parser.ParserHandler;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuHandler {
	private final FileHandler fileHandler = new FileHandler();
	private final ParserHandler parserHandler = new ParserHandler();

	public void displayWelcome() {
		System.out.println("Welcome to the Data Processor");
		System.out.println("Usage: java Main <file_path> [parser_type]");
		System.out.println("file_path: Path to the file to process.");
		System.out.println("parser_type: (Optional) Specifies a parser type (mixed, integer, decimal).");

		displayMenu();
	}

	private void displayMenu() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Choose an option:");
		System.out.println("1. Enter file path and parser type");
		System.out.println("2. Exit");

		int choice = getValidatedChoice(scanner);

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
	}

	private int getValidatedChoice(Scanner scanner) {
		while (true) {
			System.out.println("Enter a number:");
			try {
				while (!scanner.hasNextInt()) {
					System.out.println("Invalid choice. Please try again.");
					scanner.nextLine();
				}
				return scanner.nextInt();
			} catch (InputMismatchException e) {
				scanner.nextLine();  // Consume invalid input
				System.out.println("Invalid input. Please enter a number.");
			}
		}
	}

	private void handleFileProcessing(Scanner scanner) {
		File file = fileHandler.getValidFile(scanner);
		if (file == null) {
			System.out.println("No valid file provided.");
			return;
		}

		System.out.println("Enter parser type (mixed, integer, decimal):");
		String parserType = scanner.next().toLowerCase();

		try {
			List<List<Object>> data = parserHandler.handleParsing(file, parserType);
			ProcessingOptionsHandler.displayProcessingOptions(scanner, data, parserHandler.getParser());
		} catch (IOException e) {
			System.out.println("Failed to process the file: " + e.getMessage());
		}
	}
}
