package com.example.studentattendencesystem.admin;

import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.NetworkClass.AdminInfoNetwork;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminDeleteBranchdialog extends Dialog implements android.view.View.OnClickListener{
Context context;
EditText bid;
Button delete;
String brid="";
	public AdminDeleteBranchdialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.admin_deletebranch);
		bid = (EditText) findViewById(R.id.admin_d_bID);
		delete = (Button) findViewById(R.id.admin_d_delete);
		delete.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {

		case R.id.admin_d_delete:
			brid = bid.getText().toString();
			if (brid.equals("")) {
				Toast.makeText(context, "Enter Branch Id..!!",
						Toast.LENGTH_SHORT).show();
			} else {
				final AdminInfoNetwork obj = new AdminInfoNetwork();
				new Thread(new Runnable() {
					public void run() {
						obj.deleteAdminBranch(brid);

					}
				}).start();
				Toast.makeText(context, "Branch Deleted Successfully",
						Toast.LENGTH_SHORT).show();

				dismiss();
			}
			break;

		default:
			dismiss();
			break;
		}
	}

}
