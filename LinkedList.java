public class LinkedList <T> {
	
	Node<T> front = null;
	private T data;
	
	public void addFirst(T data) {
		Node<T> newFirst = new Node <T>(data);
		newFirst.next = front;
		front = newFirst;
	}
	
	public T removeFirst() throws MyUnderflowException{
		Node<T> oldFirst = front;
		front = front.next;
		return oldFirst.getData();
	}

	public boolean isEmpty() {
		return front == null;
	}
	
	@Override
	public String toString() {
		String listString = "";
		
		Node<T> current = front;
		
		System.out.println("Elements in stack: \n");
		while(current != null) {
			listString += current.getData().toString() + "\n";
			current = current.next;
		}
		System.out.println(listString);
		System.out.println("Stack list over \n\n");
		return listString; 
	}
}
