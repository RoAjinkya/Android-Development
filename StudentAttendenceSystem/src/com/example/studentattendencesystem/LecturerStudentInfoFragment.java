package com.example.studentattendencesystem;

import java.util.Calendar;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LecturerStudentInfoFragment extends Fragment {
	View v;
	Bundle lectInfoBundle;
	TextView lectName, lectLogout, date, time;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.lecturer_studentinfo, container, false);
		Button submit = (Button) v.findViewById(R.id.studentIfo_submit);
		Button view = (Button) v.findViewById(R.id.submitInfo_view);
		lectName = (TextView) v.findViewById(R.id.studentIfo_lectname);
		lectLogout = (TextView) v.findViewById(R.id.studentIfo_logout);
		date = (TextView) v.findViewById(R.id.studentIfo__date);
		time = (TextView) v.findViewById(R.id.studentIfo__time);
		LecturerActivity main = (LecturerActivity) getActivity();
		lectInfoBundle = main.getbundleLecturerInfo();
		String lname = lectInfoBundle.getString("FirstName");
		String fname = lectInfoBundle.getString("LastName");
		lectName.setText(fname+" "+lname);
		setDateTime();
		logout();
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				LecturerActivity main = (LecturerActivity) getActivity();
				main.submitStudentInfo();

			}
		});

		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LecturerActivity main = (LecturerActivity) getActivity();
				main.viewStudentInfo();
			}
		});
		return v;
	}

	private void setDateTime() {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		int monthNo = c.get(Calendar.MONTH) + 1;
		c.get(Calendar.DAY_OF_MONTH);
		c.get(Calendar.YEAR);
		c.get(Calendar.SECOND);
		c.get(Calendar.MINUTE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		date.setText(" " + c.get(Calendar.DAY_OF_MONTH) + "-" + monthNo + "-"
				+ c.get(Calendar.YEAR));

		time.setText(" " + hour + ":" + c.get(Calendar.MINUTE) + ":"
				+ c.get(Calendar.SECOND));

	}
	private void logout() {
		// TODO Auto-generated method stub
		lectLogout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "You Are Successfully LoggedOut",Toast.LENGTH_SHORT).show();
				LecturerActivity main=(LecturerActivity)getActivity();
				main.lecturerLogINFragment();
			}
		});
	}

}
