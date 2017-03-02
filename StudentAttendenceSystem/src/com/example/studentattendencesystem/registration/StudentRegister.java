package com.example.studentattendencesystem.registration;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.NetworkClass.RegistrationNetwork;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;
import com.example.studentattendencesystem.javaclasses.StudentInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StudentRegister extends Activity {

	 TextView Sstudid;
	 EditText Sfname;
	 EditText Slname;
	 EditText Saddress;	
	 EditText Scontno;
	 EditText Semaiid;
	 TextView Sstdrollno;
    Spinner Syear;
    Spinner Sbranch;
    EditText Sloginid;
    EditText Spasswd;
	
	String getstudid;
	String getstudname;
	String getstudfname;
	String getstudlname;
	String getstudaddress;
	String getstudcontno;
	String getbranch;
	String getyear;
	String getstudrollno;
	String getstudemailid;
	String getstudloginid;
	String getstudpasswd;
	String batString;
	String dataString;
	String num;
	Button submit;
	Button newrgister;
	
	ArrayAdapter<String> adapter;
	ArrayAdapter<String> adapter2;
	ArrayList<StudentInfo> iddetails=new ArrayList<StudentInfo>();
	ArrayList<StudentInfo> rolliddetails=new ArrayList<StudentInfo>();
	ArrayList<String> branchdetails=new ArrayList<String>();
	ArrayList<String> yeardetails=new ArrayList<String>();
	ArrayList<String> yeariddetails=new ArrayList<String>();
	ArrayList<String> branchiddetails=new ArrayList<String>();
	ArrayList<String> loginddetails=new ArrayList<String>();

	RegistrationNetwork getDb;
	int maxid;
	String getbranchid;
	String getyearid;
	String var;
	String var1;
	int maxidrollno;
	private static int counter=0;
	private static int counteroll=0;
	String studbranchid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		System.out.println("in layout");
		setContentView(R.layout.student_register);
		
		System.out.println("on create in studregister");
	   Sstudid=(TextView)findViewById(R.id.StudentRegister_editstudid);
	   Sfname=(EditText)findViewById(R.id.StudentRegister_editfname);
	   Slname=(EditText)findViewById(R.id.StudentRegister_editlname);
	   Saddress=(EditText)findViewById(R.id.StudentRegister_editaddress);
	   Scontno=(EditText)findViewById(R.id.StudentRegister_editcontno);
	   Semaiid=(EditText)findViewById(R.id.StudentRegister_editemailid);
	   Sstdrollno=(TextView)findViewById(R.id.StudentRegister_editrollno);
		 Sbranch=(Spinner)findViewById(R.id.StudentRegister_editbranch);
		 Syear=(Spinner)findViewById(R.id.StudentRegister_edityear);
	   Sloginid=(EditText)findViewById(R.id.StudentRegister_editloginid);
	   Spasswd=(EditText)findViewById(R.id.StudentRegister_editpasswd);
		submit=(Button)findViewById(R.id.StudentRegister_Submit);
	 newrgister=(Button)findViewById(R.id.StudentRegister_new);
		
	 Sloginid.addTextChangedListener(new TextWatcher() {
			
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
				getbatchyearID();
				login_id();
			}
		});

	 Scontno.addTextChangedListener(new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			contact_valid(Scontno);
		}
	});
	 

					
	        getDb=new RegistrationNetwork();
	        System.out.println("enter in getdb");
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					String data=getDb.getstudids();
					System.out.println(data+"00000000000000");
					maxid=Integer.parseInt(data.trim());
					System.out.println("maxid"+maxid);
					counter=maxid+1;
					System.out.println("counter"+counter);
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
						System.out.println("In run method");
							ArrayList<StudentInfo> arrayList=parseJson(maxid);
							System.out.println("8");
							Iterator iter=arrayList.iterator();
							System.out.println("9");
							while(iter.hasNext())
							{
								System.out.println("10");
								StudentInfo data=(StudentInfo)iter.next();
	                      System.out.println("11");
	
								iddetails.add(data);
							System.out.println("12");
									System.out.println("var :"+maxid);
							}
							Sstudid.setText(counter+"");
							
						}
	
							});					
				}
			}).start();

		getDb = new RegistrationNetwork();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				String data=getDb.getstudrollno();
				System.out.println(data+"00000000000000");
				maxidrollno=Integer.parseInt(data.trim());
				System.out.println("maxid"+maxidrollno);
				counteroll=maxidrollno+1;
				System.out.println("counterroll"+counteroll);
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
					System.out.println("In run method of rollno ids");
						ArrayList<StudentInfo> arrayList=parseJson(maxidrollno);
						System.out.println("8");
						Iterator iter=arrayList.iterator();
						System.out.println("9");
						while(iter.hasNext())
						{
							System.out.println("10");
							StudentInfo data=(StudentInfo)iter.next();
							System.out.println("11");

							rolliddetails.add(data);
							System.out.println("12");

							System.out.println("var :"+maxid);
						}
						Sstdrollno.setText(counteroll+"");
						
					}

						});
				
			}
		}).start();		
		

		System.out.println("in student spinner");
		 getDb=new RegistrationNetwork();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				var=getDb.getBranchName();
				System.out.println(var+"data in var");

				var1=getDb.getyearName();
				System.out.println(var1+"data in var1");
