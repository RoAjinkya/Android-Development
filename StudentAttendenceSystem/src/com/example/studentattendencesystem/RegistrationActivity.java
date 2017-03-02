package com.example.studentattendencesystem;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.NetworkClass.RegistrationNetwork;
import com.example.studentattendencesystem.javaclasses.AdminInfo;
import com.example.studentattendencesystem.registration.AdminRegister;
import com.example.studentattendencesystem.registration.LecturerRegister;
import com.example.studentattendencesystem.registration.StudentRegister;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends Activity implements OnClickListener
	{


	Button registration;
	Button login;
	String var;
	String var1;
	String var2;
	String num;
	Button student;
	 String value;
	 String batString;
	RegistrationNetwork getDb;
	EditText input;

	ArrayList<String> loginiddetails=new ArrayList<String>();
	ArrayList<String> lectiddetails=new ArrayList<String>();
	ArrayList<String> studiddetails=new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_activity);
		
		System.out.println("Registe class on click");
					
		//registration=(Button)findViewById(R.id.reistrationactvity_admin);
		System.out.println("Registe class on click1");

		login=(Button)findViewById(R.id.reistrationactvity_lecturer);
		System.out.println("2");

	    student=(Button)findViewById(R.id.reistrationactvity_Student);
		System.out.println("Registe class on click3");
			//registration.setOnClickListener(this);
			System.out.println("Registe class on click4");

			login.setOnClickListener(this);
			System.out.println("5");

			student.setOnClickListener(this);
			System.out.println("Registe class on click6");	
			
		}
	@Override
	public void onClick(View v) {
	
		switch (v.getId()) {
//		case R.id.reistrationactvity_admin:
//			System.out.println("adminreg buton click");
//			
//			AlertDialog.Builder alert = new AlertDialog.Builder(this);
//			alert.setTitle("Authentication");
//			alert.setMessage("Enter the Id");
//			alert.setIcon(R.drawable.ic_launcher);
//
//			// Set an EditText view to get user input 
//			input= new EditText(this);
//			alert.setView(input);
//
//			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog, int whichButton) {
//			
//				System.out.println("calling loginid()");
//
//				value=input.getText().toString();
//				System.out.println("value00000000"+value);
//			  
//				 getDb=new RegistrationNetwork();
//					
//					new Thread(new Runnable() {
//						
//						@Override
//						public void run() {
//							System.out.println("calling php script");
//							var=getDb.getadminidregister(value);
//							System.out.println(var);
//							
//							runOnUiThread(new Runnable() {
//								
//								@Override
//								public void run() {
//									// TODO Auto-generated method stub
//								System.out.println("calling parsejson");
//									ArrayList<AdminInfo> arrayList=parseJson(var);
//									System.out.println("iterator00000");
//									Iterator iter=arrayList.iterator();
//									System.out.println("iterator00000");
//									while(iter.hasNext())
//									{
//										System.out.println("4");
//										AdminInfo data=(AdminInfo)iter.next();
//										 batString=(String)data.getAdminid_Reg();
//										System.out.println("data iS : "+ batString);
//										loginiddetails.add(batString);
//									}
//									 Login_valid(input);
//								}
//								
//							});
//						}
//					}).start();
//			}
//			});
//			alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//		  public void onClick(DialogInterface dialog, int whichButton) {
//			    // Canceled.
//			 Intent intent1=new Intent(RegistrationActivity.this,RegistrationActivity.class);
//					startActivity(intent1);
//}
//			}).show();
//			
//		
//			break;

		case R.id.reistrationactvity_lecturer:
			System.out.println("lectreg buton click");
			AlertDialog.Builder Lalert = new AlertDialog.Builder(this);
			Lalert.setTitle("Authentication");
			Lalert.setMessage("Enter the Id");
			Lalert.setIcon(R.drawable.ic_launcher);

			// Set an EditText view to get user input 
			input= new EditText(this);
			Lalert.setView(input);

			Lalert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			
				System.out.println("calling loginid()");

				value=input.getText().toString();
				System.out.println("value00000000"+value);
			  
				 getDb=new RegistrationNetwork();
					
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							System.out.println("calling php script");
						
							var1=getDb.getlectid(value);
							System.out.println(value);
				
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
								System.out.println("calling parsejson");
									ArrayList<AdminInfo> arrayList=parseJsonlectid(var1);
						
									System.out.println("iterator00000");
									Iterator iter=arrayList.iterator();
						
									System.out.println("iterator00000");

									while(iter.hasNext())
									{
										System.out.println("4");
										AdminInfo data=(AdminInfo)iter.next();
										 batString=(String)data.getAdminid_Reg();
										System.out.println("data iS : "+ batString);
										lectiddetails.add(batString);
									}
									 Login_validlectid(input);

								}
								
							});
						}
					}).start();
			}
			});
			Lalert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
			    // Canceled.
			 Intent intent1=new Intent(RegistrationActivity.this,RegistrationActivity.class);
					startActivity(intent1);
}
			}).show();

			
			break;
			
		case R.id.reistrationactvity_Student:	
			
			System.out.println("studreg buton click");

			AlertDialog.Builder Salert = new AlertDialog.Builder(this);
			Salert.setTitle("Authentication");
			Salert.setMessage("Enter the Id");
			Salert.setIcon(R.drawable.ic_launcher);

			// Set an EditText view to get user input 
			input= new EditText(this);
			Salert.setView(input);

			Salert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			
				System.out.println("calling loginid()");

				value=input.getText().toString();
				System.out.println("value00000000"+value);
			  
				 getDb=new RegistrationNetwork();
					
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							System.out.println("calling php script");
						
							
							var2=getDb.getstudid(value);
							System.out.println(var2);
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
								System.out.println("calling parsejson");
									ArrayList<AdminInfo> arrayList3=parseJsonstudid(var2);
									System.out.println("iterator00000");

									Iterator iter2=arrayList3.iterator();
									System.out.println("iterator00000");


									while(iter2.hasNext())
									{
										System.out.println("4");
										AdminInfo data=(AdminInfo)iter2.next();
										 batString=(String)data.getAdminid_Reg();
										System.out.println("data iS : "+ batString);
										studiddetails.add(batString);
									}

									System.out.println("entering in if condition");
									 Login_validstudid(input);
								}
								
							});
						}
					}).start();
			}
			});
			Salert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
			    // Canceled.
			 Intent intent1=new Intent(RegistrationActivity.this,RegistrationActivity.class);
					startActivity(intent1);
}
			}).show();

			
			break;
		}
	}
	private ArrayList<AdminInfo> parseJson(
			String result) {
		ArrayList<AdminInfo> getadminid=new ArrayList<AdminInfo>();
		try
		{
			System.out.println("jsonarray");
			JSONArray array=new JSONArray(result);
			System.out.println("for loop");
			for(int i=0;i<array.length();i++)
			{
				System.out.println("in for loop");
				JSONObject json_data=array.getJSONObject(i);
				System.out.println("Admininfo");
				AdminInfo newbatch=new AdminInfo();
				System.out.println("setdata");
				newbatch.setAdminid_Reg(json_data.getString("Adminid"));
				System.out.println("getadminid"+getadminid);
				getadminid.add(newbatch);
			}
		}catch (Exception e) {
			Log.getStackTraceString(e);
		}
		return getadminid;
	}
	private ArrayList<AdminInfo> parseJsonlectid(
			String result) {
		ArrayList<AdminInfo> getadminid=new ArrayList<AdminInfo>();
		try
		{
			System.out.println("jsonarray");
			JSONArray array=new JSONArray(result);
			System.out.println("for loop");
			for(int i=0;i<array.length();i++)
			{
				System.out.println("in for loop");
				JSONObject json_data=array.getJSONObject(i);
				System.out.println("Admininfo");
				AdminInfo newbatch=new AdminInfo();
				System.out.println("setdata");
				newbatch.setAdminid_Reg(json_data.getString("Lectid"));
				System.out.println("getadminid"+getadminid);
				getadminid.add(newbatch);
			}
		}catch (Exception e) {
			Log.getStackTraceString(e);
		}
		return getadminid;
	}
	
	private ArrayList<AdminInfo> parseJsonstudid(
			String result) {
		ArrayList<AdminInfo> getadminid=new ArrayList<AdminInfo>();
		try
		{
			System.out.println("jsonarray");
			JSONArray array=new JSONArray(result);
			System.out.println("for loop");
			for(int i=0;i<array.length();i++)
			{
				System.out.println("in for loop");
				JSONObject json_data=array.getJSONObject(i);
				System.out.println("Admininfo");
				AdminInfo newbatch=new AdminInfo();
				System.out.println("setdata");
				newbatch.setAdminid_Reg(json_data.getString("Studid"));
				System.out.println("getadminid"+getadminid);
				getadminid.add(newbatch);
			}
		}catch (Exception e) {
			Log.getStackTraceString(e);
		}
		return getadminid;
	}

	private void Login_valid(EditText edt) {
		// TODO Auto-generated method stub

		if (edt.getText().toString().equals(batString)) {
			Intent intent=new Intent(RegistrationActivity.this,AdminRegister.class);
			startActivity(intent);
		} else if(edt.getText().toString().equals(" "))
		{
			edt.setError("Please Enter Id");

			num= "";
		}
		
}
	private void Login_validlectid(EditText edt) {
		// TODO Auto-generated method stub

		if (edt.getText().toString().equals(batString)) {
			Intent intent=new Intent(RegistrationActivity.this,LecturerRegister.class);
			startActivity(intent);
		} else if(edt.getText().toString().equals(" "))
		{
			edt.setError("Please Enter Id");

			num= "";
		}
		
}
	private void Login_validstudid(EditText edt) {
		// TODO Auto-generated method stub

		if (edt.getText().toString().equals(batString)) {
			Intent intent=new Intent(RegistrationActivity.this,StudentRegister.class);
			startActivity(intent);
		} else if(edt.getText().toString().equals(" "))
		{
			edt.setError("Please Enter Id");
			num= "";
		}
}
	}