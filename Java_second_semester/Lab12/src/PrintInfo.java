package ua.khpi;

public class PrintInfo {
	static void menuPrint() {
		System.out.println("0  - Exit");
		System.out.println("1  - Enter account");
		System.out.println("2  - Show all accounts");
		System.out.println("3  - Remove account");
		System.out.println("4  - Remove all accounts");
		System.out.println("5  - Scan account");
		System.out.println("6  - Use XML encoder");
		System.out.println("7  - Use XML decoder");
		System.out.println("8  - Serialize data");
		System.out.println("9  - Deserialize data");
		System.out.println("10 - Sort data");
	}
	public static void attributePrint() {
		System.out.println("0  - Back");
		System.out.println("1  - First name");
		System.out.println("2  - Second name");
		System.out.println("3  - Birthday");
		System.out.println("4  - Address");
	}
	public static void sortPrint() {
		System.out.println("0  - Back");
		System.out.println("1  - First name");
		System.out.println("2  - Second name");
		System.out.println("3  - Birthday");
		System.out.println("4  - Editing date");
	}
	public static void scanPrint() {
		System.out.println("0  - Back");
		System.out.println("1  - First name");
		System.out.println("2  - Second name");
		System.out.println("3  - Birthday");
		System.out.println("4  - Address");
		System.out.println("5  - Mobile phone");
	}
	public static void mobilePrint(boolean isOR, boolean has_langline, boolean has_vodafone, boolean has_lifecell, boolean has_kyivstar) {
		System.out.println("0  - Back");
		System.out.println("1  - Landline" + ((has_langline) ? "   [chosen]" : ""));
		System.out.println("2  - Vodafone" + ((has_vodafone) ? "   [chosen]" : ""));
		System.out.println("3  - Lifecell" + ((has_lifecell) ? "   [chosen]" : ""));
		System.out.println("4  - Kyivstar" + ((has_kyivstar) ? "   [chosen]" : ""));
		System.out.println("5  - Scan");
		System.out.println("6  - Change scan mode [mode chosen: " + ((isOR) ? "OR]" : "AND]"));
	}
}
