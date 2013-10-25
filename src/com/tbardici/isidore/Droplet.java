package com.tbardici.isidore;

import org.json.JSONException;
import org.json.JSONObject;

public class Droplet {

	public final int id;
	public final String name;
	public final int image_id;
	public final int size_id;
	//public final DropletType type;
	public final int region_id;
	public final boolean backups_active;
	public final String ip_address;
	public final String private_ip_address;
	public final boolean locked;
	public final String status;
	public final String created_at;
	
	public Droplet(JSONObject from) throws JSONException{
		this.id = 					from.getInt("id");
		this.name = 				from.getString("name");
		this.image_id = 			from.getInt("image_id");
		this.size_id = 				from.getInt("size_id");
		this.region_id = 			from.getInt("region_id");
		this.backups_active =		from.getBoolean("backups_active");
		this.ip_address = 			from.getString("ip_adddress");
		this.private_ip_address = 	from.getString("private_ip_address");
		this.locked = 				from.getBoolean("locked");
		this.status = 				from.getString("status");
		this.created_at = 			from.getString("created_at");
			
	}
	
}

/*

		"id":434219,
		"name":"BARISTA",
		"image_id":284211,
		"size_id":66,
		"region_id":2,
		"backups_active":false,
		"ip_address":"146.185.134.241",
		"private_ip_address":null,
		"locked":false,
		"status":"active",
		"created_at":"2013-09-05T11:29:16Z"

*/