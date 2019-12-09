/*---------------------------------------
 Genuine author: <Aviv Gai>, I.D.: <203147988>
 Date: 01-01-2018 
---------------------------------------*/
import java.util.Comparator;

public class TestBalance {

	public static void main(String[] args) {
		Comparator<BankAccount> c = new AccountComparatorByNumber();
		BankAccountBinarySearchTree t1 = new BankAccountBinarySearchTree(c);
		t1.insert(new BankAccount("a",4,0));
		t1.insert(new BankAccount("a",2,0));
		t1.insert(new BankAccount("a",6,0));
		t1.insert(new BankAccount("a",1,0));
		t1.insert(new BankAccount("a",3,0));
		t1.insert(new BankAccount("a",5,0));
		t1.insert(new BankAccount("a",7,0));
		t1.insert(new BankAccount("a",8,0));
		System.out.println("----------unbalanced t1:----------\n" + t1);
		t1.balance();
		System.out.println("\n----------balanced t1:----------\n" + t1 +"\n\n");

		BankAccountBinarySearchTree t2 = new BankAccountBinarySearchTree(c);
		t2.insert(new BankAccount("a",5,0));
		t2.insert(new BankAccount("a",2,0));
		t2.insert(new BankAccount("a",6,0));
		t2.insert(new BankAccount("a",1,0));
		t2.insert(new BankAccount("a",4,0));
		t2.insert(new BankAccount("a",7,0));
		t2.insert(new BankAccount("a",3,0));
		t2.insert(new BankAccount("a",8,0));
		System.out.println("----------unbalanced t2:----------\n" + t2);
		t2.balance();
		System.out.println("\n----------balanced t2:----------\n" + t2 +"\n\n");
		BankAccountBinarySearchTree t3 = new BankAccountBinarySearchTree(c);
		t3.insert(new BankAccount("a",2,0));
		t3.insert(new BankAccount("a",1,0));
		t3.insert(new BankAccount("a",3,0));
		t3.insert(new BankAccount("a",4,0));
		t3.insert(new BankAccount("a",5,0));
		t3.insert(new BankAccount("a",6,0));
		t3.insert(new BankAccount("a",7,0));
		t3.insert(new BankAccount("a",8,0));
		System.out.println("----------unbalanced t3:----------\n" + t3);
		t3.balance();
		System.out.println("\n----------balanced t3:----------\n" + t3 +"\n\n");

		BankAccountBinarySearchTree t4 = new BankAccountBinarySearchTree(c);
		t4.insert(new BankAccount("a",8,0));
		t4.insert(new BankAccount("a",7,0));
		t4.insert(new BankAccount("a",6,0));
		t4.insert(new BankAccount("a",5,0));
		t4.insert(new BankAccount("a",4,0));
		t4.insert(new BankAccount("a",3,0));
		t4.insert(new BankAccount("a",2,0));
		t4.insert(new BankAccount("a",1,0));
		System.out.println("----------unbalanced t4:----------\n" + t4);
		t4.balance();
		System.out.println("\n----------balanced t4:----------\n" + t4 +"\n\n");

	}
}
