/*
 * @author: Chris, Jack, Kevin
 */
package co.grandcircus;

import java.util.ArrayList;
import java.util.Iterator;
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
//		Members person1 = new Single("12", "Tony");
//		Members person2 = new Multi("21", "George", 0);
//		membersMap.put(person1.getId(), person1);
//		membersMap.put(person2.getId(), person2);
		
		String cont = "yes";

		while (cont.toLowerCase().startsWith("y")) {
			// your code should start here!
			String userResponse = Validator.getString(scnr, "Are you a new or returning member?").toLowerCase();
			if (userResponse.equals("n")) {
				String membershipChoice = Validator.getString(scnr, "Are you interested in our single or multi-club membership? ").toLowerCase();
				if (membershipChoice.startsWith("s")) {
					String userName = Validator.getString(scnr, "What is your name? ");
					Members m = new Single("13", userName);
					membersMap.put(m.getId(), m);
					int counter = 1;
					for (Club c : clubsList) {
						System.out.printf("%d. %s\n", counter++, c.getName());
					}
					int userChoice = Validator.getInt(scnr, "Select club would you like to join? (Choose a number)", 1, 4);
					System.out.println("You selected: " + clubsList.get(userChoice-1));
					clubsList.get(userChoice-1).getMembers().add(m);
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
			// your logic should stop here if it doesn't need to be included in the loop
			System.out.println("Do you want to continue (yes/no)");
			cont = scnr.nextLine();
		}
		for (Members m : clubsList.get(3).getMembers()) {
			System.out.println(m);
		}

		//This is our indication that the program has ended
		System.out.println("Goodbye!");
		
	}

}
