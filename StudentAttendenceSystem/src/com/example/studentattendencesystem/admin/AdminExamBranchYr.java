package com.example.studentattendencesystem.admin;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.LecturerActivity;
import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.NetworkClass.AdminInfoNetwork;
import com.example.studentattendencesystem.NetworkClass.LecturerInfoNetwork;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;
import com.example.studentattendencesystem.javaclasses.StudentInfo;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

@SuppressLint("NewApi")
public class AdminExamBranchYr extends Fragment {
	View v;
	Spinner brnch, year;
	Button ok;
	String spYear, spBranch, bid="", yid="", bdataid, ydataid, bname, yname;
	ArrayList<String> branchArray, yearArray;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.admin_studntexam_by, container, false);
		branchArray = new ArrayList<String>();
		yearArray = new ArrayList<String>();

		brnch = (Spinner) v.findViewById(R.id.adminexamby_branch);
		year = (Spinner) v.findViewById(R.id.adminexamby_year);
		ok = (Button) v.findViewById(R.id.adminexamby_btn);
		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				getBYID();
				
				
			}
		});

		setBranchYearSpinner();
		return v;
	}

	protected void getBYID() {
		// TODO Auto-generated method stub
		bname = brnch.getSelectedItem().toString();
		yname = year.getSelectedItem().toString();
		
		System.out.println("bname =" + bname);
		System.out.println("yname =" + yname);
		

		final AdminInfoNetwork obj = new AdminInfoNetwork();

		new Thread(new Runnable() {

			public void run() {

				bdataid = obj.getAdminBranchID(bname);
				ydataid = obj.getAdminYearID(yname);
				getActivity().runOnUiThread(new Runnable() {
					public void run() {

						ArrayList<BranchYearInfo> mainInfo = parseJsonBranchID(bdataid);
						Iterator itr = mainInfo.iterator();
						while (itr.hasNext()) {
							BranchYearInfo binfo = (BranchYearInfo) itr.next();
							bid=binfo.getBranch_id();
						}

						ArrayList<BranchYearInfo> subInfo = parseJsonYearID(ydataid);
						Iterator subitr = subInfo.iterator();
						while (subitr.hasNext()) {
							BranchYearInfo yinfo = (BranchYearInfo) subitr
									.next();
							yid=yinfo.getYear_id();
							
						}

						System.out.println("BID<<YID=="+bid+"<<"+yid);
						Bundle bndl = new Bundle();
						bndl.putString("BranchName", bname);
						bndl.putString("YeanName", yname);
						bndl.putString("BranchID", bid);
						bndl.putString("YeanID", yid);
						AdminActivity main = (AdminActivity) getActivity();
						main.setBYNameBundle(bndl);
						main.adminViewExamMarks();

					}
				});
			}
		}).start();

	}

	private void setBranchYearSpinner() {
		// TODO Auto-generated method stub

		final AdminInfoNetwork obj = new AdminInfoNetwork();

		new Thread(new Runnable() {

			public void run() {
				spYear = obj.getAdminAllYear();
				System.out.println("spYear =" + spYear);
				spBranch = obj.getAdminAllBranch();
				System.out.println("spBranch =" + spBranch);

				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						ArrayList<BranchYearInfo> branchInfo = parseJsonBranch(spBranch);
						Iterator branchitr = branchInfo.iterator();
						while (branchitr.hasNext()) {
							BranchYearInfo ainfo = (BranchYearInfo) branchitr
									.next();
							branchArray.add(ainfo.getBranch_name());
							System.out.println("bName " + ainfo.getBranch_id());

						}
						ArrayAdapter<String> branchAdapter = new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_spinner_item,
								branchArray);
						branchAdapter
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						brnch.setAdapter(branchAdapter);

						ArrayList<BranchYearInfo> yearInfo = parseJsonYear(spYear);

						Iterator yearitr = yearInfo.iterator();
						while (yearitr.hasNext()) {
							BranchYearInfo linfo = (BranchYearInfo) yearitr
									.next();
							yearArray.add(linfo.getYear_name());
							System.out.println("yName " + linfo.getYear_id());

						}

						ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_spinner_item, yearArray);
						yearAdapter
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						year.setAdapter(yearAdapter);

					}

				});
			}
		}).start();
		
		

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
	
	
	

	public ArrayList<BranchYearInfo> parseJsonBranchID(String result) {
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

	public ArrayList<BranchYearInfo> parseJsonYearID(String result) {
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
