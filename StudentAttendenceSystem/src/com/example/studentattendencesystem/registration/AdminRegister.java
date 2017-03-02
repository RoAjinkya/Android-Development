package com.example.studentattendencesystem.registration;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.NetworkClass.RegistrationNetwork;
import com.example.studentattendencesystem.javaclasses.AdminInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminRegister extends Activity{
	String getadmin;
	String getadname;
	String getadfname;
	String getadlname;
	String getaddress;
	String getadcontno;
	String getademailid;	
	String getadloginid;
	String getadpasswd;
	String num;
	String batString;
	RegistrationNetwork getDb;
	String var;
	int maxid;
	ArrayList<AdminInfo> iddetails=new ArrayList<AdminInfo>();
	ArrayList<String> longindetails=new ArrayList<String>();


	private int counter=0; 

	TextView Aadminid;
	EditText Afname;
	EditText Alname;
	EditText Aaddress;
	EditText Acontno;
	EditText Aemaiid;
	EditText Aloginid;
	EditText Apasswd;

	Button submit;
	Button newregister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_register);
		
		System.out.println("oncreate of adminRegister class");
		Aadminid=(TextView)findViewById(R.id.AdminRegister_editadminid);
	    Afname=(EditText)findViewById(R.id.AdminRegister_editfname);
	    Alname=(EditText)findViewById(R.id.AdminRegister_editlname);
	    Aaddress=(EditText)findViewById(R.id.AdminRegister_editaddress);
	    Acontno=(EditText)findViewById(R.id.AdminRegister_editcontno);
	    Aemaiid=(EditText)findViewById(R.id.AdminRegister_editemailid);
	    Aloginid=(EditText)findViewById(R.id.AdminRegister_editloginid);
	    Apasswd=(EditText)findViewById(R.id.AdminRegister_editpasswd);
	    submit=(Button)findViewById(R.id.AdminRegister_submit);
	    newregister=(Button)findViewById(R.id.newregister);
	       
	    Acontno.addTextChangedListener(new  TextWatcher() {
			
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
					contact_valid(Acontno);
				}
			});
		   Aloginid.addTextChangedListener(new TextWatcher() {
			
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

				login_id();
			
				
			}
		}); 
	    
		getDb=new RegistrationNetwork();
		new Thread(new Runnable() {
			
			@Override
			public void run() {

				String data=getDb.getAdminids();
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
						ArrayList<AdminInfo> arrayList=parseJson(maxid);
						System.out.println("8");
						Iterator iter=arrayList.iterator();
						System.out.println("9");
						while(iter.hasNext())
						{
							System.out.println("10");
							AdminInfo data=(AdminInfo)iter.next();
							System.out.println("11");

							iddetails.add(data);
							System.out.println("12");

							System.out.println("var :"+maxid);
						}
						Aadminid.setText(counter+"");
						
					}

								});
				
			}
		}).start();
		
		submit.setOnClickListener(new OnClickListener() {
	        
	        
	        @Override
	        public void onClick(View v) {
	            
	          
	            System.out.println("Submit button click");
	            getadmin=Aadminid.getText().toString();
	            System.out.println(getadmin);
	            getadfname=Afname.getText().toString();
	             getadlname=Alname.getText().toString();
	             getaddress=Aaddress.getText().toString();
	            getadcontno=Acontno.getText().toString();
	            getademailid=Aemaiid.getText().toString();
	            getadloginid=Aloginid.getText().toString();
	            getadpasswd=Apasswd.getText().toString();
	            
	            
	             getDb=new RegistrationNetwork();
	                new Thread(new Runnable() {
	                    public void run() {
	                            getDb.setadmininsert(getadmin, getadfname, getadlname, getaddress, getadcontno, getademailid, getadloginid, getadpasswd);
	                           
	                            System.out.println("i m in onclick method");
	                            
	                            }  
	                   
	                }).start();
	                Aadminid.setText("");
	                Afname.setText("");
	                Alname.setText("");
	                Aaddress.setText("");
	                Acontno.setText("");
	                Aemaiid.setText("");
	                Aloginid.setText("");
	                Apasswd.setText("");
	                Toast.makeText(getApplicationContext(), "Registeration Sucessfully Done", Toast.LENGTH_SHORT).show();

	                }
	        
	            });  
	    newregister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				System.out.println("onclick newregister");
//				 if(newregister.isPressed())
//				    {
//				    	submit.setEnabled(false);
//				    }else if(submit.isPressed())
//				    {
//						newregister.setEnabled(false);
//					}
//								System.out.println("3");
	// 
								Intent intent=new Intent(AdminRegister.this,AdminRegister.class);
								startActivity(intent);
								getDb=new RegistrationNetwork();
								new Thread(new Runnable() {
									
									@Override
									public void run() {

										String data=getDb.getAdminids();
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
												ArrayList<AdminInfo> arrayList=parseJson(maxid);
												System.out.println("8");
												Iterator iter=arrayList.iterator();
												System.out.println("9");
												while(iter.hasNext())
												{
													System.out.println("10");
													AdminInfo data=(AdminInfo)iter.next();
													System.out.println("11");

													iddetails.add(data);
													System.out.println("12");

													System.out.println("var :"+maxid);
												}
												Aadminid.setText(counter+"");
												
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
	        getadloginid=Aloginid.getText().toString();

			 var=getDb.getLogindata(getadloginid);
			System.out.println("getloginid0000000"+var);
			runOnUiThread(new Runnable() {
				public void run() {

					ArrayList<AdminInfo> mainInfo = parseJsonlogid(var);
					Iterator itr = mainInfo.iterator();
					while(itr.hasNext())
					{
						System.out.println("4");
						ArrayList<AdminInfo> iddetails=new ArrayList<AdminInfo>();
						AdminInfo data=(AdminInfo)itr.next();
						batString=(String)data.getAdmin_LogId();
						System.out.println("data iS : "+ batString);
						longindetails.add(batString);
					}
					Login_valid(Aloginid);
				}
			});
		}
	}).start();
	}

		 private ArrayList<AdminInfo> parseJson(int maxid) {
				ArrayList<AdminInfo> getbatchname=new ArrayList<AdminInfo>();
				try{
					JSONArray jArray= new JSONArray();
					for(int i=0;i<jArray.length();i++)
					{
						JSONObject json_data=jArray.getJSONObject(i);
						AdminInfo newbatch=new AdminInfo();
						newbatch.setAdminId(json_data.getInt("Admin_id"));
						
						getbatchname.add(newbatch);
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
				return getbatchname;
			
		 }
		 public void mToast(String text)
		 {
		        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();		

		 }
		 
		 private ArrayList<AdminInfo> parseJsonlogid(String result)
			{
				ArrayList<AdminInfo> getbatchname=new ArrayList<AdminInfo>();
				try{
					JSONArray jArray= new JSONArray(result);
					for(int i=0;i<jArray.length();i++)
					{
						JSONObject json_data=jArray.getJSONObject(i);
						AdminInfo newbatch=new AdminInfo();
						newbatch.setAdmin_LogId(json_data.getString("Login_id"));
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
				} else if(edt.getText().toString().equals(" "))
				{
					edt.setError("Please Enter Mobile Number");
					num= "";
				}else {
					num = edt.getText().toString();
				}
				}
		 
			private void Login_valid(EditText edt) {
				// TODO Auto-generated method stub

				if (edt.getText().toString().equals(batString)) {
					edt.setError("Already Login Exist");
					num = "";
				} else if(edt.getText().toString().equals(" "))
				{
					edt.setError("Please Enter LoginId");

					num= "";
				}
					else
					{
					num = edt.getText().toString();
				}
			}
}
