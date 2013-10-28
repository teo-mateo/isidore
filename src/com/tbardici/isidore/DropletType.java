package com.tbardici.isidore;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Teo
 *
 */
public class DropletType {

	public final int id;
	public final double cost_per_hour;
	public final int disk_size;
	public final String name;
	public final int cpu_count;
	public final int memory;
	public final double cost_per_month;
	
	public DropletType(JSONObject from) throws JSONException{
		this.id = 				from.getInt("id");
		this.cost_per_hour = 	from.getDouble("cost_per_hour");
		this.disk_size = 		from.getInt("disk");
		this.name = 			from.getString("name");
		this.cpu_count = 		from.getInt("cpu");
		this.memory = 			from.getInt("memory");
		this.cost_per_month = 	from.getDouble("cost_per_month");
	}
	
	public static void initialize(JSONObject obj) throws JSONException{
		DropletType.dropletTypes = new ArrayList<DropletType>();
		if (obj != null){
			DropletType.rawJson = obj.toString();
			JSONArray array = obj.getJSONArray("sizes");
			for (int i = 0; i < array.length(); i++){
				DropletType.dropletTypes.add(new DropletType(array.getJSONObject(i)));
			}
		}
	}
	
	public static DropletType getWithId(int id){
		for (int i = 0; i < DropletType.dropletTypes.size(); i++){
			if (DropletType.dropletTypes.get(i).id == id){
				return DropletType.dropletTypes.get(i);
			}
		}
		return null;
	}
	
	private static ArrayList<DropletType> dropletTypes = new ArrayList<DropletType>();
	private static String rawJson;
	
	
	public static ArrayList<DropletType> getDropletTypes(){
		return DropletType.dropletTypes;
	}
	
	public static String getRawJson(){
		return DropletType.rawJson;
	}
	
	@Override
	public String toString() {
		String placeholder = "%s MB RAM,  %d GB HDD, %d CPUs, %.2f USD/mo";
		return String.format(placeholder, memory, disk_size, cpu_count, cost_per_month);
	}
	
}
