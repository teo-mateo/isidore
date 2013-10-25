package com.tbardici.isidore;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Region {
	public final int id;
	public final String slug;
	public final String name;
	
	public Region(JSONObject from) throws JSONException{
		this.id = 		from.getInt("id");
		this.name = 	from.getString("name");
		this.slug = 	from.getString("slug");
	}
	
	private static ArrayList<Region> regions = new ArrayList<Region>();
	private static String rawJson;
	
	public static void initialize(JSONObject obj) throws JSONException{
		Region.regions = new ArrayList<Region>();
		if (obj != null){
			Region.rawJson = obj.toString();
			JSONArray array = obj.getJSONArray("regions");
			for (int i = 0; i < array.length(); i++){
				Region.regions.add(new Region(array.getJSONObject(i)));	
			}
		}
	}
	
	public static ArrayList<Region> getRegions(){
		return Region.regions;
	}
	
	public static String getRawJson(){
		return Region.rawJson;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
