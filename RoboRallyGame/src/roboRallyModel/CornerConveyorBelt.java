package roboRallyModel;

import java.io.IOException;

/**
 * @author Umar0
 *
 */
public class CornerConveyorBelt extends Tile{

	/* (non-Javadoc)
	 * @see roboRallyModel.Tile#setTileForm(char)
	 */
	@Override
	public void setTileForm(char form) {
		switch(form) {
		case 'n':
			setTileImage(".\\Assets\\images\\tiles\\nACW.jpg");
			break;
		case 'N':
			setTileImage(".\\Assets\\images\\tiles\\nCW.jpg");
			break;
		case 'e':
			setTileImage(".\\Assets\\images\\tiles\\eACW.jpg");
			break;
		case 'E':
			setTileImage(".\\Assets\\images\\tiles\\eCW.jpg");
			break;
		case 's':
			setTileImage(".\\Assets\\images\\tiles\\sACW.jpg");
			break;
		case 'S':
			setTileImage(".\\Assets\\images\\tiles\\sCW.jpg");
			break;
		case 'w':
			setTileImage(".\\Assets\\images\\tiles\\wACW.jpg");
			break;
		case 'W':
			setTileImage(".\\Assets\\images\\tiles\\wCW.jpg");
			break;
		}
	setTileType(form);
	}

	/* (non-Javadoc)
	 * @see roboRallyModel.Tile#tileBehaviour(roboRallyModel.Player, roboRallyModel.Tile[][])
	 */
	@Override
	public void tileBehaviour(Player currentPlayer, Tile[][] bTiles) throws IOException {
		if(currentPlayer.getPlayerRobot().getGotOffSCBelt()) {
			ReaderBoard rb = new ReaderBoard();
			
			int currentPlayerX = currentPlayer.getPlayerRobot().getRobotX();
			int currentPlayerY = currentPlayer.getPlayerRobot().getRobotY();
			
			Tile tile = rb.checkTileType(bTiles[currentPlayerY][currentPlayerX].getTileType());
			
			switch(tile.getTileType()) {
			case 'n':
				if(currentPlayerY != 0) {
					currentPlayer.getPlayerRobot().turnLeft();
					currentPlayer.getPlayerRobot().setRobotY(currentPlayerY-1);
				}
				else {
					currentPlayer.getPlayerRobot().moveRobotToStart(currentPlayer, bTiles);
				}
				break;
			case 'N':
				if(currentPlayerY != 0) {
					currentPlayer.getPlayerRobot().turnRight();
					currentPlayer.getPlayerRobot().setRobotY(currentPlayerY-1);
				}
				else {
					currentPlayer.getPlayerRobot().moveRobotToStart(currentPlayer, bTiles);
				}
				break;
			case 'e':
				if(currentPlayerX != bTiles[0].length-1) {
					currentPlayer.getPlayerRobot().turnLeft();
					currentPlayer.getPlayerRobot().setRobotX(currentPlayerX+1);
				}
				else {
					currentPlayer.getPlayerRobot().moveRobotToStart(currentPlayer, bTiles);
				}
				break;
			case 'E':
				if(currentPlayerX != bTiles[0].length-1) {
					currentPlayer.getPlayerRobot().turnRight();
					currentPlayer.getPlayerRobot().setRobotX(currentPlayerX+1);
				}
				else {
					currentPlayer.getPlayerRobot().moveRobotToStart(currentPlayer, bTiles);
				}
				break;
			case 's':
				if(currentPlayerY != bTiles.length-1) {
					currentPlayer.getPlayerRobot().turnLeft();
					currentPlayer.getPlayerRobot().setRobotY(currentPlayerY+1);
				}
				else {
					currentPlayer.getPlayerRobot().moveRobotToStart(currentPlayer, bTiles);
				}
				break;
			case 'S':
				if(currentPlayerY != bTiles.length-1) {
					currentPlayer.getPlayerRobot().turnRight();
					currentPlayer.getPlayerRobot().setRobotY(currentPlayerY+1);
				}
				else {
					currentPlayer.getPlayerRobot().moveRobotToStart(currentPlayer, bTiles);
				}
				break;
			case 'w':
				if(currentPlayerX != 0) {
					currentPlayer.getPlayerRobot().turnLeft();
					currentPlayer.getPlayerRobot().setRobotX(currentPlayerX-1);
				}
				else {
					currentPlayer.getPlayerRobot().moveRobotToStart(currentPlayer, bTiles);
				}
				break;
			case 'W':
				if(currentPlayerX != 0) {
					currentPlayer.getPlayerRobot().turnRight();
					currentPlayer.getPlayerRobot().setRobotX(currentPlayerX-1);
				}
				else {
					currentPlayer.getPlayerRobot().moveRobotToStart(currentPlayer, bTiles);
				}
				break;
			}
			
			bTiles[currentPlayerY][currentPlayerX] = new CornerConveyorBelt();
			bTiles[currentPlayerY][currentPlayerX].setTileForm(tile.getTileType());
		}
	}
}
