/*
 * @author: Chris, Jack, Kevin
 */
package co.grandcircus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class MainApp {

	public static void main(String[] args) {

		Scanner scnr = new Scanner(System.in);
		// create new files and folders. If they already exist, nothing happens
		FileIOHelper.createDir();
		FileIOHelper.createOurFiles("Members.txt");
		FileIOHelper.createOurFiles("Clubs.txt");
		FileIOHelper.createOurFiles("Time.txt");

		List<Club> clubsList = new LinkedList<>();
		Map<String, Members> membersMap = new TreeMap<>();
		// These methods read from the text files
		Calendar calendar = FileIOHelper.readFromFileTime();
		FileIOHelper.readFromFileList(clubsList);
		FileIOHelper.readFromFileMap(membersMap, clubsList);
		// Since we don't need time and timezone, we converted to a simpler date/time
		// format
		Date d = calendar.getTime();
		SimpleDateFormat format1 = new SimpleDateFormat("E MMM dd, yyyy");
		String date1 = format1.format(d);
		System.out.println(date1);
		System.out.println();

		System.out.println("Welcome to a newer and fitter version of you!\n");
		int action = 0;
		String userId = "";
		int clubChoice = 0;
		// Main Menu -- this will continue to loop until the user chooses to quit
		do {
			String prompt = "Please choose from the following options:\n\n1. Check in\n2. Sign up for membership"
					+ "\n3. Get bill/check points (multi-club membership)\n4. Search member database\n5. Cancel membership\n6. Add club\n7. Quit \n";
			action = Validator.getInt(scnr, prompt, 1, 7);

			switch (action) {
			case 1: // checks in user
				int counter = 1;
				System.out.println("");
				for (Club c : clubsList) {
					System.out.printf("%d. %s\n", counter++, c.getName());
				}
				System.out.println("");

				clubChoice = Validator.getInt(scnr, "Enter the branch number: ", 1, clubsList.size());
				userId = Validator.getStringMatchingRegex(scnr, "Please enter your member ID: ", "[A-Z][\\d]{3}");

				if (membersMap.get(userId) instanceof Single) {
					membersMap.get(userId).checkIn(clubsList.get(clubChoice - 1));
				} else if (membersMap.get(userId) instanceof Multi) {
					membersMap.get(userId).checkIn(clubsList.get(clubChoice - 1));
				} else {
					System.out.println("\nPlease see the welcome desk.\n");
				}
				break;
			case 2: // adds new member
				addNewMember(clubsList, membersMap, scnr);
				break;
			case 3: // bills the member
				String billingId = Validator.getStringMatchingRegex(scnr, "Please enter your member ID: ",
						"[A-Z][\\d]{3}");
				String holidayMessage = "";
				if (membersMap.containsKey(billingId)) {
					if (membersMap.get(billingId) instanceof Single) {
						double bill = membersMap.get(billingId).getMonthlyFee();
						Calendar Dec = Calendar.getInstance();
						Dec.set(2019, 11, 1, 16, 04, 05);
						Calendar Jan = Calendar.getInstance();
						Jan.set(2020, 0, 1, 16, 04, 05);
						if (calendar.get(Calendar.MONTH) == Dec.get(Calendar.MONTH)) {
							bill = bill * 0.66;
							holidayMessage = "Merry Christmas!";
						} else if (calendar.get(Calendar.MONTH) == Jan.get(Calendar.MONTH)) {
							bill = bill * 1.33;
							holidayMessage = "Happy New Year!";
						}
						System.out.printf("\nYour bill this month is: $%.2f. %s\n\n", bill, holidayMessage);
					} else if (membersMap.get(billingId) instanceof Multi) {
						double bill = membersMap.get(billingId).getMonthlyFee();
						int points = ((Multi) membersMap.get(billingId)).getPoints();
						Calendar Dec = Calendar.getInstance();
						Dec.set(2019, 11, 1, 16, 04, 05);
						Calendar Jan = Calendar.getInstance();
						Jan.set(2020, 0, 1, 16, 04, 05);
						if (calendar.get(Calendar.MONTH) == Dec.get(Calendar.MONTH)) {
							bill = bill * 0.66;
							holidayMessage = "Merry Christmas!";
						} else if (calendar.get(Calendar.MONTH) == Jan.get(Calendar.MONTH)) {
							bill = bill * 1.33;
							holidayMessage = "Happy New Year!";
						}
						System.out.printf("\nYour bill this month is: $%.2f. %s\n", bill, holidayMessage);
						if (points == 1) {
							System.out.println("You have " + points + " point\n");
						} else
							System.out.println("You have " + points + " points\n");
					}
				} else {
					System.out.println("\nYou are not currently in our system. Please see the welcome desk!\n");
				}
				break;
			case 4: // searches member database
				String userPrompt = "Welcome to the member database!\n\nWould you like to search by:\n\n"
						+ "1. ID\n2. Name\n3. Club\n4. Return to main menu\n\nPlease enter a number: ";
				int searchAction = Validator.getInt(scnr, userPrompt, 1, 4);
				switch (searchAction) {
				case 1: // searches by ID
					userId = Validator.getStringMatchingRegex(scnr, "Please enter your member ID: ", "[A-Z][\\d]{3}");
					if (membersMap.containsKey(userId)) {
						if (membersMap.get(userId) instanceof Single) {
							String[] memberInfo = membersMap.get(userId).toString().split(",");
							System.out.printf("\n%s %s\n%s %s\n%s %s\n%s $%s\n%s %s\n\n", "Member ID:", memberInfo[0],
									"Member Name:", memberInfo[1], "Checked In Status:", memberInfo[2], "Monthly Fee:",
									memberInfo[3], "Club Info:", memberInfo[4] + " " + memberInfo[5]);
						} else if (membersMap.get(userId) instanceof Multi) {
							String[] memberInfo = membersMap.get(userId).toString().split(",");
							List<String> clubNames = new ArrayList<>();
							for (Club c : ((Multi) membersMap.get(userId)).getClubs()) {
								clubNames.add(c.getName());
							}
							System.out.printf("\n%s %s\n%s %s\n%s %s\n%s $%s\n", "Member ID:", memberInfo[0],
									"Member Name:", memberInfo[1], "Checked In Status:", memberInfo[2], "Monthly Fee:",
									memberInfo[3]);
							System.out.print("Clubs: ");
							for (int i = 0; i < clubNames.size(); ++i) {
								if (i < clubNames.size() - 1) {
									System.out.print(clubNames.get(i) + ", ");
								} else {
									System.out.print(clubNames.get(i));
								}
							}
							System.out.println("\n");
						}

					} else {
						System.out.println("\nSorry, you are not in our system!\n");
					}
					break;
				case 2: // searches by name
					String userName = Validator.getString(scnr, "Please enter your name: ");
					ArrayList<Members> foundMembers = new ArrayList<>();
					for (Members m : membersMap.values()) {
						if (m.getName().equals(userName)) {
							foundMembers.add(m);
						}
					}
					if (foundMembers.size() == 0) {
						System.out.println("\nSorry, you are not in our system!\n");
					} else {
						for (Members m : foundMembers) {
							if (m instanceof Single) {
								String[] memberInfo = m.toString().split(",");
								System.out.printf("\n%s %s\n%s %s\n%s %s\n%s $%s\n%s %s\n\n", "Member ID:",
										memberInfo[0], "Member Name:", memberInfo[1], "Checked In Status:",
										memberInfo[2], "Monthly Fee:", memberInfo[3], "Club Info:",
										memberInfo[4] + " " + memberInfo[5]);
							} else if (m instanceof Multi) {
								String[] memberInfo = m.toString().split(",");
								List<String> clubNames = new ArrayList<>();
								for (Club c : ((Multi) m).getClubs()) {
									clubNames.add(c.getName());
								}
								System.out.printf("\n%s %s\n%s %s\n%s %s\n%s $%s\n", "Member ID:", memberInfo[0],
										"Member Name:", memberInfo[1], "Checked In Status:", memberInfo[2],
										"Monthly Fee:", memberInfo[3]);
								System.out.print("Clubs: ");
								for (int i = 0; i < clubNames.size(); ++i) {
									if (i < clubNames.size() - 1) {
										System.out.print(clubNames.get(i) + ", ");
									} else {
										System.out.print(clubNames.get(i));
									}
								}
								System.out.println("\n");
							}
						}
					}
					break;
				case 3: // searches by club
					int clubCounter = 1;
					System.out.println();
					for (Club c : clubsList) {
						System.out.printf("%d. %s\n", clubCounter++, c.getName());
					}
					System.out.println();
					int userClubChoice = Validator.getInt(scnr, "Please choose a club: ", 1, clubsList.size());
					System.out.println();
					if (clubsList.get(userClubChoice - 1).getMembers().isEmpty()) {
						System.out.println("There are currently no members attending this club");
					} else {
						for (Members m : clubsList.get(userClubChoice - 1).getMembers()) {
							System.out.printf("%s %s, %s %s\n", "Member ID:", m.getId(), "Member Name:", m.getName());
						}
					}
					System.out.println();
					break;
				case 4: // quits and returns to the main menu
					break;
				}
				break;
			case 5: // cancels subscription and removes user from membersMap and clubsList
				removeUser(scnr, userId, membersMap, clubsList);
				break;
			case 6: // adds new club and adds multi-members to the new club's ArrayList
				String name = Validator.getString(scnr, "Enter club name: ");
				String address = Validator.getString(scnr, "Enter club address: ");
				Club c = new Club(name, address);
				clubsList.add(c);
				for (Members m : membersMap.values()) {
					if (m instanceof Multi) {
						c.getMembers().add(m);
					}
				}
				System.out.println();
				break;
			case 7: // quits the program and resets everyone's checked-in status to false
				checkOutAll(membersMap);
				break;
			}

		} while (action != 7);
		// increments the calendar month by one
		updateTime(calendar);
		// writes everything to the text files, updates new information
		FileIOHelper.writeToFileMap(membersMap);
		FileIOHelper.writeToFileList(clubsList);
		FileIOHelper.writeToFileTime(calendar);

		System.out.println("Goodbye!");
	}

	// creates a unique ID for the member signing up
	public static String generateID(String name) {
		Random rndm = new Random();
		String newID = "";
		try {
			newID = name.substring(0, 1).toUpperCase() + (rndm.nextInt(900) + 100);
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Invalid input\n");
		}
		return newID;
	}

	// adds new member and depending on their selection, creates new single or multi
	// members
	public static void addNewMember(List<Club> clubsList, Map<String, Members> membersMap, Scanner scnr) {
		System.out.println(
				"These are your options: \n\nSingle membership: $15.05 per month (one club)\nMulti-club membership: $99.99 per month (full access)\n");
		String membershipChoice = Validator.getStringMatchingRegex(scnr,
				"Are you interested in our single or multi-club membership? (choose S / M) ",
				"([SMsm]{1})|(([SMsm]{1})([ulti]{4}|[ingle]{5}))").toLowerCase();
		if (membershipChoice.startsWith("s")) {
			String userName = "";
			String ID = "";
			do {
				userName = Validator.getString(scnr, "What is your name? ");
				ID = generateID(userName);
			} while (userName.isEmpty());
			Members m = new Single(ID, userName);
			membersMap.put(m.getId(), m);
			int clubCounter = 1;
			System.out.println("");
			for (Club c : clubsList) {
				System.out.printf("%d. %s\n", clubCounter++, c.getName());
			}
			System.out.println("");
			int userChoice = Validator.getInt(scnr, "Select which club you would like to join. (Choose a number) ", 1,
					clubsList.size());
			System.out.println("\nYou selected: " + clubsList.get(userChoice - 1));
			clubsList.get(userChoice - 1).getMembers().add(m);
			((Single) m).setClub(clubsList.get(userChoice - 1));
			System.out.println("This is your new Club ID: " + ID + ". Please remember it. ");
		} else if (membershipChoice.startsWith("m")) {
			String userName = Validator.getString(scnr, "What is your name? ");
			String ID = generateID(userName);
			Members m = new Multi(ID, userName, 0);
			membersMap.put(m.getId(), m);
			int clubCounter = 1;
			System.out.println("\nYou have joined the following clubs:\n");
			for (Club c : clubsList) {
				System.out.printf("%d. %s\n", clubCounter++, c.getName());
			}
			System.out.println("");
			for (int i = 0; i < clubsList.size(); i++) {
				clubsList.get(i).getMembers().add(m);
			}
			((Multi) m).setClubs(clubsList);
			System.out.println("This is your new member ID: " + ID + ". Please remember it. ");
		}
		System.out.println("");
	}

	// allows user to cancel subscription, and removes them from membersMap and
	// club's member ArrayList
	public static void removeUser(Scanner scnr, String userId, Map<String, Members> membersMap, List<Club> clubsList) {

		userId = Validator.getStringMatchingRegex(scnr, "Please enter your member ID: ", "[A-Z][\\d]{3}");

		if (membersMap.containsKey(userId)) {
			try {
				System.out.println("\nWe're sorry to see you go.\n");
				for (int i = 0; i < clubsList.size(); i++) {
					if (clubsList.get(i).getMembers().contains(membersMap.get(userId))) {
						clubsList.get(i).getMembers().remove(membersMap.get(userId));
					}
				}
				membersMap.remove(userId);
			} catch (Exception e) {
				System.out.println("Something went terribly wrong. Our bad.");
			}
		} else {
			System.out.println("You are not currently in our system. Please see the welcome desk.");
		}
	}

	// checks out everyone at the end of the day
	public static void checkOutAll(Map<String, Members> membersMap) {
		for (Members m : membersMap.values()) {
			m.setCheckedIn(false);
		}
	}

	// increments the calendar month by one
	public static void updateTime(Calendar calendar) {
		calendar.add(Calendar.MONTH, 1);
	}
}
