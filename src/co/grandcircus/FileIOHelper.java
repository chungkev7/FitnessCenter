package co.grandcircus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class FileIOHelper {

	public static void readFromFileMap(Map<String, Members> m) {
		String fileName = "Members.txt";
		Path path = Paths.get("FitFolder", fileName);

		File file = path.toFile();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = br.readLine();
			while (line != null) {
				String[] arr = line.split(",");
				m.put(arr[0], new Single(arr[0], arr[1]));
				line = br.readLine();
			}

			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong with the file.");
		} catch (IOException e) {
			System.out.println("Something went wrong when we tried to read from the file.");
		}
	}

	public static void readFromFileList() {
		String fileName = "Clubs.txt";
		Path path = Paths.get("resources", fileName);

		File file = path.toFile();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = br.readLine();
			while (line != null) {
				System.out.println(line);
				line = br.readLine();
			}

			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong with the file.");
		} catch (IOException e) {
			System.out.println("Something went wrong when we tried to read from the file.");
		}
	}

	public static void readFromFileTime() {
		String fileName = "Time.txt";
		Path path = Paths.get("resources", fileName);

		File file = path.toFile();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = br.readLine();
			while (line != null) {
				System.out.println(line);
				line = br.readLine();
			}

			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong with the file.");
		} catch (IOException e) {
			System.out.println("Something went wrong when we tried to read from the file.");
		}
	}

	public static void writeToFileMap(Map<String, Members> m) {
		String fileName = "Members.txt";
		Path path = Paths.get("Fitfolder", fileName);

		File file = path.toFile();
		PrintWriter output = null;
		try {
			output = new PrintWriter(new FileOutputStream(file, true));
			for (Members member : m.values()) {

				output.println(member);

			}
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Hey, contact customer service!");
		} finally {
			output.close();
		}

	}

	public static void writeToFileList(List<Club> c) {
		String fileName = "Clubs.txt";
		Path path = Paths.get("Fitfolder", fileName);

		File file = path.toFile();
		PrintWriter output = null;
		try {
			output = new PrintWriter(new FileOutputStream(file, false)); // fix me!
			for (Club clubs : c) {
				output.println(clubs);
			}
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Hey, contact customer service!");
		} finally {
			output.close();
		}

	}

	public static void writeToFileTime(Calendar c) {
		String fileName = "Time.txt";
		Path path = Paths.get("Fitfolder", fileName);

		File file = path.toFile();
		PrintWriter output = null;
		try {
			output = new PrintWriter(new FileOutputStream(file, false));
			output.println(c);
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Hey, contact customer service!");
		} finally {
			output.close();
		}

	}

	public static void createOurFiles(String fileName) {
		Path path = Paths.get("FitFolder", fileName);

		if (Files.notExists(path)) {
			try {
				Files.createFile(path);
//				System.out.println("The file was created successfully.");
			} catch (IOException e) {
				System.out.println("Something went terribly wrong!");
			}
		} // else {
//			System.out.println("The file already exists!");
//		}
	}

	public static void createDir() {
		String dirPath = "FitFolder";

		Path folder = Paths.get(dirPath);

		if (Files.notExists(folder)) {
			try {

				Files.createDirectories(folder);
//				System.out.println("The file was created successfully!");
			} catch (IOException e) {
				System.out.println("Something went wrong with the folder creation.");
			}

		} // else {
//			System.out.println("The folder already exists!");
//		}
	}

}