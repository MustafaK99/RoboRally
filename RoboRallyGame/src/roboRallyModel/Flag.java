package roboRallyModel;

import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

/**
 * @author 180034882
 *
 */
public class Flag extends Tile{
	private ReaderBoard rb;
	
	/* (non-Javadoc)
	 * @see roboRallyModel.Tile#setTileForm(java.lang.String)
	 */
	@Override
	public void setTileForm(char form) {
		setTileImage(".\\Assets\\images\\tiles\\flag" + form + ".jpg");
		setTileType(form);
	}

	/* (non-Javadoc)
	 * @see roboRallyModel.Tile#tileBehaviour(int[], javafx.scene.layout.GridPane)
	 */
	@Override
	public void tileBehaviour(Player currentPlayer, Tile[][] bTiles) throws IOException {
		rb = new ReaderBoard();
		int currentPlayerX = currentPlayer.getPlayerRobot().getRobotX();
		int currentPlayerY = currentPlayer.getPlayerRobot().getRobotY();
		Tile tile = rb.checkTileType(bTiles[currentPlayerY][currentPlayerX].getTileType());
	
		if (currentPlayer.getNoOfFlags() == 1) {
			youWin(currentPlayer);
		}else if(currentPlayer.getNoOfFlags() == 2) {
			switch(tile.getTileType()) {
			case '1':
				if(currentPlayer.getPlayerRobot().getFlagsCollected() == 0) {
					currentPlayer.getPlayerRobot().collectFlag();
				}
				break;
			case '2':
				if(currentPlayer.getPlayerRobot().getFlagsCollected() == 1) {
					currentPlayer.getPlayerRobot().collectFlag();
					youWin(currentPlayer);
				}
				break;
			}
		}else if(currentPlayer.getNoOfFlags() == 3) {
			switch(tile.getTileType()) {
			case '1':
				if(currentPlayer.getPlayerRobot().getFlagsCollected() == 0) {
					currentPlayer.getPlayerRobot().collectFlag();
				}
				break;
			case '2':
				if(currentPlayer.getPlayerRobot().getFlagsCollected() == 1) {
					currentPlayer.getPlayerRobot().collectFlag();
				}
				break;
			case '3':
				if(currentPlayer.getPlayerRobot().getFlagsCollected() == 2) {
					currentPlayer.getPlayerRobot().collectFlag();
					youWin(currentPlayer);
				}
				break;
			}
		}else if(currentPlayer.getNoOfFlags() == 4) {
			switch(tile.getTileType()) {
			case '1':
				if(currentPlayer.getPlayerRobot().getFlagsCollected() == 0) {
					currentPlayer.getPlayerRobot().collectFlag();
				}
				break;
			case '2':
				if(currentPlayer.getPlayerRobot().getFlagsCollected() == 1) {
					currentPlayer.getPlayerRobot().collectFlag();
				}
				break;
			case '3':
				if(currentPlayer.getPlayerRobot().getFlagsCollected() == 2) {
					currentPlayer.getPlayerRobot().collectFlag();
				}
				break;
			case '4':
				if(currentPlayer.getPlayerRobot().getFlagsCollected() == 3) {
					currentPlayer.getPlayerRobot().collectFlag();
					youWin(currentPlayer);
				}
				break;
			}
		}
	}
	
	/**
	 * this shows a player winning if they collected all flags
	 * @param currentPlayer
	 */
	private void youWin(Player currentPlayer) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initStyle(StageStyle.UNDECORATED);
		alert.getDialogPane().setStyle("-fx-border-color: lightblue; -fx-border-width: 5;");
		alert.setTitle("GAME OVER!");
		alert.setHeaderText(null);
		alert.setContentText(currentPlayer.getName() + " has won the game by collecting all of the flags!");

		if (alert.showAndWait().get() == ButtonType.OK) {
			Platform.exit();
	        System.exit(0);	
		}
	}
}
