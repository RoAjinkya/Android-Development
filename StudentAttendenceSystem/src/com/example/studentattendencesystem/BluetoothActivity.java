package com.example.studentattendencesystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import com.example.studentattendencesystem.NetworkClass.LecturerInfoNetwork;
import com.example.studentattendencesystem.NetworkClass.RegistrationNetwork;
import com.example.studentattendencesystem.javaclasses.AdminInfo;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;
import com.example.studentattendencesystem.javaclasses.StudentInfo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.method.DateTimeKeyListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BluetoothActivity  extends Activity
{
		 private static final int REQUEST_ENABLE = 1;
		  private BluetoothAdapter btAdapter; 
		  BluetoothDevice device;
		  ArrayList<String> array=null;
		  private ArrayList<BluetoothDevice> btDeviceList = new ArrayList<BluetoothDevice>();
		  TextView out;
		  TextView bdevice;
		  Button submit;
		  String var;
		  String dates;
		  int is=0;
		  TextView dat;
		  StudentInfo data;
		  StudentInfo dstring;
		  BranchYearInfo str;
		  String bname;
		  String yname;
		  String sid="";
		  TextView time;
		  Button attd;
		  public ArrayList<String> bluetoothList=null;
		  ArrayList<StudentInfo> iddetails=new ArrayList<StudentInfo>();
		  ArrayList<BranchYearInfo> branchdetails=new ArrayList<BranchYearInfo>();
		  ArrayList<BranchYearInfo> yeardetails=new ArrayList<BranchYearInfo>();
		  ArrayList<StudentInfo> firstlastdetails=new ArrayList<StudentInfo>();
		  String studId=" ";
		  String branch=" ";
		  String year=" ";
		  String date1=" "; 
		  String time1=" ";
		  String subject1=" ";
		  String rollno=" ";
		  String f_name=" ";
		  String  l_name=" ";
		  String p="Present";
		  LecturerInfoNetwork infoNetwork;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.bluetoothactivity);
				System.out.println("1");
				
			 		  out = (TextView) findViewById(R.id.out);
			 		 bdevice=(TextView)findViewById(R.id.bdevicenm);
			 		    submit=(Button)findViewById(R.id.submit);
			 		    attd=(Button)findViewById(R.id.TakeAttendance);
			 		   
			 	
