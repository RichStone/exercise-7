/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  David J. Barnes and Michael Kolling 
 * @version 2008.03.30
 */
public class CalcEngine
{
	
	private Stack<Integer> stack;	 

    String displayValue = "";

    /**
     * Create a CalcEngine.
     */
    public CalcEngine(Stack<Integer> stack)
    {
    	this.stack = stack;
        clear();
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
    	
    	//if displayValue and command Is Numeric jump out of the method
    	//TODO also add clause for avoiding of doubling operators
    	//TODO add isALetter method to check if command is a hex to not have hex numbers a after normal numbers 
    	if(!displayValue.isEmpty() && isNumeric(displayValue) && (isNumeric(command))) {
    		return;
    	}
    	else {
    		
    		displayValue += command;
    		if(displayValue.substring(0).equals("+") || displayValue.substring(0).equals("*")) {
        		displayValue = "";
        	}
    	}
    	
    }
    
    /**
     * check if last character of a String is an Integer
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

	public int evaluate (String pfx){
		char res;
		int result; 
		int stringLength = pfx.length();				//Länge des Strings
		char[] charArray = new char[stringLength];
		
		pfx.getChars(0, stringLength, charArray, 0);	//String in Char-Array einspeichern
		
		// for(int l=0; l<stringLength; l++){
		// 	System.out.println(charArray[l]);
		// }
		
		for(int i=0; i<stringLength; i++){
			char t = charArray[i];
			if(t=='1' || t=='2' || t=='3' || t=='4' || t=='5' || t=='6' || t=='7' || t=='8' || t=='9'){
				stack.push(Character.getNumericValue(charArray[i]));
			}
			else if (t == 'A') {
				stack.push(10);
			}
			else if (t == 'B') {
				stack.push(11);
			}
			else if (t == 'C') {
				stack.push(12);
			}
			else if (t == 'D') {
				stack.push(13);
			}
			else if (t == 'E') {
				stack.push(14);
			}
			else if (t == 'F') {
				stack.push(15);
			}
			else if(t=='+' || t=='-' || t=='*' || t=='/' || t == '^'){
				int rhs = stack.top();
				stack.pop();
				int lhs = stack.top();
				stack.pop();
				
				if(t=='+'){
					result = lhs + rhs;
					stack.push(result);
				}
				if(t=='-'){
					result = lhs - rhs;
					stack.push(result);
				}
				if(t=='*'){
					result = lhs * rhs;
					stack.push(result);
				}
				if(t=='^') {
					result = (int)Math.pow(lhs, rhs);
					stack.push(result);
				}
				
				//not present in our calculator yet
//				if(t=='/'){
//					result = lhs / rhs;
//					stack.push(result);
//				}
				
			}
		}
		return stack.top();
	}
  //create a stack for storage of operators in ifx to pfx method
  	Stack<Character> operatorStack = new Stack <Character> ();
  	
  	public String infixToPostfix(String infixString){
  		
  		String postfixString = "";
  		char charToCheck = 0;
  		
  		for(int i = 0; i < infixString.length(); i++) {
  			charToCheck = infixString.charAt(i);
  			
  			if(Character.isDigit(charToCheck) || Character.isLetter(charToCheck)) {
  				postfixString += charToCheck;
  			}
  			else {
  				while(!operatorStack.isEmpty() && getPrecedence(charToCheck) <= getPrecedence(operatorStack.top())) {
  					postfixString += operatorStack.pop();
  				}
  				operatorStack.push(charToCheck);
  			}
  		}
  		
  		while(!operatorStack.isEmpty()) {
  			postfixString += operatorStack.pop();
  		}
  		
  		return postfixString;
  	}
  	
  	
  	
  	public int getPrecedence(char c){
  		
  		int returnValue = -1;
  		
  		switch(c){
  			case '+' : 
  			case '-' : returnValue = 0;  break;
  			case '*' : 
  			case '/' : returnValue = 1;  break;
  			case '^' : returnValue = 2;  break;
  			default  : returnValue = -1; break;		
  		}
  		
  		return returnValue;
  	}
    /**
     * The '=' button was pressed.
     */
    public void equals()
    {	
    	String postfix = "";
    	
    	postfix = infixToPostfix(displayValue);
    	
    	String evaluatedPostfix = Integer.toString(evaluate(postfix));
    	
    	displayValue = evaluatedPostfix;
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
    
}
