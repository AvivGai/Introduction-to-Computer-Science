/*---------------------------------------
 Genuine author: <Aviv Gai>, I.D.: <203147988>
 Date: 01-01-2018 
---------------------------------------*/
public class BankAccount {
	
	private String name;
	private int accountNumber;
	private int balance;
	
	public BankAccount(String name, int accountNumber, int balance) {
		if(name == null || name.length() == 0){
			throw new IllegalArgumentException();
		}
		if(accountNumber <= 0){
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	public String getName(){
		return name;
	}

	public int getBalance(){
		return balance;
	}

	public int getAccountNumber(){
		return accountNumber;
	}
	
	public String toString(){
		//return "Name: "+name+", AccountNumber: "+accountNumber;
		
		//use the following string to easily test your answers
		return ""+accountNumber;
	}

	//the method gets an amount to spend(<0) or to deposit(>0). if the spend causes a negative balance return false, else return true. 
	public boolean spendOrDepositMoney(int amount){
		if(amount<0){
			if(balance+amount < 0 )
				return false;
		}
		balance = balance+amount;
		return true;
	}
}
