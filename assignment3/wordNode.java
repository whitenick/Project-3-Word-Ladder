package assignment3;

public class wordNode {
	int marker = 0;
	String word;
	public wordNode() {
		this.word = null;
		this.marker = 0;
	}
	
	
}

//subclass for initializing the word nodes 
class wordArray extends wordNode {
	
	public wordArray() {
		
	}
	
	public wordArray(String inputWord) {
		super();
		this.word = inputWord;
		
		// TODO Auto-generated constructor stub
	}
	
}