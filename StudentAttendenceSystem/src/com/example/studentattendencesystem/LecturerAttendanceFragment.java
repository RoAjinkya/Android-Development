package com.example.studentattendencesystem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.NetworkClass.LecturerInfoNetwork;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;
import com.example.studentattendencesystem.javaclasses.StudentInfo;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
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

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LecturerAttendanceFragment extends Fragment {
	private View v;
	private TextView date, time, subjectSlct;
	private Spinner subject;
	private Button ok;
	private Bundle brnchyearid;
	private String ids, bid, yid, allsubjects;
	private ArrayList<BranchYearInfo> subArrayId, subArray;
	private ArrayList<String> subStringArray;
	ArrayList<StudentInfo> studentArray;
	ArrayList<BranchYearInfo> studentIDArray;
	FragmentManager fm;
	FragmentTransaction ft;
	String selectedSubject = "", oldSubject;   
	private int[] presentRollNo;
	ArrayList<StudentInfo> gridArray;
	int presenti = 0;
	Button submit;
	Bundle lectInfoBundle;
	String studId, rollno, f_name, l_name, branch, year, date1, status, time1,
			subject1;

	 String rolltext;
	 String fnmtext;
	 String lnmtext;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	
		v = inflater.inflate(R.layout.lecturer_attendance, container, false);

		LecturerActivity main = (LecturerActivity) getActivity();
		lectInfoBundle = main.getbundleLecturerInfo();
		String lname = lectInfoBundle.getString("FirstName");
		String fname = lectInfoBundle.getString("LastName");
		//submit=(Button)v.findViewById(R.id.btnsub);
		date = (TextView) v.findViewById(R.id.lectattence_date);
		time = (TextView) v.findViewById(R.id.lectattence_time);
		subject = (Spinner) v.findViewById(R.id.lectattence_subject);
		subArray = new ArrayList<BranchYearInfo>();
		subArrayId = new ArrayList<BranchYearInfo>();
		studentArray = new ArrayList<StudentInfo>();
		subStringArray = new ArrayList<String>();
		studentIDArray = new ArrayList<BranchYearInfo>();
		gridArray = new ArrayList<StudentInfo>();
		ok = (Button) v.findViewById(R.id.lectattence_btnsubject);
		subjectSlct = (TextView) v.findViewById(R.id.lectattence_btnsubject);
		setSpinner();
		setDateTime();

		subjectSlct.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (selectedSubject.equals("")) 
				{
					Toast.makeText(getActivity(),
							"There is no subject to select..",
							Toast.LENGTH_SHORT).show();
				} else {
					selectedSubject = subject.getSelectedItem().toString();
					System.out.println("selectedsubject"+selectedSubject);
					gridData(selectedSubject);
					presenti = 1;
				}

			}
		});

//	submit.setOnClickListener(new OnClickListener() 			
//		@Override
//		public void onClick(View v) {
////				// TODO Auto-generated method stub
////				
////				
////				 rolltext = getArguments().getString("rlno");  
////			 fnmtext = getArguments().getString("fname");  
////				 lnmtext = getArguments().getString("lname");  
////				 System.out.println("=========="+rolltext+fnmtext+lnmtext);
////				
//				
//			}
	//	});
		
		ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				selectedSubject = subject.getSelectedItem().toString();
				LecturerActivity main=(LecturerActivity)getActivity();
				
				main.LecturerAttendance(bid,yid,selectedSubject);
				System.out.println("branchid"+bid);
				System.out.println("yearid"+yid);
				System.out.println("subjectname"+selectedSubject);
			}
			});
		return v;
		}
			
	private void gridData(String selectedSubject) {
		// TODO Auto-generated method stub
		fm = getFragmentManager();
		ft = fm.beginTransaction();
		// selectedSubject = subject.getSelectedItem().toString();
		ft.add(R.id.gridContainer,
				new LecturerGridViewFragment(selectedSubject));
		ft.commit();
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
		date1 = " " + c.get(Calendar.YEAR) + "-" + monthNo + "-"
				+ c.get(Calendar.DAY_OF_MONTH);
		time.setText(" " + hour + ":" + c.get(Calendar.MINUTE) + ":"
				+ c.get(Calendar.SECOND));

	}

	private void setSpinner() {
		// TODO Auto-generated method stub
		LecturerActivity main = (LecturerActivity) getActivity();
		brnchyearid = main.getyearBranchID();
		bid = brnchyearid.getString("BranchID");
		yid = brnchyearid.getString("YearID");
		branch = brnchyearid.getString("BranchName");
		year = brnchyearid.getString("YearName");

		final LecturerInfoNetwork obj = new LecturerInfoNetwork();

		new Thread(new Runnable() {

			public void run() {

				ids = obj.getLecturersSubjectesID(bid, yid);
				allsubjects = obj.getAllSubjects();
				getActivity().runOnUiThread(new Runnable() {
					public void run() {

						ArrayList<BranchYearInfo> mainInfo = parseJson(ids);
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

	public ArrayList<StudentInfo> parseJsonStudentInfo(String result) {
		ArrayList<StudentInfo> mainObj = new ArrayList<StudentInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				StudentInfo menu = new StudentInfo();
				menu.setStud_id(jsonObj.getString("Stud_id"));
				menu.setStud_Rollno(jsonObj.getString("Roll_No"));
				menu.setStud_FirstName(jsonObj.getString("First_Name"));
				menu.setStud_LastName(jsonObj.getString("Last_Name"));
				menu.setStud_Address(jsonObj.getString("City"));
				mainObj.add(menu);

			}
		} catch (Exception e) {

		}
		return mainObj;
	}
}
