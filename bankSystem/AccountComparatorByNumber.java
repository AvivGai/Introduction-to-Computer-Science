/*---------------------------------------
 Genuine author: <Aviv Gai>, I.D.: <203147988>
 Date: 01-01-2018 
---------------------------------------*/
import java.util.Comparator;

public class AccountComparatorByNumber implements Comparator<BankAccount>{

	@Override
	//the method compares 2 accounts by their account number
	public int compare(BankAccount account1, BankAccount account2) {
		return account1.getAccountNumber()-account2.getAccountNumber();
	}

}
