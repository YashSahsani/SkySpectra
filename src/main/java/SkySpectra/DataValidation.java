package SkySpectra;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {
	public static boolean validate(String data) {
		String dateRegex = "^(19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
		Pattern pattern = Pattern.compile(dateRegex);
		Matcher matcher = pattern.matcher(data);
		boolean formatCheck = matcher.matches();
		boolean checkCurrentDate = false;

		try {
			LocalDate enteredDate = LocalDate.parse(data);
			LocalDate currentDate = LocalDate.now();

			checkCurrentDate = enteredDate.isBefore(currentDate);
		} catch (DateTimeParseException e) {
			// Parsing exception, meaning the input is not a valid date format
			return false;
		}
		return formatCheck && !checkCurrentDate;
	}

}
