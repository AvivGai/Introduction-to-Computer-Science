/*---------------------------------------
 Genuine author: <Aviv Gai>, I.D.: <203147988>
 Date: 01-01-2018 
---------------------------------------*/
import java.util.Comparator;

public class AccountComparatorByName implements Comparator<BankAccount>{

	@Override
	//the method compares 2 accounts by name (lexicographic order)
	public int compare(BankAccount account1, BankAccount account2) {
		return account1.getName().compareTo(account2.getName());
	}

}
