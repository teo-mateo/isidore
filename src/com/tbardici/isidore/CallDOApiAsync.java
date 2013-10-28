package com.tbardici.isidore;

import android.os.AsyncTask;

/** 
 * 
 * @author Teo
 *
 */
public class CallDOApiAsync extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... params) {
		return DOApi.callApiGetRaw(params[0]);
	}

}
