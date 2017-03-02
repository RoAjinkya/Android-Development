package com.example.studentattendencesystem;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;

public class ExamDateDialog extends Dialog implements android.view.View.OnClickListener{

	Context context;
	int day,month,year;
	DatePicker dp;
	Bundle bndl;
	LecturerSubmitStudentInfo submitdate;
	public ExamDateDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.exam_date_dialog);
		Button ok = (Button) findViewById(R.id.date_ok);
		 dp=(DatePicker)findViewById(R.id.examdte);
		ok.setOnClickListener(this);
	
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.date_ok:
		day=dp.getDayOfMonth();
		month=dp.getMonth()+1;
		year=dp.getYear();
		
	bndl=new Bundle();
	bndl.putString("ExamDate",""+day+"-"+month+"-"+year);
	System.out.println("ExamDate"+day+"-"+month+"-"+year);
	LecturerActivity main=(LecturerActivity)context;
			main.setexamDate(bndl);
			dismiss();
			break;
		default:
			break;
		}

	}
}



