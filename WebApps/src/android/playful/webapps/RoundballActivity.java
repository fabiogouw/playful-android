package android.playful.webapps;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebView;

public class RoundballActivity extends Activity {

	private WebView _webView;
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_roundball);
		_webView = (WebView)findViewById(R.id.webView1);
		_webView.getSettings().setBuiltInZoomControls(true);
		_webView.getSettings().setJavaScriptEnabled(true);
		_webView.getSettings().setDomStorageEnabled(true);
		_webView.loadUrl("file:///android_asset/roundball/roundball.html");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.roundball, menu);
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && _webView.canGoBack()) {
			_webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
