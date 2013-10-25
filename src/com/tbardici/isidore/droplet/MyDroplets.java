package com.tbardici.isidore.droplet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tbardici.isidore.DropletImage;
import com.tbardici.isidore.DropletType;
import com.tbardici.isidore.Region;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MyDroplets {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<Droplet> ITEMS = new ArrayList<Droplet>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, Droplet> ITEM_MAP = new HashMap<String, Droplet>();

	public static void initialize(JSONObject from) throws JSONException{
		ITEMS.clear();
		ITEM_MAP.clear();
		JSONArray array  = from.getJSONArray("droplets");
		for (int i = 0; i < array.length(); i++){
			addItem(new Droplet(array.getJSONObject(i)));
		}
	}
	
	/*
	static {
		// Add 3 sample items.
		addItem(new DummyItem("1", "Item 1"));
		addItem(new DummyItem("2", "Item 2"));
		addItem(new DummyItem("3", "Item 3"));1
	}
	*/

	private static void addItem(Droplet item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.name, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class Droplet {
		public final int id;
		public final String name;
		public final int image_id;
		public final DropletImage image;
		public final int size_id;
		public final DropletType size;
		public final int region_id;
		public final Region region;
		public final boolean backups_active;
		public final String ip_address;
		public final String private_ip_address;
		public final boolean locked;
		public final String status;
		public final String created_at;
		
		public Droplet(JSONObject from) throws JSONException {
			this.id = from.getInt("id");
			this.name = from.getString("name");
			this.image_id = from.getInt("image_id");
			this.image = DropletImage.getWithId(image_id);
			this.size_id = from.getInt("size_id");
			this.size = DropletType.getWithId(size_id);
			this.region_id = from.getInt("region_id");
			this.region = Region.getWithId(region_id);
			this.backups_active = from.getBoolean("backups_active");
			this.ip_address = from.getString("ip_address");
			this.private_ip_address = from.getString("private_ip_address");
			this.locked = from.getBoolean("locked");
			this.status = from.getString("status");
			this.created_at = from.getString("created_at");
		}

		@Override
		public String toString() {
			return name;
		}
	}
}
