 package com.example.studentattendencesystem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.NetworkClass.LecturerInfoNetwork;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;
import com.example.studentattendencesystem.javaclasses.StudentInfo;

import android.R.color;
import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint({ "NewApi", "ValidFragment" })
public class LecturerGridViewFragment extends Fragment {

	View v;
	GridView gridView;
	Bundle brnchyearid;
	private String bid, yid, stud, allstudID;
	private ArrayList<BranchYearInfo> studentIDArray;
	private ArrayList<StudentInfo> studentArray, gridArray;
	private String[] studRollNo, StudentIDS;
	int[] checkarg;

	int color=1;

	LinearLayout ln;
	TextView tx;
	String selectedSubject;

	@SuppressLint("ValidFragment")
	public LecturerGridViewFragment(String selectedSubject) {
		// TODO Auto-generated constructor stub
		this.selectedSubject = selectedSubject;
		System.out.println("selectedSubject =" + selectedSubject);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.lecturer_gridview, container, false);
		studentArray = new ArrayList<StudentInfo>();
		studentIDArray = new ArrayList<BranchYearInfo>();
		gridArray = new ArrayList<StudentInfo>();
		gridView = (GridView) v.findViewById(R.id.gridView1);

		getNoOfStudents();

		return v;
	}

	private void getNoOfStudents() {
		// TODO Auto-generated method stub
		LecturerActivity main = (LecturerActivity) getActivity();
		brnchyearid = main.getyearBranchID();
		bid = brnchyearid.getString("BranchID");
		yid = brnchyearid.getString("YearID");

		System.out.println("bid=" + bid + " yid=" + yid);
		final LecturerInfoNetwork obj = new LecturerInfoNetwork();
		// menuList = source.getAllComments();

		new Thread(new Runnable() {

			public void run() {

				allstudID = obj.getStudentIDs(bid, yid);
				System.out.println("Selected ID1=" + allstudID);
				stud = obj.getStudentInfo();
				System.out.println("Students1=" + stud);
				getActivity().runOnUiThread(new Runnable() {
					public void run() {

						ArrayList<BranchYearInfo> mainInfo = parseJson(allstudID);
						Iterator itr = mainInfo.iterator();
						while (itr.hasNext()) {
							BranchYearInfo info = (BranchYearInfo) itr.next();
							studentIDArray.add(info);
						}
						System.out.println("Selected ID2=" + studentIDArray);
						ArrayList<StudentInfo> studinfo = parseJsonStudentInfo(stud);
						Iterator studitr = studinfo.iterator();
						while (studitr.hasNext()) {
							StudentInfo info = (StudentInfo) studitr.next();
							studentArray.add(info);
						}
						System.out.println("Students2=" + studentArray);
						studRollNo = new String[studentIDArray.size()];
						StudentIDS= new String[studentIDArray.size()];
						checkarg=new int[studentIDArray.size()];

						for (int i = 0; i < studentIDArray.size(); i++) {
							BranchYearInfo bstudId = studentIDArray.get(i);
							String bsid = bstudId.getStudent_id();
							System.out.println("bsid " + bsid);
							for (int j = 0; j < studentArray.size(); j++) {
								StudentInfo sstudId = studentArray.get(j);
								String ssid = sstudId.getStud_id();
								System.out.println("ssid " + ssid);
								if (bsid.equals(ssid)) {

									studRollNo[i] = sstudId.getStud_Rollno();
									StudentIDS[i]=sstudId.getStud_id();
									gridArray.add(sstudId);
									System.out.println("Roll No="
											+ studRollNo[i]);
									break;
								}
							}
							checkarg[i]=0;
							
						}
						LecturerActivity main=(LecturerActivity)getActivity();
						main.setSizeOfArray(studRollNo.length);
						main.sendGridData(gridArray);
						gridView.setAdapter(new GridAdapter(getActivity(),
								R.layout.child_gridview, studRollNo));

					}
				});
			}
		}).start();

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				color=1;
				int sub = 0;
				ln = (LinearLayout) arg1.findViewById(R.id.chngBackground);
				int id=Integer.parseInt(StudentIDS[arg2]);
				System.out.println("StudentIDS[arg2]="+StudentIDS[arg2]);
				for(int i=0;i<StudentIDS.length;i++)
				{
				if (checkarg[i]==arg2+1) {
					color=0;
					sub=i;
					break;
					}	
			
				}
				System.out.println("color="+color);
				System.out.println("sub="+sub);
				if(color==0)
				{
					ln.setBackgroundColor(Color.parseColor("#40E0D0"));
					
					LecturerActivity main=(LecturerActivity)getActivity();
					main.resetPresentRollNo(arg2);
					checkarg[arg2]=0;
					//update
					
				}else{
					ln.setBackgroundColor(Color.parseColor("#5F9EA0"));
					LecturerActivity main=(LecturerActivity)getActivity();
					main.setPresentRollNo(arg2,id);
					checkarg[arg2]=arg2+1;
					
				}
				
			
			}
		});
	}

	public ArrayList<BranchYearInfo> parseJson(String result) {
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
