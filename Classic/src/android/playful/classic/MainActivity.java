package android.playful.classic;

import java.io.IOException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;

@SuppressLint("DefaultLocale")
public class MainActivity extends Activity {

	private MediaPlayer[] _players;
	private String[] _composersWikipediaUrls;
	private WebView _webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		_webView = (WebView)findViewById(R.id.webView1);
		_players = new MediaPlayer[] {
				MediaPlayer.create(this, R.raw.beethoven_concerto_5),
				MediaPlayer.create(this, R.raw.bach_prelude_1st_suite_for_cello_in_g_major),
				MediaPlayer.create(this, R.raw.mozart_eine_kleine_nachtmusik),
				MediaPlayer.create(this, R.raw.pachelbel_canon_d)
		};
		_composersWikipediaUrls = new String[]{
				"http://en.m.wikipedia.org/wiki/Beethoven",
				"http://en.m.wikipedia.org/wiki/Bach",
				"http://en.m.wikipedia.org/wiki/Wolfgang_Amadeus_Mozart",
				"http://en.m.wikipedia.org/wiki/Pachelbel"
		};
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if((keyCode == KeyEvent.KEYCODE_BACK) && _webView.canGoBack()){
			_webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void onStop(){
		super.onStop();
		pauseAll();
	}
	
	@Override
	public void onPause(){
		super.onPause();
		pauseAll();
	}
	
	public void playBeethoven(View v) throws IOException {
		play(0);
	}
	
	public void playBach(View v) throws IOException {
		play(1);
	}
	
	public void playMozart(View v) throws IOException {
		play(2);
	}
	
	public void playPachelbel(View v) throws IOException {
		play(3);
	}
	
	private void play(int composerIndex) throws IOException {
		pauseAll();
		MediaPlayer player = _players[composerIndex];
		player.start();
		String url = _composersWikipediaUrls[composerIndex];
		_webView.loadUrl(url);
	}
	
	private void pauseAll(){
		for(MediaPlayer player : _players){
			if(player.isPlaying()){
				player.pause();
			}
		}
	}
}
