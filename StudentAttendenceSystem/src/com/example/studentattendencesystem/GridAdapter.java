package com.example.studentattendencesystem;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GridAdapter extends ArrayAdapter<String>{

	Context context;
	int resource;
	String[] objects;
	TextView name;
	LinearLayout ln;
	public GridAdapter(Context context, int resource, String[] objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.resource=resource;
		this.objects=objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(resource, null);
		}
		
		ln = (LinearLayout)v.findViewById(R.id.chngBackground);
		ln.setBackgroundColor(Color.parseColor("#40E0D0"));
		
		
			name = (TextView) v.findViewById(R.id.rollNo);
			
			if (name != null) {
				name.setText(objects[position]);
			}

			
		
		return v;
	}
	

}
