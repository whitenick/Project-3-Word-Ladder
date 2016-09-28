/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Nicholas White
 * Nww295	
 * 16465
 * Gaurav Nagar
 * gn3544
 * 16480
 * Slip days used: <0>
 * Git URL: https://github.com/whitenick/Project-3-Word-Ladder
 * Fall 2016
 */


package assignment3;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
	
	private static ArrayList<wordNode> nodeArr;
	private static HashMap<wordNode, ArrayList<wordNode>> graph;
	// static variables and constants only here.
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		return;
		// TODO methods to read in words, output ladder
	}
	
	private static boolean edgeExists(wordNode wordOne, wordNode wordTwo){
		
		int difference = 0;
		if (wordOne.getWord().length() != wordTwo.getWord().length()){
			return false;
		}
		for (int i = 0; i < wordOne.getWord().length(); i++){
			if (wordOne.getWord().charAt(i) != wordTwo.getWord().charAt(i)){
				difference++;
			}
		}
		if (difference > 1){
			return false;
		}
		return true;
	}
	
	public static void initialize() {
		
		boolean inputSuccess = false;
		
		Set<String> dict = makeDictionary();
		for (String str: dict){
			wordNode newNode = new wordNode(str);
			nodeArr.add(newNode);
		}
		makeGraph();
		
	}
	
	

	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		// TO DO
		return null;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		// TODO more code 
		Set<String> dict = makeDictionary();
		// TODO more code
		
		return null; // replace this line later with real return
	}
	
	public static void DFSVisit(wordNode start, wordNode end){

		
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code
		
		
		return null; // replace this line later with real return
	}
    
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	private static void makeGraph(){
		
		for (int i = 0; i < nodeArr.size(); i++){
			graph.put(nodeArr.get(i), new ArrayList<wordNode>());
		}
		
		for (int i = 0; i < nodeArr.size(); i++){
			for (int j = i; j < nodeArr.size(); j++){
				if (edgeExists(nodeArr.get(i), nodeArr.get(j))){
					graph.get(nodeArr.get(i)).add(nodeArr.get(j));
					graph.get(nodeArr.get(j)).add(nodeArr.get(i));
				}
			}
		}
		
	}
	
	public static void printLadder(ArrayList<String> ladder) {
		
	}
	// TODO
	// Other private static methods here
}
