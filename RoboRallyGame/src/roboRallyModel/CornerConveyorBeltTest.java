package roboRallyModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CornerConveyorBeltTest {
	private CornerConveyorBelt conBeltn;
	private CornerConveyorBelt conBeltN;
	private CornerConveyorBelt conBelte;
	private CornerConveyorBelt conBeltE;
	private CornerConveyorBelt conBelts;
	private CornerConveyorBelt conBeltS;
	private CornerConveyorBelt conBeltw;
	private CornerConveyorBelt conBeltW;
	@Before
	public void setUp() throws Exception {
		
		 this.conBeltn = new CornerConveyorBelt();
		 this.conBeltN = new CornerConveyorBelt();
		 this.conBelte = new CornerConveyorBelt();
		 this.conBeltE = new CornerConveyorBelt();
		 this.conBelts = new CornerConveyorBelt();
		 this.conBeltS = new CornerConveyorBelt();
		 this.conBeltw = new CornerConveyorBelt();
		 this.conBeltW = new CornerConveyorBelt();
		 
		 
		 this.conBeltn.setTileForm('n');
		 this.conBeltN.setTileForm('N');
		 this.conBelte.setTileForm('e');
		 this.conBeltE.setTileForm('E');
		 this.conBelts.setTileForm('s');
		 this.conBeltS.setTileForm('S');
		 this.conBeltw.setTileForm('w');
		 this.conBeltW.setTileForm('W');
		
		
	}

	@Test
	public void testGetTileImage() {
		assertEquals(conBeltn.getTileImage(), ".\\Assets\\images\\tiles\\nACW.jpg");
		assertEquals(conBeltN.getTileImage(), ".\\Assets\\images\\tiles\\nCW.jpg");
		assertEquals(conBelte.getTileImage(), ".\\Assets\\images\\tiles\\eACW.jpg");
		assertEquals(conBeltE.getTileImage(),".\\Assets\\images\\tiles\\eCW.jpg");
		assertEquals(conBelts.getTileImage(), ".\\Assets\\images\\tiles\\sACW.jpg");
		assertEquals(conBeltS.getTileImage(),".\\Assets\\images\\tiles\\sCW.jpg");
		assertEquals(conBeltw.getTileImage(),".\\Assets\\images\\tiles\\wACW.jpg");
		assertEquals(conBeltW.getTileImage(),".\\Assets\\images\\tiles\\wCW.jpg");
	}
	
	@Test
	public void testSetAndGetTileType() {
		conBeltn.setTileType('n');
		assertEquals(conBeltn.getTileType(),'n');
		
		conBeltN.setTileType('N');
		assertEquals(conBeltN.getTileType(),'N');
	
		conBelte.setTileType('e');
		assertEquals(conBelte.getTileType(),'e');
		
		conBeltE.setTileType('E');
		assertEquals(conBeltE.getTileType(),'E');
		
		conBelts.setTileType('s');
		assertEquals(conBelts.getTileType(),'s');
		
		conBeltS.setTileType('S');
		assertEquals(conBeltS.getTileType(),'S');
		
		conBeltw.setTileType('w');
		assertEquals(conBeltw.getTileType(),'w');
		
		conBeltW.setTileType('W');
		assertEquals(conBeltW.getTileType(),'W');
		
	}
	

}
