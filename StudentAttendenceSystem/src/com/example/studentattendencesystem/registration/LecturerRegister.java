package com.example.studentattendencesystem.registration;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.NetworkClass.RegistrationNetwork;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;
import com.example.studentattendencesystem.javaclasses.LectuererInfo;
import android.app.Activity;
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

public class LecturerRegister extends Activity {
	
	TextView Llectid;
	 EditText Lfname;
	 EditText Llname;
	 EditText Laddress;
	 EditText Lcontno;
	 Spinner Lbranch;
	 Spinner Lyear;
	 EditText Lemaiid;
	 EditText Lloginid;
	 EditText Lpasswd;
	 Button submit;
	 Button newregister;
	 
	 
		String getlectid;
		String getlectname;
		String getlectfname;
		String getlectlname;
		String getaddress;
		String getlectcontno;
		String getbranch;
		String getyear;
		String getlectemailid;
		String getlectloginid;
		String getlectpasswd;
		String batString;
		String datastring;
		String var;
		String var1;
		String getyearid;
		String getbranchid;
		String num;
		ArrayAdapter<String> adapter;
		ArrayAdapter<String> adapter2;
		ArrayList<LectuererInfo> iddetails=new ArrayList<LectuererInfo>();
	    ArrayList<String> branchdetails=new ArrayList<String>();
	    ArrayList<String> branchiddetails=new ArrayList<String>();
	    ArrayList<String> yeardetails=new ArrayList<String>();
	    ArrayList<String> yeariddetails=new ArrayList<String>();
	    ArrayList<String> loginiddetails=new ArrayList<String>();

		RegistrationNetwork getDb;
		int maxid;
		private static int counter=0;
		
		
			
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.lecturer_register);
			
			 Llectid=(TextView)findViewById(R.id.LecturerRegister_editlectid);
			 Lfname=(EditText)findViewById(R.id.LecturerRegister_editfname);
		     Llname=(EditText)findViewById(R.id.LecturerRegister_editlname);
		     Laddress=(EditText)findViewById(R.id.LecturerRegister_editaddress);
		     Lcontno=(EditText)findViewById(R.id.LecturerRegister_editcontno);
			 Lbranch=(Spinner)findViewById(R.id.LecturerRegister_branchedit);
			 Lyear=(Spinner)findViewById(R.id.LecturerRegister_yearedit);
			 Lemaiid=(EditText)findViewById(R.id.LecturerRegister_editemailid);
			 Lloginid=(EditText)findViewById(R.id.LecturerRegister_editloginid);
		      Lpasswd=(EditText)findViewById(R.id.LecturerRegister_editpasswd);
		      	submit	=(Button)findViewById(R.id.LecturerRegister_Submit);
		 		newregister=(Button)findViewById(R.id.LecturerRegister_new);

//			 List<String> list = new ArrayList<String>();
//		        list.add("Computer Science and Engg");
//		        list.add("Electronics");
//		        list.add("Information Technology");
//		        list.add("Electrical Technology");
//		        list.add("Mechanical Enginnering");
//			
//			
//			adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,list);
//			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//			Lbranch.setAdapter(adapter);
		

				Lloginid.addTextChangedListener(new TextWatcher() {
					
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
						
						getbatchyearID();
						login_id();
						
					}
				});	Lcontno.addTextChangedListener(new TextWatcher() {
				
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

					contact_valid(Lcontno);
				}
			});
		 		
			getDb=new RegistrationNetwork();
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					String data=getDb.getlectids();
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
							ArrayList<LectuererInfo> arrayList=parseJson(maxid);
							System.out.println("8");
							Iterator iter=arrayList.iterator();
							System.out.println("9");
							while(iter.hasNext())
							{
								System.out.println("10");
								LectuererInfo data=(LectuererInfo)iter.next();
								System.out.println("11");

								iddetails.add(data);
								System.out.println("12");

								System.out.println("var :"+maxid);
							}
							Llectid.setText(counter+"");
							
						}

							});
					
				}
			}).start();


			System.out.println("in lectspinner");
			 getDb=new RegistrationNetwork();
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					var=getDb.getBranchName();
					System.out.println(var+"data in var");

					var1=getDb.getyearName();
					System.out.println(var1);
