/*
 * @author: Chris, Jack, Kevin
 */
package co.grandcircus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class MainApp {

	public static void main(String[] args) {

		Scanner scnr = new Scanner(System.in);

		List<Club> clubsList = new ArrayList<>();
		Map<String, Members> membersMap = new TreeMap<>();
		clubsList.add(new Club("Planet Fitness", "1234 Woodward Ave"));
		clubsList.add(new Club("Golds Gym", "5678 Main St"));
		clubsList.add(new Club("The Jacked Ones", "532 Mount Branch"));
		clubsList.add(new Club("Lunk-free Zone", "333 Fourth St"));

		System.out.println("Welcome to a new and fitter version of you!\n");
		int action = 0;
		String userId = "";
		int clubChoice = 0;

		do {
			String prompt = "Please choose from the following options:\n\n1. Check in\n2. Sign up for membership"
					+ "\n3. Get bill\n4. Search Member Database\n5. Cancel membership\n6. Add Club\n7. Quit \n";
			action = Validator.getInt(scnr, prompt, 1, 7);

			switch (action) {
			case 1:
				int counter = 1;
				System.out.println("");
				for (Club c : clubsList) {
					System.out.printf("%d. %s\n", counter++, c.getName());
				}
				System.out.println("");

				clubChoice = Validator.getInt(scnr, "Enter the branch number: ", 1, clubsList.size());
				userId = Validator.getString(scnr, "Please enter your member ID: ");

				if (membersMap.get(userId) instanceof Single) {
					membersMap.get(userId).checkIn(clubsList.get(clubChoice - 1));
					if (((Single) membersMap.get(userId)).getClub().equals(clubsList.get(clubChoice - 1))) {
						System.out.println();
						System.out.println("Welcome " + membersMap.get(userId).getName());
						System.out.println();
					} else {
						System.out.println();
						System.out.println("\nSorry, you're not in our system.");
						System.out.println();
					}
				} else if (membersMap.get(userId) instanceof Multi) {
					membersMap.get(userId).checkIn(clubsList.get(clubChoice - 1));
					if (((Multi) membersMap.get(userId)).getClubs().contains(clubsList.get(clubChoice - 1))) {
						System.out.println();
						System.out.println("Welcome " + membersMap.get(userId).getName());
						System.out.println();
					} else {
						System.out.println();
						System.out.println("\nSorry, you're not in our system.");
						System.out.println();
					}
				} else {
					System.out.println("\nPlease see the Welcome Desk.");
				}
				break;
			case 2:
				addNewMember(clubsList, membersMap, scnr);
				break;
			case 3:
				String billingId = Validator.getString(scnr, "Please enter your Member ID: ");
				if (membersMap.get(billingId) instanceof Single) {
					double bill = membersMap.get(billingId).getMonthlyFee();
					System.out.println("\nYour bill this month is: $" + bill + "\n");
				} else if (membersMap.get(billingId) instanceof Multi) {
					double bill = membersMap.get(billingId).getMonthlyFee();
					int points = ((Multi) membersMap.get(billingId)).getPoints();
					System.out.println("\nYour bill this month is: $" + bill);
					if (points == 1) {
						System.out.println("You have " + points + " point\n");
					} else 
						System.out.println("You have " + points + " points\n");
				}
				break;
			case 4:
				String userPrompt = "Welcome to the Member Database!\n\nWould you like to search by:\n\n"
						+ "1. ID\n2. Name\n3. Club\n4. Return to main menu\n\nPlease enter a number: ";
				int searchAction = Validator.getInt(scnr, userPrompt, 1, 4);
				switch (searchAction) {
				case 1:
					userId = Validator.getString(scnr, "Please enter your member ID: ");
					if (membersMap.containsKey(userId)) {
						if (membersMap.get(userId) instanceof Single) {
							String[] memberInfo = membersMap.get(userId).toString().split(",");
							System.out.printf("\n%s %s\n%s %s\n%s %s\n%s $%s\n%s %s\n\n", "Member ID:", memberInfo[0], "Member Name:", memberInfo[1]
									, "Checked In Status:", memberInfo[2], "Monthly Fee:", memberInfo[3], "Club info:", memberInfo[4] + " " + memberInfo[5]);
						} else if (membersMap.get(userId) instanceof Multi) {
							String[] memberInfo = membersMap.get(userId).toString().split(",");
							List<String> clubNames = new ArrayList<>();
							for (Club c : ((Multi)membersMap.get(userId)).getClubs()) {
								clubNames.add(c.getName());
							}
							System.out.printf("\n%s %s\n%s %s\n%s %s\n%s $%s\n", "Member ID:", memberInfo[0], "Member Name:", memberInfo[1]
									, "Checked In Status:", memberInfo[2], "Monthly Fee:", memberInfo[3]);
							System.out.print("Clubs: ");
							for (int i = 0; i < clubNames.size(); ++i) {
								if (i < clubNames.size()-1) {
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
				case 2:
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
								System.out.printf("\n%s %s\n%s %s\n%s %s\n%s $%s\n%s %s\n\n", "Member ID:", memberInfo[0], "Member Name:", memberInfo[1]
										, "Checked In Status:", memberInfo[2], "Monthly Fee:", memberInfo[3], "Club info:", memberInfo[4] + " " + memberInfo[5]);
							} else if (m instanceof Multi) {
								String[] memberInfo = m.toString().split(",");
								List<String> clubNames = new ArrayList<>();
								for (Club c : ((Multi)m).getClubs()) {
									clubNames.add(c.getName());
								}
								System.out.printf("\n%s %s\n%s %s\n%s %s\n%s $%s\n", "Member ID:", memberInfo[0], "Member Name:", memberInfo[1]
										, "Checked In Status:", memberInfo[2], "Monthly Fee:", memberInfo[3]);
								System.out.print("Clubs: ");
								for (int i = 0; i < clubNames.size(); ++i) {
									if (i < clubNames.size()-1) {
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
				case 3:
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
							System.out.printf("%s %s, %s %s\n", "Member ID:", m.getId(), "Member Name:", m.getName() );
						}
					}
					System.out.println();
					break;
				case 4:
					break;
				}
				break;
			case 5:
				removeUser(scnr, userId, membersMap);
				break;
			case 6:
				String name = Validator.getString(scnr, "Enter the club name: ");
				String address = Validator.getString(scnr, "Enter club address: ");
				clubsList.add(new Club(name, address));
				break;
			case 7:
				checkOutAll(membersMap);
				break;
			}

		} while (action != 7);

		System.out.println("Goodbye!");
	}

	public static String generateID(String name) {
		Random rndm = new Random();
		return name.substring(0, 1).toUpperCase() + (rndm.nextInt(900) + 100);
	}

	public static void addNewMember(List<Club> clubsList, Map<String, Members> membersMap, Scanner scnr) {
		System.out.println("These are your options: \n\nSingle Membership: $15.05 per month (One Club)\nMulti Membership: $99.99 per month (Full Access)\n");
		String membershipChoice = Validator
				.getString(scnr, "Are you interested in our single or multi-club membership? ").toLowerCase();
		if (membershipChoice.startsWith("s")) {
			String userName = Validator.getString(scnr, "What is your name? ");
			String ID = generateID(userName);
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
			System.out.println("This is your new Club Member ID: " + ID + ". Please remember it. ");
		}
		System.out.println("");
	}

	public static void removeUser(Scanner scnr, String userId, Map<String, Members> membersMap) {

		userId = Validator.getString(scnr, "Please enter your member ID: ");

		if (membersMap.containsKey(userId)) {
			try {
				System.out.println("\nWe're sorry to see you go.\n");
				membersMap.remove(userId);

			} catch (Exception e) {
				System.out.println("Something went terribly wrong. Our bad.");

			}
		} else {
			System.out.println("You are not currently in our system. Please see the Welcome Desk.");

		}
	}

	public static void checkOutAll(Map<String, Members> membersMap) {
		for (Members m : membersMap.values()) {
			m.setCheckedIn(false);
		}
	}
}
