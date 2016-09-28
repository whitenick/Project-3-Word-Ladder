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
	private static HashMap<wordNode, Integer> getIndex;
	private static String startWord;
	private static String endWord;
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
		ArrayList<String> words = parse(kb);
		if (words == null){
			System.out.println("You messed up.");
			return ;
		}
		else if (words.size() == 0){
			System.out.println("Quitting...");
			return ;
		}
		long begin =System.nanoTime(); //use for time
		printLadder(getWordLadderDFS(startWord, endWord));
		long end = System.nanoTime();
		System.out.println("Time taken: " + (end - begin)/1000000 + " ms");
	}
	
	public static void initialize() {
		
		nodeArr = new ArrayList<wordNode>();
		getIndex = new HashMap<wordNode, Integer>();
		Set<String> dict = makeDictionary();
		for (String str: dict){
			wordNode newNode = new wordNode(str);
			nodeArr.add(newNode);
		}
		for (int i = 0; i < nodeArr.size(); i++) {
			getIndex.put(nodeArr.get(i), i);
		}
		makeGraph();
	}

	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		
		String input;
		input = keyboard.nextLine();
		if (input.equals("quit")){
			return new ArrayList<String>();
		}
		ArrayList<String> inputArr = new ArrayList<String>(Arrays.asList(input.split(" ")));
		if (inputArr.size() == 2){
			startWord = inputArr.get(0);
			endWord = inputArr.get(1);
			return inputArr;
		}
		return null;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		// TODO more code
		
		int startIndex, endIndex, currentIndex;
		ArrayList<String> wordLadder = new ArrayList<String>();
		startIndex = 0;
		endIndex = 0;
		
		for(int i = 0; i < nodeArr.size(); i++) {
			if(nodeArr.get(i).getWord().toLowerCase().equals(start)){
				startIndex = i;
			}
			if(nodeArr.get(i).getWord().toLowerCase().equals(end)) {
				endIndex = i;
			}
		}
		if (startIndex == endIndex){ //special case
			wordLadder.add(nodeArr.get(startIndex).getWord());
			wordLadder.add(nodeArr.get(endIndex).getWord());
			return wordLadder;
		}
		
		DFSVisit(nodeArr.get(startIndex), nodeArr.get(endIndex));
		currentIndex = endIndex;
		
		while(currentIndex != startIndex){
				wordLadder.add(nodeArr.get(currentIndex).getWord());
				if (nodeArr.get(currentIndex).getPrev() == null){
					break;
				}
				currentIndex = getIndex.get(nodeArr.get(currentIndex).getPrev());
		}
		
		if (currentIndex != startIndex){ //if a link doesn't exist between start and end
			return new ArrayList<String>();
		}
		wordLadder.add(nodeArr.get(startIndex).getWord());
		Collections.reverse(wordLadder);
		return wordLadder;
	}
		
	public static void DFSVisit(wordNode start, wordNode end){
		
		if (start.equals(end)){
			end.setMarker(2);
		}
		else{
			//TODO: sort graph.get(size) based on closeness to end
			start.setMarker(1); //start node is visited
			for (int i = 0; i < graph.get(start).size(); i++){ //Exploring unvisited neighbors
				if (graph.get(start).get(i).getMarker() == 0){
					graph.get(start).get(i).setPrev(start);
					DFSVisit(graph.get(start).get(i), end);
				}
			}
			start.setMarker(2); //start node is explored
		}
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
	
	public static void printLadder(ArrayList<String> ladder) {
		
		if (ladder.size() == 0){
			System.out.println("no word ladder can be found between " + startWord + " and " + endWord + ".");
			return ;
		}
		else{
			System.out.println("a " + (ladder.size() - 2) + "-rung word ladder exists between " + startWord + " and " + endWord + ".");
			for (int i = 0; i < ladder.size(); i++){
				System.out.println(ladder.get(i).toLowerCase());
			}
		}
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
	
	private static void makeGraph(){
		
		graph = new HashMap<wordNode, ArrayList<wordNode>>();
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
	
}
