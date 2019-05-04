package roboRallyModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PitTest {
       private Pit pit;
	@Before
	public void setUp() throws Exception {
		
		pit = new Pit();
		pit.setTileForm('x');
		
	}

	@Test
	public void testGetTileImage() {
	
		
		assertEquals(pit.getTileImage(),".\\Assets\\images\\tiles\\pit.jpg");
		
		
		
	}
	
	public void testGetTileType() {
		
		assertEquals(pit.getTileType(),'x');
	}

}
