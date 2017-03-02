package com.example.studentattendencesystem.admin;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.NetworkClass.AdminInfoNetwork;
import com.example.studentattendencesystem.NetworkClass.LecturerInfoNetwork;
import com.example.studentattendencesystem.javaclasses.*;

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
public class AdminViewExamInfoFragment extends Fragment implements
		OnClickListener {
	View v;
	TextView branch, year, name, sem1, sem2, sem3, sem4, sem5, sem6, sem7,
			sem8;
	TextView chcksem1, chcksem2, chcksem3, chcksem4, chcksem5, chcksem6,
			chcksem7, chcksem8;
	Button ok, chngyr, clr;
	Spinner studid;
	Bundle bynameBndl;
	String bname, yname, bid, yid, studIDs, Exam_Info, sid, StudName,
			getsid = "", Graduate;
	ArrayList<String> StudentArray, sendArray;
	int chngyear;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.admin_studentexam_ex, container, false);
		branch = (TextView) v.findViewById(R.id.adminviewex_branch);
		StudentArray = new ArrayList<String>();
		sendArray = new ArrayList<String>();

		System.out.println("StudentArray====" + StudentArray.size());
		year = (TextView) v.findViewById(R.id.adminviewex_year);
		name = (TextView) v.findViewById(R.id.adminviewex_name);
		sem1 = (TextView) v.findViewById(R.id.adminviewex_sem1);
		sem2 = (TextView) v.findViewById(R.id.adminviewex_sem2);
		sem3 = (TextView) v.findViewById(R.id.adminviewex_sem3);
		sem4 = (TextView) v.findViewById(R.id.adminviewex_sem4);
		sem5 = (TextView) v.findViewById(R.id.adminviewex_sem5);
		sem6 = (TextView) v.findViewById(R.id.adminviewex_sem6);
		sem7 = (TextView) v.findViewById(R.id.adminviewex_sem7);
		sem8 = (TextView) v.findViewById(R.id.adminviewex_sem8);

		chcksem1 = (TextView) v.findViewById(R.id.adminviewex_chcksem1);
		chcksem2 = (TextView) v.findViewById(R.id.adminviewex_chcksem2);
		chcksem3 = (TextView) v.findViewById(R.id.adminviewex_chcksem3);
		chcksem4 = (TextView) v.findViewById(R.id.adminviewex_chcksem4);
		chcksem5 = (TextView) v.findViewById(R.id.adminviewex_chcksem5);
		chcksem6 = (TextView) v.findViewById(R.id.adminviewex_chcksem6);
		chcksem7 = (TextView) v.findViewById(R.id.adminviewex_chcksem7);
		chcksem8 = (TextView) v.findViewById(R.id.adminviewex_chcksem8);
		studid = (Spinner) v.findViewById(R.id.adminviewex_studID);
		ok = (Button) v.findViewById(R.id.adminviewex_btn);
		chngyr = (Button) v.findViewById(R.id.adminviewex_nxtyearBtn);
		clr = (Button) v.findViewById(R.id.adminviewex_clrbtn);
		ok.setOnClickListener(this);
		clr.setOnClickListener(this);
		chngyr.setOnClickListener(this);

		AdminActivity main = (AdminActivity) getActivity();
		bynameBndl = main.getBYNameBundle();
		bname = bynameBndl.getString("BranchName");
		yname = bynameBndl.getString("YeanName");
		bid = bynameBndl.getString("BranchID");
		yid = bynameBndl.getString("YeanID");

		branch.setText(bname);
		year.setText(yname);
		setSpinnerStudId();

		return v;
	}

	private void setSpinnerStudId() {
		// TODO Auto-generated method stub

		final AdminInfoNetwork obj = new AdminInfoNetwork();

		new Thread(new Runnable() {

			public void run() {
				studIDs = obj.getAdminStudentID(yid, bid);
				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						ArrayList<BranchYearInfo> branchInfo = parseJsonStudid(studIDs);
						Iterator itr = branchInfo.iterator();
						while (itr.hasNext()) {
							BranchYearInfo info = (BranchYearInfo) itr.next();
							StudentArray.add(info.getStudent_id());
							getsid = info.getStudent_id();

						}
						if (getsid.equals("")) {
							StudentArray.add("No Student Present");
						} else {
							Iterator siter = StudentArray.iterator();
							int i = 0;
							while (siter.hasNext()) {
								String sinfo = (String) siter.next();
								if (sendArray.size() > 0) {
									for (i = 0; i < sendArray.size(); i++) {
										if (sinfo.equals(sendArray.get(i))) {
											break;
										}
									}
								}

								if (i == sendArray.size()) {
									sendArray.add(sinfo);
								}
							}
						}

						ArrayAdapter<String> branchAdapter = new ArrayAdapter<String>(
								getActivity(),
								android.R.layout.simple_spinner_item, sendArray);
						branchAdapter
								.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						studid.setAdapter(branchAdapter);

					}

				});
			}
		}).start();

	}

	public ArrayList<BranchYearInfo> parseJsonStudid(String result) {
		ArrayList<BranchYearInfo> mainObj = new ArrayList<BranchYearInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				BranchYearInfo menu = new BranchYearInfo();

				menu.setStudent_id(jsonObj.getString("Stud_Id"));

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
		case R.id.adminviewex_btn:

			setExamInfo();
			break;
		case R.id.adminviewex_nxtyearBtn:
			changeStudentYear();
			break;
		case R.id.adminviewex_clrbtn:
			name.setText("null");
			sem1.setText("null");
			sem2.setText("null");
			sem3.setText("null");
			sem4.setText("null");
			sem5.setText("null");
			sem6.setText("null");
			sem7.setText("null");
			sem8.setText("null");
			break;

		default:
			break;
		}

	}

	private void changeStudentYear() {
		// TODO Auto-generated method stub
		System.out.println("getsidvvvvvv" + getsid);
		if (getsid.equals("")) {
			Toast.makeText(getActivity(), "No Student ID Exist..!!",
					Toast.LENGTH_SHORT).show();
		} else {
			if (yid.equals("4")) {
				Graduate = "Graduate";
				final AdminInfoNetwork obj = new AdminInfoNetwork();
				new Thread(new Runnable() {
					public void run() {
						obj.updateStudentyear(Graduate, sid);
						Toast.makeText(getActivity(), "Student Year=" + Graduate,
								Toast.LENGTH_SHORT).show();
					}
				}).start();
			} else {
				chngyear = Integer.parseInt(yid) + 1;
				Graduate = "" + chngyear;
				final AdminInfoNetwork obj = new AdminInfoNetwork();
				new Thread(new Runnable() {
					public void run() {
						obj.updateStudentyear(Graduate, sid);
						
					}
				}).start();
				
				Toast.makeText(getActivity(), "Student Year=" + Graduate,
						Toast.LENGTH_SHORT).show();
			}
			
		}

	}

	private void setExamInfo() {
		// TODO Auto-generated method stub
		if (getsid.equals("")) {
			Toast.makeText(getActivity(), "No Student ID Exist..!!",
					Toast.LENGTH_SHORT).show();
		} else {

			sid = studid.getSelectedItem().toString();

			final AdminInfoNetwork obj = new AdminInfoNetwork();

			new Thread(new Runnable() {

				public void run() {

					Exam_Info = obj.getAdminStudExamInfo(sid);
					StudName = obj.getAdminStudentName(sid);
					getActivity().runOnUiThread(new Runnable() {
						public void run() {

							ArrayList<ExamInfo> mainInfo = parseJsonExam(Exam_Info);
							Iterator itr = mainInfo.iterator();
							while (itr.hasNext()) {
								ExamInfo info = (ExamInfo) itr.next();

								if (chcksem1.getText().toString()
										.equals(info.getExamName())) {
									sem1.setText(info.getPercentage());
									sem2.setText("null");
									sem3.setText("null");
									sem4.setText("null");
									sem5.setText("null");
									sem6.setText("null");
									sem7.setText("null");
									sem8.setText("null");
								} else if (chcksem2.getText().toString()
										.equals(info.getExamName())) {
									sem2.setText(info.getPercentage());
									sem3.setText("null");
									sem4.setText("null");
									sem5.setText("null");
									sem6.setText("null");
									sem7.setText("null");
									sem8.setText("null");
								} else if (chcksem3.getText().toString()
										.equals(info.getExamName())) {
									sem3.setText(info.getPercentage());
									sem4.setText("null");
									sem5.setText("null");
									sem6.setText("null");
									sem7.setText("null");
									sem8.setText("null");
								} else if (chcksem4.getText().toString()
										.equals(info.getExamName())) {
									sem4.setText(info.getPercentage());
									sem5.setText("null");
									sem6.setText("null");
									sem7.setText("null");
									sem8.setText("null");
								} else if (chcksem5.getText().toString()
										.equals(info.getExamName())) {
									sem5.setText(info.getPercentage());
									sem6.setText("null");
									sem7.setText("null");
									sem8.setText("null");
								} else if (chcksem6.getText().toString()
										.equals(info.getExamName())) {
									sem6.setText(info.getPercentage());
									sem7.setText("null");
									sem8.setText("null");
								} else if (chcksem7.getText().toString()
										.equals(info.getExamName())) {
									sem7.setText(info.getPercentage());
									sem8.setText("null");
								} else {
									sem8.setText(info.getPercentage());
								}
							}

							ArrayList<StudentInfo> stnameInfo = parseJsonStudName(StudName);
							Iterator stitr = stnameInfo.iterator();
							while (stitr.hasNext()) {
								StudentInfo stnfo = (StudentInfo) stitr.next();
								name.setText(stnfo.getStud_FirstName() + " "
										+ stnfo.getStud_LastName());
							}

						}

					});
				}
			}).start();
		}

	}

	public ArrayList<ExamInfo> parseJsonExam(String result) {
		ArrayList<ExamInfo> mainObj = new ArrayList<ExamInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				ExamInfo menu = new ExamInfo();

				menu.setExamName(jsonObj.getString("Exam_Id"));
				menu.setPercentage(jsonObj.getString("Percentage"));

				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	public ArrayList<StudentInfo> parseJsonStudName(String result) {
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
}
