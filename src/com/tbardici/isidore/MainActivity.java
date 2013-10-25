package com.tbardici.isidore;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        try {
			getDataFromSharedPrefs();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String url = DOApi.getUrl("http://www.google.ro", null);
				
				JSONObject response = DOApi.callApi(url);
				TextView txt = (TextView)findViewById(R.id.textView1);
				txt.setText(response.toString());
				
			}
		});
        
        Button btnGetSizes = (Button)findViewById(R.id.btnGetSizes);
        btnGetSizes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {


				
				TextView txt = (TextView)findViewById(R.id.textView1);
				txt.setText(DropletType.getRawJson());
			}
        	
        });
        
        
    }

    public void getRegions(View view) throws JSONException{
    	String url;
    	JSONObject response;
    	
		//get all the regions and initialize our Region collection
		url = DOApi.getUrl(DOApi.REGIONS_GET_ALL, null);
    	response = DOApi.callApi(url);
    	Region.initialize(response);
    	
		TextView txt = (TextView)findViewById(R.id.textView1);
		txt.setText(Region.getRawJson());
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()){
    		case R.id.action_new_droplet:
	    		Intent intent = new Intent(this, CreateDropletActivity.class);
	    		startActivity(intent);
	    		return true;
    		case R.id.action_droplet_list:
    			Intent intent1 = new Intent(this, ItemListActivity.class);
    			startActivity(intent1);
    			return true;
    		default:
    			return super.onOptionsItemSelected(item);
    	}
    	
    }
    
    public void startActivity(View view){
    	Intent intent = new Intent(this, CreateDropletActivity.class);
    	startActivity(intent);
    }
    

    public void getImages(View view){
    	String url;
    	JSONObject response;
    	
    	url = DOApi.getUrl(DOApi.IMAGES_GET_ALL, null);
    	response = DOApi.callApi(url);
    	try {
			DropletImage.initialize(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	TextView txt = (TextView)findViewById(R.id.textView1);
		txt.setText(DropletImage.getRawJson());
    }
    
    private void getDataFromSharedPrefs() throws JSONException{
    	SharedPreferences settings = getSharedPreferences(
    			getString(R.string.preference_file_key), 0);
    	
    	String sizeJson = settings.getString("size", "");
    	String regionJson = settings.getString("region", "");
    	String imageJson = settings.getString("image", "");
    	
    	if (sizeJson.length() > 0){
    		DropletType.initialize(new JSONObject(sizeJson));
    	} else {
        	String url;
        	JSONObject response;
	        //get all the sizes and initialize our DropletType collection
	        url = DOApi.getUrl(DOApi.SIZES_GET_ALL,  null);
			response = DOApi.callApi(url);
			DropletType.initialize(response);
			Editor editor = settings.edit();
			editor.putString("size", response.toString());
			editor.commit();
    	}
    	
    	if (regionJson.length() > 0){
    		Region.initialize(new JSONObject(regionJson));
    	} else {
        	String url;
        	JSONObject response;
	        //get all the sizes and initialize our DropletType collection
	        url = DOApi.getUrl(DOApi.REGIONS_GET_ALL,  null);
			response = DOApi.callApi(url);
			Region.initialize(response);
			Editor editor = settings.edit();
			editor.putString("region", response.toString());
			editor.commit();
    	}
    	
    	if (imageJson.length() > 0){
    		DropletImage.initialize(new JSONObject(imageJson));
    	} else {
        	String url;
        	JSONObject response;
	        //get all the sizes and initialize our DropletType collection
	        url = DOApi.getUrl(DOApi.IMAGES_GET_ALL,  null);
			response = DOApi.callApi(url);
			DropletImage.initialize(response);
			Editor editor = settings.edit();
			editor.putString("image", response.toString());
			editor.commit();
    	}
    }
    
    public void masterDetailShow(View view){
    	Intent intent = new Intent(this, ItemListActivity.class);
    	startActivity(intent);
    }
}
