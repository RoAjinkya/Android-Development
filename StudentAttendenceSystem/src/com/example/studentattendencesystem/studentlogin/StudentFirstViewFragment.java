package com.example.studentattendencesystem.studentlogin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.NetworkClass.ExamInfoNetwork;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;
import com.example.studentattendencesystem.javaclasses.StudentInfo;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class StudentFirstViewFragment extends Fragment implements OnClickListener {
	View v;
	String bid, yid, bName, yName, Branch, Year;
	String sid, sfname, slname, srollno;
	TextView loginName, logout, S_name, S_id, S_rollno, S_branch, S_year,date;
	Button attendance,StudInfo;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.student_firstview, container, false);
		StudentActivity main = (StudentActivity) getActivity();
		Bundle BYBundle = main.getStudBrnchYrBundle();
		bid = BYBundle.getString("Branch");
		yid = BYBundle.getString("Year");
		getBYName();
		loginName = (TextView) v.findViewById(R.id.studfirstview_studname);
		logout = (TextView) v.findViewById(R.id.studfirstview_logout);
		S_name = (TextView) v.findViewById(R.id.studfirstview_studname1);
		S_id = (TextView) v.findViewById(R.id.studfirstview_studID);
		S_rollno = (TextView) v.findViewById(R.id.studfirstview_studRollno);
		S_branch = (TextView) v.findViewById(R.id.studfirstview_studbranch);
		S_year = (TextView) v.findViewById(R.id.studfirstview_studyear);
		attendance=(Button)v.findViewById(R.id.studfirstview_btnatendnc);
		StudInfo=(Button)v.findViewById(R.id.studfirstview_btnmarks);
		date=(TextView)v.findViewById(R.id.studfirstview_crntdate);
		Bundle sinfobndl = main.getloginStudentInfoBundle();
		sfname = sinfobndl.getString("FName");
		slname = sinfobndl.getString("LName");
		srollno = sinfobndl.getString("Rollno");
		sid = sinfobndl.getString("StudID");
		attendance.setOnClickListener(this);
		StudInfo.setOnClickListener(this);
		seTextStudInfo();
		setCurrentDateAndTime();
		
		logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			StudentActivity main=(StudentActivity)getActivity();
			main.studentLogINFragment();
			Toast.makeText(getActivity(), "You are Logged out Successfully..", Toast.LENGTH_SHORT).show();
			}
		});
		return v;
	}

	public void setCurrentDateAndTime() {
		Calendar c = Calendar.getInstance();
		int monthNo = c.get(Calendar.MONTH) + 1;
		int dayNo = c.get(Calendar.DAY_OF_MONTH);
		int yearNo = c.get(Calendar.YEAR);
		int hour = c.get(Calendar.HOUR);
		int min = c.get(Calendar.MINUTE);
		date.setText("" + dayNo + "-" + monthNo + "-" + yearNo);
		
	}
	
	private void seTextStudInfo() {
		// TODO Auto-generated method stub
		loginName.setText(sfname + " " + slname);
		S_name.setText(sfname + " " + slname);
		S_id.setText(sid);
		S_rollno.setText(srollno);

	}

	private void getBYName() {
		// TODO Auto-generated method stub

		final ExamInfoNetwork obj = new ExamInfoNetwork();
		// menuList = source.getAllComments();

		new Thread(new Runnable() {

			public void run() {

				bName = obj.getStudBranchName(bid);
				yName = obj.getStudYearName(yid);
				System.out.println("marks ====" + bName);
				System.out.println("B_NAME====" + yName);
				getActivity().runOnUiThread(new Runnable() {
					public void run() {

						ArrayList<BranchYearInfo> brInfo = parseJsonBNAME(bName);
						Iterator britr = brInfo.iterator();
						while (britr.hasNext()) {
							BranchYearInfo info = (BranchYearInfo) britr.next();
							Branch = info.getBranch_name();
							System.out.println("Branch=" + Branch);

						}
						ArrayList<BranchYearInfo> yrInfo = parseJsonYNAME(yName);
						Iterator yritr = yrInfo.iterator();
						while (yritr.hasNext()) {
							BranchYearInfo info = (BranchYearInfo) yritr.next();
							Year = info.getYear_name();
							System.out.println("Year=" + Year);
						}
						S_year.setText(Year);
						S_branch.setText(Branch);
					}
				});

			}
		}).start();

	}

	public ArrayList<BranchYearInfo> parseJsonBNAME(String result) {
		ArrayList<BranchYearInfo> mainObj = new ArrayList<BranchYearInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				BranchYearInfo menu = new BranchYearInfo();
				menu.setBranch_name(jsonObj.getString("Branch_Name"));

				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	public ArrayList<BranchYearInfo> parseJsonYNAME(String result) {
		ArrayList<BranchYearInfo> mainObj = new ArrayList<BranchYearInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				BranchYearInfo menu = new BranchYearInfo();
				menu.setYear_name(jsonObj.getString("Year_Name"));

				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.studfirstview_btnatendnc:
			StudentActivity main=(StudentActivity)getActivity();
			main.studentAttendanceFrag();
			break;
		case R.id.studfirstview_btnmarks:
			StudentActivity main1=(StudentActivity)getActivity();
			main1.studentViewMarksFrag();
			break;

		default:
			break;
		}
	}
}
