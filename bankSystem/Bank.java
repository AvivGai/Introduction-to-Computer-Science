import java.util.Iterator;

/*---------------------------------------
 Genuine author: <Aviv Gai>, I.D.: <203147988>
 Date: 01-01-2018 
---------------------------------------*/
public class Bank {

	private BankAccountBinarySearchTree namesTree;
	private BankAccountBinarySearchTree accountNumbersTree;
	
	public Bank() {
		namesTree = new BankAccountBinarySearchTree(new AccountComparatorByName());
		accountNumbersTree = new BankAccountBinarySearchTree(new AccountComparatorByNumber());
	}

	public BankAccount lookUp(String name){
		// create an Entry with the given name, a "dummy" accountNumber (1) and zero balance
		// This "dummy" accountNumber will be ignored when executing getData
		BankAccount lookFor = new BankAccount(name, 1, 0);
		return (BankAccount)namesTree.findData(lookFor);
	}
	
	public BankAccount lookUp(int accountNumber){
		// create an Entry with a "dummy" name, zero balance and the given accountNumber
		// This "dummy" name will be ignored when executing getData
		BankAccount lookFor = new BankAccount("dummy", accountNumber,0);
		return (BankAccount)accountNumbersTree.findData(lookFor);
	}
	
	public void balance(){
		namesTree.balance();
		accountNumbersTree.balance();
	}
	
	public Object exportNames() {
		return this.namesTree;
	}
	public Object exportAccountNumbers() {
		return this.accountNumbersTree;
	}
	
	// END OF Given code -----------------------------------
	
	//the method add a bank account if there isn't already an account with the same name or number
	public boolean add(BankAccount newAccount) {
		if(lookUp(newAccount.getAccountNumber())==null & lookUp(newAccount.getName())==null ){
			namesTree.insert(newAccount);
			accountNumbersTree.insert(newAccount);
			return true;
		}
		return true;
	}

	//the method delete a bank account by name (if exist)
	public boolean delete(String name){
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(name);
		// complete this:
		if(toRemove == null)
			return false;
		namesTree.remove(toRemove);
		accountNumbersTree.remove(toRemove);
		return true;	
	}
	
	//the method delete a bank account by acoount number (if exist)
	public boolean delete(int accountNumber){
		// this first line is given in the assignment file
		BankAccount toRemove = lookUp(accountNumber);
		// complete this:
		if(toRemove == null)
			return false;
		namesTree.remove(toRemove);
		accountNumbersTree.remove(toRemove);
		return true;	
	}
	
	//Complete the following method
	public boolean spendOrDepositMoney(int amount, int accountNumber){
		BankAccount account = lookUp(accountNumber);
		return account.spendOrDepositMoney(amount);
	}
}
