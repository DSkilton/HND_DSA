package com.duncancodes;

import com.duncancodes.algorithms.registry.SearchAlgorithmRegistry;
import com.duncancodes.algorithms.registry.SortAlgorithmRegistry;
import com.duncancodes.algorithms.search.SearchAlgorithm;
import com.duncancodes.algorithms.sort.SortAlgorithm;
import com.duncancodes.algorithms.util.PerformanceMetrics;
import com.duncancodes.parser.AbstractDataParser;
import com.duncancodes.parser.Parser;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProcessingOptionsHandler {
	public static void displayProcessingOptions(Scanner scanner, List<List<Object>> data, Parser parser) {
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
					displayHeaders(AbstractDataParser.getHeaders());
					int colIndex = scanner.nextInt();
					System.out.println("Choose sorting algorithm (bubble, insertion, quick):");
					String sortAlgorithm = scanner.next();
					sortData(data, colIndex, sortAlgorithm);
					System.out.println("Sorted data: " + data);
					break;
				case 2:
					System.out.println("Enter value to search for:");
					String searchValue = scanner.next();
					System.out.println("Choose searching algorithm (linear, binary):");
					String searchAlgorithm = scanner.next();
					int index = searchData(data, searchValue, searchAlgorithm);
					System.out.println("Found at row: " + index);
					break;
				case 3:
					displayHeaders(parser);
					break;
				case 4:
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	public static int getValidatedChoice(Scanner scanner) {
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

	private static void displayHeaders(Parser parser) {
		System.out.println("Display Headers -> ParserType: " + parser.getClass());

		System.out.println("Headers: ");
		List<String> headers = AbstractDataParser.getHeaders();
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

	private static void sortData(List<List<Object>> data, int colIndex, String sortAlgorithm) {
		List<String> columnData = extractColumnData(data, colIndex);
		SortAlgorithm<String> algorithm = (SortAlgorithm<String>) SortAlgorithmRegistry.getAlgorithm(sortAlgorithm.toLowerCase());
		PerformanceMetrics.measureExecution(() -> algorithm.sort(columnData));
		updateColumnData(data, columnData, colIndex);
	}

	private static int searchData(List<List<Object>> data, String value, String searchAlgorithm) {
		List<String> columnData = extractColumnData(data, 0); // Assuming search is done on the first column
		int index = -1;
		try {
			SearchAlgorithm<String> algorithm = (SearchAlgorithm<String>) SearchAlgorithmRegistry.getAlgorithm(searchAlgorithm.toLowerCase());
			if (searchAlgorithm.equalsIgnoreCase("binary")) {
				SortAlgorithm<String> quickSort = (SortAlgorithm<String>) SortAlgorithmRegistry.getAlgorithm("quick");
				quickSort.sort(columnData); // Ensure data is sorted for binary search
			}
			index = PerformanceMetrics.measureExecution(() -> algorithm.search(columnData, value));
		} catch (Exception e) {
			System.out.println("Error during search: " + e.getMessage());
		}
		return index;
	}

	private static List<String> extractColumnData(List<List<Object>> data, int colIndex) {
		// Extract column data from the 2D list
		List<String> columnData = new ArrayList<>();
		for (List<Object> row : data) {
			columnData.add(row.get(colIndex).toString());
		}
		return columnData;
	}

	private static void updateColumnData(List<List<Object>> data, List<String> columnData, int colIndex) {
		// Update the original data with sorted column data
		for (int i = 0; i < data.size(); i++) {
			data.get(i).set(colIndex, columnData.get(i));
		}
	}
}
