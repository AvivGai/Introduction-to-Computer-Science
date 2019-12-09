/*---------------------------------------
 Genuine author: <Aviv Gai>, I.D.: <203147988>
 Date: 01-01-2018 
---------------------------------------*/
import java.util.Comparator;
import java.util.Iterator;


public class BankAccountBinarySearchTree extends BinarySearchTree<BankAccount>{

	public BankAccountBinarySearchTree(Comparator<BankAccount> myComparator) {
		super(myComparator);
	}
	
	//the method balances a given tree. 
	public void balance(){
		if(!isEmpty()){
			BankAccountBinarySearchTree balancedtree=new BankAccountBinarySearchTree(treeComparator);
			List<BankAccount> list = new LinkedList<>();
			Iterator<BankAccount> iter = this.iterator();
			while(iter.hasNext())
				list.add(iter.next());
			buildBalancedTree(balancedtree,  list, 0, list.size()-1);
			this.root=balancedtree.root;
		}
	}
	
	//auxiliary method - creates a balanced tree with the  elements of the original tree
	private void buildBalancedTree(BankAccountBinarySearchTree tree, List<BankAccount> list, int low, int high){
		int middle = (low+high) / 2;
		tree.insert(list.get(middle));
		if(middle==low){
			tree.insert(list.get(high));
			return;
		}
		buildBalancedTree(tree, list, middle+1, high);
		buildBalancedTree(tree, list, low, middle-1);
	}
}
