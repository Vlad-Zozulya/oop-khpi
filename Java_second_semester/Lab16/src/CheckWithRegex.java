package test007;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class CheckWithRegex {
	public static boolean check_name_surname(String name) {
		return name.matches("[A-Z][a-z]*");
	}

	public static boolean check_phone_number(String number) {
		return number.matches("\\+38\\(\\d{3}\\)-\\d{3}-\\d{2}-\\d{2}");
	}

	public static boolean check_birthday(String date) {
		return date.matches("\\d{2}/\\d{2}/\\d{4}");
	}
	
    public static boolean check_file_extension(String file) {
    	return file.matches("^.*\\.(ser|SER|xml|XML)$");
    }
	public static boolean isXml(String filename) {
		return filename.matches("^.*\\.(xml|XML)$");
	}
	public static boolean check_landline(ArrayList<String> o) {
		for(String str : o) {
			if(Pattern.matches("^\\+38\\(057\\).*", str)) 
				return true;
		}
		return false;
	}
	public static boolean check_vodafone(ArrayList<String> o) {
		for(String str : o) {
			if(Pattern.matches("^\\+38\\(050\\).*", str) || Pattern.matches("^\\+38\\(066\\).*", str)
			|| Pattern.matches("^\\+38\\(095\\).*", str) || Pattern.matches("^\\+38\\(099\\).*", str)) 
				return true;
		}
		return false;
	}
	public static boolean check_lifecell(ArrayList<String> o) {
		for(String str : o) {
			if(Pattern.matches("^\\+38\\(063\\).*", str) || Pattern.matches("^\\+38\\(093\\).*", str) 
			|| Pattern.matches("^\\+38\\(073\\).*", str)) 
				return true;
		}
		return false;
	}
	public static boolean check_kyivstar(ArrayList<String> o) {
		for(String str : o) {
			if(Pattern.matches("^\\+38\\(039\\).*", str) || Pattern.matches("^\\+38\\(067\\).*", str)
			|| Pattern.matches("^\\+38\\(068\\).*", str) || Pattern.matches("^\\+38\\(096\\).*", str)
			|| Pattern.matches("^\\+38\\(097\\).*", str) || Pattern.matches("^\\+38\\(098\\).*", str)) 
				return true;
		}
		return false;
	}
	public static boolean check_name(String search, String name) {
		if(name.indexOf(search) >= 0) {
			return true;
		}
		return false;
	}
	public static boolean check_surname(String search, String surname) {
		if(surname.indexOf(search) >= 0) {
			return true;
		}
		return false;
	}
	public static boolean check_address(String search, String address) {
		if(address.indexOf(search) >= 0) {
			return true;
		}
		return false;
	}
}
