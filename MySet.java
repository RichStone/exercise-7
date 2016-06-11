import java.util.ArrayList;
import java.util.Collection;
import java.lang.Iterable;

public abstract class MySet<T> implements Iterable<T>{

	//implement set using an ArrayList
	ArrayList<T> list = new ArrayList<T>();
	
	//add
	public boolean add(T obj) {
		int initialSize = list.size();
		if(!list.contains(obj)){
			list.add(obj);
		}
		if(list.size() == initialSize) {
			return false;
		}
		else {
			return true;
		}
		
	}
	
	//addAll method
	public boolean addAll(Collection <T> collectionToAdd) {
		int initialSize = list.size();
		for(T obj : collectionToAdd) {
			if(!list.contains(obj)){
				list.add(obj);
			}
		}
		
		if(list.size() == initialSize) {
			return false;
		}
		else {
			return true;
		}
	}
	
	//remove
	public boolean remove(T obj) {
		int initialSize = list.size();
		if(list.contains(obj)){
			list.remove(obj);
		}
		if(list.size() == initialSize) {
			return false;
		}
		else {
			return true;
		}
	}
	
	//removeAll 
	public boolean removeAll(Collection <T> collectionToAdd) {
		int initialSize = list.size();
		for(T obj : collectionToAdd) {
			if(list.contains(obj)){
				list.remove(obj);
			}
		}
		
		if(list.size() == initialSize) {
			return false;
		}
		else {
			return true;
		}
	}
	//retainAll
	public boolean retainAll(Collection <T> collectionToAdd) {
		int initialSize = list.size();
		for(T obj : collectionToAdd) {
			if(list.contains(obj)){
				list.remove(obj);
			}
		}
		
		if(list.size() == initialSize) {
			return false;
		}
		else {
			return true;
		}
	}
	
	//toString
	public String toString() {
		String outputString = "";
		for(T obj : list){
			outputString += obj.toString();
		}
		return outputString;
	}

	public int size() {
		return list.size();
	}
	
}
