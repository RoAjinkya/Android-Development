package com.example.studentattendencesystem.studentlogin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.NetworkClass.ExamInfoNetwork;
import com.example.studentattendencesystem.NetworkClass.StudentInfoNetwork;
import com.example.studentattendencesystem.javaclasses.ExamInfo;

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
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class StudentViewMarksFragment extends Fragment implements
		OnClickListener {
	View v;
	Spinner ex_name;
	Button ok;
	TextView marks, outoff, percentage, date;
	TextView crntdate, name, logout;
	String allExam, sid, eid, studINFO, fname, lname;
	ArrayList<ExamInfo> examArrayId;
	Bundle sidBundl;
	String emarks = "", eoutoff = "", epercentage = "", edate = "";
	private ArrayList<String> subStringArray;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.student_marks, container, false);
		StudentActivity main = (StudentActivity) getActivity();
		sidBundl = main.getloginStudentInfoBundle();
		sid = sidBundl.getString("StudID");
		fname = sidBundl.getString("FName");
		lname = sidBundl.getString("LName");

		examArrayId = new ArrayList<ExamInfo>();
		subStringArray = new ArrayList<String>();
		ex_name = (Spinner) v.findViewById(R.id.Studentmarks_exam);
		ok = (Button) v.findViewById(R.id.Studentmarks_btnok);
		marks = (TextView) v.findViewById(R.id.Studentmarks_marks);
		outoff = (TextView) v.findViewById(R.id.Studentmarks_outoff);
		percentage = (TextView) v.findViewById(R.id.Studentmarks_Percentage);
		date = (TextView) v.findViewById(R.id.Studentmarks_date);
		crntdate = (TextView) v.findViewById(R.id.Studentmarks_crntdate);
		name = (TextView) v.findViewById(R.id.Studentmarks_name);
		logout = (TextView) v.findViewById(R.id.Studentmarks_logout);
		ok.setOnClickListener(this);
		setSpinner();
		setStudInfo();
		return v;
	}

	public void setStudInfo() {

		Calendar c = Calendar.getInstance();
		int monthNo = c.get(Calendar.MONTH) + 1;
		int dayNo = c.get(Calendar.DAY_OF_MONTH);
		int yearNo = c.get(Calendar.YEAR);

		crntdate.setText(" " + c.get(Calendar.DAY_OF_MONTH) + "-" + monthNo
				+ "-" + c.get(Calendar.YEAR));
		name.setText(fname + " " + lname);

		logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				StudentActivity main = (StudentActivity) getActivity();
				main.studentLogINFragment();
				Toast.makeText(getActivity(),
						"You are Logged Out Successfully..", Toast.LENGTH_SHORT)
						.show();
			}
		});

	}

	private void setSpinner() {
		// TODO Auto-generated method stub

		final ExamInfoNetwork obj = new ExamInfoNetwork();
		// menuList = source.getAllComments();

		new Thread(new Runnable() {

			public void run() {

				allExam = obj.getExamName();

				getActivity().runOnUiThread(new Runnable() {
					public void run() {

						ArrayList<ExamInfo> mainInfo = parseJson(allExam);
						Iterator itr = mainInfo.iterator();
						while (itr.hasNext()) {
							ExamInfo info = (ExamInfo) itr.next();
							examArrayId.add(info);
							subStringArray.add(info.getExamName());

						}

						ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_spinner_item,
								subStringArray);
						yearAdapter
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						ex_name.setAdapter(yearAdapter);

					}
				});

			}
		}).start();

	}

	public ArrayList<ExamInfo> parseJson(String result) {
		ArrayList<ExamInfo> mainObj = new ArrayList<ExamInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				ExamInfo menu = new ExamInfo();
				menu.setExamId(jsonObj.getString("Exam_Id"));
				menu.setExamName(jsonObj.getString("Name_Exam"));

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
		case R.id.Studentmarks_btnok:

			eid = ex_name.getSelectedItem().toString();
			final StudentInfoNetwork obj = new StudentInfoNetwork();
			// menuList = source.getAllComments();

			new Thread(new Runnable() {

				public void run() {

					studINFO = obj.getStudentMarks(sid, eid);

					System.out.println("studINFO====" + studINFO);

					getActivity().runOnUiThread(new Runnable() {
						public void run() {

							ArrayList<ExamInfo> mainInfo = parseJsonMarks(studINFO);
							Iterator itr = mainInfo.iterator();
							while (itr.hasNext()) {
								ExamInfo info = (ExamInfo) itr.next();
								emarks = info.getMarks();
								eoutoff = info.getOutOff();
								epercentage = info.getPercentage();
								edate = info.getExamDate();

							}

							if (emarks.equals("")) {
								Toast.makeText(getActivity(),
										"Exam Not Given yet...",
										Toast.LENGTH_SHORT).show();
								date.setText(edate);
								marks.setText(emarks);
								outoff.setText(eoutoff);
								percentage.setText(epercentage);

							} else {

								date.setText(edate);
								marks.setText(emarks);
								outoff.setText(eoutoff);
								percentage.setText(epercentage);

								edate = "";
								emarks = "";
								eoutoff = "";
								epercentage = "";

							}
						}
					});

				}
			}).start();
			break;

		default:
			break;
		}

	}

	public ArrayList<ExamInfo> parseJsonMarks(String result) {
		ArrayList<ExamInfo> mainObj = new ArrayList<ExamInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				ExamInfo menu = new ExamInfo();
				menu.setMarks(jsonObj.getString("Marks"));
				menu.setOutOff(jsonObj.getString("OutOffMarks"));
				menu.setPercentage(jsonObj.getString("Percentage"));
				menu.setExamDate(jsonObj.getString("ExamDate"));

				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

}
