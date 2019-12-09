/*---------------------------------------
 Genuine author: <Aviv Gai>,  I.D.: <203147988>
 Date: 01-01-2018 
---------------------------------------*/
public class TestPrimeIterator {

	public static void main(String[] args) {
		PrimeIterator iter = new PrimeIterator();
		for (int i = 1; i < 21; i = i + 1) {
			System.out.print(iter.next()+", ");
		}
	}

}
