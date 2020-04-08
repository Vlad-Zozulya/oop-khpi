package ua.khpi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
public class Generator {
	private static String f_name = ".\\file_name.txt";
	private static String f_surname = ".\\file_surname.txt";
	private static int size;
	private static Random rand = new Random();
	public static void starter_accountGenerator() {
		System.out.println("Enter the amount of accounts to be generated [0 - 100 000 000]");
		size = MenuHelper.numberEnterer(100_000_000);
		System.out.println("Starting generation...");
		try {
			_gen_add();
		} catch (IOException e) {
			System.out.println("Error" + e);
		}
		System.out.println("\nFinished");
	}
	public static void _gen_add() throws IOException {
		ArrayList<String> name = read_name();
		ArrayList<String> surname = read_surname();
		for(int i = 0; i < size; i++) {
			Account account = new Account();
			account.setName(name.get(rand.nextInt(name.size())));
			account.setSurname(surname.get(rand.nextInt(surname.size())));
			account.setBirthday(_gen_birthdays());
			account.setAddress("street #" + i);
			account.setMobileNumbers(_gen_mobilephones());
			MenuHelper.accounts.add(account);
			PrintInfo.updateProgress((double)i/size);
		}
	}

	public static Date _gen_birthdays() {
		Date theDate = null;
		try {
			String line = rand.nextInt(3) + rand.nextInt(10) + "/" + rand.nextInt(1) + rand.nextInt(10) + "/" + rand.nextInt(3) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
			theDate = new SimpleDateFormat("ddMMyyyy").parse(line.replaceAll("/", ""));
		} catch (ParseException e) {
			System.out.println(e);
		}
		return theDate;
	}
	public static ArrayList<String> _gen_mobilephones() {
		ArrayList<String> mobilephones = new ArrayList<String>();
		String[] codes = {"+38(050)-","+38(066)-","+38(095)-","+38(099)-","+38(057)-","+38(039)-","+38(067)-","+38(068)-","+38(096)-","+38(097)-",
				"+38(098)-","+38(093)-","+38(063)-","+38(073)-"};
		int how_many_numbers = rand.nextInt(3) + 1;
		int index = 0;
		while(index++ < how_many_numbers) {
			String line = codes[rand.nextInt(codes.length)] + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + "-" + rand.nextInt(10) +
					rand.nextInt(10) + "-" + rand.nextInt(10) + rand.nextInt(10);
			mobilephones.add(line);
		}
		return mobilephones;
	}
	public static ArrayList<String> read_name() {
		ArrayList<String> name = new ArrayList<String>();
		FileReader filereader;
		try {
			filereader = new FileReader(f_name);
			BufferedReader reader = new BufferedReader(filereader);
			String line = reader.readLine();
			while(!line.equals("----")) {
			    name.add(new String(line));
			    line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return name;	
	}
	public static ArrayList<String> read_surname() {
		ArrayList<String> surname = new ArrayList<String>();
		FileReader filereader;
		try {
			filereader = new FileReader(f_surname);
			BufferedReader reader = new BufferedReader(filereader);
			String line = reader.readLine();
			while(!line.equals("----")) {
			    surname.add(new String(line));
			    line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return surname;	
	}
}

