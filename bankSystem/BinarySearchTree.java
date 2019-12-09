/*---------------------------------------
 Genuine author: <Aviv Gai>, I.D.: <203147988>
 Date: 01-01-2018 
---------------------------------------*/
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<T> extends BinaryTree<T> implements Iterable<T>{
	
	protected Comparator<T> treeComparator;
	
	// This constructor builds an empty tree
	public BinarySearchTree(Comparator<T> myComparator){
		super();
		this.treeComparator = myComparator;
	}
	
	public T findData(T element){
		if(isEmpty())
			return null;
		return ((BinarySearchNode<T>)root).findData(element);
	}

	public Comparator<T> getComparator(){
		return treeComparator;
	}
	
	public void insert(T toInsert) {
		if (isEmpty()) 
			root = new BinarySearchNode<T>(toInsert, treeComparator);
		else 
			root.insert(toInsert);
	}
	
	public void remove(T toRemove){
		if(isEmpty())
			throw new NoSuchElementException();
		root = root.remove(toRemove);
	}
	
	public boolean equals(Object other){
		boolean isEqual = true;
		if(!(other instanceof BinarySearchTree<?>))
			isEqual = false ;
		else {
			Iterator<?> thisIter = this.iterator();
			Iterator<?> otherIter = ((BinarySearchTree<?>) other).iterator();
			while (thisIter.hasNext() & otherIter.hasNext() & isEqual) {
				if (!thisIter.next().equals(otherIter.next()))
					isEqual = false;
			}
			if (isEqual)
				isEqual = thisIter.hasNext() == otherIter.hasNext();
		}
		return isEqual;
	}
	
	public Iterator<T> iterator(){
		return new BinaryTreeInOrderIterator<T>(root);
	}
	
}

