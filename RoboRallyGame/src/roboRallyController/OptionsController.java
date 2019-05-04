package roboRallyController;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import roboRallyModel.Board;
import roboRallyModel.Player;
import roboRallyModel.ReaderPlayer;

/**
 * @author Umar0
 *
 */
public class OptionsController {
	private ReaderPlayer readerPlayer;
	private Player[] players;
	private int rounds;
	private int numOfPlayers;
	private Board board;
	
	
	/**
	 * @param boardLoc
	 * @param playerImg
	 */
	public OptionsController() {

	}
	
	@FXML TextField txtPrgFile;
	@FXML TextField txtBrdFile;
	@FXML AnchorPane rootPane;
	
	@FXML
	public void initialize() {
		BackgroundImage myBI= new BackgroundImage(new Image("file:.\\Assets\\images\\bg_brickwall.jpg",Region.USE_COMPUTED_SIZE,Region.USE_COMPUTED_SIZE,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		rootPane.setBackground(new Background(myBI));
	}
	
	/**
	 * @throws IOException
	 */
	@FXML
	private void playPressed() throws IOException {
		if(validation()) {			
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("BoardScene.fxml"));
			final BoardController controller = new BoardController(board, players);
			loader.setController(controller);
			try {
				final Parent parent = (Parent) loader.load();
				final Stage boardStage = new Stage();
				boardStage.setTitle("RoboRally");
				boardStage.initModality(Modality.APPLICATION_MODAL);
				boardStage.setScene(new Scene(parent, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
				boardStage.setResizable(false);	
				boardStage.show();
				controller.playersList.setFixedCellSize(controller.playersList.getHeight()/controller.players.length-1);
				controller.btnNext.setPrefHeight(controller.instructionsList.getHeight());
			} catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * @return
	 * @throws IOException
	 */
	private boolean validation() throws IOException {
		gameSetup();
		if(board.getBoardErrorsLength() == 0 && readerPlayer.getPlayerErrorsLength() == 0) {
			if(txtBrdFile.getText().length() == 0 || txtPrgFile.getText().length() == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Input Errors!");
				alert.setHeaderText(null);
				alert.setContentText("Please provide both a .prg and .brd file location.");
				alert.showAndWait();
			}
			else {
				if(txtBrdFile.getText().contains(".brd") && txtPrgFile.getText().contains(".prg")) {
					if(board.getNoOfStarts() == players.length) {
						return true;
					}
					else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Input Errors!");
						alert.setHeaderText(null);
						alert.setContentText("Please provide a .prg and a .brd file with matching number of players and start positions.");
						alert.showAndWait();
					}
				}
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Input Errors!");
					alert.setHeaderText(null);
					alert.setContentText("Please provide files with a .prg and .brd extension.");
					alert.showAndWait();
				}
			}
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Reading Errors!");
			alert.setHeaderText(null);
			alert.setContentText("Board File Errors:\n"+board.getBoardErrors()+"\n\nPlayer File Errors:\n"+readerPlayer.getPlayerErrors());
			alert.showAndWait();
		}
		
		return false;
	}
	
	@FXML
	private void prgFilePressed() {
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(null);
		
		if(selectedFile != null) {
			txtPrgFile.setText(selectedFile.getAbsolutePath());
		}
	}
	
	@FXML
	private void brdFilePressed() {
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(null);
		
		if(selectedFile != null) {
			txtBrdFile.setText(selectedFile.getAbsolutePath());
		}
	}
	
	/**
	 * @throws IOException
	 */
	private void gameSetup() throws IOException {
		this.readerPlayer = new ReaderPlayer(txtPrgFile.getText());
		this.board = new Board(txtBrdFile.getText());
		
		if(board.getBoardErrorsLength() == 0 && readerPlayer.getPlayerErrorsLength() == 0) {
			String[] names = readerPlayer.getNames();
			String[][] instructions = readerPlayer.getInstructionsArray();
			char[] givenStartPos = new char[] {'A', 'B', 'C', 'D'};
			numOfPlayers = names.length;
			this.rounds = instructions.length;
			players = new Player[names.length];
	
			String instructs = "";
			for(int i = 0; i < numOfPlayers; i++){
		        for(int j = 0; j < rounds; j++){
		        	if(j < rounds-1) {
		        		instructs += instructions[j][i] + "+";
		        	}
		        	else {
		        		instructs += instructions[j][i];
		        	}
	
		        }
	
		        players[i] = new Player(names[i], instructs, i);
		        players[i].setNoOfStarts(board.getNoOfStarts());
		        players[i].getPlayerRobot().setStartPos(players[i].getNoOfStarts(), givenStartPos[i], board.getBoardTiles());
				instructs = "";
		    }
		}
  }

}
