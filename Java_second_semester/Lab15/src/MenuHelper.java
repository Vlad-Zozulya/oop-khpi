package ua.khpi;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class MenuHelper {
	static LinkedList<Account> accounts = new LinkedList<Account>();
    static Scanner userInput = new Scanner(System.in);
    static private boolean isAuto;
    static public void starter(String[] arg)  {
    	if(arg.length == 0) {
    		isAuto = false;
    		consoleMode();
    	} else if("-auto".equals(arg[0])) {
    		isAuto = true;
    		autoMode();
    	}
    }
    static public void autoMode() {
		try {
			FileReader filereader = new FileReader(directory());
			BufferedReader reader = new BufferedReader(filereader);
			String line = reader.readLine();
			int size = Integer.parseInt(line);
			int index = 0;
			while (index++ < size) {
				accounts.add(AccountHelper.newAccountFromFile(reader));
            }
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		consoleMode();
    }
	static public void consoleMode() {
        PrintInfo.menuPrint();
        int choice = numberEnterer(12);
        while(choice != 0) {
        	menuChoice(choice);
			choice = numberEnterer(12);
        }
        System.out.println("Closing...");
	}
	static void menuChoice(int numMenu) {
		switch (numMenu) {
		case 1 : 
			if(isAuto)	
				System.out.println("Sorry, in auto mode this function is not available");
			else
				accounts.add(AccountHelper.newAccountAdded());
			break;
		case 2 :
			if(accounts.size() == 0) System.out.println("Enter data first.");
			else {
				for(Account a : accounts) {
					System.out.println(a.toString());
				}
			}
			break;
		case 3 :
			if(accounts.size() == 0) System.out.println("Enter data first.");
			else AccountHelper.removeAccount();
			break;
		case 4 :
			if(accounts.size() == 0) System.out.println("Enter data first.");
			else accounts.clear();
			break;
		case 5 :
			if(accounts.size() == 0) System.out.println("Enter data first.");
			else AccountHelper.scanAccount();
			break;
		case 6 :
			if(accounts.size() == 0) System.out.println("Enter data first.");
			else
				try {
					AccountHelper.xmlSaver(accounts);
				} catch (FileNotFoundException e3) {
					e3.printStackTrace();
				}
			break;
		case 7 :
			try {
				accounts = AccountHelper.xmlLoader();
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
			break;
		case 8 :
			if(accounts.size() == 0) System.out.println("Enter data first.");
			else
				try {
					AccountHelper.saveContainer(accounts);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			break;
		case 9 :
			try {
				accounts = AccountHelper.recoverContainer();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 10 :
			AccountHelper.sortAccount();
			break;
		case 11 :
			Generator.starter_accountGenerator();
			break;
		default:
			System.out.println("Try again");
		}
	}

	static String directory() {
		Scanner dirsc = new Scanner(System.in);
		int index_const = 3, index2, index3, choice;
		String fileName = writeFileName();
		File file = new File(fileName);
		File file2 = new File(file.getAbsolutePath());
		String currPos = file2.getParent();
		while(true) {
			index2 = 0;
			index3 = 0;
			File currFolder = new File(currPos);
			File[] folders = currFolder.listFiles();
			int[] indexes = new int[folders.length];

			System.out.printf("%-30s","1) .");
			System.out.printf("%-30s","2) ..");
			for(int i = 0; i < folders.length; i++) {
				if(folders[i].isDirectory() && !folders[i].isHidden())   {
					indexes[index2] = i;
					if(index2%4 == 3) System.out.printf("%-30s", (index_const + index3++) + ") " + folders[i].getName());
					else if(index2%4 == 2) System.out.printf("%-30s", (index_const + index3++) + ") " + folders[i].getName());
					else if(index2%4 == 1) System.out.printf("%-30s\n", (index_const + index3++) + ") " + folders[i].getName());
					else System.out.printf("%-30s", (index_const + index3++) + ") " + folders[i].getName());
					index2++;
				}
			}
			System.out.print("\n" + currPos + "> ");
			choice = dirsc.nextInt();
			if(choice == 1) 
				break;
			else if(choice == 2 && currFolder.getParent() != null) {
				currPos = currFolder.getParent();
			}
			else if((choice-index_const) < index2 && (choice-index_const) >= 0) {
				currPos = folders[indexes[choice-index_const]].getAbsolutePath();
			}
			else {
				System.out.println("Try again.");
			}
		}
		return currPos + "\\" + fileName;
	}
	private static String writeFileName() {
		System.out.print("Enter file name: ");
		String fileName = userInput.nextLine();
		return fileName;
	}
	public static int numberEnterer(int size) {
		String input = "";
		int userChoice;
		while (true) {
			System.out.print("[" + accounts.size() + "]" + " User> ");
			input = userInput.nextLine();
			try {
				userChoice = Integer.parseInt(input);
				if(userChoice >= 0 && userChoice < size) 
					break;
			} catch (Exception e){
				System.out.println("");
			}
		}
		return userChoice;
	}
}

