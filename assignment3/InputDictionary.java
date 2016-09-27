package assignment3;
import java.util.*;

public class InputDictionary {
	private ArrayList<String> inputString;
	InputDictionary(){
		this.inputString = new ArrayList<String>();
	}
	InputDictionary(ArrayList<String> userInput){
		this.inputString = userInput;
	}
	
	public static ArrayList<String> initializeNodes (ArrayList<String> inputString, Scanner attachScan) {
		while(attachScan.hasNextLine()) {
			inputString.add(attachScan.next());
			
			
		}
		return inputString;
	}
	
}
