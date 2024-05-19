package com.duncancodes.filehandling;

import java.io.File;
import java.util.Scanner;

public class FileHandler {

	public File getValidFile(Scanner scanner) {
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
}
