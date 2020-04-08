package ua.khpi;
import java.awt.Menu;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AccountHelper {
	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Integer> resultOfSearch = new ArrayList<Integer>();
	public static boolean check_name_surname(String name) {
		return name.matches("[A-Z][a-z]*");
	}

	public static boolean check_phone_number(String number) {
		return number.matches("\\+38\\(\\d{3}\\)-\\d{3}-\\d{2}-\\d{2}");
	}
	
	public static boolean check_birthday(String date) {
		return date.matches("\\d{2}/\\d{2}/\\d{4}");
	}
	public static boolean check_mobile_phone_assembled(ArrayList<String> o, boolean langline_needed, 
			boolean vodafone_needed, boolean lifecell_needed, boolean kyivstar_needed, boolean isOR) {
		
		boolean landline_found = check_landline(o);
		boolean vodafone_found = check_vodafone(o);
		boolean lifecell_found = check_lifecell(o);
		boolean kyivstar_found = check_kyivstar(o);
		
		boolean result;
		if(isOR) {
			result = false;
			if(langline_needed && landline_found) result = true;
			if(vodafone_needed && vodafone_found) result = true;
			if(lifecell_needed && lifecell_found) result = true;
			if(kyivstar_needed && kyivstar_found) result = true;
		} else {
			result = true;
			if(langline_needed && !landline_found)	result = false;
			if(vodafone_needed && !vodafone_found)  result = false;
			if(lifecell_needed && !lifecell_found)  result = false;
			if(kyivstar_needed && !kyivstar_found)  result = false;
		}
			
		return result;
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
	public static ArrayList<String> enterMobileNumbers(){
		ArrayList<String> mobileNumbers = new ArrayList<String>();
		System.out.println("Enter mobile numbers in \"+38(XXX)-XXX-XX-XX\" format \n\t\t(click double Enter to stop adding): ");
		String current = sc.nextLine();
		while(!current.equals("")) {
			if(check_phone_number(current)) {
			    mobileNumbers.add(new String(current));
			    current = sc.nextLine();
			}
			else {
				System.out.print("WARNING: Invalid phone number. Try again: ");
				current = sc.nextLine();
			}
		}
		return mobileNumbers;
	}
	public static String enterName() {
		System.out.print("Enter first name: ");
		String fullName = sc.nextLine();
		while(!check_name_surname(fullName)) {
			System.out.print("WARNING: Invalid name. Try again: ");
			fullName = sc.nextLine();
		}
		return fullName;
	}
	public static String enterSurname() {
		System.out.print("Enter second name: ");
		String fullName = sc.nextLine();
		while(!check_name_surname(fullName)) {
			System.out.print("WARNING: Invalid surname. Try again: ");
			fullName = sc.nextLine();
		}
		return fullName;
	}
	public static Date enterBirthday() {
	    System.out.print("Enter date of birth (in \"DD/MM/YYYY\" format): ");
	    Date theDate = null;
	    try {
	    	String birthday = sc.nextLine();
	    	while(!check_birthday(birthday)) {
	    		System.out.print("WARNING: Invalid date. Try again: ");
	    		birthday = sc.nextLine();
	    	}
    		theDate = new SimpleDateFormat("ddMMyyyy").parse(birthday.replaceAll("/", ""));
	    } catch (ParseException e) {
	        System.out.println(e);
	    }
		return theDate;
	}
	public static String enterAddress() {
		System.out.print("Enter address: ");
		String address = sc.nextLine();
		return address;
	}
	public static String enterString() {
		System.out.print("Enter string to scan: ");
		String address = sc.nextLine();
		return address;
	}
	public static Account newAccountAdded() {
		Account account = new Account();
		account.setName(enterName());
		account.setSurname(enterSurname());
		account.setBirthday(enterBirthday());
		account.setAddress(enterAddress());
		account.setMobileNumbers(enterMobileNumbers());
		return account;
	}
	public static Account newAccountFromFile(BufferedReader reader) throws IOException {
		Account account = new Account();
		account.setName(reader.readLine());
		account.setSurname(reader.readLine());
	    try {
	    	Date theDate = new SimpleDateFormat("ddMMyyyy").parse(reader.readLine().replaceAll("/", ""));
		    account.setBirthday(theDate);
	    } catch (ParseException e) {
	        System.out.println(e);
	    }

		account.setAddress(reader.readLine());
		ArrayList<String> mobileNumbers = new ArrayList<String>();
		String current = reader.readLine();
		while(!current.equals("----")) {
		    mobileNumbers.add(new String(current));
		    current = reader.readLine();
		}
		account.setMobileNumbers(mobileNumbers);
		return account;
	}
	public static void removeAccount() {
		PrintInfo.removePrint();
		int choice = MenuHelper.numberEnterer(5);
		while(choice != 0) {
			if(removeChoice(choice)) {
				System.out.println("Enter number of account to delete");
				int removeIndex = resultOfSearch.get(MenuHelper.numberEnterer(resultOfSearch.size()));
				MenuHelper.accounts.remove(removeIndex);
			}
			PrintInfo.removePrint();
			choice = MenuHelper.numberEnterer(5);
		}
	}
	///////////////////////////////////////////
	public static void scanAccount() {
		PrintInfo.scanPrint();
		int choice = MenuHelper.numberEnterer(6);
		while(choice != 0) {
			scanChoice(choice);
			PrintInfo.scanPrint();
			choice = MenuHelper.numberEnterer(6);
		}
	}
	///////////////////////////////////////////
	public static void sortAccount() {
		int choice;
		PrintInfo.sortPrint();
		while((choice = MenuHelper.numberEnterer(5)) != 0) {
			sortChoice(choice);
			PrintInfo.sortPrint();
		}
		System.out.println("Canceled");
	}

	public static boolean removeChoice(int numMenu) {
		resultOfSearch.clear();
		String search;
		int index = 0;
		switch (numMenu) {
		case 0 :
			System.out.println("Canceled");
			break;
		case 1 : 
			search = enterString();
			for(Account account : MenuHelper.accounts) {
				if(account.getName().indexOf(search) >= 0) {
					resultOfSearch.add(MenuHelper.accounts.indexOf(account));
					System.out.println(index++ + ") " + "-------------------------------------------------------");
					System.out.println(account.toString());
				}
			}
			break;
		case 2 :
			search = enterString();
			for(Account account : MenuHelper.accounts) {
				if(account.getSurname().indexOf(search) >= 0) {
					resultOfSearch.add(MenuHelper.accounts.indexOf(account));
					System.out.println(index++ + ") " + "-------------------------------------------------------");
					System.out.println(account.toString());
				}
			}
			break;
		case 3 :
			Date dateSearch = enterBirthday();
			for(Account account : MenuHelper.accounts) {
				if(account.getBirthday().compareTo(dateSearch) == 0) {
					resultOfSearch.add(MenuHelper.accounts.indexOf(account));
					System.out.println(index++ + ") " + "-------------------------------------------------------");
					System.out.println(account.toString());
				}
			}
			break;
		case 4 :
			search = enterString();
			for(Account account : MenuHelper.accounts) {
				if(account.getAddress().indexOf(search) >= 0) {
					resultOfSearch.add(MenuHelper.accounts.indexOf(account));
					System.out.println(index++ + ") " + "-------------------------------------------------------");
					System.out.println(account.toString());
				}
			}
			break;
		}
		if(resultOfSearch.size() == 0) {
			System.out.println("Oops... Nothing was found.");
			return false;
		}
		return true;
	}

	public static void sortChoice(int numMenu) {
		switch(numMenu) {
		case 1:
			Collections.sort(MenuHelper.accounts, new SortbyName());
			break;
		case 2:
			Collections.sort(MenuHelper.accounts, new SortbySurname());
			break;
		case 3:
			Collections.sort(MenuHelper.accounts, new SortbyBirthday());
			break;
		case 4:
			Collections.sort(MenuHelper.accounts, new SortbyEditingTime());
			break;
		}
		for(Account a : MenuHelper.accounts) {
			System.out.println(a.toString());
		}
	}
	public static void scanChoice(int numMenu) {
		String search;
		int index = 1;
		switch (numMenu) {
		case 0 :
			System.out.println("Canceled");
			break;
		case 1 : 
			search = enterString();
			for(Account account : MenuHelper.accounts) {
				if(account.getName().indexOf(search) >= 0) {
					System.out.println(index++ + ") " + "-------------------------------------------------------");
					System.out.println(account.toString());
				}
			}
			break;
		case 2 :
			search = enterString();
			for(Account account : MenuHelper.accounts) {
				if(account.getSurname().indexOf(search) >= 0) {
					System.out.println(index++ + ") " + "-------------------------------------------------------");
					System.out.println(account.toString());
				}
			}
			break;
		case 3 :
			Date dateSearch = enterBirthday();
			for(Account account : MenuHelper.accounts) {
				if(account.getBirthday().compareTo(dateSearch) == 0) {
					System.out.println(index++ + ") " + "-------------------------------------------------------");
					System.out.println(account.toString());
				}
			}
			break;
		case 4 :
			search = enterString();
			for(Account account : MenuHelper.accounts) {
				if(account.getAddress().indexOf(search) >= 0) {
					System.out.println(index++ + ") " + "-------------------------------------------------------");
					System.out.println(account.toString());
				}
			}
			break;
		case 5 :
			int choice;
			boolean langline_needed = false;
			boolean vodafone_needed = false;
			boolean lifecell_needed = false;
			boolean kyivstar_needed = false;
			boolean isOR = false;
			PrintInfo.mobilePrint(isOR, langline_needed, vodafone_needed, lifecell_needed, kyivstar_needed);
			while((choice = MenuHelper.numberEnterer(7)) != 0) {
				switch (choice) {
				case 1:
					if(langline_needed)  langline_needed = false;
					else langline_needed = true;
					break;
				case 2:
					if(vodafone_needed)  vodafone_needed = false;
					else vodafone_needed = true;
					break;
				case 3:
					if(lifecell_needed)  lifecell_needed = false;
					else lifecell_needed = true;
					break;
				case 4:
					if(kyivstar_needed)  kyivstar_needed = false;
					else kyivstar_needed = true;
					break;
				case 5:
					for(Account account : MenuHelper.accounts) {
						if(account.getMobileNumbers().size() != 0 && check_mobile_phone_assembled(account.getMobileNumbers(), langline_needed, vodafone_needed, lifecell_needed, kyivstar_needed, isOR)) {
							System.out.println(index++ + ") " + "-------------------------------------------------------");
							System.out.println(account.toString());
						}
					}
					index = 1;
					break;
				case 6:
					if(isOR)  isOR = false;
					else isOR = true;
					break;
				}
				PrintInfo.mobilePrint(isOR, langline_needed, vodafone_needed, lifecell_needed, kyivstar_needed);
			}
			System.out.println("Canceled");
			break;
		}
	}
	static void xmlSaver(LinkedList<Account> account) throws FileNotFoundException {
		XMLEncoder encoder = new XMLEncoder(
				new BufferedOutputStream(
						new FileOutputStream(MenuHelper.directory())));
		encoder.writeObject(account);
		encoder.close(); 
	}
	static LinkedList<Account> xmlLoader() throws FileNotFoundException {
		XMLDecoder decoder = new XMLDecoder(
				new BufferedInputStream(
						new FileInputStream(MenuHelper.directory())));
		LinkedList<Account> account =  (LinkedList<Account>) decoder.readObject();
		decoder.close();
		return account; 
	}
	static public void saveContainer(LinkedList<Account> account) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(MenuHelper.directory());
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
	    objectOutputStream.writeObject(account);
	    objectOutputStream.close();
	}
	static public LinkedList<Account> recoverContainer() throws IOException, ClassNotFoundException {
		FileInputStream fileInputStream = new FileInputStream(MenuHelper.directory());
	    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
	    LinkedList<Account> account = (LinkedList<Account>) objectInputStream.readObject();
	    return account;
	}
}
class SortbyName implements Comparator<Account> {
    public int compare(Account a, Account b) { 
        return a.getName().compareTo(b.getName());
    } 
}
class SortbySurname implements Comparator<Account> { 
    public int compare(Account a, Account b) { 
        return a.getSurname().compareTo(b.getSurname());
    }
}
class SortbyBirthday implements Comparator<Account> {
    public int compare(Account a, Account b) {
    	return a.getBirthday().compareTo(b.getBirthday());
    }
}
class SortbyEditingTime implements Comparator<Account> {
    public int compare(Account a, Account b) {
    	return a.getEditingTime().compareTo(b.getEditingTime());
    }
}


