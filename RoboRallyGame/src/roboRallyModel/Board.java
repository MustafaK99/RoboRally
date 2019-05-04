package roboRallyModel;

import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

/**
 * @author 180034882
 *
 */
public class Board {
	private ReaderBoard readerBoard;
	private Tile[][] boardTiles;
	
	/**
	 * @param boardLoc
	 * @throws IOException
	 */
	public Board(String boardLoc) throws IOException {
		readerBoard = new ReaderBoard();
		readerBoard.setBoardLocation(boardLoc);
		this.boardTiles = readerBoard.readBoardTiles();
	}
	
	/**
	 * this returns all the board tiles of an array
	 * @return board tiles
	 */
	public Tile[][] getBoardTiles() {
		return boardTiles;
	}
	
	/**
	 * this function returns the size of the entire board this is useful for creating a board with the correct size
	 * @return board size
	 */
	public int[] getBoardSize() {
		return readerBoard.getBoardSize();
	}
	/**
	 * this returns the number of start tiles on a particular board
	 * @return number of starts
	 */
	public int getNoOfStarts() {
		return readerBoard.getNoOfStarts();
	}
	/**
	 * returns the number of flags available on a board
	 * @return flag numbers
	 */
	public int getNoOfFlags() {
		return readerBoard.getNoOfFlags();
	}
	
