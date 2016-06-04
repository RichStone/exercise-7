/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  David J. Barnes and Michael Kolling 
 * @version 2008.03.30
 */
public class CalcEngine
{
	
	private Stack<Integer> stack;	 
	
	char res;
	int result; 
	
    // The calculator's state is maintained in three fields:
    //     buildingDisplayValue, haveLeftOperand, and lastOperator.
    
    // Are we already building a value in the display, or will the
    // next digit be the first of a new one?
    private boolean buildingDisplayValue;
    // Has a left operand already been entered (or calculated)?
    private boolean haveLeftOperand;
    // The most recent operator that was entered.
    private char lastOperator;
    // The current value (to be) shown in the display.
//    private int displayValue;
    // The value of an existing left operand.
    private int leftOperand;
    
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
    	//TODO also add clause for doubling operators
    	if(!displayValue.isEmpty() && isNumeric(displayValue) && isNumeric(command)) {
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
     * the calculator accepts only single digit numbers
     * transform a hex letter in a String of two single digit numbers
     * and add them to numberPressed
     * @param hexLetter pressed in the interface
     */
    public void hexPressed(String hexLetter) {
    	
    	String sumOfHex = "";
    	
    	if(!displayValue.isEmpty() && isNumeric(displayValue)) {
    		return;
    	}
    	
    	if(hexLetter.equals("A")) {
    		sumOfHex = "9+1";
    	}
    	else if(hexLetter.equals("B")) {
    		sumOfHex = "9+2";
    	}
    	else if(hexLetter.equals("C")) {
    		sumOfHex = "9+3";
		}
    	else if(hexLetter.equals("D")) {
    		sumOfHex = "9+4";
		}
    	else if(hexLetter.equals("E")) {
    		sumOfHex = "9+5";
		}
    	else if(hexLetter.equals("F")) {
    		sumOfHex = "9+6";
		}
    	numberPressed(sumOfHex);
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
			else if(t=='+' || t=='-' || t=='*' || t=='/'){
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
  			
  			if(Character.isDigit(charToCheck)) {
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
        lastOperator = '?';
        haveLeftOperand = false;
        buildingDisplayValue = false;
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
     * Report an error in the sequence of keys that was pressed.
     */
    private void keySequenceError()
    {
        System.out.println("A key sequence error has occurred.");
        // Reset everything.
        clear();
    }
    
}
