package assignment3;

public class wordNode {
	
	private wordNode prev;
	private int marker;
	private String word;
	
	public wordNode(String word) {
		
		this.prev = null;
		this.word = word;
		this.marker = 0;
	}
	
	public wordNode getPrev(){
		
		return prev;
	}
	
	public String getWord(){
		
		return word;
	}
	
	public int getMarker(){
		
		return marker;
	}
	
	public void setWord(String word){
		
		this.word = word;
	}
	
	public void setMarker(int marker){
		
		this.marker = marker;
	}
	
	public void setPrev(wordNode prev){
		
		this.prev = prev;
	}
	
	@Override
	public boolean equals(Object otherObject){
		
		wordNode otherNode = (wordNode) otherObject;
		
		if (otherNode == this){ //for the base case
			return true;
		}
		if (this.getWord().equals(otherNode.getWord())){
			return true;
		}
		return false;
		
	}
}