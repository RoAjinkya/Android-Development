package com.example.studentattendencesystem.admin;

import java.util.ArrayList;

import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdminSubjectListAdapter extends ArrayAdapter<BranchYearInfo>{
	
	
	Context context;
	int resource;
	ArrayList<BranchYearInfo> objects;
	TextView name, id;
	BranchYearInfo o;

	public AdminSubjectListAdapter(Context context, int resource,
			ArrayList<BranchYearInfo> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.resource = resource;
		this.objects = objects;
		System.out.println("ADAPTER>>>");
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;

		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.admin_subjectlist, null);
		}
		o = objects.get(position);
		if (o != null) {
			name = (TextView) v.findViewById(R.id.admin_subject_name);
			id = (TextView) v.findViewById(R.id.admin_subjectId_admin);

			if (name != null) {

				name.setText(o.getSubject_name());

			}
			if (id != null) {
				id.setText(""+o.getSubject_id());
				System.out.println("admin_subjectId_admin="+o.getSubject_id());
			}
		}

		return v;
	}
}