//				runOnUiThread(new Runnable() {
//					
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//				
				System.out.println("in array List");
						ArrayList<BranchYearInfo> arrayList=parseJsonbranch(var);
						ArrayList<BranchYearInfo> arrayList1=parseJsonyear(var1);
						Iterator iter=arrayList.iterator();
						Iterator iter1=arrayList1.iterator();

						while(iter.hasNext())
						{
							System.out.println("4");
							BranchYearInfo data=(BranchYearInfo)iter.next();
							System.out.println("dataaaaaaaaaa"+data);
							String branch=(String)data.getBranch_name();
							System.out.println("data iS : "+ branch);
							branchdetails.add(branch);
							System.out.println("data in branchdetails"+branchdetails);
						}
						while(iter1.hasNext())
						{
							System.out.println("4");
							BranchYearInfo data=(BranchYearInfo)iter1.next();
							String datastring=(String)data.getYear_name();
							System.out.println("data iS : "+ datastring);
						     yeardetails.add(datastring);
						     System.out.println("data in yeardetails"+yeardetails);
						}
						
						adapter=new ArrayAdapter<String>(StudentRegister.this, android.R.layout.simple_spinner_dropdown_item,branchdetails);
						adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						Sbranch.setAdapter(adapter);
						
						
						adapter2=new ArrayAdapter<String>(StudentRegister.this, android.R.layout.simple_spinner_dropdown_item,yeardetails);
						adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					 Syear.setAdapter(adapter2);
					}
				
		}).start();
			submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
						
