package roboRallyModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FlagTest {
	private Flag flag;

	@Before
	public void setUp() throws Exception {
		
		flag = new Flag();
		flag.setTileImage(".\\Assets\\images\\tiles\\flag.jpg");
		
	}

	@Test
	public void getFlageImage() {

		assertEquals(flag.getTileImage(),".\\Assets\\images\\tiles\\flag.jpg");

		
	}
	@Test
	public void setFlagImage() {
		flag.setTileImage(".\\Assets\\images\\tiles\\flag.jpg");
		assertEquals(flag.getTileImage(),".\\Assets\\images\\tiles\\flag.jpg");
	
	}
	
	@Test 
	public void setFlagType() {
		
		flag.setTileType('1');
		assertEquals(flag.getTileType(),'1');
		
	}

}
