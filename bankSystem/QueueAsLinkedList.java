/*---------------------------------------
 Genuine author: <Aviv Gai>, I.D.: <203147988>
 Date: 01-01-2018 
---------------------------------------*/
import java.util.NoSuchElementException;

public class QueueAsLinkedList<T> implements Queue<T>{
	
	private List<T> list;
	
	public QueueAsLinkedList() {
		this.list = new LinkedList<T>();
	}
	
	public void enqueue(T element) {
		list.add(element);
	}
	
	public T dequeue() {
		if(isEmpty())
			throw new NoSuchElementException();
		T output = list.remove(0);
		return output;
	}

	public boolean isEmpty() {
		return list.size() == 0;
	}
}
