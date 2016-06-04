
public class Node <T> {
	
	private final T data;
	Node <T> next = null;
	
	public Node (T data) {
		this.data = data;
	}
	
	public T getData () {
		return this.data;
	}
	
	public Node<T> getNode() {
		return this.next;
	}
	
	@Override
	public String toString() {
		return data.toString();
	}
}
