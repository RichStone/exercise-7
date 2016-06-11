
import java.util.HashSet;
import java.util.Iterator;

/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  David J. Barnes and Michael Kolling 
 * @version 2008.03.30
 */
public class CalcEngine
{	

	private HashSet<Integer> setRight;
	private HashSet<Integer> setLeft;
	
    String displayValue = "";

    /**
     * Create a CalcEngine.
     */
    public CalcEngine()
    {
    	
    }

    /**
     * @return The value that should currently be displayed
     * on the calculator display.
     */
    public String getDisplayValue()
    {
        return displayValue;
    }

    /**
     * A number button was pressed.
     * Either start a new operand, or incorporate this number as
     * the least significant digit of an existing one.
     * @param number The number pressed on the calculator.
     */
    public void numberPressed(String command)
    {
        		displayValue += command;
    }
    
	public HashSet<Integer> evaluateStringToSet (String setString){
		
		HashSet<Integer> set = new HashSet<>();
		
		String setGesplittet [];
		
		//divide String where the commatas are, parse to int and add to Set
		setGesplittet = setString.split(",");
		
		for(int i = 0; i < setGesplittet.length;i++) {
			set.add(Integer.parseInt(setGesplittet[i]));
		}
		
		return set;
	}  	

    /**
     * The '=' button was pressed.
     */
    public void equals()
    {	
    	String setString[];
    	String operator = "";
    	
    	//extract operator
    	for(int i = 0; i < displayValue.length()-1;i++) {
    		
    		String string = displayValue.substring(i,i+1);
    		
    		if(string.equals("U")) {
    			operator = "U";
    		}
    		else if (string.equals("-")) {
    			operator = "-";
    		}
    		else if (string.equals("\u2229")) {
    			operator = "\u2229";
    		}
    	}
    	
    	//divide displayValue String where the operator is
    	if(operator.equals("U")) {
    		setString = displayValue.split("U");
    	}
    	else if(operator.equals("-")) {
    		setString = displayValue.split("-");
    	}
    	else {
    		setString = displayValue.split("\u2229");
    	}
    	
    	//left set
    	setLeft = evaluateStringToSet(setString[0]);
    	//right set
    	setRight = evaluateStringToSet(setString[1]);
    	
    	//apply operator
    	if(operator.equals("U")) {
    		sumUpSets();
    	}
    	else if(operator.equals("-")) {
    		subtractSets();
    	}
    	else if(operator.equals("\u2229")) {
    		interesectionOfSets();
    	}
    	
    }
    

    public void sumUpSets() {
    	//just add one set in another
    	setLeft.addAll(setRight);
    	System.out.println(setLeft.toString());
    	
    	displayValue = setLeft.toString();
    }
    
    public void subtractSets() {
    	setLeft.removeAll(setRight);
    	displayValue = setLeft.toString();
    }
    
    public void interesectionOfSets() {	//geschnitten
    	//create new set with corresponding elements of the two sets
    	setLeft.retainAll(setRight);
    	displayValue = setLeft.toString();
    }
    
    /**
     * set displayValue to the number of ints in the set
     */
    public String countElements() {
    	//TODO look for symbols besides ints and commatas in the String
    	//end the method using return if one is found
    	String numberOfElements = "";
    	
    	HashSet<Integer> setToCount = new HashSet<>();
    	
    	setToCount = evaluateStringToSet(displayValue);
    	
    	numberOfElements += setToCount.size();
    	
    	displayValue = numberOfElements;
    	
    	return numberOfElements;
    }

    /**
     * The 'C' (clear) button was pressed.
     * Reset everything to a starting state.
     */
    public void clear()
    {
        displayValue = "";
    }

    /**
     * @return The title of this calculation engine.
     */
    public String getTitle()
    {
        return "Java Calculator";
    }

    /**
     * @return The author of this engine.
     */
    public String getAuthor()
    {
        return "David J. Barnes and Michael Kolling";
    }

    /**
     * @return The version number of this engine.
     */
    public String getVersion()
    {
       return "Version 1.0";
    }
    
    /**
     * check if last character of a String is an Integer
     * @deprecated
     */
    public boolean isNumeric(String str) {
    	
    	//extract last character from command
    	String lastCharacterOfString = str.substring(str.length() - 1);
    	
    	//try if it's possible to parse the character as an int
    	try {
    		int i = Integer.parseInt(lastCharacterOfString);
    	}
    	catch(NumberFormatException nfe) {
    		return false;
    	}
    	return true;
    }
}
