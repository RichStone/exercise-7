import java.util.ArrayList;
import java.util.Iterator;


public class MySet<T>{

	//implement set using an ArrayList
	private ArrayList<T> list;
	Iterator<T> it;
		
	public MySet() {
		list = new ArrayList<T>();
	}
	
	public ArrayList<T> getList()  {
		return list;
	}
	
	
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
	public boolean addAll(MySet <T> collectionToAdd) {
		//memorize initial size
		int initialSize = list.size();
		
		it = collectionToAdd.getList().iterator();
		
		while(it.hasNext()) {
			T objectOfInitialSet = it.next();
			if(!list.contains(objectOfInitialSet)){
				list.add(objectOfInitialSet);
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
	public boolean removeAll(MySet <T> collectionToRemove) {
		int initialSize = list.size();
		
		it = collectionToRemove.getList().iterator();
		
		while(it.hasNext()) {
			T objectOfInitialSet = it.next();
			if(list.contains(objectOfInitialSet)){
				list.remove(objectOfInitialSet);
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
	public boolean retainAll(MySet <T> collectionToAdd) {
		int initialSize = list.size();
		
		//create new list where the doubled elements will be stored
		ArrayList<T> listOfDuplicates = new ArrayList<T>();
		
		it = collectionToAdd.getList().iterator();
		
		while(it.hasNext()) {
			T obj = it.next();
			if(list.contains(obj)){
				listOfDuplicates.add(obj);
			}
		}
		
		list = listOfDuplicates;
		
		if(list.size() == initialSize) {
			return false;
		}
		else {
			return true;
		}
	}
	
	//toString
	public String toString() {
		String outputString = "[ ";
		for(T obj : list){
			outputString += obj.toString() + ", ";
		}
		
		outputString = outputString.substring(0, outputString.length()-2);
		outputString += " ]";
		
		return outputString;
	}

	public int size() {
		return list.size();
	}
	
}
