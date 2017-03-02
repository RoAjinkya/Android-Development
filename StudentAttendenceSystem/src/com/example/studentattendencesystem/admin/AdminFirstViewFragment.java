package com.example.studentattendencesystem.admin;

import com.example.studentattendencesystem.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

@SuppressLint("NewApi")
public class AdminFirstViewFragment extends Fragment
{
View v;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v=inflater.inflate(R.layout.admin_firstview,container,false);
		Button stud=(Button)v.findViewById(R.id.adminfirstview_studexam);
		Button brnchyr=(Button)v.findViewById(R.id.adminfirstview_brnchyr);
		Button lecture=(Button)v.findViewById(R.id.adminfirstview_lecturer);
		Button change=(Button)v.findViewById(R.id.adminfirstview_changepasswd);
		Button register=(Button)v.findViewById(R.id.activity_Registeration);
		//Button student=(Button)v.findViewById(R.id.adminfirstview_studentinfo);
//		
//		student.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//				AdminActivity main=(AdminActivity)getActivity();
//				main.studentinfo();	
//				
//			}
//		});
//		
		
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AdminActivity main=(AdminActivity)getActivity();
				main.register();
			}
		});
		
		stud.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AdminActivity main=(AdminActivity)getActivity();
				main.adminBranchYearInfo();
				
			}
		});
		brnchyr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AdminActivity main=(AdminActivity)getActivity();
				main.adminBranch();
			}
		});
		
		lecture.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AdminActivity main=(AdminActivity)getActivity();
				main.adminLecturerInfo();
			}
		});

	       change.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					AdminActivity main=(AdminActivity)getActivity();
					main.changepwd();
				}
			});
		
		return v;
	}
}
