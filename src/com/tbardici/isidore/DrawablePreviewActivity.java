package com.tbardici.isidore;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleAdapter;

public class DrawablePreviewActivity extends ListActivity
{
    private static final String TAG = "DrawablePreviewActivity";

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setTitle("Preview of android.R.drawable.*");

        try {
            Class RClass = Class.forName("android.R");

            Class[] subclasses = RClass.getDeclaredClasses();

            Class RDrawable = null;

            for(Class subclass : subclasses) {
                if("android.R.drawable".equals(subclass.getCanonicalName())) {
                    RDrawable = subclass;
                    break;
                }
            }

            List<Map<String, Object>> drinfo = new ArrayList<Map<String, Object>>();

            Field[] drawables = RDrawable.getFields();
            for(Field dr : drawables) {
                Map<String, Object> map = new HashMap<String, Object>();
                Drawable img = getResources().getDrawable(dr.getInt(null));

                map.put("drimg", dr.getInt(null));
                map.put("drname", dr.getName());

                drinfo.add(map);
            }

            setListAdapter(new SimpleAdapter(this,
                            drinfo,
                            R.layout.listitem,
                            new String[] { "drimg", "drname" },
                            new int[] { R.id.drimg, R.id.drname }));

        } catch(IllegalAccessException iae) {
            Log.e(TAG, iae.toString());
        } catch(ClassNotFoundException cnfe) {
            Log.e(TAG, cnfe.toString());
        }
    }
}