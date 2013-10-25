package com.tbardici.isidore;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;

public class CreateDropletActivity extends FragmentActivity implements DCConfirmDialogFragment.NoticeDialogListener{

	private Bundle mDataBundle = new Bundle();
	
	private void loadSpinners(){
		Spinner spinSize = (Spinner) findViewById(R.id.spinSize);
		ArrayAdapter<DropletType> sizeAdapter = 
				new ArrayAdapter<DropletType>(
						this, 
						android.R.layout.simple_spinner_dropdown_item, 
						DropletType.getDropletTypes());
		spinSize.setAdapter(sizeAdapter);
		
		Spinner spinRegion = (Spinner) findViewById(R.id.spinRegion);
		ArrayAdapter<Region> regionAdapter = 
				new ArrayAdapter<Region>(
						this, 
						android.R.layout.simple_spinner_dropdown_item, 
						Region.getRegions());
		spinRegion.setAdapter(regionAdapter);
		
		Spinner spinImage = (Spinner) findViewById(R.id.spinImage);
		ArrayAdapter<DropletImage> imageAdapter = 
				new ArrayAdapter<DropletImage>(
						this, 
						android.R.layout.simple_spinner_dropdown_item,
						DropletImage.getImages());
		spinImage.setAdapter(imageAdapter);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_droplet);
		// Show the Up button in the action bar.
		setupActionBar();
		loadSpinners();
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_droplet, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
		
	public void createDroplet(View view){
		
		EditText txtName = (EditText) findViewById(R.id.dropletName);
		if (txtName.getText().length() == 0){
			Toast.makeText(getApplicationContext(), "Hostname is required", Toast.LENGTH_LONG).show();
			return;
		}
		
		String hostname = txtName.getText().toString().trim();
		
		Spinner spinSize = (Spinner) findViewById(R.id.spinSize);
		DropletType type = (DropletType) spinSize.getSelectedItem();
		
		Spinner spinRegion = (Spinner) findViewById(R.id.spinRegion);
		Region region = (Region) spinRegion.getSelectedItem();
		
		Spinner spinImage = (Spinner) findViewById(R.id.spinImage);
		DropletImage image = (DropletImage) spinImage.getSelectedItem();
		
		mDataBundle.putString("hostname", hostname);
		mDataBundle.putString("type", type.toString());
		mDataBundle.putString("region", region.toString());
		mDataBundle.putString("image", image.toString());
		
		DialogFragment dialog = new DCConfirmDialogFragment();
		dialog.setArguments(mDataBundle);
		dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
		
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		Log.i("i", "attempting to create the droplet.");
		Toast.makeText(getApplicationContext(), "attempting to create the droplet", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		Log.i("i", "user changed his mind.");
		Toast.makeText(getApplicationContext(), "user changed his mind", Toast.LENGTH_LONG).show();
		
	}

}
