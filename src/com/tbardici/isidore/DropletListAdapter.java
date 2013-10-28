package com.tbardici.isidore;

import java.util.List;

import com.tbardici.isidore.droplet.MyDroplets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author Teo
 *
 */
public class DropletListAdapter extends ArrayAdapter<MyDroplets.Droplet> {

	Context mContext;
	
	public DropletListAdapter(Context context,
			int textViewResourceId, List<MyDroplets.Droplet> objects) {
		super(context, textViewResourceId, objects);
		mContext = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		/* This is the droplet for which we're drawing now */
		MyDroplets.Droplet droplet = (MyDroplets.Droplet) super.getItem(position);
		
		/* Inflating the row */
		LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.row, parent, false);
	    TextView label=(TextView)row.findViewById(R.id.row_droplet_name);
	    label.setText(droplet.name);
	    ImageView icon=(ImageView)row.findViewById(R.id.row_icon);
	    
	    /* Pick icon for the droplet, based on distro */
	    if (droplet.image.distribution.equals(DOApi.DISTRO.Ubuntu)) {
	    	icon.setImageResource(R.drawable.ubuntuactive);
	    } else if (droplet.image.distribution.equals(DOApi.DISTRO.CentOS)){
	    	icon.setImageResource(R.drawable.centosactive);
	    } else if (droplet.image.distribution.equals(DOApi.DISTRO.ArchLinux)){
	    	icon.setImageResource(R.drawable.archlinuxactive);
	    } else if (droplet.image.distribution.equals(DOApi.DISTRO.Debian)){
	    	icon.setImageResource(R.drawable.debianactive);
	    } else if (droplet.image.distribution.equals(DOApi.DISTRO.Fedora)){
	    	icon.setImageResource(R.drawable.fedoraactive);
	    }
		return row;
	}
	
	
}
