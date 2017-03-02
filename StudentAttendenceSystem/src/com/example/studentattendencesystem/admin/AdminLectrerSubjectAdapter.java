package com.example.studentattendencesystem.admin;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;
import com.example.studentattendencesystem.javaclasses.LectuererInfo;

public class AdminLectrerSubjectAdapter extends ArrayAdapter<LectuererInfo>{
	
	
	Context context;
	int resource;
	ArrayList<LectuererInfo> subjectarray;
	ArrayList<LectuererInfo> brancharray;
	ArrayList<LectuererInfo> yeararray;
	TextView subject, branch,year,index;
	LectuererInfo s,b,y;

	public AdminLectrerSubjectAdapter(Context context, int resource,
			ArrayList<LectuererInfo> subjectarray,ArrayList<LectuererInfo> brancharray,ArrayList<LectuererInfo> yeararray) {
		super(context, resource, subjectarray);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.resource = resource;
		this.subjectarray = subjectarray;
		this.brancharray=brancharray;
		this.yeararray=yeararray;
		System.out.println("ADAPTER>>>");
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;

		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.admin_lectlist, null);
		}
		s = subjectarray.get(position);
		b=brancharray.get(position);
		y=yeararray.get(position);
		if (s != null) {
			index = (TextView) v.findViewById(R.id.adminlecturerlist_index);
			branch = (TextView) v.findViewById(R.id.adminlecturerlist_branch);
			year = (TextView) v.findViewById(R.id.adminlecturerlist_year);
			subject = (TextView) v.findViewById(R.id.adminlecturerlist_subject);

			if (index != null) {

				index.setText(""+(position+1));

			}
			if (branch != null) {
				branch.setText(""+b.getLect_branch());
			}
			if (year != null) {
				year.setText(""+y.getLect_year());
			}
			if (subject != null) {
				subject.setText(""+s.getLect_subject());
			}
		}

		return v;
	}
}



