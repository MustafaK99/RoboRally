package roboRallyModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GearTest {
	private Gear gear1;
	private Gear gear2;

	@Before
	public void setUp() throws Exception {
		gear1 = new Gear();
		gear2 = new Gear();
		gear1.setTileForm('+');
		gear2.setTileForm('-');
	}

	@Test
	public void testGetImage() {
	
		assertEquals(gear1.getTileImage(),".\\Assets\\images\\tiles\\rotateCW.jpg");
		assertEquals(gear2.getTileImage(),".\\Assets\\images\\tiles\\rotateACW.jpg");
		
		
	}
	@Test
	public void testGetTileType() {
		
		assertEquals(gear1.getTileType(),'+');
		assertEquals(gear2.getTileType(),'-');
		
	}

}
