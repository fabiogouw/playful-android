package android.playful.strangemaps;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends Activity {

	private static final String TAG = "StrangeMaps";
	private static final String LAST_POSITION = "lastPosition";
	
	private Button _btnGoMaps;
	private Spinner _ddlPlaces;
	private ImageView _img;
	private SharedPreferences _preferences;
	
	// ths urls of the places
	private String[] _urls = new String[]{
			"http://www.google.com/maps/preview/place/@51.8479189,-0.5545522,526m/data=!3m1!1e3",
			"http://www.google.com/maps/preview/place/@33.7418876,-112.6323519,3700m/data=!3m1!1e3",
			"http://www.google.com/maps/preview/place/@48.3528272,11.7316266,475m/data=!3m1!1e3",
			"http://www.google.com/maps/preview/place/@29.8705666,31.2170562,730m/data=!3m1!1e3"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// getting the control's references
		_btnGoMaps = (Button)findViewById(R.id.btnGoMaps);
		_ddlPlaces = (Spinner) findViewById(R.id.ddlPlaces);
		_img = (ImageView) findViewById(R.id.img);
		// filling the spinner with the contents of the array of places
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.places, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		_ddlPlaces.setAdapter(adapter);
		// getting the last position the user has selected
		_preferences = getPreferences(MODE_PRIVATE);
		int lastPosition = _preferences.getInt(LAST_POSITION, 0);
		_ddlPlaces.setSelection(lastPosition);
		// setting the button's action
		_btnGoMaps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	try {
            		// getting the position of the selected item on the spinner, so I can find the right url
            		int selectedPosition = _ddlPlaces.getSelectedItemPosition();
					String url = _urls[selectedPosition];
					// create the intent to use Google Maps or the Internet browser
					Intent geoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
					startActivity(Intent.createChooser(geoIntent, "Select..."));
				} catch (Exception e) {
					Log.e(TAG, e.toString());	// any error goes here
				}
            }
        });
		
		_ddlPlaces.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		    	// setting the image view
		    	// (I didn't like this switch, but I don't know yet how to get the resource from its name)
		    	switch(position){
		    		case 0:
		    			_img.setImageResource(R.drawable.i0);
		    			break;
		    		case 1:
		    			_img.setImageResource(R.drawable.i1);
		    			break;
		    		case 2:
		    			_img.setImageResource(R.drawable.i2);
		    			break;
		    		case 3:
		    			_img.setImageResource(R.drawable.i3);
		    			break;		    					    	
		    	}
		    	// saving the last item the user selected on the spinner
		    	Editor editor = _preferences.edit();
		    	editor.putInt(LAST_POSITION, position);
		    	editor.commit();
		    }

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
