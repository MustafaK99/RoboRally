package roboRallyModel;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 180034882
 *
 */
public class PlayerTest {
	private Player player1;
	private Player player2;
	private Robot rob1;
	private Robot rob2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//playerRobot = new Robot();
		player1 = new Player("Player1", "FLFWF+WFWFL", 0);
		player2 = new Player("Player2", "FLFWF", 1);
		rob1 = new Robot("Player1", 0);
		rob2 = new Robot("Player2", 1);
	}

	/**
	 * Test method for {@link javacw.roboRally.Model.Player#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals(player1.getName(), "Player1");
		assertEquals(player2.getName(), "Player2");
	}

	/**
	 * Test method for {@link javacw.roboRally.Model.Player#getInstruction()}.
	 */
	@Test
	public void testGetInstruction() {
		String[] test = new String[]{"FLFWF", "WFWFL"};
		assertArrayEquals(player1.getInstruction(), test);
		
		String[] test2 = new String[]{"FLFWF"};
		assertArrayEquals(player2.getInstruction(), test2);
	}

	/**
	 * Test method for {@link javacw.roboRally.Model.Player#getPlayerRobot()}.
	 */
	@Test
	public void testGetPlayerRobot() {
		assertEquals(player1.getPlayerRobot().getPlayerName(), rob1.getPlayerName());
		assertEquals(player1.getPlayerRobot().getNumber(), rob1.getNumber());
		assertEquals(player2.getPlayerRobot().getPlayerName(), rob2.getPlayerName());
		assertEquals(player2.getPlayerRobot().getNumber(), rob2.getNumber());
	}

}