//					runOnUiThread(new Runnable() {
//						
//						@Override
//						public void run() {
//							// TODO Auto-generated method stub
//						
							ArrayList<BranchYearInfo> arrayList=parseJson(var);
							ArrayList<BranchYearInfo> arrayList1=parseJson1(var1);
							Iterator iter=arrayList.iterator();
							Iterator iter1=arrayList1.iterator();

							while(iter.hasNext())
							{
								System.out.println("4");
								BranchYearInfo data=(BranchYearInfo)iter.next();
								String batString=(String)data.getBranch_name();
								System.out.println("data iS : "+ batString);
								branchdetails.add(batString);
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
							
							
							adapter=new ArrayAdapter<String>(LecturerRegister.this, android.R.layout.simple_spinner_dropdown_item,branchdetails);
							adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
							Lbranch.setAdapter(adapter);
							
							
							adapter2=new ArrayAdapter<String>(LecturerRegister.this, android.R.layout.simple_spinner_dropdown_item,yeardetails);
							adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						  Lyear.setAdapter(adapter2);
			
						}
					
			}).start();

				
			
					submit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					System.out.println("submit button click");
					getlectid=Llectid.getText().toString();
					getlectfname=Lfname.getText().toString();
					 getlectlname=Llname.getText().toString();
				     getaddress=Laddress.getText().toString();
					getlectcontno=Lcontno.getText().toString();
					getlectemailid=Lemaiid.getText().toString();
					getlectloginid=Lloginid.getText().toString();
					getlectpasswd=Lpasswd.getText().toString();
					
									
				 getDb=new RegistrationNetwork();
					new Thread(new Runnable() {
						public void run() {
							
							System.out.println("//****////"+batString+datastring);
								getDb.setlectInsert(getlectid, getlectfname, getlectlname, getaddress, getlectcontno,getlectemailid, getlectloginid, getlectpasswd);
								getDb.setlectBranchIdInsert(getlectid,batString,datastring);
							
								System.out.println("i m in onclick method");
								}						
					}).start();
					
					Llectid.setText("");
					Lfname.setText("");
					Llname.setText("");
					Laddress.setText("");
					Lcontno.setText("");
					Lemaiid.setText("");
					Lloginid.setText("");
					Lpasswd.setText("");
					
	                Toast.makeText(getApplicationContext(), "Registeration Sucessfully Done", Toast.LENGTH_SHORT).show();

					}
				});	
									
					newregister.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							
							getDb=new RegistrationNetwork();
							new Thread(new Runnable() {
								
								@Override
								public void run() {
									
									String data=getDb.getlectids();
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
											ArrayList<LectuererInfo> arrayList=parseJson(maxid);
											System.out.println("8");
											Iterator iter=arrayList.iterator();
											System.out.println("9");
											while(iter.hasNext())
											{
												System.out.println("10");
												LectuererInfo data=(LectuererInfo)iter.next();
												System.out.println("11");

												iddetails.add(data);
												System.out.println("12");

												System.out.println("var :"+maxid);
											}
											Llectid.setText(counter+"");
											
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
	            getlectloginid=Lloginid.getText().toString();

				 var=getDb.getLogindatalect(getlectloginid);
				System.out.println("getloginid0000000"+var);
				
				runOnUiThread(new Runnable() {
					public void run() {

						ArrayList<LectuererInfo> mainInfo = parseJsonlogid(var);
						Iterator itr = mainInfo.iterator();
						while(itr.hasNext())
						{
							System.out.println("4");
							LectuererInfo data=(LectuererInfo)itr.next();
							batString=(String)data.getLect_Login();
							System.out.println("data iS : "+ batString);
							loginiddetails.add(batString);
						}
						Login_valid(Lloginid);
					}
				});
			}
		}).start();
		}

		protected void getbatchyearID() {
			// TODO Auto-generated method stub
			   getbranch=Lbranch.getSelectedItem().toString();
			   getyear=Lyear.getSelectedItem().toString();
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
									datastring=data.getYear_id();
									System.out.println("6");
									System.out.println(datastring+"00000000000000");
									yeariddetails.add(batString);
									System.out.println("7  yeariddetails"+yeariddetails);
									

								}

										}

										});
						}
					}).start();				
						
				
		}

		private ArrayList<LectuererInfo> parseJson(int maxid) {
				ArrayList<LectuererInfo> getiddetails=new ArrayList<LectuererInfo>();
				try{
					JSONArray jArray= new JSONArray();
					for(int i=0;i<jArray.length();i++)
					{
						JSONObject json_data=jArray.getJSONObject(i);
						LectuererInfo newbatch=new LectuererInfo();
						newbatch.setLect_id(json_data.getInt("Lecturer_id"));
						
						getiddetails.add(newbatch);
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
				return getiddetails;
		}
		private ArrayList<BranchYearInfo> parseJson(String result) {
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
		
		
		private ArrayList<BranchYearInfo> parseJson1(String result) {
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
		
		
		 private ArrayList<LectuererInfo> parseJsonlogid(String result)
			{
				ArrayList<LectuererInfo> getbatchname=new ArrayList<LectuererInfo>();
				try{
					JSONArray jArray= new JSONArray(result);
					for(int i=0;i<jArray.length();i++)
					{
						JSONObject json_data=jArray.getJSONObject(i);
						LectuererInfo newbatch=new LectuererInfo();
						newbatch.setLect_Login(json_data.getString("Login_id"));
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

			if (edt.getText().toString().equals(batString)) 
			{
				edt.setError("Already Login Exist");
				num = "";
			} else {

				num = edt.getText().toString();
			}
	}

	}

