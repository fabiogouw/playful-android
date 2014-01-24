package android.playful.GameElements;

public class AnimalNode extends Node {
	private String _name;
	
	public AnimalNode(String name){
		_name = name;
	}
	
	public String getName(){
		return _name;
	}
}
