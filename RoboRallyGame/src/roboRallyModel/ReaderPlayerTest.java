package roboRallyModel;

import static org.junit.Assert.*;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReaderPlayerTest {
	
     private ReaderPlayer readerPlayer;
     
     
	@Before
	public void setUp() throws Exception {
	
		readerPlayer = new ReaderPlayer(".\\Assets\\programs\\2players-2rounds.prg");
	
		
	}
	
	
	@Test
	public void testGetNames() {
		String[] expectedResult = new String[]{"Alice","Bob"};
		Assert.assertArrayEquals( expectedResult, readerPlayer.getNames());
		
		
		
	}
	@Test
	public void testGetInstructionsArray() {
		String[][]expectedResult = new String[][] {{"FLFWF","WFWUL"},{"RFWFL","FRBRF"}};
		readerPlayer.getNames();
		Assert.assertArrayEquals(expectedResult, readerPlayer.getInstructionsArray());
		
	}
	
	@Test
	public void testGetPlayerErrors() {
		
		assertEquals(readerPlayer.getPlayerErrors(), "");
	}
	
	
	@Test
	public void testGetPlayerErrorsLength() {
		assertEquals(readerPlayer.getPlayerErrorsLength(),0);
		
	}
	
	@Test
	public void testGetInstructions() {
		String[] expectedResult = new String[]{"FLFWF" ,"WFWUL","RFWFL" ,"FRBRF"};
		Assert.assertArrayEquals(expectedResult, readerPlayer.getInstructions());
	}


}
