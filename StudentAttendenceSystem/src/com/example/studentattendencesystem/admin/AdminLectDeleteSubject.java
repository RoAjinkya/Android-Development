package com.example.studentattendencesystem.admin;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.NetworkClass.AdminInfoNetwork;
import com.example.studentattendencesystem.javaclasses.AdminInfo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLectDeleteSubject extends Dialog implements android.view.View.OnClickListener{

	 Context context;
	String sid;
	String arg;
	Button delete,no;
    String data; 
    String subid;
    String catString;
    String size;
	public AdminLectDeleteSubject(Context context,String sid,String arg,String size) {
		super(context);
		
		this.context=context;
		this.sid=sid;
		this.arg=arg;
		this.size=size;
	
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.admin_lectdeletesubject);
		delete = (Button) findViewById(R.id.admin_delete_yes);
		no=(Button)findViewById(R.id.admin_delete_no);
		delete.setOnClickListener(this);
		no.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.admin_delete_yes:
			data = sid;
		
			
				final AdminInfoNetwork obj = new AdminInfoNetwork();
				new Thread(new Runnable() {
					public void run() {
						obj.deleteSubjetctList(data);
					}
				}).start();
				Toast.makeText(context, "Subject Deleted Successfully",
						Toast.LENGTH_SHORT).show();
				AdminActivity main=(AdminActivity)context;
				Bundle bndl=new Bundle();
				bndl.putString("dltSub", arg);
				main.setDelteLectSubject(bndl);
				dismiss();
			
			break;
		case R.id.admin_delete_no:
			AdminActivity cmain=(AdminActivity)context;
			Bundle cbndl=new Bundle();
			cbndl.putString("dltSub", size);
			cmain.setDelteLectSubject(cbndl);
			
			dismiss();
			break;

		default:
			dismiss();
			break;
		}
	}	
	

	}

