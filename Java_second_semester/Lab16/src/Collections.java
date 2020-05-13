package test007;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Collections {
	public static LinkedList<Account> accounts = new LinkedList<Account>();
	public static ObservableList<Account> accountsCollectionView = FXCollections.observableArrayList();

	public static void update() {
		accountsCollectionView.clear();
		accountsCollectionView.addAll(accounts);
	}
	public static int setUniqueId() {
		int id = 0;
		for(Account a : accounts) {
			if(a.getId() != id) {
				break;
			}
			id++;
		}
		return id;
	}
}
