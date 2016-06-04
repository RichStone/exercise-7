
public class Stack<T> {
	
	private LinkedList<T> linkedList = new LinkedList<>();
	
	public void push (T data) {
		linkedList.addFirst(data);
	}
	
	public T pop() throws MyUnderflowException {
		if(linkedList.isEmpty()) {
			throw new MyUnderflowException("The list is empty you Dumpster!");
		}
		else {
			return linkedList.removeFirst();
		}
	}
	
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}
	
	public T top() {
		return linkedList.front.getData();
	}
	
	public String topToString() {
		return linkedList.front.toString();
	}
	@Override
	public String toString() {
		return linkedList.toString();
	}

}