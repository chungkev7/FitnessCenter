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
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class FileIOHelper {

	/*
	 * This method reads from Members.txt and depending on whether they are single
	 * or multi members, a new single or multi member will be created, put into the
	 * membersMap, and add the members to the clubs members ArrayList
	 * 
	 * If these actions couldn't be completed, this method would catch that
	 * exception and print out a warning statement
	 */
	public static void readFromFileMap(Map<String, Members> membersMap, List<Club> clubsList) {
		String fileName = "Members.txt";
		Path path = Paths.get("FitFolder", fileName);
		File file = path.toFile();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();

			while (line != null) {
				String[] arr = line.split(",");
				String first = arr[5].substring(0, 1);
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
					for (Club c : clubsList) {
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

	/*
	 * This method reads from Clubs.txt and adds them to the clubsList. If a problem
	 * occurs, it prints out a warning.
	 */
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

	/*
	 * This method reads from the Time.txt file. It converts a string to a Calendar
	 * object. Then it returns that Calendar object. If a problem occurs, it prints
	 * out a warning.
	 */
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
				DateTimeFormatter f = DateTimeFormatter.ofPattern("E MMM d HH:mm:ss z uuuu");
				ZonedDateTime zdt = ZonedDateTime.parse(input, f);
				gc = GregorianCalendar.from(zdt);
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

	/*
	 * This method writes to the Members.txt file. It iterates through the
	 * membersMap and prints out each member on a separate line. It prints out a
	 * warning if an error occurs.
	 */
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

	/*
	 * This method writes to the Clubs.txt file. it works exactly in the same way as
	 * the writeToFileMap method, only it's iterating through an ArrayList.
	 */
	public static void writeToFileList(List<Club> c) {
		String fileName = "Clubs.txt";
		Path path = Paths.get("Fitfolder", fileName);

		File file = path.toFile();
		PrintWriter output = null;
		try {
			output = new PrintWriter(new FileOutputStream(file, false));
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

	/*
	 * This method writes to the Time.txt file the time after being incremented by
	 * one month.
	 */
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

	/*
	 * This method is always called at the beginning of the program so that new
	 * users downloading our software will have this file automatically created.
	 */
	public static void createOurFiles(String fileName) {
		Path path = Paths.get("FitFolder", fileName);

		if (Files.notExists(path)) {
			try {
				Files.createFile(path);
			} catch (IOException e) {
				System.out.println("Something went terribly wrong!");
			}
		}
	}

	/*
	 * This method is always called at the beginning of the program so that new
	 * users downloading our software will have this folder automatically created.
	 */
	public static void createDir() {
		String dirPath = "FitFolder";
		Path folder = Paths.get(dirPath);

		if (Files.notExists(folder)) {
			try {
				Files.createDirectories(folder);
			} catch (IOException e) {
				System.out.println("Something went wrong with the folder creation.");
			}
		}
	}
}
