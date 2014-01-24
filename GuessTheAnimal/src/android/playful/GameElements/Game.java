package android.playful.GameElements;

public class Game {
	private static Game _instance;
	private Node _rootQuestion;
	private Node _currentNode;
	private QuestionNode _previousQuestionNode;
	private Boolean _previousAnswer;
	private Boolean _playingGame = false;
	private int _questionNumber = 1;
	
	private Game(){
		_rootQuestion = new QuestionNode("Is it a mammal?",
				new QuestionNode("Is it big?", 
						new QuestionNode("Does it live only in Afrika and Asia?", 
								new QuestionNode("Does it have a long nose that's used to help it drinking?", new AnimalNode("Elephant"), new AnimalNode("Rhinoceros")), 
								new QuestionNode("Do people use them as a mode of transport?", new AnimalNode("Horse"), new AnimalNode("Cow"))), 
						new QuestionNode("Does it fly?", 
								new QuestionNode("Does it drink blood?", new AnimalNode("Bat"), new AnimalNode("Flying squirrel")), 
								new QuestionNode("Does it like cheese?", new AnimalNode("Mouse"), new AnimalNode("Rabbit")))), 
				new QuestionNode("Does it have wings?", 
						new QuestionNode("Does it have a beak?", 
								new QuestionNode("Do people tame it as a sport?", new AnimalNode("Falcon"), new AnimalNode("Crow")), 
								new QuestionNode("Does it eat a lot of leaves, ruining the plantations?", new AnimalNode("Grasshooper"), new AnimalNode("Lady Bug"))), 
						new QuestionNode("Does it live in the water?", 
								new QuestionNode("Do people fear it?", new AnimalNode("White Shark"), new AnimalNode("Clown Fish")), 
								new QuestionNode("Does it have legs?", new AnimalNode("Gila Monster"), new AnimalNode("Rattlesnake")))));
		reset();
	}
	
	public static Game GetInstance(){
		if(_instance == null)
			_instance = new Game();
		return _instance;
	}
	
	public void reset(){
		_currentNode = _rootQuestion;
		_previousQuestionNode = null;
		_playingGame = true;
		_questionNumber = 1;
	}
	
	public Boolean IsAAnswerAvailable(){
		return _currentNode instanceof AnimalNode;
	}
	
	public String getCurrentQuestion(){
		if(!IsAAnswerAvailable()){
			QuestionNode questionNode = (QuestionNode)_currentNode;
			return String.format("Question # %d %s", _questionNumber, questionNode.getQuestion());
		}
		return "";
	}
	
	public String getAnimalName(){
		if(IsAAnswerAvailable()){
			AnimalNode animalNode = (AnimalNode)_currentNode;
			return animalNode.getName();
		}
		return "";
	}
	
	public void AnswerQuestion(Boolean answer){
		if(_playingGame){
			if(!IsAAnswerAvailable()){
				_questionNumber++;
				_previousAnswer = answer;
				_previousQuestionNode = (QuestionNode)_currentNode;
				_currentNode = answer ? _previousQuestionNode.getYesNode() : _previousQuestionNode.getNoNode();
			}
		}
	}
	
	public void addNewQuestion(String newQuestion, String newAnimal){
		if(_playingGame){
			if(IsAAnswerAvailable()){
				Node oldAnimalNode = _previousAnswer ? _previousQuestionNode.getYesNode() : _previousQuestionNode.getNoNode();
				AnimalNode newAnimalNode = new AnimalNode(newAnimal);
				QuestionNode newQuestionNode = new QuestionNode(newQuestion, newAnimalNode, oldAnimalNode);
				if(_previousAnswer)
					_previousQuestionNode.setYesNode(newQuestionNode);
				else
					_previousQuestionNode.setNoNode(newQuestionNode);
				_playingGame = false;
			}
		}
	}
	
}
