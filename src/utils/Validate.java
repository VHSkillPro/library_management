package utils;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
	public static boolean isExist(String str) {
		return (str != null && !str.equals("")); 
	}
	
	public static boolean isEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.find();
    }

    public static boolean isNumberPhone(String numberPhone) {
        Pattern phonePattern = Pattern.compile("(84|0[3|5|7|8|9])+([0-9]{8})\\b", Pattern.CASE_INSENSITIVE);
        Matcher phoneMatcher = phonePattern.matcher(numberPhone);
        return phoneMatcher.find();
    }

    public static boolean isDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false);
        try {
            formatter.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isStartWithNumber(String str) {
        Pattern pattern = Pattern.compile("^[0-9]");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^([0-9]+)(.[0-9]+)?$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
