package com.example.studentattendencesystem.admin;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.NetworkClass.AdminInfoNetwork;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;
import com.example.studentattendencesystem.javaclasses.LectuererInfo;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class AdminLecturerInfoFragment extends Fragment implements
		OnClickListener {
	View v;
	Spinner lectname;
	Button ok, subject;
	String Lecturer_INFO, lectID, getAllIDs,lnm="";
	ArrayList<LectuererInfo> lecturerArray;
	ArrayList<BranchYearInfo> idsArray;
	ArrayList<String> lectnameArray;
	int selectedPosn;
	TextView lectId, lectName, lectAddrs, lectPhone, lectEmail;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.admin_lecturerinfo, container, false);
		lecturerArray = new ArrayList<LectuererInfo>();
		lectnameArray = new ArrayList<String>();
		idsArray = new ArrayList<BranchYearInfo>();
		lectname = (Spinner) v.findViewById(R.id.adminlectinfo_spinnername);
		ok = (Button) v.findViewById(R.id.adminlectinfo_submit);
		subject = (Button) v.findViewById(R.id.adminlectinfo_btnsub);
		subject.setOnClickListener(this);
		ok.setOnClickListener(this);
		setLecturerNameSpinner();
		lectId = (TextView) v.findViewById(R.id.adminlectinfo_ID);
		lectName = (TextView) v.findViewById(R.id.adminlectinfo_name);
		lectAddrs = (TextView) v.findViewById(R.id.adminlectinfo_addrss);
		lectEmail = (TextView) v.findViewById(R.id.adminlectinfo_email);
		lectPhone = (TextView) v.findViewById(R.id.adminlectinfo_contact);
		lectname.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				System.out.println("ITEM POSITION=" + arg2);

				selectedPosn = arg2;
				LectuererInfo lectnminfo = lecturerArray.get(selectedPosn);
				lectID = "" + lectnminfo.getLect_id();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		return v;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		case R.id.adminlectinfo_submit:
			setLecturerINFO();
			getSubjectbrnchyrIDS();
			break;
		case R.id.adminlectinfo_btnsub:
			if (lnm.equals("")) {
				Toast.makeText(getActivity(), "Click On Ok Button..!!",
						Toast.LENGTH_SHORT).show();
			} else {

				AdminActivity main = (AdminActivity) getActivity();
				main.adminLecturerSubject(idsArray);
			}
			break;

		default:
			break;
		}

	}

	private void getSubjectbrnchyrIDS() {
		// TODO Auto-generated method stub
		final AdminInfoNetwork obj = new AdminInfoNetwork();

		new Thread(new Runnable() {

			public void run() {
				System.out.println("lectID=" + lectID);
				getAllIDs = obj.getAdminSubBrnchYearIDs(lectID);
				System.out.println("getAllIDs =" + getAllIDs);

				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						ArrayList<BranchYearInfo> lectInfo = parseJsonBranchYrSub(getAllIDs);
						Iterator branchitr = lectInfo.iterator();
						while (branchitr.hasNext()) {
							BranchYearInfo ainfo = (BranchYearInfo) branchitr
									.next();
							idsArray.add(ainfo);

							System.out.println("idsArray  " + idsArray);

						}

					}

				});
			}
		}).start();

	}

	private void setLecturerINFO() {
		// TODO Auto-generated method stub
		LectuererInfo lectinfo = lecturerArray.get(selectedPosn);

		lectId.setText(lectID);
		AdminActivity main = (AdminActivity) getActivity();
		Bundle bndl = new Bundle();
		bndl.putString("LectID", lectID);
		main.setAdminLectid(bndl);
		lnm=lectinfo.getLect_Lname() + " "
				+ lectinfo.getLect_Fname();
		lectName.setText(lnm);
		
		lectAddrs.setText(lectinfo.getLect_address());
		lectEmail.setText(lectinfo.getLect_email());
		lectPhone.setText("" + lectinfo.getLect_contact());

	}

	private void setLecturerNameSpinner() {
		// TODO Auto-generated method stub

		final AdminInfoNetwork obj = new AdminInfoNetwork();

		new Thread(new Runnable() {

			public void run() {
				Lecturer_INFO = obj.getAdminLecturerInfo();
				System.out.println("Lecturer_INFO =" + Lecturer_INFO);

				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						ArrayList<LectuererInfo> lectInfo = parseJsonBranch(Lecturer_INFO);
						Iterator branchitr = lectInfo.iterator();
						while (branchitr.hasNext()) {
							LectuererInfo ainfo = (LectuererInfo) branchitr
									.next();
							lecturerArray.add(ainfo);
							lectnameArray.add(ainfo.getLect_Lname() + " "
									+ ainfo.getLect_Fname());
							System.out.println("lectnameArray "
									+ ainfo.getLect_Fname() + " "
									+ ainfo.getLect_Lname());

							System.out.println("lecturerArray00"
									+ lecturerArray);

						}
						ArrayAdapter<String> branchAdapter = new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_spinner_item,
								lectnameArray);
						branchAdapter
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						lectname.setAdapter(branchAdapter);

					}

				});
			}
		}).start();

	}

	public ArrayList<LectuererInfo> parseJsonBranch(String result) {
		ArrayList<LectuererInfo> mainObj = new ArrayList<LectuererInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				LectuererInfo menu = new LectuererInfo();
				menu.setLect_id(jsonObj.getInt("Lecturer_id"));
				menu.setLect_Fname(jsonObj.getString("FirstName"));
				menu.setLect_Lname(jsonObj.getString("LastName"));
				menu.setLect_address(jsonObj.getString("City"));
				menu.setLect_contact(jsonObj.getInt("Contact_No"));
				menu.setLect_email(jsonObj.getString("Email_Id"));
				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	public ArrayList<BranchYearInfo> parseJsonBranchYrSub(String result) {
		ArrayList<BranchYearInfo> mainObj = new ArrayList<BranchYearInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				BranchYearInfo menu = new BranchYearInfo();
				menu.setLecturer_id(jsonObj.getString("Lect_id"));
				menu.setSubject_id(jsonObj.getString("Subject_Id"));
				menu.setBranch_id(jsonObj.getString("Branch_id"));
				menu.setYear_id(jsonObj.getString("Year_id"));
				mainObj.add(menu);
			}

		} catch (Exception e) {

		}
		return mainObj;
	}

}
