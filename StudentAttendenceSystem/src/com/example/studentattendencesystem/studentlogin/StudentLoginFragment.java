package com.example.studentattendencesystem.studentlogin;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.LecturerActivity;
import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.NetworkClass.ExamInfoNetwork;
import com.example.studentattendencesystem.NetworkClass.LecturerInfoNetwork;
import com.example.studentattendencesystem.NetworkClass.StudentInfoNetwork;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;
import com.example.studentattendencesystem.javaclasses.LectuererInfo;
import com.example.studentattendencesystem.javaclasses.StudentInfo;

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
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class StudentLoginFragment extends Fragment implements OnClickListener {
	View v;
	EditText lID, lPassword;
	Button login;
	String logID = "", logPassword = "";
	String stud_id="",BY_ID,bid,yid;
	String Logdata, log_password = "";
	Bundle studIfobndl,brnchYrBundle;
	ArrayList<StudentInfo> studinfoArray;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v = inflater.inflate(R.layout.student_login, container, false);
		studinfoArray = new ArrayList<StudentInfo>();
		lID = (EditText) v.findViewById(R.id.Studentlogin_id);
		lPassword = (EditText) v.findViewById(R.id.Studentlogin_password);
		login = (Button) v.findViewById(R.id.Studentlogin_button);
		login.setOnClickListener(this);

		
		lID.addTextChangedListener(new TextWatcher() {

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
				logID = lID.getText().toString();
				studentinfo();

			}
		});

		
		
		lPassword.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				//logID = lID.getText().toString();
				//studentinfo();
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				studYearBranch();

			}
		});

		return v;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.Studentlogin_button:
			
			logPassword = lPassword.getText().toString();
			System.out.println("log_password..11" + log_password);
		
			if (logID.equals("") || logPassword.equals("")) {
				Toast.makeText(getActivity(), "Enter LoginID and Password..",
						Toast.LENGTH_SHORT).show();
				lID.setText("");
				lPassword.setText("");
			} else {

				if (log_password.equals("")) {
					Toast.makeText(getActivity(), "LoginId Does not Exist..",
							Toast.LENGTH_SHORT).show();
				} else if (!log_password.equals(logPassword)) {
					Toast.makeText(getActivity(), "Enter Correct Password..",
							Toast.LENGTH_SHORT).show();
				} else {
					
					
					StudentActivity main=(StudentActivity)getActivity();
					main.setloginStudentInfoBundle(studIfobndl);
					main.studentFirstViewFrag();
					Toast.makeText(getActivity(), "Login Successfull..",
							Toast.LENGTH_SHORT).show();
				}
			}
			break;

		default:
			break;
		}
	}

	private void studYearBranch() {
		// TODO Auto-generated method stub
		final ExamInfoNetwork obj = new ExamInfoNetwork();
		// menuList = source.getAllComments();
		
		new Thread(new Runnable() {

			public void run() {

				BY_ID = obj.getStudentBranchYearId(stud_id);

				getActivity().runOnUiThread(new Runnable() {
					public void run() {

						ArrayList<BranchYearInfo> IDInfo = parseJsonIDs(BY_ID);
						Iterator itre = IDInfo.iterator();
						while (itre.hasNext()) {
							BranchYearInfo info = (BranchYearInfo) itre.next();

							bid = info.getBranch_id();
							yid = info.getYear_id();
						}
						System.out.println("bid= "+bid+" yid= "+yid);
						brnchYrBundle=new Bundle();
						brnchYrBundle.putString("Branch",bid);
						brnchYrBundle.putString("Year",yid);
						StudentActivity main=(StudentActivity)getActivity();
						main.setStudBrnchYrBundle(brnchYrBundle);
					}
				});

			}
		}).start();
	}

	public ArrayList<StudentInfo> parseJson(String result) {
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
				menu.setStud_Login(jsonObj.getString("Login_id"));
				menu.setStud_Password(jsonObj.getString("Password"));

				mainObj.add(menu);

			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	public void studentinfo() {
		final StudentInfoNetwork obj = new StudentInfoNetwork();

		new Thread(new Runnable() {

			public void run() {

				Logdata = obj.getStudentPassword(logID);
				System.out.println("Student Info =" + Logdata);
				getActivity().runOnUiThread(new Runnable() {
					public void run() {

						ArrayList<StudentInfo> mainInfo = parseJson(Logdata);
						Iterator itr = mainInfo.iterator();
						while (itr.hasNext()) {
							StudentInfo linfo = (StudentInfo) itr.next();
							log_password = linfo.getStud_Password();
							studIfobndl=new Bundle();
							stud_id=linfo.getStud_id();
							studIfobndl.putString("StudID",linfo.getStud_id());
							studIfobndl.putString("Rollno",linfo.getStud_Rollno());
							studIfobndl.putString("FName",linfo.getStud_FirstName());
							studIfobndl.putString("LName",linfo.getStud_LastName());
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
}
