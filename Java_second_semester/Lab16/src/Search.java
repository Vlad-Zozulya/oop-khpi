package test007;
import javafx.scene.control.TextField;

public class Search {
	boolean name_needed;
	boolean surname_needed;
	boolean address_needed;
	public void search(boolean landline_needed, boolean vodafone_needed, boolean lifecell_needed, 
			boolean kyivstar_needed, TextField name, TextField surname, TextField address) {
		name_needed = !name.getText().trim().isEmpty();
		surname_needed = !surname.getText().trim().isEmpty();
		address_needed = !address.getText().trim().isEmpty();
		if(!landline_needed && !vodafone_needed && !lifecell_needed && !kyivstar_needed &&
				!name_needed && !surname_needed && !address_needed) return;
		Collections.accountsCollectionView.clear();
		for(Account a : Collections.accounts) {
			if(check(a, landline_needed,vodafone_needed,lifecell_needed,kyivstar_needed,name,surname,address)) {
				Collections.accountsCollectionView.add(a);
			}
		}
	}
	private boolean check(Account a, boolean landline_needed, boolean vodafone_needed, 
			boolean lifecell_needed, boolean kyivstar_needed, TextField name, TextField surname, TextField address) {

		boolean landline_found = CheckWithRegex.check_landline(a.getMobileNumbers());
		boolean vodafone_found = CheckWithRegex.check_vodafone(a.getMobileNumbers());
		boolean lifecell_found = CheckWithRegex.check_lifecell(a.getMobileNumbers());
		boolean kyivstar_found = CheckWithRegex.check_kyivstar(a.getMobileNumbers());
		boolean name_found     = CheckWithRegex.check_name(name.getText(), a.getName());
		boolean surname_found  = CheckWithRegex.check_surname(surname.getText(), a.getSurname());
		boolean address_found  = CheckWithRegex.check_address(address.getText(), a.getAddress());
		
		boolean result = true;
		if(landline_needed && !landline_found)	result = false;
		if(vodafone_needed && !vodafone_found)  result = false;
		if(lifecell_needed && !lifecell_found)  result = false;
		if(kyivstar_needed && !kyivstar_found)  result = false;
		if(name_needed && !name_found)  result = false;
		if(surname_needed && !surname_found)  result = false;
		if(address_needed && !address_found)  result = false;

		return result;
	}
}