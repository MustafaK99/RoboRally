package roboRallyController;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import roboRallyModel.Board;
import roboRallyModel.Player;
import roboRallyModel.Tile;

/**
 * @author 180034882
 *
 */
public class BoardController {
	private Board board;
	private int[] boardSize;
	private boolean playersPlaced;
	
	public Player[] players;	
	
	Tile[][] boardTiles;
	
	/**
	 * @param boardLoc
	 * @param playerImg
	 * @throws IOException
	 */
	public BoardController(Board board, Player[] players) throws IOException {
		this.players = players;
		this.board = board;
	}
	
	@FXML private GridPane boardGrid;
	@FXML private AnchorPane rootPane;
	@FXML public ListView<String> playersList;
	@FXML public GridPane instructionsList;
	@FXML private Label lblRound;
	@FXML public Button btnNext;
	
	/**
	 * @throws IOException
	 */
	@FXML
	public void initialize() throws IOException{
		BackgroundImage myBI= new BackgroundImage(new Image("file:.\\Assets\\images\\bg_brickwall.jpg",Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		rootPane.setBackground(new Background(myBI));
		
		playersList.setMouseTransparent(true);
		boardSize = board.getBoardSize();
		boardTiles = board.getBoardTiles();
		playersPlaced = false;
		
		displayBoard();
	}
	
	/**
	 * @throws IOException
	 */
	private void displayBoard() throws IOException {
		if(players[0].getCurrentRound() < players[0].getRounds()) {
			lblRound.setText("Instructions - Round: " + (players[0].getCurrentRound()+1));
		} else {
			lblRound.setText("Instructions - Round: " + (players[0].getCurrentRound()));
		}
		
		board.updatePlayersList(players, playersList);
		board.updateInstructionsList(players, instructionsList);
		
		for(int i = 0; i < boardSize[0]; i++) {
			for(int j = 0; j < boardSize[1]; j++) {			
				FileInputStream imgStream = new FileInputStream(boardTiles[i][j].getTileImage());
				final Image img = new Image(imgStream);
				ImageView imageView = new ImageView(img);
				imageView.setFitHeight(70);
				imageView.setPreserveRatio(true);
				
				boardGrid.add(imageView, j, i);
			}
		}
	}
	
	/**
	 * @throws IOException
	 */
	@FXML
	public void nextPressed() throws IOException {
		if(playersPlaced) {
			board.updateBoard(players, boardGrid, boardTiles);	
			displayBoard();	
			placePlayers();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Place the players!");
			alert.setHeaderText(null);
			alert.setContentText("You have to place the players first!");
			alert.showAndWait().get();
		}
	}
	
	/**
	 * @throws IOException
	 */
	@FXML
	public void placePlayers() throws IOException{
		for(int currentPlayer = 0; currentPlayer < players.length; currentPlayer++) {
			int currentPlayerX = players[currentPlayer].getPlayerRobot().getRobotX();
			int currentPlayerY = players[currentPlayer].getPlayerRobot().getRobotY();
		
			boardTiles[currentPlayerY][currentPlayerX].setTileImage(players[currentPlayer].getPlayerRobot().getImgLoc());
		}
		
		displayBoard();
	}
	
	/**
	 * @throws IOException
	 */
	public void placePlayersPressed() throws IOException{
		if(!playersPlaced) {
			placePlayers();
			playersPlaced = true;
		}
	}
	
	@FXML
	public void menuPressed() {
		boardGrid.getScene().getWindow().hide();
	}

	@FXML
	public void exitPressed() {
		Platform.exit();
        System.exit(0);
	}
}
