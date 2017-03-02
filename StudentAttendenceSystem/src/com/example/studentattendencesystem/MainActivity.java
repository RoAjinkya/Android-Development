package com.example.studentattendencesystem;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Button registration=(Button)findViewById(R.id.activity_Registeration);
		Button login=(Button)findViewById(R.id.activity_Login);
		//registration.setOnClickListener(this);
		login.setOnClickListener(this);

		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
//		case R.id.activity_Registeration:
//			System.out.println("Reg buton click");
//			Intent intent1=new Intent(MainActivity.this,RegistrationActivity.class);
//			startActivity(intent1);
//			break;

		case R.id.activity_Login:
			System.out.println("Log buton click");

			Intent intent2=new Intent(MainActivity.this,LoginActivity.class);
			startActivity(intent2);
			break;
		}
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Intent.ACTION_MAIN);
		   intent.addCategory(Intent.CATEGORY_HOME);
		   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		   startActivity(intent);

	}
	
	
	
	
	

}
