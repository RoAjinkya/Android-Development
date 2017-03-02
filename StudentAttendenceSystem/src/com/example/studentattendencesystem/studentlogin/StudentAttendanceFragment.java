package com.example.studentattendencesystem.studentlogin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.LecturerActivity;
import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.NetworkClass.LecturerInfoNetwork;
import com.example.studentattendencesystem.NetworkClass.StudentInfoNetwork;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;
import com.example.studentattendencesystem.javaclasses.StudentInfo;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class StudentAttendanceFragment extends Fragment implements
		OnClickListener {
	View v;
	Spinner subject;
	TextView attendnce;
	EditText dd, mm, yyyy;
	TextView crntdate, studname, logout;
	Button ok;
	String bid, yid, subids, allsubjects, day = "", month = "", yr = "";
	String date = "", selectedSub, sid, studAttStatus, attendance;
	Bundle sidBundl;
	private ArrayList<BranchYearInfo> subArrayId, subArray;
	private ArrayList<String> subStringArray;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.student_attendance, container, false);
		subArray = new ArrayList<BranchYearInfo>();
		subArrayId = new ArrayList<BranchYearInfo>();
		subStringArray = new ArrayList<String>();
		subject = (Spinner) v.findViewById(R.id.studattence_subject);
		attendnce = (TextView) v.findViewById(R.id.studattence_att);
		crntdate = (TextView) v.findViewById(R.id.studattence_crntdate);
		studname = (TextView) v.findViewById(R.id.studattence_studname);
		logout = (TextView) v.findViewById(R.id.studattence_logout);
		dd = (EditText) v.findViewById(R.id.studattence_dd);
		mm = (EditText) v.findViewById(R.id.studattence_mm);
		yyyy = (EditText) v.findViewById(R.id.studattence_yyyy);
		ok = (Button) v.findViewById(R.id.studattence_btnok);
		StudentActivity main = (StudentActivity) getActivity();
		Bundle sinfobndl = main.getloginStudentInfoBundle();
		String sfname = sinfobndl.getString("FName");
		String slname = sinfobndl.getString("LName");
		studname.setText(sfname + " " + slname);
		ok.setOnClickListener(this);
		setCurrentDateAndTime();
		setSpinner();

		logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				StudentActivity main = (StudentActivity) getActivity();
				main.studentLogINFragment();
				Toast.makeText(getActivity(),
						"You are Logged out Successfully..", Toast.LENGTH_SHORT)
						.show();
			}
		});
		dd.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				try {
					day_attendance(31, dd);
					attendance = "";
				} catch (NumberFormatException e) {
					dd.setError("Enter Number...");
				}
			}
		});

		mm.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				try {
					month_attendance(12, mm);
				} catch (NumberFormatException e) {
					mm.setError("Enter Number...");
				}
			}
		});

		yyyy.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				try {
					Year_attendance(4, yyyy);
				} catch (NumberFormatException e) {
					mm.setError("Enter Number...");
				}
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
		crntdate.setText("" + dayNo + "-" + monthNo + "-" + yearNo);
		yyyy.setText("" + yearNo);
	}

	private void setSpinner() {
		// TODO Auto-generated method stub
		StudentActivity main = (StudentActivity) getActivity();
		Bundle BYBundle = main.getStudBrnchYrBundle();
		bid = BYBundle.getString("Branch");
		yid = BYBundle.getString("Year");

		final StudentInfoNetwork obj = new StudentInfoNetwork();

		new Thread(new Runnable() {

			public void run() {

				subids = obj.getStudentSubjectesID(bid, yid);
				allsubjects = obj.getAllSubjects();
				getActivity().runOnUiThread(new Runnable() {
					public void run() {

						ArrayList<BranchYearInfo> mainInfo = parseJson(subids);
						Iterator itr = mainInfo.iterator();
						while (itr.hasNext()) {
							BranchYearInfo info = (BranchYearInfo) itr.next();
							subArrayId.add(info);
						}

						ArrayList<BranchYearInfo> subInfo = parseJsonSub(allsubjects);
						Iterator subitr = subInfo.iterator();
						while (subitr.hasNext()) {
							BranchYearInfo info = (BranchYearInfo) subitr
									.next();
							subArray.add(info);
						}

						for (int j = 0; j < subArrayId.size(); j++) {
							BranchYearInfo idTabSub = subArray.get(j);
							String subId1 = idTabSub.getSubject_id();
							System.out.println("subId1 =" + subId1);

							for (int i = 0; i < subArray.size(); i++) {
								BranchYearInfo mTabSub = subArray.get(i);
								String subId2 = mTabSub.getSubject_id();
								System.out.println("subId2 =" + subId2);

								if (subId1.equals(subId2)) {
									subStringArray.add(mTabSub
											.getSubject_name());
									System.out.println(mTabSub
											.getSubject_name());
									break;
								}

							}
						}

						ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_spinner_item,
								subStringArray);
						yearAdapter
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						subject.setAdapter(yearAdapter);

					}
				});
			}
		}).start();

	}

	public void day_attendance(int dd, EditText edt)
			throws NumberFormatException {
		if (edt.getText().toString().length() < 1) {
			edt.setError("Enter Day");
			day = "";
		} else if (edt.getText().toString().length() > 2) {
			edt.setError("Invalid Day");
			day = "";
		} else {
			if (Integer.parseInt(edt.getText().toString()) <= 0
					|| Integer.parseInt(edt.getText().toString()) > dd) {
				edt.setError("Enter Valid Day");
				day = "";
			} else {
				day = edt.getText().toString();
			}

		}

	}

	private void month_attendance(int i, EditText edt) {
		// TODO Auto-generated method stub
		if (edt.getText().toString().length() < 1) {
			edt.setError("Enter Month");
			month = "";
		} else if (edt.getText().toString().length() > 2) {
			edt.setError("Invalid Month");
			month = "";
		} else {
			if (Integer.parseInt(edt.getText().toString()) <= 0
					|| Integer.parseInt(edt.getText().toString()) > i) {
				edt.setError("Enter Valid Month");
				month = "";
			} else {
				month = edt.getText().toString();
			}

		}
	}

	private void Year_attendance(int i, EditText edt) {
		// TODO Auto-generated method stub
		if (edt.getText().toString().length() < i) {
			edt.setError("Enter Year");
			yr = "";
		} else if (edt.getText().toString().length() > i) {
			edt.setError("Invalid Year");
			yr = "";
		} else {

			yr = edt.getText().toString();

		}
	}

	public ArrayList<BranchYearInfo> parseJsonSub(String result) {
		ArrayList<BranchYearInfo> mainObj = new ArrayList<BranchYearInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				BranchYearInfo menu = new BranchYearInfo();
				menu.setSubject_id(jsonObj.getString("Subject_id"));
				menu.setSubject_name(jsonObj.getString("Subject_Name"));
				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	public ArrayList<BranchYearInfo> parseJson(String result) {
		ArrayList<BranchYearInfo> mainObj = new ArrayList<BranchYearInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				BranchYearInfo menu = new BranchYearInfo();
				menu.setSubject_id(jsonObj.getString("Sub_id"));
				menu.setBranch_id(jsonObj.getString("Branch_id"));
				menu.setYear_id(jsonObj.getString("Year_id"));

				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		case R.id.studattence_btnok:
			day = dd.getText().toString();
			month = mm.getText().toString();
			yr = yyyy.getText().toString();
			if (day.equals("") || month.equals("") || yr.equals("")) {
				Toast.makeText(getActivity(), "Enter Correct Date..",
						Toast.LENGTH_SHORT).show();
			} else {
				date = yr + "-" + month + "-" + day;
				selectedSub = subject.getSelectedItem().toString();
				StudentActivity main = (StudentActivity) getActivity();
				sidBundl = main.getloginStudentInfoBundle();
				sid = sidBundl.getString("StudID");

				final StudentInfoNetwork obj = new StudentInfoNetwork();

				new Thread(new Runnable() {

					public void run() {
						System.out.println("sid=" + sid + " date=" + date
								+ " selectedSub=" + selectedSub);
						studAttStatus = obj.getStudentStatus(sid, date,
								selectedSub);

						getActivity().runOnUiThread(new Runnable() {
							public void run() {

								ArrayList<StudentInfo> mainInfo = parseJsonATTendance(studAttStatus);
								Iterator itr = mainInfo.iterator();
								while (itr.hasNext()) {
									StudentInfo info = (StudentInfo) itr.next();
									attendance = info.getStatus();
								}
								System.out.println("DDD" + attendance);
								if (attendance.equals("")) {
									attendnce.setText("Not Submited");
								} else {
									attendnce.setText(attendance);
									attendance = "";
								}

							}
						});
					}
				}).start();

			}

			break;

		default:
			break;
		}

	}

	public ArrayList<StudentInfo> parseJsonATTendance(String result) {
		ArrayList<StudentInfo> mainObj = new ArrayList<StudentInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				StudentInfo menu = new StudentInfo();

				menu.setStatus(jsonObj.getString("Status"));

				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}
}
