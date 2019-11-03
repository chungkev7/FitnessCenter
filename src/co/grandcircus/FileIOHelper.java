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
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class FileIOHelper {

	public static void readFromFileMap(Map<String, Members> membersMap, List<Club> clubsList) {
		String fileName = "Members.txt";
		Path path = Paths.get("FitFolder", fileName);

		File file = path.toFile();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = br.readLine();
			while (line != null) {
				String[] arr = line.split(",");
				String first = arr[5].substring(0,1);
				if (!first.equalsIgnoreCase("[")) {
					Members s = new Single(arr[0], arr[1]);
					membersMap.put(arr[0], s);
					((Single) s).setClub(new Club(arr[4], arr[5]));
				} else {
					Members m = new Multi(arr[0], arr[1], Integer.parseInt(arr[4]));
					membersMap.put(arr[0], m);
				}
				line = br.readLine();
			}
			for (Members m : membersMap.values()) {
				if (m instanceof Single) {
					Club c = ((Single) m).getClub();
					for (Club club : clubsList) {
						if (club.getName().equalsIgnoreCase(c.getName())) {
							club.getMembers().add(m);
						}
					}
				} else if (m instanceof Multi) {
					for(Club c : clubsList){
						c.getMembers().add(m);
					}
					((Multi) m).setClubs(clubsList);
				}

			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong with the file.");
		} catch (IOException e) {
			System.out.println("Something went wrong when we tried to read from the file.");
		}
	}

	public static void readFromFileList(List<Club> clubsList) {
		String fileName = "Clubs.txt";
		Path path = Paths.get("FitFolder", fileName);

		File file = path.toFile();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = br.readLine();
			while (line != null) {
				String[] clubInfo = line.split(",");
				clubsList.add(new Club(clubInfo[0], clubInfo[1]));
				line = br.readLine();
			}

			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong with the file.");
		} catch (IOException e) {
			System.out.println("Something went wrong when we tried to read from the file.");
		}
	}

	public static Calendar readFromFileTime() {
		String fileName = "Time.txt";
		Path path = Paths.get("FitFolder", fileName);

		File file = path.toFile();
		GregorianCalendar gc = new GregorianCalendar();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = br.readLine();
			while (line != null) {
				String input = line;
				DateTimeFormatter f = DateTimeFormatter.ofPattern( "E MMM d HH:mm:ss z uuuu" );
				ZonedDateTime zdt = ZonedDateTime.parse( input , f );
				gc = GregorianCalendar.from( zdt );
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong with the file.");
		} catch (IOException e) {
			System.out.println("Something went wrong when we tried to read from the file.");
		}
		return gc;
	}

	public static void writeToFileMap(Map<String, Members> m) {
		String fileName = "Members.txt";
		Path path = Paths.get("Fitfolder", fileName);

		File file = path.toFile();
		PrintWriter output = null;
		try {
			output = new PrintWriter(new FileOutputStream(file, false));
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
			output = new PrintWriter(new FileOutputStream(file, false)); // fix me maybe???
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
			output.println(c.getTime());
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
