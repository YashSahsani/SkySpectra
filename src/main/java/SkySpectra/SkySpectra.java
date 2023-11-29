package SkySpectra;

import java.util.ArrayList;
import java.util.Scanner;

public class SkySpectra {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the flight details: ");
		System.out.print("From: ");
		String from = sc.nextLine();
		System.out.print("To: ");
		String to = sc.nextLine();;
		System.out.print("Date: ");
		String startDate = sc.nextLine();;
		System.out.print("Return Date: ");
		String endDate = sc.nextLine();;
		sc.reset();
				
		from = dataValidate(from, false);
		to = dataValidate(to, false);
		startDate = dataValidate(startDate, true);
		if(!endDate.isEmpty()) endDate = dataValidate(endDate, true);
		
		System.out.println(from + " " + to + " " +  startDate + " " +  endDate);
		System.out.println("\n\n");
		FlightAnalysis.flightAnalysis(from, to, startDate, endDate);
		
		System.out.println("DONE");
		System.exit(0);
	}
	
	private static String dataValidate(String _word, boolean isDate) {
		Scanner sc = new Scanner(System.in);
		String userInput = "";
		int userIntInput = -1;
		
		if (isDate) {
			String date = _word;
			boolean isValid = DataValidation.validate(date);
			if (!isValid) {
				System.out.print("Please Enter a valid date: ");
				userInput = sc.nextLine();
				sc.reset();
				_word = userInput;
				_word = dataValidate(_word, true);
			}
			return _word;
		}
		
		String correctedWord = SpellChecker.spellCheck(_word);
		if(correctedWord != "OK") {
			System.out.print("Did you mean -> " + correctedWord + " (yes/no): ");
			
			userInput = sc.nextLine();
			sc.reset();
			
			if(userInput.equals("yes")) {
				_word = correctedWord;
			} else {
				ArrayList<String> wordList = WordCompletion.spellSuggestions(_word);
				System.out.println("Suggestions: ");
				for(int i=0; i<wordList.size(); i++) {					
					System.out.println((i+1) + ". " + wordList.get(i));
				}
				
				System.out.print("Enter the word number you want to select: ");
				userIntInput = Integer.parseInt(sc.nextLine());
				sc.reset();
				_word = wordList.get(userIntInput - 1);
			}
		} 
		
		return _word;
	}	
}