//				int id=counter++;
//				data=""+id;
				getstudid=Sstudid.getText().toString();
				   getstudrollno=Sstdrollno.getText().toString(); 
				getstudfname=Sfname.getText().toString();
				 getstudlname=Slname.getText().toString();
			     getstudaddress=Saddress.getText().toString();
				getstudcontno=Scontno.getText().toString();
				getstudemailid=Semaiid.getText().toString();
				getstudloginid=Sloginid.getText().toString();
				getstudpasswd=Spasswd.getText().toString();
				 getyear=Syear.getSelectedItem().toString();
				    getbranch=Sbranch.getSelectedItem().toString();
		
				    getDb=new RegistrationNetwork();
					new Thread(new Runnable() {
						public void run() {
								getDb.setstudInsert(getstudid,getstudrollno,getstudfname, getstudlname, getstudaddress, getstudcontno, getbranch, getyear, getstudemailid, getstudloginid, getstudpasswd);
								//getDb.setstudBranchIdInsert(batString);
								
//									System.out.println("insertion");
								getDb.setstudBranchIdInsert(getstudid,batString,dataString);
								
//						
//									//getadminlogid=getDb.getuserpasswd(loginid);
//									studbranchid=getDb.getstudInsbranch(getbranch);
//									System.out.println("getadminlogid"+studbranchid);
//									runOnUiThread(new Runnable() {
//										
//										@Override
//										public void run() {
//											// TODO Auto-generated method stub
//										
//											//ArrayList<StudentAttendance> arrayList=parseJson(getadminlogid);
//											ArrayList<StudentAttendance> arrayList=parseJson(studbranchid);
//											
//											System.out.println("arrayList"+arrayList);
//											
//											Iterator iter=arrayList.iterator();
//											while(iter.hasNext())
//											{
//												System.out.println("4");
//												StudentAttendance data=(StudentAttendance)iter.next();
//												System.out.println("5"+data);
//												//batString=(String)data.getAdminloginid();
//												batString=(String)data.getBranchid();
//												System.out.println("data iS (6) : "+ batString);
//												logindetails.add(batString);	
//								System.out.println("i m in onclick method");
									}										
								}).start();
									
					Sstudid.setText("");
					Sfname.setText("");
					Slname.setText("");
					Saddress.setText("");
					Scontno.setText("");
					Semaiid.setText("");
					Sloginid.setText("");
					Spasswd.setText("");
				     Sstdrollno.setText("");
		                Toast.makeText(getApplicationContext(), "Registeration Sucessfully Done", Toast.LENGTH_SHORT).show();

										
			}
		});	
		
		newrgister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent=new Intent(StudentRegister.this,StudentRegister.class);
				startActivity(intent);
		 getDb = new RegistrationNetwork();
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						
						String data=getDb.getstudids();
						System.out.println(data+"00000000");
						maxid=Integer.parseInt(data.trim());
						System.out.println("maxid"+maxid);
						counter=maxid+1;
						System.out.println("counter"+counter);	
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
							System.out.println("In run method");
								ArrayList<StudentInfo> arrayList=parseJson(maxid);
								System.out.println("8");
								Iterator iter=arrayList.iterator();
								System.out.println("9");
								while(iter.hasNext())
								{
									System.out.println("10");
									StudentInfo data=(StudentInfo)iter.next();
									System.out.println("11");

									iddetails.add(data);
									System.out.println("12");

									System.out.println("var :"+maxid);
								}
								Sstudid.setText(counter+"");
								
							}
							
								});
						
					}
				}).start();
				
				
				getDb = new RegistrationNetwork();
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						
						String data=getDb.getstudrollno();
						System.out.println(data+"00000000000000");
						maxidrollno=Integer.parseInt(data.trim());
						System.out.println("maxid"+maxidrollno);
						counteroll=maxidrollno+1;
						System.out.println("counterroll"+counteroll);
						runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
							System.out.println("In run method of rollno ids");
								ArrayList<StudentInfo> arrayList=parseJson(maxidrollno);
								System.out.println("8");
								Iterator iter=arrayList.iterator();
								System.out.println("9");
								while(iter.hasNext())
								{
									System.out.println("10");
									StudentInfo data=(StudentInfo)iter.next();
									System.out.println("11");

									rolliddetails.add(data);
									System.out.println("12");

									System.out.println("var :"+maxid);
								}
								Sstdrollno.setText(counteroll+"");
								
							}

								});
						
					}
				}).start();
							
			}
		});

		
		}
	public void login_id()
	{
	new Thread(new Runnable() {
		public void run() {
            getstudloginid=Sloginid.getText().toString();

			 var=getDb.getLogindatastud(getstudloginid);
			System.out.println("getloginid0000000"+var);
			runOnUiThread(new Runnable() {
				public void run() {

					ArrayList<StudentInfo> mainInfo = parseJsonlogid(var);
					Iterator itr = mainInfo.iterator();
					while(itr.hasNext())
					{
						System.out.println("4");
						StudentInfo data=(StudentInfo)itr.next();
						batString=(String)data.getStud_Login();
						System.out.println("data iS : "+ batString);
						loginddetails.add(batString);
					}
					Login_valid(Sloginid);
				}
			});
		}
	}).start();
	}

	
	protected void getbatchyearID() {
		 getbranch=Sbranch.getSelectedItem().toString();
		   getyear=Syear.getSelectedItem().toString();
		   System.out.println("getbranchvvvvv"+getbranch);
		 getDb=new RegistrationNetwork();
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					System.out.println("branch set"+getbranch);
					 getbranchid=getDb.getlectbranchids(getbranch);
					System.out.println("getbranchid0000000"+getbranchid);
				getyearid=getDb.getlectyearids(getyear);
					System.out.println("getyearid0000000"+getyearid);

					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
						
							ArrayList<BranchYearInfo> arrayList=parseJsonid(getbranchid);
							ArrayList<BranchYearInfo> arrayList2=parsejsonyearids(getyearid);
							System.out.println("arrayList"+arrayList);
							
							Iterator iter=arrayList.iterator();
							Iterator iter2=arrayList2.iterator();
							while(iter.hasNext())
							{
								System.out.println("4");
								BranchYearInfo data=(BranchYearInfo)iter.next();
								System.out.println("5"+data);
								batString=data.getBranch_id();
								System.out.println("6");
								System.out.println(batString+"00000000000000");
								branchiddetails.add(batString);
								System.out.println("7  branchiddetails"+branchiddetails);
								

							}
						
							while(iter2.hasNext())
							{
								System.out.println("4");
								BranchYearInfo data=(BranchYearInfo)iter2.next();
								System.out.println("5"+data);
								dataString=data.getYear_id();
								System.out.println("6");
								System.out.println(dataString+"00000000000000");
								yeariddetails.add(batString);
								System.out.println("7  yeariddetails"+yeariddetails);
								

							}

									}

									});
					}
				}).start();				

		
	}

	private ArrayList<StudentInfo> parseJson(int maxid) {
		ArrayList<StudentInfo> getiddetails=new ArrayList<StudentInfo>();
		try{
			JSONArray jArray= new JSONArray();
			for(int i=0;i<jArray.length();i++)
			{
				JSONObject json_data=jArray.getJSONObject(i);
				StudentInfo newbatch=new StudentInfo();
				newbatch.setStud_id(json_data.getString("Stud_id"));
				
				getiddetails.add(newbatch);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return getiddetails;
	}

	private ArrayList<StudentInfo> parseJsonrollno(int maxidrollno) {
		ArrayList<StudentInfo> getrollnodetails=new ArrayList<StudentInfo>();
		try{
			JSONArray jArray= new JSONArray();
			for(int i=0;i<jArray.length();i++)
			{
				JSONObject json_data=jArray.getJSONObject(i);
				StudentInfo newbatch=new StudentInfo();
				newbatch.setStud_Rollno(json_data.getString("Roll_No"));
				
				getrollnodetails.add(newbatch);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return getrollnodetails;
}
	
	
	private ArrayList<BranchYearInfo> parseJsonbranch(String result) {
		ArrayList<BranchYearInfo> getiddetails=new ArrayList<BranchYearInfo>();
		try{
			JSONArray jArray= new JSONArray(result);
			for(int i=0;i<jArray.length();i++)
			{
				JSONObject json_data=jArray.getJSONObject(i);
				BranchYearInfo newbatch=new BranchYearInfo();
				newbatch.setBranch_name(json_data.getString("Branch_Name"));
				
				getiddetails.add(newbatch);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return getiddetails;
	}
	
	
	private ArrayList<BranchYearInfo> parseJsonyear(String result) {
		ArrayList<BranchYearInfo> getiddetails=new ArrayList<BranchYearInfo>();
		try{
			JSONArray jArray= new JSONArray(result);
			for(int i=0;i<jArray.length();i++)
			{
				JSONObject json_data=jArray.getJSONObject(i);
				BranchYearInfo newbatch=new BranchYearInfo();
				newbatch.setYear_name(json_data.getString("Year_Name"));
				
				getiddetails.add(newbatch);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return getiddetails;
	}
	private ArrayList<BranchYearInfo> parseJson(String result) {
		ArrayList<BranchYearInfo> getlogid=new ArrayList<BranchYearInfo>();
		try{
			System.out.println("1");
			JSONArray jArray= new JSONArray(result);
			System.out.println("2");
			for(int i=0;i<jArray.length();i++)	
			{
				System.out.println("in for loop");
				JSONObject json_data=jArray.getJSONObject(i);
				System.out.println("3");
				BranchYearInfo newbatch=new BranchYearInfo();
				System.out.println("4");
				newbatch.setBranch_name(json_data.getString("Branch_Name"));
				System.out.println("5");
				getlogid.add(newbatch);
				System.out.println("6");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return getlogid;
	}

	private ArrayList<BranchYearInfo> parseJsonid( String result) {
		ArrayList<BranchYearInfo> getlogid=new ArrayList<BranchYearInfo>();
		try{
			System.out.println("1");
			JSONArray jArray= new JSONArray(result);
			System.out.println("2");
			for(int i=0;i<jArray.length();i++)	
			{
				System.out.println("in for loop");
				JSONObject json_data=jArray.getJSONObject(i);
				System.out.println("3");
				BranchYearInfo newbatch=new BranchYearInfo();
				System.out.println("4");
				newbatch.setBranch_id(json_data.getString("Branch_id"));
				System.out.println("5");
				getlogid.add(newbatch);
				System.out.println("6");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return getlogid;
	}
	private ArrayList<BranchYearInfo> parsejsonyearids(String result) {
		ArrayList<BranchYearInfo> getlogid=new ArrayList<BranchYearInfo>();
		try{
			System.out.println("1");
			JSONArray jArray= new JSONArray(result);
			System.out.println("2");
			for(int i=0;i<jArray.length();i++)	
			{
				System.out.println("in for loop");
				JSONObject json_data=jArray.getJSONObject(i);
				System.out.println("3");
				BranchYearInfo newbatch=new BranchYearInfo();
				System.out.println("4");
				newbatch.setYear_id(json_data.getString("Year_id"));
				System.out.println("5");
				getlogid.add(newbatch);
				System.out.println("6");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return getlogid;
	}

	 private ArrayList<StudentInfo> parseJsonlogid(String result)
		{
			ArrayList<StudentInfo> getbatchname=new ArrayList<StudentInfo>();
			try{
				JSONArray jArray= new JSONArray(result);
				for(int i=0;i<jArray.length();i++)
				{
					JSONObject json_data=jArray.getJSONObject(i);
					StudentInfo newbatch=new StudentInfo();
					newbatch.setStud_Login(json_data.getString("Login_id"));
					getbatchname.add(newbatch);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
			return getbatchname;
		}
	private void contact_valid(EditText edt) {
		// TODO Auto-generated method stub
		if (edt.getText().toString().length() < 10) {
			edt.setError("Enter Correct Number");
			num = "";
		} else if (edt.getText().toString().length() > 10) {
			edt.setError("Invalid Mobile Number");
			num = "";
		} else {

			num = edt.getText().toString();

		}
	
}
	
	private void Login_valid(EditText edt) {
		// TODO Auto-generated method stub

		if (edt.getText().toString().equals(batString)) {
			edt.setError("Already Login Exist");
			num = "";
		} else {
			num = edt.getText().toString();
		}
}
	
}
