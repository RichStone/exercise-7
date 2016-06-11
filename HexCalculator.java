/**
 * The main class of a simple calculator. Create one of these and you'll
 * get the calculator on screen.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2008.03.30
 */
public class HexCalculator
{
    private CalcEngine engine;
    private HexInterface gui;

    /**
     * Create a new calculator and show it.
     */
    public HexCalculator()
    {
        engine = new CalcEngine();
        gui = new HexInterface(engine);
    }
    
    public static void main(String[]args) {
    	HexCalculator calHex = new HexCalculator();
    	calHex.show();
    }
    
    /**
     * get CalcEngine for testing purposes
     */
    public CalcEngine getCalcEngine() {
    	return engine;
    }

    /**
     * In case the window was closed, show it again.
     */
    public void show()
    {
        gui.setVisible(true);
    }
    
    
}
