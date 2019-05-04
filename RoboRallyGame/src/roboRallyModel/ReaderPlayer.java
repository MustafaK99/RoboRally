package roboRallyModel;
import java.util.ArrayList;
//import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

 /**
 * @author 180034882
 *
 */
public class ReaderPlayer {
    private  ArrayList<String> playerDetails;
    private String[] nameArray;
    private String[] instruction;
	private ArrayList<String> playerErrors;
    
    /**
     * reads in a file type and then adds it to an array which contains all the details of a file
     * if the file does not exist an error is displayed using the player errors
     * @param fName
     */
    public ReaderPlayer(String fName){
    	this.playerErrors = new ArrayList<>(); 
    	
    	 this.playerDetails = new ArrayList<String>();
         try (BufferedReader br = new BufferedReader(new FileReader(fName)))
         {
             String sCurrentLine;
             while ((sCurrentLine = br.readLine()) != null) {
            	 sCurrentLine.split("\\s+");
            
            	 playerDetails.add(sCurrentLine);
             }
             
             validationCheck();

         } catch (IOException e) {
        	 playerErrors.add("The .prg file does not exist!");
         }
    }
           
    /**
     * this is a method that returns the names of all the players within a particular program file providing there are no errors within validation
     * @return names array
     */
    public String [] getNames() {
    
             if(playerErrors.size() == 0) {     
    		String name = playerDetails.get(1);
    	    this.nameArray = name.split("\\s+");   
    	    
    		return this.nameArray;
    		
    		}
             return null;
    }
    
    /**
     * this method is used to separate all the instructions within the player details array
     * @return  all instructions in a file
     */
    public String[] getInstructions() {
	    String instructions = "";
	    for(int i = 2 ; i < playerDetails.size(); i ++) {
	    	if(i == 2) {
	    	instructions  = playerDetails.get(i);
	    	}
	    	else {
	    	instructions = instructions + " " + playerDetails.get(i);
	    		
	    	}
	    }
	    this.instruction = instructions.split("\\s+");
	    return instruction;
     }
    
    /**
     * this is the method used by the main class when the game is set up this returns a multidimensional array in which
     * each row is a round and each column is a player this sorts the instructions correctly making sure each player is assigned the correct
     * instructions for each round
     * 
     * @return instructions organized by player and round
     */
    public String[][] getInstructionsArray() {
    	if(getPlayerErrorsLength() == 0) {
        	getInstructions();
			int columns = nameArray.length;
			int rows = playerDetails.size() - 2;
			
			String[][] InstrucArray = new String [rows][columns];
			int k = 0;
			for(int i = 0; i < rows; i++){
		        for(int j = 0; j < columns; j++){
		        	InstrucArray[i][j] = instruction[k];
		            k++;
		        }
			}
			return InstrucArray;
    	}
    	return null;
    }
    
    
    /**
     * this function validates each player file against a set of pre-defined parameters and then if any of the validations fails
     * this code will add player error strings to an ArrayList which will then display a dialog box to the user however if the ArrayList
     * is empty this means there are no errors and thus the program is free to continue 
     */
    public void validationCheck() {
    	if(playerDetails.size() <= 1) {
  		  playerErrors.add("The .prg file is empty!");
  		    return;
  	       }
     
    	 String formatType = playerDetails.get(0);
    	  if(!(formatType.equalsIgnoreCase("format 1"))) {
    		  playerErrors.add("The .prg file is in the wrong format!");
    	  }
    	  
    	  
    	  String names = playerDetails.get(1);
    	  String[] testNameArray = names.split("\\s+");
    	  getInstructions();
    	  
    	  if(instruction.length%testNameArray.length != 0 ) {
    		  playerErrors.add("The .prg contains mismatched names/instructions!");
    	  }
    	  else {
    		  for(int i = 0; i < instruction.length;i++) {
    			  String[] instrucTest = instruction[i].split("(?!^)");
    			  
    			  if(instrucTest.length != 5) {
    				  playerErrors.add("The .prg file does not contain the correct number of instructions!");
    				  break;
    			  }
		    	
    			  for(int j = 0; j < instrucTest.length-1;j++) {
    				  if(instrucTest[j].equals(instrucTest[j+1])) {
    					  playerErrors.add("The .prg file contains repeated commands!");
    					  break;
    				  }
    			  } 	
    		  }  
    	  }
    }
    
	/**
	 * this returns the length of the player error array 
	 * if this is greater than 0 then the program will not continue beyond the file selection phase
	 * @return  player errors array length
	 */
	public int getPlayerErrorsLength() {
		return playerErrors.size();
	}
    
	/**
	 * this string essentially allows the player error array to be returned in a suitable format
	 * should any errors arise this is then displayed within a dialog box showing the user what errors they
	 * have and what action to take.
	
	 * @return  String of player errors
	 */
	public String getPlayerErrors() {
		String errors = "";
		
		if(getPlayerErrorsLength() > 0) {
			errors = "Error 1: " + playerErrors.get(0);
			
			for(int i = 1; i < getPlayerErrorsLength(); i++) {
				errors += "\nError "+(i+1)+": "+playerErrors.get(i);
			}
		}
		
		return errors;
	}
}