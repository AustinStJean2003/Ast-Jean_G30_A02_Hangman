package linked_data_structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game_Frame {

	public static String[] word = { "apple", "youtube", "facebook", "happy" };
	public static Scanner kb = new Scanner(System.in);
	public static Random random = new Random();
	public static ArrayList<String> arr = new ArrayList<>();

	public static boolean checkWinner(ArrayList<String> arr, String theWord, int guesses) {
		String winnerCheck = "";

		for (String x : arr) {
			winnerCheck += x;
		}

		if (winnerCheck.contentEquals(theWord)) {
			System.out.println("Congratulations You Won!");
			System.exit(-1);
			return false;
		} else if (guesses == 0) {
			System.out.println("No guesses left, Game Over");
		}

		return true;
	}
	
	public static void gameLine(ArrayList<String> arr, int guesses) {
		for (String x : arr) {
			System.out.print(x);
		}

		System.out.print("\n" + guesses + " guesses left. ");
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			String theWord = "";
			arr.clear();

			int guesses = 6;

			System.out.println("Welcome to hang man!");
			System.out.println("Please enter your name");
			String name = kb.nextLine();
			System.out.println("Welcome " + name);
			System.out.println("Press N for new game or Q to quit game");
			String input = kb.nextLine();
			while (!(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("q"))) {

				System.out.println("Invalid Charatcer, please enter a letter A-Z");
				input = kb.nextLine();
			}
			if (input.equalsIgnoreCase("q")) {
				System.exit(-1);
			} else if (input.equalsIgnoreCase("n")) {
				int index = random.nextInt(word.length);
				theWord += word[index];

				for (int i = 0; i < theWord.length(); i++) {
					arr.add("_");
				}

				gameLine(arr, guesses);
				while (checkWinner(arr, theWord, guesses)) {
					System.out.println("Enter a letter from A-Z or type \"hint\" to get a hint");
					String ans = kb.nextLine();

					char letter = ans.charAt(0);
					
					if(ans.equalsIgnoreCase("hint")) {
						int randomIndex = (int)(Math.random() * theWord.length() -1);
						while(arr.get(randomIndex) != "_") {
							randomIndex = (int)(Math.random() * theWord.length() -1);
//							System.out.println(randomIndex);
						}
						for (int i = 0; i < theWord.length(); i++) {
							char s = theWord.charAt(randomIndex);
							String Str = String.valueOf(s);
							if (theWord.charAt(i) == s) {
								arr.remove(randomIndex);
								arr.add(randomIndex, Str);
								for (int h = 0; h < theWord.length(); h++) {
									if (theWord.charAt(i) == theWord.charAt(h)) {
										arr.remove(h);
										arr.add(h, Str);
									}
								}
							}
						}
					}
					
					if (arr.contains(ans)) {
						System.out.println("Already answered");
					} else {
						for (int i = 0; i < theWord.length(); i++) {
							if (theWord.charAt(i) == letter) {
								arr.remove(i);
								arr.add(i, ans);
							}
						}
					}

					if (!arr.contains(ans)) {
						guesses -= 1;
					}

					gameLine(arr, guesses);
					System.out.println("");
				}

			}
		}
	}

}

