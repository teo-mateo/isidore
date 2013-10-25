package com.tbardici.isidore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class CallDOApiAsync extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... params) {
		
		HttpClient client = new DefaultHttpClient();
		try {
			//HttpResponse response = client.execute(new HttpGet(params[0]));
			HttpGet request = new HttpGet();
			request.setURI(new URI(params[0]));
			HttpResponse response = client.execute(request);
			
			StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == HttpStatus.SC_OK){
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				HttpEntity entity = response.getEntity();
				entity.writeTo(out);
				out.close();
				String r = out.toString();
				return r;
			} else {
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
