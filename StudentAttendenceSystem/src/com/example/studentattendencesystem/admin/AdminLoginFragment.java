package com.example.studentattendencesystem.admin;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.LecturerActivity;
import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.NetworkClass.AdminInfoNetwork;
import com.example.studentattendencesystem.NetworkClass.LecturerInfoNetwork;
import com.example.studentattendencesystem.NetworkClass.StudentInfoNetwork;
import com.example.studentattendencesystem.javaclasses.AdminInfo;
import com.example.studentattendencesystem.javaclasses.LectuererInfo;
import com.example.studentattendencesystem.javaclasses.StudentInfo;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class AdminLoginFragment extends Fragment implements OnClickListener{
View v;
EditText logid,password;
Button ok;
String adminID="",adminData,log_password="",entered_password="";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		v=inflater.inflate(R.layout.admin_login,container,false);
		logid=(EditText)v.findViewById(R.id.adminlogin_id);
		password=(EditText)v.findViewById(R.id.adminlogin_password);
		ok=(Button)v.findViewById(R.id.adminlogin_button);
		
		ok.setOnClickListener(this);
		logid.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				 adminID=logid.getText().toString();
				 getAdmininfo();
			}

			
		});
	
		return v;
	}
	protected void getAdmininfo() {
		// TODO Auto-generated method stub
		final AdminInfoNetwork obj = new AdminInfoNetwork();

		new Thread(new Runnable() {

			public void run() {

				adminData = obj.getAdminInfo(adminID);
				System.out.println("adminData Info =" + adminData);
				getActivity().runOnUiThread(new Runnable() {
					public void run() {

						ArrayList<AdminInfo> mainInfo = parseJson(adminData);
						Iterator itr = mainInfo.iterator();
						while (itr.hasNext()) {
							AdminInfo ainfo = (AdminInfo) itr.next();
							log_password = ainfo.getAdmin_Password();
							Bundle bndl=new Bundle();
							bndl.putInt("AdminID",ainfo.getAdminId());
							bndl.putString("AdminName", ainfo.getAdmin_Fname()+" "+ainfo.getAdmin_Lname());
							AdminActivity main=(AdminActivity)getActivity();
							main.setAdminInfoBundle(bndl);
					
							
						}

					}
				});
			}
		}).start();
	}
	
	
	
	
	public ArrayList<AdminInfo> parseJson(String result) {
		ArrayList<AdminInfo> mainObj = new ArrayList<AdminInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				AdminInfo menu = new AdminInfo();
				menu.setAdminId(jsonObj.getInt("Admin_id"));
				menu.setAdmin_Fname(jsonObj.getString("FirstName"));
				menu.setAdmin_Lname(jsonObj.getString("LastName"));
				menu.setAdmin_Password(jsonObj.getString("Password"));

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
		case R.id.adminlogin_button:
			entered_password=password.getText().toString();
			if(adminID.equals(""))
			{
				Toast.makeText(getActivity(), "Enter Login ID ", Toast.LENGTH_SHORT).show();
			}
			else if(log_password.equals(""))
			{
				Toast.makeText(getActivity(), "Enter Correct Login ID ", Toast.LENGTH_SHORT).show();
				logid.setText("");
			
			}
			else if (entered_password.equals("")) {
				Toast.makeText(getActivity(), "Enter Password ", Toast.LENGTH_SHORT).show();
			}
			else
			{
				if(log_password.equals(entered_password))
				{
					Toast.makeText(getActivity(), "Login Successfull", Toast.LENGTH_SHORT).show();
					AdminActivity main=(AdminActivity)getActivity();
					main.adminFirstViewFragment();
					log_password="";
				}
				else {
					Toast.makeText(getActivity(), "Enter Correct Password ", Toast.LENGTH_SHORT).show();
				}
			}
			break;

		default:
			break;
		}
		
	}
	
	

}
