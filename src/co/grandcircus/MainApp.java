/*
 * @author: Chris, Jack, Kevin
 */
package co.grandcircus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

		System.out.println("Welcome to a new and fitter version of you!");
		Members person1 = new Single("12", "Tony");
		clubsList.get(3).getMembers().add(person1);
		Members person2 = new Multi("21", "George", 0);
		membersMap.put(person1.getId(), person1);
		membersMap.put(person2.getId(), person2);

		int action = 0;
		String userId = "";
		int clubChoice = 0;
		
		do {
			String prompt = "Please choose from the following options:\n1. Check in\n2. Sign up for membership"
					+ "\n3. Get bill\n4. Search member database\n5. Cancel membership\n6. Quit ";
			action = Validator.getInt(scnr, prompt, 1, 6);
			
			switch (action) {
			case 1:
				int counter = 1;
				for (Club c : clubsList) {
					System.out.printf("%d. %s\n", counter++, c.getName());
				}

				clubChoice = Validator.getInt(scnr, "Enter the branch number: ");
				userId = Validator.getString(scnr, "Please enter your two digit ID: ");

				if (membersMap.containsKey(userId)) {
					try {
						// 
						membersMap.get(userId).checkIn(clubsList.get(1));
						System.out.println("Welcome " + membersMap.get(userId).getName());
						
					} catch (Exception e) {
						System.out.println("Something went terribly wrong. Our bad.");

					}
				} else {
					System.out.println("You are not currently in our system. Please see the Welcome Desk.");

				}
				break;
			case 2:
				addNewMember(clubsList, membersMap, scnr);
				break;
			case 3:
				
				break;
			case 4:
				userId = Validator.getString(scnr, "Please enter your two digit ID: ");
				System.out.println(membersMap.get(userId));
				break;
			case 5:
				userId = Validator.getString(scnr, "Please enter your two digit ID: ");

				if (membersMap.containsKey(userId)) {
					try {
						System.out.println("We're sorry to see you go. ");
						membersMap.remove(userId);
						
					} catch (Exception e) {
						System.out.println("Something went terribly wrong. Our bad.");

					}
				} else {
					System.out.println("You are not currently in our system. Please see the Welcome Desk.");

				}
				break;
			case 6:
				break;
			} 

		} while (action != 6);
		
		System.out.println("Goodbye!");

	}

	public static void addNewMember(List<Club> clubsList, Map<String, Members> membersMap, Scanner scnr) {

		String membershipChoice = Validator
				.getString(scnr, "Are you interested in our single or multi-club membership? ").toLowerCase();
		if (membershipChoice.startsWith("s")) {
			String userName = Validator.getString(scnr, "What is your name? ");
			Members m = new Single("13", userName);
			membersMap.put(m.getId(), m);
			int counter = 1;
			for (Club c : clubsList) {
				System.out.printf("%d. %s\n", counter++, c.getName());
			}
			int userChoice = Validator.getInt(scnr, "Select club would you like to join? (Choose a number)", 1, 4);
			System.out.println("You selected: " + clubsList.get(userChoice - 1));
			clubsList.get(userChoice - 1).getMembers().add(m);
		} else if (membershipChoice.startsWith("m")) {
			String userName = Validator.getString(scnr, "What is your name? ");
			Members m = new Multi("13", userName, 0);
			membersMap.put(m.getId(), m);
			int counter = 1;
			System.out.println("You can join the following clubs: ");
			for (Club c : clubsList) {
				System.out.printf("%d. %s\n", counter++, c.getName());
			}
			for (int i = 0; i < clubsList.size(); i++) {
				clubsList.get(i).getMembers().add(m);

			}

		}

	}
}
