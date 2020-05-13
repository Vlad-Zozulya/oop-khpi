package test007;

import java.util.Collections;
import java.util.Comparator;

public class Sort {
	public static void sortChoice(String choice) {
		switch(choice) {
		case "name":
			Collections.sort(test007.Collections.accountsCollectionView, new SortbyName());
			break;
		case "surname":
			Collections.sort(test007.Collections.accountsCollectionView, new SortbySurname());
			break;
		case "birthday":
			Collections.sort(test007.Collections.accountsCollectionView, new SortbyBirthday());
			break;
		case "edit. time":
			Collections.sort(test007.Collections.accountsCollectionView, new SortbyEditingTime());
			break;
		}
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