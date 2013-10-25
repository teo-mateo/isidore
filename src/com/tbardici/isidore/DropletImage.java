package com.tbardici.isidore;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DropletImage {
	
	public final int id;
	public final String name;
	public final String distribution;
	public final boolean is_public; 
	
	private static ArrayList<DropletImage> images = new ArrayList<DropletImage>();
	private static String rawJson;
	
	public DropletImage(JSONObject from) throws JSONException{
		this.id = 				from.getInt("id");
		this.name = 			from.getString("name");
		this.distribution = 	from.getString("distribution");
		this.is_public = 		from.getBoolean("public");
	}
	
	public static void initialize(JSONObject obj) throws JSONException{
		DropletImage.images = new ArrayList<DropletImage>();
		if (obj != null){
			DropletImage.rawJson = obj.toString();
			JSONArray array = obj.getJSONArray("images");
			
			for (int i = 0; i < array.length(); i++){
				DropletImage.images.add(new DropletImage(array.getJSONObject(i)));
			}
		}
	}
	
	public static DropletImage getWithId(int id){
		for (int i = 0; i < DropletImage.images.size(); i++){
			if (DropletImage.images.get(i).id == id){
				return DropletImage.images.get(i);
			}
		}
		return null;
	}
	
	public static ArrayList<DropletImage> getImages(){
		return DropletImage.images;
	}
	
	public static String getRawJson(){
		return DropletImage.rawJson;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
