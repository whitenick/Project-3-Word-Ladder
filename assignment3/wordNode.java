package assignment3;

public class wordNode {
	
	private int marker;
	private String word;
	
	public wordNode(String word) {
		
		this.word = word;
		this.marker = 0;
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
	
}