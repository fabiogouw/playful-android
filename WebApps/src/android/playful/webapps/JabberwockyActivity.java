package android.playful.webapps;

import java.io.IOException;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;

public class JabberwockyActivity extends Activity {

	private WebView _webView;
	private MediaPlayer _player;
	private boolean _showingImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jabberwocky);
		_webView = (WebView)findViewById(R.id.webView1);
		_player = MediaPlayer.create(this, R.raw.creepy);
		_webView.getSettings().setBuiltInZoomControls(true);
		_webView.loadUrl("file:///android_asset/jabberwocky.html");
		_showingImage = false;
		_player.setLooping(true);
		_player.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jabberwocky, menu);
		return true;
	}
	
	@Override
	public void onPause(){
		super.onPause();
		if(_player.isPlaying()){
			_player.pause();
		}
	}
	
	@Override
	public void onResume(){
		super.onPause();
		if(!_player.isPlaying()){
			_player.start();
		}
	}
	
	public void openWiki(View v) {
		String url = "http://en.m.wikipedia.org/wiki/Jabberwocky"; 
	    Intent i = new Intent(Intent.ACTION_VIEW); 
	    i.setData(Uri.parse(url)); 
	    startActivity(i); 
	}
	
	public void changeImage(View v) {
		if(_showingImage) {
			_webView.loadUrl("file:///android_asset/jabberwocky.html");
			_showingImage = false;
		}
		else {
			_webView.loadUrl("file:///android_asset/jabberwocky.jpg");
			_showingImage = true;
		}
	}
}
