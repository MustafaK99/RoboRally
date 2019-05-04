package roboRallyModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EmptyTest {
        
	private Empty emptyTile;
	
	@Before
	public void setUp() throws Exception {
		
		emptyTile = new Empty();
		emptyTile.setTileImage(".\\Assets\\images\\tiles\\empty.jpg");
		
	}

	@Test
	public void getEmptyTileImage() {

		assertEquals(emptyTile.getTileImage(),".\\Assets\\images\\tiles\\empty.jpg");

		
	}
	@Test
	public void setEmptyTileImages() {
		emptyTile.setTileImage(".\\Assets\\images\\tiles\\empty.jpg");
		assertEquals(emptyTile.getTileImage(),".\\Assets\\images\\tiles\\empty.jpg");
	
	}
	
	@Test 
	public void setTileType() {
		
		emptyTile.setTileType('.');
		assertEquals(emptyTile.getTileType(),'.');
		
	}

}
