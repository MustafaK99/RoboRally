package roboRallyModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StartTest {
       private Start startA;
       private Start startB;
       private Start startC;
       private Start startD;
	@Before
	public void setUp() throws Exception {
		this.startA = new Start();
		this.startB =  new Start();
		this.startC = new Start();
		this.startD =  new Start();
		
		startA.setTileForm('A');
		startB.setTileForm('B');
		startC.setTileForm('C');
		startD.setTileForm('D');
		
	}

	@Test
	public void getTileType() {
	
		assertEquals(startA.getTileType(),'A');
		assertEquals(startB.getTileType(),'B');
		assertEquals(startC.getTileType(),'C');
		assertEquals(startD.getTileType(),'D');
		
	}
	
	@Test
	public void getTileImage() {
		
		assertEquals(startA.getTileImage(),".\\Assets\\images\\tiles\\a.jpg");
		assertEquals(startB.getTileImage(),".\\Assets\\images\\tiles\\b.jpg");
        assertEquals(startC.getTileImage(),".\\Assets\\images\\tiles\\c.jpg");
        assertEquals(startD.getTileImage(),".\\Assets\\images\\tiles\\d.jpg");
		
	}
	
}
