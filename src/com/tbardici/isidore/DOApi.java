package com.tbardici.isidore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

/**
 * 
 * @author Teo
 *
 */
public class DOApi {
	
	public static Map<String, String> getBaseArgs(){
		Map<String, String> args = new HashMap<String, String>();
		args.put("your_client_id", IsidoreSettings.CLIENT_ID);
		args.put("your_api_key", IsidoreSettings.API_KEY);
		return args;
	}
	
	public static String getUrl(String apiCall, Map<String, String> args){
		Map<String, String> baseArgs = getBaseArgs();
		
		if (args != null){
			Iterator<String> it = args.keySet().iterator();
			while (it.hasNext()){
				String key = it.next().toString();
				baseArgs.put(key, args.get(key));
			}
		}
		
		Iterator<String> it = baseArgs.keySet().iterator();
		while(it.hasNext()){
			String key = it.next().toString();
			apiCall = apiCall.replace("["+key+"]", baseArgs.get(key));
		}
		
		return apiCall;
	}
	
	public static JSONObject callApiAsync(String url){
		CallDOApiAsync async = new CallDOApiAsync();
		AsyncTask<String, Void, String> task = async.execute(new String[] {url});
		try {
			String response= task.get();
			if (response != null){
				return new JSONObject(response);
			}
			else {
				return null;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String callApiGetRaw(String url){
		HttpClient client = new DefaultHttpClient();
		try {
			Log.i("isidore", url);
			
			HttpGet request = new HttpGet();
			request.setURI(new URI(url));
			HttpResponse response = client.execute(request);
			
			StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == HttpStatus.SC_OK){
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				HttpEntity entity = response.getEntity();
				entity.writeTo(out);
				out.close();
				return out.toString();
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
	
	public static JSONObject callApi(String url){
		try{
			return new JSONObject(callApiGetRaw(url));
		}
		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static class API
	{
	public static final String DROPLETS_GET_ALL = "https://api.digitalocean.com/droplets/?client_id=[your_client_id]&api_key=[your_api_key]";
	public static final String DROPLETS_NEW = "https://api.digitalocean.com/droplets/new?client_id=[your_client_id]&api_key=[your_api_key]&name=[droplet_name]&size_id=[size_id]&image_id=[image_id]&region_id=[region_id]";
	public static final String DROPLETS_GET_ONE = "https://api.digitalocean.com/droplets/[droplet_id]?client_id=[your_client_id]&api_key=[your_api_key]";
	public static final String DROPLET_REBOOT = "https://api.digitalocean.com/droplets/[droplet_id]/reboot/?client_id=[your_client_id]&api_key=[your_api_key]";
	public static final String DROPLET_POWER_CYCLE = "https://api.digitalocean.com/droplets/[droplet_id]/power_cycle/?client_id=[your_client_id]&api_key=[your_api_key]";
	public static final String DROPLET_SHUTDOWN = "https://api.digitalocean.com/droplets/[droplet_id]/shutdown/?client_id=[your_client_id]&api_key=[your_api_key]";
	public static final String DROPLET_POWER_OFF = "https://api.digitalocean.com/droplets/[droplet_id]/power_off/?client_id=[your_client_id]&api_key=[your_api_key]";
	public static final String DROPLET_POWER_ON = "https://api.digitalocean.com/droplets/[droplet_id]/power_on/?client_id=[your_client_id]&api_key=[your_api_key]";
	public static final String DROPLET_PASSWORD_RESET = "https://api.digitalocean.com/droplets/[droplet_id]/password_reset/?client_id=[your_client_id]&api_key=[your_api_key]";
	public static final String DROPLET_RESIZE = "https://api.digitalocean.com/droplets/[droplet_id]/resize/?size_id=[size_id]&client_id=[your_client_id]&api_key=[your_api_key]";
	public static final String DROPLET_TAKE_SNAPSHOT = "https://api.digitalocean.com/droplets/[droplet_id]/snapshot/?name=[snapshot_name]&client_id=[your_client_id]&api_key=[your_api_key]";
	public static final String DROPLET_RESTORE_SNAPSHOT = "https://api.digitalocean.com/droplets/[droplet_id]/restore/?image_id=[image_id]&client_id=[your_client_id]&api_key=[your_api_key]";
	public static final String DROPLET_REBUILD = "https://api.digitalocean.com/droplets/[droplet_id]/rebuild/?image_id=[image_id]&client_id=[your_client_id]&api_key=[your_api_key]";
	public static final String DROPLET_RENAME = "https://api.digitalocean.com/droplets/[droplet_id]/rename/?client_id=[your_client_id]&api_key=[your_api_key]&name=[name]";
	public static final String DROPLET_DESTROY = "https://api.digitalocean.com/droplets/[droplet_id]/destroy/?client_id=[your_client_id]&api_key=[your_api_key]";
	
	public static final String REGIONS_GET_ALL = "https://api.digitalocean.com/regions/?client_id=[your_client_id]&api_key=[your_api_key]";
	
	public static final String IMAGES_GET_ALL = "https://api.digitalocean.com/images/?client_id=[your_client_id]&api_key=[your_api_key]";
	public static final String IMAGES_GET_ONE = "https://api.digitalocean.com/images/[image_id]/?client_id=[your_client_id]&api_key=[your_api_key]";
	public static final String IMAGE_DESTROY = "https://api.digitalocean.com/images/[image_id]/destroy/?client_id=[your_client_id]&api_key=[your_api_key]";
	public static final String IMAGE_TRANSFER = "https://api.digitalocean.com/images/[image_id]/transfer/?client_id=[your_client_id]&api_key=[your_api_key]&region_id=[region_id]";
	
	public static final String SIZES_GET_ALL = "https://api.digitalocean.com/sizes/?client_id=[your_client_id]&api_key=[your_api_key]";
	
	public static final String EVENT_GET_STATUS = "https://api.digitalocean.com/events/[event_id]/?client_id=[your_client_id]&api_key=[your_api_key]";
	}

	public static class DISTRO
	{
		public static final String Ubuntu = "Ubuntu";
		public static final String CentOS = "CentOS";
		public static final String Debian = "Debian";
		public static final String Fedora = "Fedora";
		public static final String ArchLinux = "ArchLinux";
	
	}
	
}
