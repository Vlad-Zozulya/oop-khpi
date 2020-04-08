package ua.khpi;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

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
	    System.out.print("Enter date of birth (in DD/MM/YYYY format): ");
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
		attributePrint();
		int choice = MenuHelper.numberEnterer(5);
		while(choice != 0) {
			if(attributeChoice(choice)) {
				System.out.println("Enter number of account to delete");
				int removeIndex = resultOfSearch.get(MenuHelper.numberEnterer(resultOfSearch.size()));
				MenuHelper.accounts.remove(removeIndex);
			}
			attributePrint();
			choice = MenuHelper.numberEnterer(5);
		}
	}
	public static void scanAccount() {
		attributePrint();
		int choice = MenuHelper.numberEnterer(5);
		while(choice != 0) {
			attributeChoice(choice);
			attributePrint();
			choice = MenuHelper.numberEnterer(5);
		}
	}
	
	public static void sortAccount() {
		int choice;
		sortPrint();
		while((choice = MenuHelper.numberEnterer(5)) != 0) {
			sortChoice(choice);
			sortPrint();
		}
		System.out.println("Canceled");
	}
	public static void attributePrint() {
		System.out.println("0  - Cancel");
		System.out.println("1  - First name");
		System.out.println("2  - Second name");
		System.out.println("3  - Birthday");
		System.out.println("4  - Address");
	}
	public static void sortPrint() {
		System.out.println("0  - Cancel");
		System.out.println("1  - First name");
		System.out.println("2  - Second name");
		System.out.println("3  - Birthday");
		System.out.println("4  - Editing date");
	}
	public static boolean attributeChoice(int numMenu) {
		resultOfSearch.clear();
		String search;
		int index = 0;
		switch (numMenu) {
		case 0 :
			System.out.println("Canceled");
			break;
		case 1 : 
			search = enterName();
			for(Account account : MenuHelper.accounts) {
				if(account.getName().indexOf(search) >= 0) {
					resultOfSearch.add(MenuHelper.accounts.indexOf(account));
					System.out.println(index++ + ") " + "-------------------------------------------------------");
					System.out.println(account.toString());
				}
			}
			break;
		case 2 :
			search = enterSurname();
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
			search = enterAddress();
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
		boolean isSorted = false;
        Account[] o = MenuHelper.accounts.getArray(Account.class, MenuHelper.accounts.getSize());
        Account temp;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < o.length-1; i++) {
//                if(o[i] > o[i+1]){
                if(sortStatement(numMenu,o[i],o[i+1])) {
                    isSorted = false;
 
                    temp = o[i];
                    o[i] = o[i+1];
                    o[i+1] = temp;
                }
            }
        }
        for(Account a : o) {
        	System.out.println(a.toString());
        }
	}
	private static boolean sortStatement(int numMenu, Account o1, Account o2) {
		switch(numMenu) {
		case 1:
			if(o1.getName().compareToIgnoreCase(o2.getName()) >= 0) return true;
			else return false;
		case 2:
			if(o1.getSurname().compareToIgnoreCase(o2.getSurname()) >= 0) return true;
			else return false;
		case 3:
			if(o1.getBirthday().after(o2.getBirthday())) return true;
			else return false;
		case 4:
			if(o1.getEditingTime().before(o2.getEditingTime())) return true;
			else return false;
		}
		return false;
	}
}

