//			 		   dat=(TextView)findViewById(R.id.textdate);
//			 		   time=(TextView)findViewById(R.id.time);
			 		  System.out.println("2");
			 		   Intent i=getIntent();
			 		   branch=i.getStringExtra("bid");
			 		    year=i.getStringExtra("yid");
			 		    subject1=i.getStringExtra("subId2");
			 		   Bundle bundle=i.getExtras();
			 		 System.out.println("branch"+branch);
			 		 System.out.println("year"+year);
			 		 System.out.println("subjectnamee"+subject1);
			 		System.out.println("3");	
		 	    getstudid();
	 		getbrnyearname();
			 		
				
		       submit.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
							System.out.println("sid"+sid);
						    	//getstudflname(sid);
						   
									System.out.println("sidddddddd"+sid);
									infoNetwork=new LecturerInfoNetwork();
									new Thread(new Runnable() {
										
										@Override
										public void run() {
											System.out.println("11111111111"+sid);
											final String dd=infoNetwork.getstudflname(sid);
											System.out.println(dd+"00000000000000");
											runOnUiThread(new Runnable() {
												
												@Override
												public void run() {
													// TODO Auto-generated method stub
												System.out.println("In run method");
													ArrayList<StudentInfo> arrayList=parseJson(dd);
													System.out.println("8");
													Iterator iter=arrayList.iterator();
													System.out.println("9");
													while(iter.hasNext())
													{
														System.out.println("10");
														dstring=(StudentInfo)iter.next();
														System.out.println("11");
													
														firstlastdetails.add(data);
														rollno=dstring.getStud_Rollno();
														f_name=dstring.getStud_FirstName();
														l_name=dstring.getStud_LastName();
														System.out.println("rnooo"+rollno);
														System.out.println("fname"+f_name);
														System.out.println("lname"+l_name);
														
													}
													System.out.println("==================="+rollno+f_name+l_name);
													
												
													for(int j=0;j<bluetoothList.size();j++)
													{
														System.out.println("bluetoothList.get(j)"+rollno);
														bdevice.append("PresentDetails:"+bluetoothList.get(j)+rollno+f_name+l_name+p);
															
													}		
												}
										});		
										}
							
									}).start();
							}
							
							
						});
		   
		       attd.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Date date=new Date();
					System.out.println("5");
					date1= " "+date.getDate()+"/"+date.getMonth()+"/"+date.getYear();
				System.out.println("6"+date1);
				time1=""+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
				System.out.println("6"+time1);	
					bdevice.append(" size  "+bluetoothList.size()+rollno+f_name+l_name+bname+yname+date1+time1+subject1);
				System.out.println("on click in attd"+rollno+f_name+l_name);
				
				
					 infoNetwork =new LecturerInfoNetwork();
				
					 System.out.println("77");
		                new Thread(new Runnable() {
		                    public void run() {
		                    	bdevice.append(" size  "+bluetoothList.size());
		                    	boolean flag=false;
		                    	for(int j=0;j<bluetoothList.size();j++){
		                    		bdevice.append("rr   "+bluetoothList.get(j)+rollno+"   ");
		                    		if(bluetoothList.get(j).trim().equals(rollno.trim()))
		                    		{	flag=true;
		                    			bdevice.append("dd   "+bluetoothList.get(j));
		                    			break;
		                    		}
		                    		}
		                    		
                      		if(flag)
									{
		                    		 System.out.println(sid+rollno+date1+f_name+l_name+bname+yname+time1+subject1);
		                    		 array= new ArrayList<String>();
		                    		 array.add(sid);
		                    		 array.add(rollno);
		                    		 array.add(f_name);
		                    		 array.add(l_name);
		                    		 array.add(bname);
		                    		 array.add(yname);
		                    		 array.add(date1);
		                    		 array.add(time1);
		                    		 array.add(subject1);
			                    		
		                    		// sid,rollno,f_name,l_name,bname,yname,date1,time1,subject1;
			    					infoNetwork.setinsertattd(array);
			    						
							} 
		                 
		                 }
		                         
		                }).start();
		                Toast.makeText(getApplicationContext(), "Attendance send Sucessfully Total Count:=1 ", Toast.LENGTH_SHORT).show();
			}
			});
		       
			    //out.setMovementMethod(new ScrollingMovementMethod());
			    
			    //Register the BroadcastReceiver
			    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
			    filter.addAction(BluetoothDevice.ACTION_UUID);
			    filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
			    filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
			    registerReceiver(ActionFoundReceiver, filter); // Don't forget to unregister during onDestroy

			    // Getting the Bluetooth adapter
			    btAdapter = BluetoothAdapter.getDefaultAdapter();
			    out.append("\nAdapter: " + btAdapter);
			    
			    CheckBTState();
			    
			  }
		public void getbrnyearname()
		{
			System.out.println("in getbyname");
			infoNetwork=new LecturerInfoNetwork();
			new Thread(new Runnable() {
				
				@Override
				public void run() {

					final String brname=infoNetwork.getbranchname(branch);
					final String yearname=infoNetwork.getyearname(year);
					System.out.println(dat+"00000000000000");
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
						System.out.println("In run method");
							ArrayList<BranchYearInfo> arrayList=parseJsonbname(brname);
							ArrayList<BranchYearInfo> arrayList2=parseJsonYname(yearname);
							System.out.println("8");
							Iterator iter=arrayList.iterator();
							Iterator iter1=arrayList2.iterator();
							
							System.out.println("9");
							while(iter.hasNext())
							{
								System.out.println("10");
								 str=(BranchYearInfo)iter.next();
								System.out.println("11");			
								branchdetails.add(str);
								bname=str.getBranch_name();	
								System.out.println("bnameyname000000000"+bname);
								System.out.println("12"+branchdetails);
							}
							while(iter1.hasNext())
							{
								System.out.println("10");
								 str=(BranchYearInfo)iter1.next();
								System.out.println("11");			
								yeardetails.add(str);
								yname=str.getYear_name();
								System.out.println("bnameyname0000000"+yname);
								System.out.println("12"+yeardetails);
							}
							
							
						}
					});
				}
			}).start();
		}
		
		public void getstudid()
		{
			infoNetwork=new LecturerInfoNetwork();
			new Thread(new Runnable() {
				
				@Override
				public void run() {

					final String data1=infoNetwork.getstudid(branch,year);
					System.out.println(data1+"00000000000000");
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
						System.out.println("In run method");
							ArrayList<StudentInfo> arrayList=parseJsonIDs(data1);
							System.out.println("8");
							Iterator iter=arrayList.iterator();
							System.out.println("9");
							while(iter.hasNext())
							{
								System.out.println("10");
								 data=(StudentInfo)iter.next();
								System.out.println("11");
							
								iddetails.add(data);
								sid=data.getStud_id();
								System.out.println("sid"+sid);
								System.out.println("12"+data);
							}
							
						}
				});		
				}
			}).start();
	 		
		}
	

		
			  /* This routine is called when an activity completes.*/
			  @Override
			  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			    super.onActivityResult(requestCode, resultCode, data);
			    if (requestCode == REQUEST_ENABLE) {
			      CheckBTState();
			    }
			  }

			  @Override
			  protected void onDestroy() {
			    super.onDestroy();
			    if (btAdapter != null) {
			      btAdapter.cancelDiscovery();
			    }
			    unregisterReceiver(ActionFoundReceiver);
			  }

			  private void CheckBTState() {
			    // Check for Bluetooth support and then check to make sure it is turned on
			    // If it isn't request to turn it on
			    // List paired devices
			    // Emulator doesn't support Bluetooth and will return null
			    if(btAdapter==null) { 
			     // out.append("\nBluetooth NOT supported. Aborting.");
			      return;
			    } else {
			      if (btAdapter.isEnabled()) {
			       // out.append("\nBluetooth is enabled...");
			        
			        //Starting the device discovery
			        btAdapter.startDiscovery();
			      } else {
			        Intent enableBtIntent = new Intent(btAdapter.ACTION_REQUEST_ENABLE);
			        startActivityForResult(enableBtIntent, REQUEST_ENABLE);
			      }
			    }
			  } 
			  private final BroadcastReceiver ActionFoundReceiver = new BroadcastReceiver(){
			    
			    @SuppressLint("NewApi")
				@Override
			    public void onReceive(Context context, Intent intent) {
			     String action = intent.getAction();
			    bluetoothList=new ArrayList<String>();
			     if(BluetoothDevice.ACTION_FOUND.equals(action)) {
			      device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
			   out.append("\n  Present Roll No. : " + device.getName());
			       btDeviceList.add(device);
			      
			       bluetoothList.add(device.getName().toString());
			   	   
			       bdevice.setText("arrraylistdata ==="+bluetoothList.get(is));
			       bdevice.append("arary list size"+bluetoothList.size());
			       is++;
			       System.out.println("arraylist"+bluetoothList);
			     } else {
			       if(BluetoothDevice.ACTION_UUID.equals(action)) {
			         BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
			         Parcelable[] uuidExtra = intent.getParcelableArrayExtra(BluetoothDevice.EXTRA_UUID);
			         for (int i=0; i<uuidExtra.length; i++) {
			        	 out.append("\n  Device: " + device.getName() + ", " + device + ", Service: " + uuidExtra[i].toString());
			   
			       System.out.println("arraylist"+bluetoothList);
			         }
			       
			       } else {
			         if(BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
			        	 out.append("\nDiscovery Started...");
			         } else {
			           if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
			        	   out.append("\nDiscovery Finished");
			             Iterator<BluetoothDevice> itr = btDeviceList.iterator();
			             while (itr.hasNext()) {
			               // Get Services for paired devices
			               BluetoothDevice device = itr.next();
			               out.append("\nGetting Services for " + device.getName() + ", " + device);
			               if(!device.fetchUuidsWithSdp()) {
			            	   out.append("\nSDP Failed for " + device.getName());
			               
			               }
			               
			             }
			           
			           }
			         }
			       }
			      }
			    }	  
			  };
			  
				public ArrayList<StudentInfo> parseJsonIDs(String result) {
					ArrayList<StudentInfo> mainObj = new ArrayList<StudentInfo>();
					try {
						JSONArray mainMEnu = new JSONArray(result);
						System.out.println("11");
						for (int i = 0; i < mainMEnu.length(); i++) {
							JSONObject jsonObj = mainMEnu.getJSONObject(i);
							System.out.println("12");
							StudentInfo menu = new StudentInfo();
							System.out.println("13");
							menu.setStud_id(jsonObj.getString("Stud_id"));
							System.out.println("14");
							mainObj.add(menu);
							System.out.println("15");
						}
					} catch (Exception e) {

					}
					return mainObj;
				}
				public ArrayList<BranchYearInfo> parseJsonbname(String result) {
					ArrayList<BranchYearInfo> mainObj = new ArrayList<BranchYearInfo>();
					try {
						JSONArray mainMEnu = new JSONArray(result);
						System.out.println("11branchyearname");
						for (int i = 0; i < mainMEnu.length(); i++) {
							JSONObject jsonObj = mainMEnu.getJSONObject(i);
							System.out.println("12");
							BranchYearInfo menu = new BranchYearInfo();
							System.out.println("13");
							menu.setBranch_name(jsonObj.getString("Branch_Name"));
							System.out.println("14");
							mainObj.add(menu);
							System.out.println("15");
						}
					} catch (Exception e) {

					}
					return mainObj;
				}
				public ArrayList<BranchYearInfo> parseJsonYname(String result) {
					ArrayList<BranchYearInfo> mainObj = new ArrayList<BranchYearInfo>();
					try {
						JSONArray mainMEnu = new JSONArray(result);
						System.out.println("11branchyearname");
						for (int i = 0; i < mainMEnu.length(); i++) {
							JSONObject jsonObj = mainMEnu.getJSONObject(i);
							System.out.println("12");
							BranchYearInfo menu = new BranchYearInfo();
							System.out.println("13");
							menu.setYear_name(jsonObj.getString("Year_Name"));
							System.out.println("14");
							mainObj.add(menu);
							System.out.println("menu"+menu);
							System.out.println("15");
						}
					} catch (Exception e) {

					}
					return mainObj;
				}
				public ArrayList<StudentInfo> parseJson(String result) {
					ArrayList<StudentInfo> mainObj = new ArrayList<StudentInfo>();
					try {
						JSONArray mainMEnu = new JSONArray(result);
						System.out.println("11firstlastname");
						for (int i = 0; i < mainMEnu.length(); i++) {
							JSONObject jsonObj = mainMEnu.getJSONObject(i);
							System.out.println("12");
							StudentInfo menu = new StudentInfo();
							System.out.println("13");
							menu.setStud_Rollno(jsonObj.getString("Roll_No"));
							menu.setStud_FirstName(jsonObj.getString("First_Name"));
							menu.setStud_LastName(jsonObj.getString("Last_Name"));		
							System.out.println("14");
							mainObj.add(menu);
							System.out.println("menu"+menu);
							System.out.println("15");
						}
					} catch (Exception e) {

					}
					return mainObj;
				}
}