	/**
	 * this updates the state of the board in real-time as each action is executed
	 * @param players
	 * @param boardGrid
	 * @param bTiles
	 * @throws IOException
	 */
	public void updateBoard(Player[] players, GridPane boardGrid, Tile[][] bTiles) throws IOException {
		for(int currentPlayer = 0; currentPlayer < players.length; currentPlayer++) {
			players[currentPlayer].setNoOfFlags(readerBoard.getNoOfFlags());
			
			int tileBLength = bTiles.length + bTiles[0].length;
			int currentPlayerX = players[currentPlayer].getPlayerRobot().getRobotX();
			int currentPlayerY = players[currentPlayer].getPlayerRobot().getRobotY();


			for(int i = 0; i < tileBLength; i++) {
				bTiles[currentPlayerY][currentPlayerX].tileBehaviour(players[currentPlayer], bTiles);
				
				currentPlayerX = players[currentPlayer].getPlayerRobot().getRobotX();
				currentPlayerY = players[currentPlayer].getPlayerRobot().getRobotY();

				switch(bTiles[currentPlayerY][currentPlayerX].getTileType()) {
					case 'n': case 'N': case 'e': case 'E': case 's': case 'S': case 'w': case 'W':
						break;
					default:
						players[currentPlayer].getPlayerRobot().setGotOffSCBelt(false);
						break;
				}
			}

			if(players[currentPlayer].getInstructionCount() < players[currentPlayer].getCurrentRoundLength()) {
				players[currentPlayer].getPlayerRobot().move(players, players[currentPlayer], bTiles);
				players[currentPlayer].incrementInstructionCount();
			}
			
			if (players[currentPlayer].getInstructionCount() == players[currentPlayer].getCurrentRoundLength()){
				if(players[currentPlayer].getCurrentRound() < players[currentPlayer].getRounds()-1) {
					players[currentPlayer].setCurrentRound(players[currentPlayer].getCurrentRound()+1);
				}
				else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.initStyle(StageStyle.UNDECORATED);
					alert.getDialogPane().setStyle("-fx-border-color: lightblue; -fx-border-width: 5;");
					alert.setTitle("GAME OVER!");
					alert.setHeaderText(null);
					alert.setContentText("No one has won the game!");
					if (alert.showAndWait().get() == ButtonType.OK) {
						Platform.exit();
				        System.exit(0);
					}
				}

				players[currentPlayer].resetInstructionCount();
			}
		}
	}

	/**
	 * this updates all the player information displayed on the board
	 * @param players
	 * @param playersList
	 */
	public void updatePlayersList(Player[] players, ListView<String> playersList) {
		 Image[] listOfImages = new Image[players.length];
		 
		 for(int i = 0; i < players.length; i++) {
			 listOfImages[i] = new Image("file:"+players[i].getPlayerRobot().getImgLoc());
		 }
		
		ObservableList<String> playerNames = FXCollections.<String>observableArrayList();
		
		for(int i = 0; i < players.length; i++) {
			if(players[i].getCurrentRound() < players[i].getRounds()) {
				playerNames.add("Name: "+players[i].getName()+"\nInstructions: "+players[i].getInstruction()[players[i].getCurrentRound()]
						+"\nFlags Collected: "+players[i].getPlayerRobot().getFlagsCollected()+"\nDirection: "+players[i].getPlayerRobot().getRobotDirection());
			}else{
				playerNames.add("Name: "+players[i].getName()+"\nInstructions: "+players[i].getInstruction()[players[i].getRounds()-1]
						+"\nFlags Collected: "+players[i].getPlayerRobot().getFlagsCollected()+"\nDirection: "+players[i].getPlayerRobot().getRobotDirection());
			}
		}
		
		playersList.setItems(playerNames);
		
		playersList.setCellFactory(param -> new ListCell<String>() {
        private ImageView imageView = new ImageView();
        @Override
        public void updateItem(String name, boolean empty) {
            super.updateItem(name, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
            	for(int i = 0; i < players.length; i++) {
            		if(players[i].getCurrentRound() < players[i].getRounds()) {
	                    if(name.equals("Name: " + players[i].getName()+"\nInstructions: "+players[i].getInstruction()[players[i].getCurrentRound()]
	                    		+"\nFlags Collected: "+players[i].getPlayerRobot().getFlagsCollected()+"\nDirection: "+players[i].getPlayerRobot().getRobotDirection())) {
		                	imageView.setImage(listOfImages[i]);
		                    imageView.setFitHeight(70);
		                    imageView.setPreserveRatio(true);
		                	setText(name);
		                	setGraphic(imageView);
		            	}
            		}else{
	                    if(name.equals("Name: " + players[i].getName()+"\nInstructions: "+players[i].getInstruction()[players[i].getRounds()-1]
	                    		+"\nFlags Collected: "+players[i].getPlayerRobot().getFlagsCollected()+"\nDirection: "+players[i].getPlayerRobot().getRobotDirection())) {
		                	imageView.setImage(listOfImages[i]);
		                    imageView.setFitHeight(70);
		                    imageView.setPreserveRatio(true);
		                	setText(name);
		                	setGraphic(imageView);
		            	}
            		}
            	}
            }
        }
    });
	}
	
	/**
	 * this updates the instructions for each player as they are displayed on the board
	 * @param players
	 * @param instructionsList
	 */
	public void updateInstructionsList(Player[] players, GridPane instructionsList) {
		Image[] listOfImages = new Image[players.length];
		Label[] playerNames = new Label[players.length];
		 for(int i = 0; i < players.length; i++) {
				 switch(players[i].getCurrentInstruction(players[i].getCurrentRound())) {
				 case "F":
					 listOfImages[i] = new Image("file:.\\Assets\\images\\actions\\f.png");
					 break;
				 case "B":
					 listOfImages[i] = new Image("file:.\\Assets\\images\\actions\\b.png");
					 break;
				 case "L":
					 listOfImages[i] = new Image("file:.\\Assets\\images\\actions\\l.png");
					 break;
				 case "R":
					 listOfImages[i] = new Image("file:.\\Assets\\images\\actions\\r.png");
					 break;
				 case "U":
					 listOfImages[i] = new Image("file:.\\Assets\\images\\actions\\u.png");
				 break;
				 case "W":
					 listOfImages[i] = new Image("file:.\\Assets\\images\\actions\\w.png");
					 break;
				 }
			 
			 playerNames[i] = new Label(players[i].getName());
			 playerNames[i].setMaxWidth(Double.MAX_VALUE);
			 playerNames[i].setAlignment(Pos.TOP_CENTER);
			 playerNames[i].setPadding(new Insets(-63,0,0,0));
			 playerNames[i].setTextFill(javafx.scene.paint.Color.WHITE);
 
			 ImageView img = new ImageView(listOfImages[i]);
			 img.setFitHeight(150);
			 img.setPreserveRatio(true);
			 
			 instructionsList.add(img, i, 0);
			 instructionsList.add(playerNames[i], i, 0);
		 }
	}
	
	/**
	 * this returns any errors within a board file length
	 * @return  board error array length
	 */
	public int getBoardErrorsLength() {
		return readerBoard.getBoardErrorsLength();
	}
	
	/**
	 * all the errors within a particular board file
	 * @return board errors string
	 */
	public String getBoardErrors() {
		return readerBoard.getBoardErrors();
	}
}
