package com.tbardici.isidore;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * 
 * @author Teo
 *
 */
public class LongDurationCall extends Observable {
	private String mCallUrl;
	private String mEventid;
	private String mPercentage = "";
	
	public LongDurationCall(String apiCall, Map<String, String> args){
		mCallUrl = DOApi.getUrl(apiCall, args);
	}
	
	public void run() {
		
		Runnable r = new Runnable() {
			public void run() {
				try {
					JSONObject json = DOApi.callApi(mCallUrl);
				
					if (json.getString("status").equals("OK")){
						mEventid = json.getString("event_id");
						int cnt = 0;
						
						while(true && cnt < 29){
							cnt++;
							try{
								Map<String, String> args = new HashMap<String, String>();
								args.put("event_id", mEventid);
								String getStatusUrl = DOApi.getUrl(DOApi.API.EVENT_GET_STATUS, args);
								
								JSONObject status = DOApi.callApi(getStatusUrl);
								JSONObject event_data = status.getJSONObject("event");
								String percentage = event_data.getString("percentage");
								if (percentage == null)
									percentage = "0";
								Log.i("isidore", String.format("%d %s", cnt, percentage));
								if (event_data.getString("action_status").equals("done")){
									Log.i("isidore", "action done.");
									setChanged();
									notifyObservers("100");
									break;
								} else{
									if (!mPercentage.equals(percentage)){
										mPercentage = percentage;
										setChanged();
										notifyObservers(mPercentage);
									}
									Thread.sleep(1000);
								}
							} catch (JSONException e) {
								Thread.sleep(1000);
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						setChanged();
						notifyObservers("100");
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		new Thread(r).start();
		

	}
}
