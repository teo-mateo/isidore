package com.tbardici.isidore;

import org.json.JSONException;
import org.json.JSONObject;

import com.tbardici.isidore.droplet.MyDroplets;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

/**
 * 
 * @author Teo
 *
 */
public class LoadDropletsAsync extends AsyncTask<Void, String, Void>{

	private Context context;
	private ProgressDialog dialog;
	
	public LoadDropletsAsync(Context ctx){
		this.context = ctx;
		this.dialog = new ProgressDialog(this.context);
	}
	
	@Override
	protected void onPreExecute() {
		this.dialog.setTitle("Please wait.");
		this.dialog.setMessage("Loading droplets...");
		this.dialog.show();
	}	
	
	@Override
	protected void onPostExecute(Void result) {
		this.dialog.dismiss();
		Intent intent1 = new Intent(this.context, ItemListActivity.class);
		this.context.startActivity(intent1);
	}
	
	@Override
	protected Void doInBackground(Void... unused) {
		try {
			String url;
	    	JSONObject response;
	        //get all the sizes and initialize our DropletType collection
	        url = DOApi.getUrl(DOApi.API.DROPLETS_GET_ALL,  null);
			response = DOApi.callApi(url);
			MyDroplets.initialize(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	protected void onProgressUpdate(String... values) {
		this.dialog.setMessage(values[0]);
	}

}
