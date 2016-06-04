import static org.junit.Assert.*;

import org.junit.*;

public class TestCalculator {
	
	HexCalculator calHex;
	
	@Before
	public void createCalculatorBeforeEachTest() {
		calHex = new HexCalculator();
	}
	
	@Test
	public void testHigherPrecedenceInFront() {
		calHex.getCalcEngine().numberPressed("5");
		calHex.getCalcEngine().numberPressed("*");
		calHex.getCalcEngine().numberPressed("6");
		calHex.getCalcEngine().numberPressed("+");
		calHex.getCalcEngine().numberPressed("6");
		calHex.getCalcEngine().equals();
		assertEquals("36", calHex.getCalcEngine().getDisplayValue());
		
	}
	
	@Test
	public void testLowerPrecedenceInFront() {
		calHex.getCalcEngine().numberPressed("5");
		calHex.getCalcEngine().numberPressed("+");
		calHex.getCalcEngine().numberPressed("6");
		calHex.getCalcEngine().numberPressed("*");
		calHex.getCalcEngine().numberPressed("6");
		calHex.getCalcEngine().equals();
		assertEquals("41", calHex.getCalcEngine().getDisplayValue());
	}
	
	@Test
	public void testEmptyDisplayValueMethodOfCalcEngine() {
		//the calculator is empty, value of display value  should be zero
		assertEquals("", calHex.getCalcEngine().getDisplayValue());
	}
	
}
