package com.example.studentattendencesystem.admin;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;


import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.NetworkClass.AdminInfoNetwork;
import com.example.studentattendencesystem.NetworkClass.RegistrationNetwork;
import com.example.studentattendencesystem.javaclasses.AdminInfo;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ChangeAuthenticationId extends Fragment implements OnClickListener
{
	View v;
	String getlogin;
	String getoldpasswd;
	String getnewpasswd;
	EditText user;
	EditText oldpasswd;
	EditText newpasswd;
	Context context;
	Button ok;
	String var="";
	String var1="";
	String var2="";
	Button cancel;
	String batString;
	String lectString;
	String studstring;
	String getpasswd;
	AdminInfoNetwork getdb;
	String idsArray;
	String lectArray;
	String studArray;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	v=inflater.inflate(R.layout.changeauthenticateid,container,false);

	
	System.out.println("enter into chang class");
	
	
	oldpasswd=(EditText)v.findViewById(R.id.oldpasswd_change);
	newpasswd=(EditText)v.findViewById(R.id.newpasswd_change);
	
	System.out.println("1");

	ok=(Button)v.findViewById(R.id.ok_change);
	System.out.println("2");
	ok.setOnClickListener(this);
	
	
	getLoginIDS();
	getlectLoginIDS();
	getstudLoginIDS();
	System.out.println("5");
	return v;

	}
		
	
	private void getLoginIDS() {
		// TODO Auto-generated method stub
		System.out.println("enter into method");
		getdb=new AdminInfoNetwork();
		new Thread(new Runnable() {

			public void run() {
			
				var = getdb.getAdminloginid();
				System.out.println("var =" + var);

				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						ArrayList<AdminInfo> Info = parseJson(var);
						Iterator branchitr = Info.iterator();
						while (branchitr.hasNext()) {
							AdminInfo ainfo = (AdminInfo) branchitr
									.next();
							
							idsArray=ainfo.getAdminlogin();

							System.out.println("idsArray  " + idsArray);

						}
						
					}

					
				});
			}
		}).start();

	}
		
	private ArrayList<AdminInfo> parseJson(String result) {
		ArrayList<AdminInfo> mainObj = new ArrayList<AdminInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				AdminInfo menu = new AdminInfo();
				menu.setAdminlogin(jsonObj.getString("Adminid"));
			
				mainObj.add(menu);
			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	private void getlectLoginIDS() {
		// TODO Auto-generated method stub
		System.out.println("enter into method");
		getdb=new AdminInfoNetwork();
		new Thread(new Runnable() {

			public void run() {
			
				var1 = getdb.getLectloginid();
				System.out.println("var1 =" + var1);

				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						ArrayList<AdminInfo> Info = parseJsonlect(var1);
						Iterator branchitr = Info.iterator();
						while (branchitr.hasNext()) {
							AdminInfo ainfo = (AdminInfo) branchitr
									.next();
							
							lectArray=ainfo.getLectlogin();

							System.out.println("lectArray  " +lectArray);

						}
						
					}

					
				});
			}
		}).start();

	}
		
	private ArrayList<AdminInfo> parseJsonlect(String result) {
		ArrayList<AdminInfo> mainObj = new ArrayList<AdminInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				AdminInfo menu = new AdminInfo();
				menu.setLectlogin(jsonObj.getString("Lectid"));
			
				mainObj.add(menu);
			}

		} catch (Exception e) {

		}
		return mainObj;
	}

	private void getstudLoginIDS() {
		// TODO Auto-generated method stub
		System.out.println("enter into method");
		getdb=new AdminInfoNetwork();
		new Thread(new Runnable() {

			public void run() {
			
				var2 = getdb.getStudloginid();
				System.out.println("var2 =" + var2);

				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						ArrayList<AdminInfo> Info = parseJsonstud(var2);
						Iterator branchitr = Info.iterator();
						while (branchitr.hasNext()) {
							AdminInfo ainfo = (AdminInfo) branchitr
									.next();
							
							studArray=ainfo.getStudlogin();

							System.out.println("lectArray  " +lectArray);

						}
						
					}

					
				});
			}
		}).start();

	}
		
	private ArrayList<AdminInfo> parseJsonstud(String result) {
		ArrayList<AdminInfo> mainObj = new ArrayList<AdminInfo>();
		try {
			JSONArray mainMEnu = new JSONArray(result);
			for (int i = 0; i < mainMEnu.length(); i++) {
				JSONObject jsonObj = mainMEnu.getJSONObject(i);
				AdminInfo menu = new AdminInfo();
				menu.setStudlogin(jsonObj.getString("Studid"));
			
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
		case R.id.ok_change:
			System.out.println("ok button click");
		getoldpasswd=oldpasswd.getText().toString();
		getnewpasswd=newpasswd.getText().toString();
		if(getoldpasswd.equals("")&&getnewpasswd.equals(""))
		{
			Toast.makeText(getActivity(), "Fields should not be blank...", Toast.LENGTH_SHORT).show();
		
		}
		else if(getoldpasswd.equals(""))
		{
			Toast.makeText(getActivity(), "Enter Old Password...", Toast.LENGTH_SHORT).show();
		}
		else if (getnewpasswd.equals("")) {
			Toast.makeText(getActivity(), "Enter New Password...", Toast.LENGTH_SHORT).show();
		}
		else
		{
			getdb=new AdminInfoNetwork();
			new Thread(new Runnable() {
				public void run() {
					System.out.println("in run method");
				
					if(getoldpasswd.equals(idsArray))
					{
					   getdb.changepasswd(getoldpasswd,getnewpasswd);
					}else if(getoldpasswd.equals(lectArray))
					{
						   getdb.changepasswdlect(getoldpasswd,getnewpasswd);

						
				}
					else if(getoldpasswd.equals(studArray))
					{
						getdb.changepasswdstud(getoldpasswd, getnewpasswd);
				}
					   
						System.out.println("i m in onclick method");
				}	
		//Toast.makeText(context, "Password Change Successfully", Toast.LENGTH_SHORT).show();
			}).start();
			
			Toast.makeText(getActivity(), "Password Changed Successfuly...", Toast.LENGTH_SHORT).show();
		}
			

			break;
		}
	}
}
	

