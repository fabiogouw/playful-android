package android.playful.GameElements;

public class QuestionNode extends Node {
	private String _question;
	private Node _yesNode;
	private Node _noNode;
	
	public QuestionNode(String question, Node yesNode, Node noNode){
		_question = question;
		_yesNode = yesNode;
		_noNode = noNode;
	}
	
	public String getQuestion(){
		return _question;
	}
	
	public Node getYesNode(){
		return _yesNode;
	}
	
	public void setYesNode(Node yesNode){
		_yesNode = yesNode;
	}
	
	public Node getNoNode(){
		return _noNode;
	}
	
	public void setNoNode(Node noNode){
		_noNode = noNode;
	}
}
