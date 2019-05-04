package roboRallyModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author 180034882
 *
 */
public class TileTest {
	Tile tile;
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		tile = new Empty();
		tile.setTileImage("testLoc");
	}

	@Test
	public void testGetTileImage() {
		assertEquals(tile.getTileImage(), "testLoc");
	}
}
