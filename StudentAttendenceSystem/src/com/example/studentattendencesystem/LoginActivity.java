package com.example.studentattendencesystem;


import com.example.studentattendencesystem.admin.AdminActivity;
import com.example.studentattendencesystem.studentlogin.StudentActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		Button admin_login=(Button)findViewById(R.id.loginactvity_adminlogin);
		Button lect_login=(Button)findViewById(R.id.loginactvity_lecturerlogin);
		Button student_login=(Button)findViewById(R.id.loginactvity_studentlogin);
		admin_login.setOnClickListener(this);
		lect_login.setOnClickListener(this);
		student_login.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.loginactvity_adminlogin:
			
			System.out.println("loginactivity");
			Intent intent1=new Intent(LoginActivity.this,AdminActivity.class);
			startActivity(intent1);
			
			break;

		case R.id.loginactvity_lecturerlogin:
			
			Intent intent2=new Intent(LoginActivity.this,LecturerActivity.class);
			startActivity(intent2);
			
			
			break;
			
         case R.id.loginactvity_studentlogin:
			
        	 Intent intent3=new Intent(LoginActivity.this,StudentActivity.class);
 			startActivity(intent3);
 			
			break;	
			
		}
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		 Intent intent3=new Intent(LoginActivity.this,MainActivity.class);
			startActivity(intent3);
			
	}
	
	

	
}
