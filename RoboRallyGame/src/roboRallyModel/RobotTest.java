package roboRallyModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author 180034882
 *
 */
public class RobotTest {
	Robot rob1;
	Robot rob2;
	Robot rob3;
	Robot rob4;
	
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		rob1 = new Robot("rob1", 0);
		rob2 = new Robot("rob2", 1);
		rob3 = new Robot("rob3", 2);
		rob4 = new Robot("rob4", 3);
	}

	@Test
	public void testGetImgLoc() {
		assertEquals(rob1.getImgLoc(), ".\\Assets\\Images\\robotIcons\\r2D2.png");
		assertEquals(rob2.getImgLoc(), ".\\Assets\\Images\\robotIcons\\bb8.png");
		assertEquals(rob3.getImgLoc(), ".\\Assets\\Images\\robotIcons\\c3PO.png");
		assertEquals(rob4.getImgLoc(), ".\\Assets\\Images\\robotIcons\\darthVadar.png");
	}

	@Test
	public void testGetPlayerName() {
		assertEquals(rob1.getPlayerName(), "rob1");
		assertEquals(rob2.getPlayerName(), "rob2");
		assertEquals(rob3.getPlayerName(), "rob3");
		assertEquals(rob4.getPlayerName(), "rob4");
	}

	@Test
	public void testGetNumber() {
		assertEquals(rob1.getNumber(), 0);
		assertEquals(rob2.getNumber(), 1);
		assertEquals(rob3.getNumber(), 2);
		assertEquals(rob4.getNumber(), 3);
	}

	@Test
	public void testGetRobotName() {
		assertEquals(rob1.getRobotName(), "R2-D2");
		assertEquals(rob2.getRobotName(), "BB-8");
		assertEquals(rob3.getRobotName(), "C-3PO");
		assertEquals(rob4.getRobotName(), "Darth Vadar");
	}

}
