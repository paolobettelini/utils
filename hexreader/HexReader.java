package hexreader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HexReader {

	static int WIDTH = 0x10;
	static char SPECIAL = '_';
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Invalid argument(s)");
			return;
		}

		if (args.length > 1) {
			try {
				WIDTH = Math.max(1, Integer.parseInt(args[1]));
			} catch (NumberFormatException e) {
				System.out.println("Invalid width amount");
				return;
			}
		}

		if (args.length > 2) {
			if (args[2].length() != 1) {
				System.out.println("Invalid non-printable char replacer");
				return;
			};
			SPECIAL = args[2].toCharArray()[0];
		}
		
		byte[] bytes = {};
		Path file = Paths.get(args[0]);
		try {
			bytes = Files.readAllBytes(file);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("An error has occured whilst reading the file.");
			return;
		}

		System.out.println("\n-\tNon-printable character: '" + SPECIAL + "'");
		System.out.println("-\tWidth: " + WIDTH);

		System.out.print("\n       ");
		for (int i = 0; i < WIDTH; i++) {
			System.out.printf("%02X ", i);
		}
		System.out.print("\n       ");

		for (int i = 0; i < WIDTH; i++) {
			System.out.print("---");
		}

		System.out.println();
		StringBuilder builder = new StringBuilder();
	
		for (int i = 0; i < bytes.length; i++) {
			if (i % WIDTH == 0) {
				if (i > 0) {
					System.out.print("| " + builder.toString());
				}

				System.out.printf((i == 0 ? "" : "\n") + "%04X | ", i);
				builder = new StringBuilder();
			}
			
			if (bytes[i] != 0x0a && bytes[i] != 0x0d) {
				builder.append(bytes[i] < 0x20 || bytes[i] > 0x7F ? SPECIAL : (char) bytes[i]);
			}
			
			System.out.printf("%02X ", bytes[i]);
		}
		
		System.out.println(" ".repeat(WIDTH * 3 - builder.length() * 3) + "| " + builder.toString() + "\n");
	}

}