package com.example.studentattendencesystem;


import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendSms extends Activity{

	EditText mobileno;
	EditText message;
	Button send;
	String num;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendsms);
		
	mobileno=(EditText)findViewById(R.id.mobileno);
	message=(EditText)findViewById(R.id.message);
	send=(Button)findViewById(R.id.send);
	
	mobileno.addTextChangedListener(new TextWatcher() {
		
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
			contact_valid(mobileno);
		}
	});
	
	send.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
		
			String mobile=mobileno.getText().toString();
			String msg=message.getText().toString();
			
			try {
				SmsManager smsManager=SmsManager.getDefault();
				smsManager.sendTextMessage(mobile, null, msg, null, null);
				Toast.makeText(getApplicationContext(), "SMS Sent!!!", Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				
				Toast.makeText(getApplicationContext(), "Sms Send Fail Try again later!!!",Toast.LENGTH_SHORT).show();
			}
			
		}
	});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
}


