package com.example.studentattendencesystem;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.NetworkClass.ExamInfoNetwork;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;
import com.example.studentattendencesystem.javaclasses.ExamInfo;
import com.example.studentattendencesystem.javaclasses.StudentInfo;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class LecturerViewMarksFragment extends Fragment implements
		OnClickListener {
	View v;
	String fname,lname;
	Spinner examSpinnr;
	EditText studId;
	TextView dateTxt, marksTxt, outoffTxt, prcntTxt, sname, sbranch, syear;
	Button ok, clear;
	String allExam, eid, sid, marks, BY_ID, S_NAME, B_NAME, Y_NAME;
	String vMarks = "", vOutoff, vPrcnt, vDate, yid, f_name, l_name, b_name,
	y_name;
	String bid = "";
	TextView lectname, lectLogout;
	ArrayList<ExamInfo> examArrayId, viewExmaArray;
	private ArrayList<String> subStringArray;
	Bundle lectInfoBundle;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.lecture_marksview, container, false);
		LecturerActivity main = (LecturerActivity) getActivity();
		lectInfoBundle = main.getbundleLecturerInfo();
		 lname = lectInfoBundle.getString("FirstName");
		 fname = lectInfoBundle.getString("LastName");
		System.out.println("///////////"+fname+"------"+lname);
		examArrayId = new ArrayList<ExamInfo>();
		viewExmaArray = new ArrayList<ExamInfo>();
		subStringArray = new ArrayList<String>();
		examSpinnr = (Spinner) v.findViewById(R.id.viewmarks_examid);
		studId = (EditText) v.findViewById(R.id.viewmarks_rollno);
		ok = (Button) v.findViewById(R.id.viewmarks_submit);
		clear = (Button) v.findViewById(R.id.viewmarks_clear);
		dateTxt = (TextView) v.findViewById(R.id.viewmarks_edate);
		marksTxt = (TextView) v.findViewById(R.id.viewmarks_marks);
		outoffTxt = (TextView) v.findViewById(R.id.viewmarks_outoff);
		prcntTxt = (TextView) v.findViewById(R.id.viewmarks_Percentage);
		sname = (TextView) v.findViewById(R.id.viewmarks_name);
		sbranch = (TextView) v.findViewById(R.id.viewmarks_branch);
		syear = (TextView) v.findViewById(R.id.viewmarks_year);
	
		
		ok.setOnClickListener(this);
		clear.setOnClickListener(this);
		setSpinner();
		
		studId.addTextChangedListener(new TextWatcher() {

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
					steStudInfo(studId);
				} catch (Exception e) {
					studId.setError("Enter Number");
				}
			}
		});

		return v;
	}

	protected void steStudInfo(final EditText stuid)
			throws NumberFormatException {
		// TODO Auto-generated method stub
		final ExamInfoNetwork obj = new ExamInfoNetwork();
		// menuList = source.getAllComments();
		sid = stuid.getText().toString();
		new Thread(new Runnable() {

			public void run() {

				BY_ID = obj.getStudentBranchYearId(sid);

				getActivity().runOnUiThread(new Runnable() {
					public void run() {

						ArrayList<BranchYearInfo> IDInfo = parseJsonIDs(BY_ID);
						Iterator itre = IDInfo.iterator();
						while (itre.hasNext()) {
							BranchYearInfo info = (BranchYearInfo) itre.next();

							bid = info.getBranch_id();
							yid = info.getYear_id();
						}

					}
				});

			}
		}).start();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.viewmarks_submit:
			eid = examSpinnr.getSelectedItem().toString();
			sid = studId.getText().toString();
			if (bid.equals("")) {
				yid = "";
				bid = "";
				studId.setText("");
				dateTxt.setText("");
				marksTxt.setText("");
				outoffTxt.setText("");
				prcntTxt.setText("");
				sbranch.setText("");
				syear.setText("");
				sname.setText("");
				Toast.makeText(getActivity(), "Enter Correct Student ID...",
						Toast.LENGTH_SHORT).show();
			} else {

				final ExamInfoNetwork obj = new ExamInfoNetwork();
				// menuList = source.getAllComments();

				new Thread(new Runnable() {

					public void run() {

						marks = obj.getExamInfo(eid, sid);

						S_NAME = obj.getStudentName(sid);
						B_NAME = obj.getStudBranchName(bid);
						Y_NAME = obj.getStudYearName(yid);
						System.out.println("marks ====" + marks);
						System.out.println("B_NAME====" + B_NAME);
						getActivity().runOnUiThread(new Runnable() {
							public void run() {

								ArrayList<ExamInfo> mainInfo = parseJsonMarks(marks);
								Iterator itr = mainInfo.iterator();

								while (itr.hasNext()) {
									ExamInfo info = (ExamInfo) itr.next();
									vDate = info.getExamDate();
									vMarks = info.getMarks();
									vOutoff = info.getOutOff();
									vPrcnt = info.getPercentage();

								}
								ArrayList<StudentInfo> nameInfo = parseJsonSNAME(S_NAME);
								Iterator nameitr = nameInfo.iterator();
								while (nameitr.hasNext()) {
									StudentInfo info = (StudentInfo) nameitr
											.next();
									f_name = info.getStud_FirstName();
									l_name = info.getStud_LastName();

								}

								ArrayList<BranchYearInfo> brInfo = parseJsonBNAME(B_NAME);
								Iterator britr = brInfo.iterator();
								while (britr.hasNext()) {
									BranchYearInfo info = (BranchYearInfo) britr
											.next();
									b_name = info.getBranch_name();

								}
								ArrayList<BranchYearInfo> yrInfo = parseJsonYNAME(Y_NAME);
								Iterator yritr = yrInfo.iterator();
								while (yritr.hasNext()) {
									BranchYearInfo info = (BranchYearInfo) yritr
											.next();
									y_name = info.getYear_name();

								}
								if (vMarks.equals("")) {
									Toast.makeText(
											getActivity(),
											eid
													+ " is not Registered for Student ID "
													+ sid, Toast.LENGTH_SHORT)
											.show();
								} else {
									sbranch.setText(b_name);
									syear.setText(y_name);
									sname.setText(f_name + " " + l_name);
									dateTxt.setText(vDate);
									marksTxt.setText(vMarks);
									outoffTxt.setText(vOutoff);
									prcntTxt.setText(vPrcnt);
									bid = "";
									vMarks = "";
								}
							}
						});

					}
				}).start();

			}
			break;
		case R.id.viewmarks_clear:

			yid = "";
			bid = "";
			studId.setText("");
			dateTxt.setText("");
			marksTxt.setText("");
			outoffTxt.setText("");
			prcntTxt.setText("");
			sbranch.setText("");
			syear.setText("");
			sname.setText("");

			break;
		default:
			break;
		}
	}

	private void setSpinner() {
		// TODO Auto-generated method stub

		final ExamInfoNetwork obj = new ExamInfoNetwork();
		// menuList = source.getAllComments();

		new Thread(new Runnable() {

			public void run() {

				allExam = obj.getExamName();

				System.out.println("allExam====" + allExam);

				getActivity().runOnUiThread(new Runnable() {
					public void run() {

						ArrayList<ExamInfo> mainInfo =  parseJsonexam(allExam);
						Iterator itr = mainInfo.iterator();
						while (itr.hasNext()) {
							ExamInfo info = (ExamInfo) itr.next();
							examArrayId.add(info);
							subStringArray.add(info.getExamName());
							System.out.println("exam name======"+info.getExamName());

						}

						ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_spinner_item,
								subStringArray);
						yearAdapter
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						examSpinnr.setAdapter(yearAdapter);

					}
				});

			}
		}).start();

	}

	


	public ArrayList<ExamInfo> parseJsonexam(String result) {
		ArrayList<ExamInfo> mainObj = new ArrayList<ExamInfo>();
		try {
			System.out.println("result==="+result);
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				ExamInfo menu = new ExamInfo();
				menu.setExamId(jsonObj.getString("Exam_id"));
				menu.setExamName(jsonObj.getString("Name_Exam"));

				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	public ArrayList<ExamInfo> parseJsonMarks(String result) {
		ArrayList<ExamInfo> mainObj = new ArrayList<ExamInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				ExamInfo menu = new ExamInfo();
				menu.setExamName(jsonObj.getString("Exam_Id"));
				menu.setStudId(jsonObj.getString("Stud_Id"));
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

	public ArrayList<BranchYearInfo> parseJsonIDs(String result) {
		ArrayList<BranchYearInfo> mainObj = new ArrayList<BranchYearInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				BranchYearInfo menu = new BranchYearInfo();
				menu.setStudent_id(jsonObj.getString("Stud_id"));
				menu.setBranch_id(jsonObj.getString("Branch_id"));
				menu.setYear_id(jsonObj.getString("Year_id"));
				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	public ArrayList<StudentInfo> parseJsonSNAME(String result) {
		ArrayList<StudentInfo> mainObj = new ArrayList<StudentInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				StudentInfo menu = new StudentInfo();
				menu.setStud_FirstName(jsonObj.getString("First_Name"));
				menu.setStud_LastName(jsonObj.getString("Last_Name"));
				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
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
}
