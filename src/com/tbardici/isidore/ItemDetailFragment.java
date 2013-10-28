package com.tbardici.isidore;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.tbardici.isidore.droplet.MyDroplets;
import android.util.Log;

/**
 * @author Teo
 * 
 * A fragment representing a single Item detail screen. This fragment is either
 * contained in a {@link ItemListActivity} in two-pane mode (on tablets) or a
 * {@link ItemDetailActivity} on handsets.
 */
public class ItemDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private MyDroplets.Droplet mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ItemDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = MyDroplets.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_item_detail,
				container, false);
		
		Typeface font = Typeface.createFromAsset(
				this.getActivity().getAssets(), 
			    "Roboto-Regular.ttf");
		
		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.name);
			((TextView) rootView.findViewById(R.id.item_detail)).setTypeface(font);
			((EditText) rootView.findViewById(R.id.item_size)).setText(mItem.size.toString());
			((EditText) rootView.findViewById(R.id.item_region)).setText(mItem.region.toString());
			((EditText) rootView.findViewById(R.id.item_image)).setText(mItem.image.name);
			((CheckBox) rootView.findViewById(R.id.item_backup_active)).setChecked(mItem.backups_active);
			((TextView) rootView.findViewById(R.id.item_ip)).setText(mItem.ip_address);
			((TextView) rootView.findViewById(R.id.item_private_ip)).setText(mItem.private_ip_address);
			((CheckBox) rootView.findViewById(R.id.item_locked)).setChecked(mItem.locked);
			((TextView) rootView.findViewById(R.id.item_status)).setText(mItem.status);
			
		}

		return rootView;
	}
	

}
