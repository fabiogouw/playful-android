package android.playful;

import android.os.Bundle;
import android.playful.GameElements.Game;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class QuestionsActivity extends Activity {
	
	private Game _game;
	private Boolean _gameHasEnded = false;
	private ScrollView _vwEdit;
	private TextView _lblMessage;
	private EditText _txtNewAnimal;
	private EditText _txtNewQuestion;
	private Button _btnYes;
	private Button _btnNo;
	private Button _btnAdd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_game = Game.GetInstance();

		setContentView(R.layout.activity_questions);
		
		loadControlReferences();

		_lblMessage.setText(_game.getCurrentQuestion());
		
		_btnYes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if(_gameHasEnded){
            		resetGame();
            	}
            	else{
            		playGame(true);
            	}
            }
        });
		
		_btnNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if(_gameHasEnded){
            		_vwEdit.setVisibility(View.VISIBLE);
            	}
            	else{
            		playGame(false);
            	}
            }
        });
		
		_btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	String newQuestion = _txtNewQuestion.getText().toString();
            	String newAnimal = _txtNewAnimal.getText().toString();
            	_game.addNewQuestion(newQuestion, newAnimal);
            	resetGame();
            }
        });
	}

	private void loadControlReferences(){
		_vwEdit = (ScrollView) findViewById(R.id.vwEdit);
		_txtNewAnimal = (EditText) findViewById(R.id.txtNewAnimal);
		_txtNewQuestion = (EditText) findViewById(R.id.txtNewQuestion);
		_lblMessage = (TextView) findViewById(R.id.lblMessage);
		_btnYes = (Button) findViewById(R.id.btnYes);
		_btnNo = (Button) findViewById(R.id.btnNo);
		_btnAdd = (Button) findViewById(R.id.btnAdd);
	}
	
	private void resetGame(){
		_vwEdit.setVisibility(View.INVISIBLE);
		_txtNewQuestion.setText("");
		_txtNewAnimal.setText("");
		_gameHasEnded = false;
    	_game.reset();
    	_lblMessage.setText(_game.getCurrentQuestion());
	}
	
	private void playGame(Boolean answer){
		_game.AnswerQuestion(answer);
    	String message = "";
    	if(_game.IsAAnswerAvailable()){
    		message = String.format("So your animal is the %s!", _game.getAnimalName());
    		_gameHasEnded = true;
    	}
    	else
    		message = _game.getCurrentQuestion();
    	_lblMessage.setText(message);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.questions, menu);
		return true;
	}

}
