package com.example.studentattendencesystem;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.NetworkClass.ExamInfoNetwork;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;
import com.example.studentattendencesystem.javaclasses.ExamInfo;
import com.example.studentattendencesystem.javaclasses.StudentInfo;

import android.R.string;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class LecturerSubmitStudentInfo extends Fragment {

	View v;
	Spinner examId;
	EditText marksTxt, outoff, rollno;
	Button submit, btnDate;
	TextView date,lectname,lectLogout;
	int year, month, day;
	int dialog = 0;
	Bundle brnchyearid;
	String branchName, yearName, allExam, StudId, bid = "", yid, exDate;
	String sid, eid, marks, outoffMrks, percentage;
	String Student_Exist, S_EXIST = "";
	ArrayList<ExamInfo> examArrayId;
	ArrayList<BranchYearInfo> studentIDArray;
	private ArrayList<String> subStringArray;
	int ssid;
	Bundle lectInfoBundle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.lecturer_entermarks, container, false);
		examArrayId = new ArrayList<ExamInfo>();
		subStringArray = new ArrayList<String>();
		studentIDArray = new ArrayList<BranchYearInfo>();
		examId = (Spinner) v.findViewById(R.id.entermarks_examid);
		outoff = (EditText) v.findViewById(R.id.entermarks_outoff);
		marksTxt = (EditText) v.findViewById(R.id.entermarks_prcent);
		rollno = (EditText) v.findViewById(R.id.entermarks_rollno);
		submit = (Button) v.findViewById(R.id.entermarks_submit);
		btnDate = (Button) v.findViewById(R.id.entermarks_btndate);
		date = (TextView) v.findViewById(R.id.entermarks_date);
		lectname=(TextView)v.findViewById(R.id.entermarks_lectname);
		lectLogout=(TextView)v.findViewById(R.id.entermarks_logout);
		LecturerActivity main = (LecturerActivity) getActivity();
		lectInfoBundle = main.getbundleLecturerInfo();
		String lname = lectInfoBundle.getString("FirstName");
		String fname = lectInfoBundle.getString("LastName");
		lectname.setText(fname+" "+lname);
		setSpinner();
		logout();
		outoff.addTextChangedListener(new TextWatcher() {

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
				
				String id = rollno.getText().toString();
				try{
					int did=Integer.parseInt(id);
				System.out.println(">>>" + did);
				getIDs(did);
				}catch (NumberFormatException e)
				{
					//Toast.makeText(getActivity(), "Enter Correct Student ID..", Toast.LENGTH_SHORT).show();
				}

			}
		});
		marksTxt.addTextChangedListener(new TextWatcher() {

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
				String id = rollno.getText().toString();
				String exid = examId.getSelectedItem().toString();
				System.out.println(">>>---" + id);
				System.out.println(">>>---" + exid);
				checkExam(id, exid);
			}
		});
		btnDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (dialog == 0) {
					ExamDateDialog date = new ExamDateDialog(getActivity());
					date.show();
					dialog = 1;
					btnDate.setText("See ExamDate");
				} else {
					setDate();
					btnDate.setText("Change Date");
					dialog = 0;
				}
			}

		});
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				LecturerActivity main = (LecturerActivity) getActivity();
				Bundle bundle = main.getexamDate();

				eid = examId.getSelectedItem().toString();
				sid = rollno.getText().toString();
				marks = marksTxt.getText().toString();
				outoffMrks = outoff.getText().toString();

				System.out.println("S_EXISToooo" + S_EXIST);
				if (bid.equals("")) {
					marksTxt.setText("");
					rollno.setText("");
					outoff.setText("");
					Toast.makeText(getActivity(),

					"Enter Correct Student ID .. ",
							Toast.LENGTH_SHORT).show();
				} else if (!S_EXIST.equals("")) {
					Toast.makeText(
							getActivity(),
							"Student " + sid + " With " + eid
									+ " Allredy Exist", Toast.LENGTH_SHORT)
							.show();
					rollno.setText("");
					marksTxt.setText("");
					outoff.setText("");
					S_EXIST="";
				} else if (sid.equals("") || outoffMrks.equals("")
						|| marks.equals("")) {
					Toast.makeText(getActivity(),
							"Fields should not be blank...", Toast.LENGTH_SHORT)
							.show();
					rollno.setText("");
					marksTxt.setText("");
					outoff.setText("");
				} else {
					try {
						exDate = bundle.getString("ExamDate");
						System.out.println("/////////" + exDate);

						try {
							float m = Integer.parseInt(marks);
							float o = Integer.parseInt(outoffMrks);
							float p = m / o * 100;
							percentage = "" + p;

							final ExamInfoNetwork object = new ExamInfoNetwork();
							new Thread(new Runnable() {
								public void run() {
									object.setStudentAttendance(eid, sid, bid,
											yid, exDate, marks, outoffMrks,
											percentage);

								}
							}).start();
							rollno.setText("");
							marksTxt.setText("");
							outoff.setText("");
							date.setText("00-00-0000");
							Toast.makeText(getActivity(), "Saved..",
									Toast.LENGTH_SHORT).show();
						} catch (NumberFormatException e) {
							Toast.makeText(getActivity(), "Enter Number..",
									Toast.LENGTH_SHORT).show();
							rollno.setText("");
							marksTxt.setText("");
							outoff.setText("");
						}

					} catch (NullPointerException e) {
						Toast.makeText(getActivity(), "Set Exam Date ...",
								Toast.LENGTH_SHORT).show();
					}
				}
				LecturerActivity mobj = (LecturerActivity) getActivity();
				Bundle bndl = null;
				mobj.setexamDate(bndl);
			}
		});
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
	protected void checkExam(String id, String exid) {
		// TODO Auto-generated method stub
		final String sssid = id;
		final String eeeid = exid;
		final ExamInfoNetwork obj = new ExamInfoNetwork();
		// menuList = source.getAllComments();

		new Thread(new Runnable() {

			public void run() {

				Student_Exist = obj.checkStudentExam(sssid, eeeid);
				System.out.println("Student_Exist\\ " + Student_Exist);
				getActivity().runOnUiThread(new Runnable() {
					public void run() {

						ArrayList<ExamInfo> chckInfo = parseJsonMarks(Student_Exist);
						Iterator chckitr = chckInfo.iterator();

						while (chckitr.hasNext()) {
							ExamInfo info = (ExamInfo) chckitr.next();
							S_EXIST = info.getMarks();
							System.out.println("S_EXIST mmm" + S_EXIST);
						}

					}
				});

			}
		}).start();

	}

	private void setDate() {
		// TODO Auto-generated method stub
		LecturerActivity main = (LecturerActivity) getActivity();
		Bundle bundle = main.getexamDate();
		date.setText(bundle.getString("ExamDate"));

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
						examId.setAdapter(yearAdapter);

					}
				});

			}
		}).start();

	}

	public void getIDs(int id) throws NumberFormatException{

		ssid = id;
		System.out.println("SID000" + ssid);
		final ExamInfoNetwork obj = new ExamInfoNetwork();
		new Thread(new Runnable() {

			public void run() {
				StudId = obj.getStudentBranchYearId(""+ssid);

				System.out.println("StudId====" + StudId);

				getActivity().runOnUiThread(new Runnable() {
					public void run() {

						ArrayList<BranchYearInfo> IDInfo = parseJsonIDs(StudId);
						Iterator itre = IDInfo.iterator();
						while (itre.hasNext()) {
							BranchYearInfo info = (BranchYearInfo) itre.next();

							studentIDArray.add(info);
							bid = info.getBranch_id();
							yid = info.getYear_id();
							System.out.println("bid=" + bid + " yid=" + yid);

						}

					}
				});

			}
		}).start();

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

	public ArrayList<ExamInfo> parseJsonMarks(String result) {
		ArrayList<ExamInfo> mainObj = new ArrayList<ExamInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				ExamInfo menu = new ExamInfo();
				menu.setMarks(jsonObj.getString("Marks"));

				System.out.println("!!!!!!!");
				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

}
