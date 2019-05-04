package roboRallyModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author 180034882
 *
 */
public class BoardTest {

	Board brd;
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		brd = new Board(".\\Assets\\boards\\test-2x2.brd");
	}

	/**
	 * 
	 */
	@Test
	public void testGetBoardTiles() {
		Empty empty = new Empty();
		empty.setTileForm('.');
		StraightConveyorBelt SCBelt = new StraightConveyorBelt();
		SCBelt.setTileForm('<');
		Gear gear = new Gear();
		gear.setTileForm('+');
		
		Tile[][] tile = new Tile[][] {{empty, SCBelt}, {empty, gear}};
		for(int i = 0; i < tile.length; i++) {
			for(int j = 0; j < tile[0].length; j++) {
				assertEquals(brd.getBoardTiles()[i][j].getTileImage(), tile[i][j].getTileImage());
			}
		}
	}

	/**
	 * 
	 */
	@Test
	public void testGetBoardSize() {
		int[] size = new int[] {2, 2};
		assertArrayEquals(brd.getBoardSize(), size);
	}

}
