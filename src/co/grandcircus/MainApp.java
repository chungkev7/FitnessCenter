/*
 * @author: Chris, Jack, Kevin
 */
package co.grandcircus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MainApp {

	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);
		
		ArrayList<Club> clubsList = new ArrayList<>();
		Map<String, Members> membersMap = new TreeMap<>();
		clubsList.add(new Club("Planet Fitness", "1234 Woodward Ave"));
		clubsList.add(new Club("Golds Gym", "5678 Main St"));
		clubsList.add(new Club("The Jacked Ones", "532 Mount Branch"));
		clubsList.add(new Club("Lunk-free Zone", "333 Fourth St"));
		
		System.out.println("Welcome to a new and fitter version of you!");
		Members person1 = new Single("12", "Tony");
		Members person2 = new Multi("21", "George", 0);
		membersMap.put(person1.getId(), person1);
		membersMap.put(person2.getId(), person2);
		String userResponse = Validator.getString(scnr, "Are you a new or returning member?").toLowerCase();
		if (userResponse.equals("n")) {
			String membershipChoice = Validator.getString(scnr, "Are you interested in our single or multi-club membership? ").toLowerCase();
			if (membershipChoice.startsWith("s")) {
				String userName = Validator.getString(scnr, "What is your name? ");
				Members m = new Single("13", userName);
				membersMap.put(m.getId(), m);
			for (Club c : clubsList) {
				System.out.println(c.getName());
			}
			}
		}
	}

}
