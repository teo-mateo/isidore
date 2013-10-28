package com.tbardici.isidore;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * 
 * @author Teo
 *
 */
public class DCConfirmDialogFragment extends DialogFragment {

	private String default_message = "Are you sure you want to create this droplet?";
	
    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
	
 // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;
    
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		Bundle args = this.getArguments();
		
		default_message += "\nHostname: " + args.getString("hostname");
		default_message += "\nSize: " + args.getString("type");
		default_message += "\nRegion: " + args.getString("region");
		default_message += "\nImage: " + args.getString("image");
		
		//use the Builder class for convenient dialog creation
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Create droplet");
		builder.setMessage(default_message);
		builder.setPositiveButton(R.string.yes, yesClick );
		builder.setNegativeButton(R.string.no, noClick);
		
		return builder.create();
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
	}
	
	private DialogInterface.OnClickListener yesClick = new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			mListener.onDialogPositiveClick(DCConfirmDialogFragment.this);
			
		}
	};
	
	private DialogInterface.OnClickListener noClick = new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			mListener.onDialogNegativeClick(DCConfirmDialogFragment.this);
			
		}
	};

}
