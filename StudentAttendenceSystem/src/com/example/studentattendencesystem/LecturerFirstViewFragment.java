package com.example.studentattendencesystem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.NetworkClass.LecturerInfoNetwork;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class LecturerFirstViewFragment extends Fragment implements
		OnClickListener {
	private View v;
	private TextView date,time,lectname,lectLogout;
	private Spinner branch, year;
	private Button attendance,Bluetooth_attendance, marks,sendmail,sendsms;
	private Bundle lectInfoBundle;
	int lID;
	String yearBranchId, YearId, BranchId;

	ArrayList<BranchYearInfo> branchArray, yearArray, idBY;
	ArrayList<String> branchName, yearName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.lecturer_firstview, container, false);
		idBY = new ArrayList<BranchYearInfo>();
		branchArray = new ArrayList<BranchYearInfo>();
		yearArray = new ArrayList<BranchYearInfo>();
		branchName = new ArrayList<String>();
		yearName = new ArrayList<String>();

		LecturerActivity main = (LecturerActivity) getActivity();
		lectInfoBundle = main.getbundleLecturerInfo();
		String lname = lectInfoBundle.getString("FirstName");
		String fname = lectInfoBundle.getString("LastName");
		lID = lectInfoBundle.getInt("LectID");
		lectname=(TextView)v.findViewById(R.id.lectattence_lectname);
		lectname.setText(fname+" "+lname);
		lectLogout=(TextView)v.findViewById(R.id.lectattence_logout);

		
		branch = (Spinner) v.findViewById(R.id.firstview_branch);
		year = (Spinner) v.findViewById(R.id.firstview_year);
		attendance = (Button) v.findViewById(R.id.firstview_btnatendnc);
		Bluetooth_attendance=(Button)v.findViewById(R.id.firstview_btnatenbluetooth);
		marks = (Button) v.findViewById(R.id.firstview_btnmarks);
		sendmail=(Button)v.findViewById(R.id.firstview_btnemail);
		sendsms=(Button)v.findViewById(R.id.firstview_btnsms);
		date=(TextView)v.findViewById(R.id.firstview__date);
		time=(TextView)v.findViewById(R.id.firstview__time);
		setDateTime();
		setSpinner();

		attendance.setOnClickListener(this);
		marks.setOnClickListener(this);
		sendmail.setOnClickListener(this);
		sendsms.setOnClickListener(this);
		Bluetooth_attendance.setOnClickListener(this);
		logout();
		return v;
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
	
	private void setDateTime() {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		int monthNo = c.get(Calendar.MONTH) + 1;
		c.get(Calendar.DAY_OF_MONTH);
		c.get(Calendar.YEAR);
		c.get(Calendar.SECOND);
		c.get(Calendar.MINUTE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		date.setText(" " + c.get(Calendar.DAY_OF_MONTH)+ "-" + monthNo + "-"
				+ c.get(Calendar.YEAR)) ; 
		time.setText(" " + hour + ":" + c.get(Calendar.MINUTE) + ":"
				+ c.get(Calendar.SECOND));

	}
	
	private void setSpinner() {
		// TODO Auto-generated method stub

		// For Branch Spinner
		final LecturerInfoNetwork obj = new LecturerInfoNetwork();

		new Thread(new Runnable() {

			public void run() {
				yearBranchId = obj.getLecturerBranchYearId("" + lID);
				System.out.println("LEct_yrtable =" + yearBranchId);
				YearId = obj.getAllYear();
				System.out.println("YearId =" + YearId);
				BranchId = obj.getAllBranches();
				System.out.println("Branch =" + BranchId);
				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						ArrayList<BranchYearInfo> branchInfo = parseJsonBranch(BranchId);
						Iterator branchitr = branchInfo.iterator();
						while (branchitr.hasNext()) {
							BranchYearInfo linfo = (BranchYearInfo) branchitr
									.next();
							branchArray.add(linfo);
							branchName.add(linfo.getBranch_name());
							System.out.println("bName " + linfo.getBranch_id());

						}
						ArrayAdapter<String> branchAdapter = new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_spinner_item,
								branchName);
						branchAdapter
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						branch.setAdapter(branchAdapter);

						ArrayList<BranchYearInfo> yearInfo = parseJsonYear(YearId);

						Iterator yearitr = yearInfo.iterator();
						while (yearitr.hasNext()) {
							BranchYearInfo linfo = (BranchYearInfo) yearitr
									.next();
							yearArray.add(linfo);
							yearName.add(linfo.getYear_name());
							System.out.println("yName " + linfo.getYear_id());

						}

						ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_spinner_item, yearName);
						yearAdapter
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						year.setAdapter(yearAdapter);

						ArrayList<BranchYearInfo> mainInfo = parseJson(yearBranchId);
						Iterator itr = mainInfo.iterator();
						while (itr.hasNext()) {
							BranchYearInfo linfo = (BranchYearInfo) itr.next();
							idBY.add(linfo);
						}

					}

				});
			}
		}).start();
	}

	@Override
	public void onClick(View vc) {
		// TODO Auto-generated method stub
		switch (vc.getId()) {
		
		case R.id.firstview_btnatendnc:
			int click1 = 1;
			setSubject(click1);

			
			
			
			break;

		case R.id.firstview_btnatenbluetooth:
			int click3=3;
			setSubject(click3);
			break;
			
		case R.id.firstview_btnmarks:

			int click2 = 2;
			setSubject(click2);

			break;
		case R.id.firstview_btnemail:
			LecturerActivity main = (LecturerActivity) getActivity();
			main.LectSendemail();

			break;
			
		case R.id.firstview_btnsms:
			LecturerActivity main1 = (LecturerActivity) getActivity();
			main1.LectSmssend();

		}
	}

	public void setSubject(int click) {
		int check = 0;
		String bid = "";
		String yid = "";
		String brName = branch.getSelectedItem().toString();
		Iterator bitr = branchArray.iterator();
		while (bitr.hasNext()) {
			BranchYearInfo binfo = (BranchYearInfo) bitr.next();
			if (binfo.getBranch_name() == brName) {

				bid = binfo.getBranch_id();
				
				
				break;
				
			}
		}

		String yrName = year.getSelectedItem().toString();
		Iterator yitr = yearArray.iterator();
		while (yitr.hasNext()) {
			BranchYearInfo yinfo = (BranchYearInfo) yitr.next();
			if (yinfo.getYear_name() == yrName) {

				yid = yinfo.getYear_id();
				break;
			}

		}
		Iterator itr = idBY.iterator();
		while (itr.hasNext()) {
			BranchYearInfo linfo = (BranchYearInfo) itr.next();
			System.out.println("Lbr=bid :" + linfo.getBranch_id() + "=" + bid);
			System.out.println("Lyr=yid :" + linfo.getYear_id() + "=" + yid);
			if (linfo.getBranch_id().equals(bid)
					&& linfo.getYear_id().equals(yid)) {
				check = 1;
				break;
			}

		}

		if (click == 1 && check == 1) {
			LecturerActivity main = (LecturerActivity) getActivity();
			Bundle yearBranchid = new Bundle();
			yearBranchid.putString("YearID", yid);
			yearBranchid.putString("BranchID", bid);
			yearBranchid.putString("BranchName", brName);
			yearBranchid.putString("YearName", yrName);
			main.setYearBranchID(yearBranchid);
			main.attendanceFragment();
			
		} else if (click == 2 && check == 1) {
			LecturerActivity main1 = (LecturerActivity) getActivity();
			Bundle yearBranchid = new Bundle();
			yearBranchid.putString("YearID", yid);
			yearBranchid.putString("BranchID", bid);
			yearBranchid.putString("BranchName", brName);
			yearBranchid.putString("YearName", yrName);
			main1.setYearBranchID(yearBranchid);
			main1.marksFragment();
		}else if(click == 3 && check == 1) {
			LecturerActivity main = (LecturerActivity) getActivity();
			Bundle yearBranchid = new Bundle();
			yearBranchid.putString("YearID", yid);
			yearBranchid.putString("BranchID", bid);
			yearBranchid.putString("BranchName", brName);
			yearBranchid.putString("YearName", yrName);
			main.setYearBranchID(yearBranchid);
			main.attendanceFragmentmanual();
		}
		else {
			Toast.makeText(getActivity(), "Enter Correct Branch And Year",
					Toast.LENGTH_SHORT).show();
		}
	}

	public ArrayList<BranchYearInfo> parseJson(String result) {
		ArrayList<BranchYearInfo> mainObj = new ArrayList<BranchYearInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				BranchYearInfo menu = new BranchYearInfo();
				menu.setLecturer_id(jsonObj.getString("Lect_id"));
				menu.setBranch_id(jsonObj.getString("Branch_id"));
				menu.setYear_id(jsonObj.getString("Year_id"));

				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	public ArrayList<BranchYearInfo> parseJsonBranch(String result) {
		ArrayList<BranchYearInfo> mainObj = new ArrayList<BranchYearInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				BranchYearInfo menu = new BranchYearInfo();
				menu.setBranch_id(jsonObj.getString("Branch_id"));
				menu.setBranch_name(jsonObj.getString("Branch_Name"));
				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	public ArrayList<BranchYearInfo> parseJsonYear(String result) {
		ArrayList<BranchYearInfo> mainObj = new ArrayList<BranchYearInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				BranchYearInfo menu = new BranchYearInfo();
				menu.setYear_id(jsonObj.getString("Year_id"));
				menu.setYear_name(jsonObj.getString("Year_Name"));
				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

}
