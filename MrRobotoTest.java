import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;


public class MrRobotoTest {

	@Test
	public void test() throws Exception {
		MrRoboto.MoveMouse(0, 0);
		assertEquals(true, true );
		System.out.println(Color.BLACK);
	}

}
