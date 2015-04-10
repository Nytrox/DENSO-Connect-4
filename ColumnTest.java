import junit.framework.TestCase;

import org.junit.Test;


public class ColumnTest extends TestCase {

	@Test
	public void test() {
		/**
	     * Tests the base cases of Column
	     */
		Column Colin = new Column();
		//Test nulls
		assertEquals(Colin.getValue(), 0 );
		assertEquals(Colin.getPrevValue(), 0 );
		assertEquals(Colin.getCoordinate(), 0);
		assertEquals(Colin.RedtoString(), "0000000");
		assertEquals(Colin.YellowtoString(), "0000000");
		//Test setCoordinate
		Colin.setCoordinate(50);
		assertEquals(Colin.getCoordinate(), 50);
		assertFalse(Colin.getCoordinate() == 0);
		//test set value Red
		Colin.setValue();
		Colin.changeRed();
		assertTrue(Colin.getValue() == 1);
		assertTrue(Colin.getPrevValue() == 0);
		assertEquals(Colin.RedtoString(), "1000000");
		assertEquals(Colin.YellowtoString(), "0000000");
		//test that values and strings do not change
		Colin.changeRed();
		Colin.changeYellow();
		Colin.changeYellow();
		assertEquals(Colin.RedtoString(), "1000000");
		assertEquals(Colin.YellowtoString(), "0000000");
		//test set value Yellow
		Colin.setValue();
		Colin.changeYellow();
		assertTrue(Colin.getValue() == 2);
		assertTrue(Colin.getPrevValue() == 1);
		assertEquals(Colin.RedtoString(), "1000000");
		assertEquals(Colin.YellowtoString(), "0100000");
		System.out.println(Colin.RedtoString());
		System.out.println(Colin.YellowtoString());
	}
	
	public void testycols(){
		/**
	     * Tests reset and that changed columns cannot be overwritten
	     */
		Column Colossus = new Column(500);
		//test Column function 2
		assertTrue(Colossus.getCoordinate() == 500);
		assertFalse(Colossus.getCoordinate() == 0);
		//test setting until full
		Colossus.setValue();
		Colossus.changeRed();
		Colossus.setValue();
		Colossus.changeRed();
		Colossus.setValue();
		Colossus.changeRed();
		Colossus.setValue();
		Colossus.changeYellow();
		Colossus.setValue();
		Colossus.changeYellow();
		Colossus.setValue();
		Colossus.changeRed();
		Colossus.setValue();
		Colossus.changeYellow();
		Colossus.setValue();
		Colossus.changeYellow();
		Colossus.changeYellow();
		Colossus.setValue();
		Colossus.changeRed();
		Colossus.setValue();
		Colossus.changeYellow();
		System.out.println(Colossus.RedtoString());
		System.out.println(Colossus.YellowtoString());
		
		Colossus.resetValues();
		assertTrue(Colossus.getValue() == 0);
		Colossus.resetString();
		assertEquals(Colossus.RedtoString(), "0000000");
		assertEquals(Colossus.YellowtoString(), "0000000");
		
	}
	public void testbool(){
		/**
	     * Tests isChanged
	     */
		Column Colombus = new Column(500);
		//test Column function 2
		assertTrue(!Colombus.isChanged());
		Colombus.setValue();
		Colombus.changeRed();
		assertTrue(Colombus.isChanged());
		assertFalse(Colombus.isChanged());
		assertFalse(Colombus.isChanged());
		Colombus.setValue();
		Colombus.changeRed();
		assertTrue(Colombus.isChanged());
		assertFalse(Colombus.isChanged());
		assertFalse(Colombus.isChanged());
	}
	
	public void testaract(){
		/**
	     * Tests Column arrays and string concatenation
	     */
		Column[] C = new Column[7];
		for(int x = 0; x < 7; x++){
			C[x] = new Column(480 + (50*x)); 
		}
		C[0].setValue();
		C[0].changeRed();
		C[0].setValue();
		C[0].changeRed();
		C[1].setValue();
		C[1].changeRed();
		C[2].setValue();
		C[2].changeYellow();
		C[2].setValue();
		C[2].changeYellow();
		C[2].setValue();
		C[2].changeRed();
		C[5].setValue();
		C[5].changeYellow();
		C[5].setValue();
		C[5].changeYellow();
		C[6].setValue();
		C[6].changeRed();
		C[6].setValue();
		C[6].changeYellow();
		String key = C[0].RedtoString() + C[1].RedtoString() + C[2].RedtoString() + C[3].RedtoString()
				+ C[4].RedtoString() + C[5].RedtoString() + C[6].RedtoString();
		long bitBoard = Long.parseLong(key, 2);
		System.out.println(bitBoard);
		System.out.println(key);
	}
}
