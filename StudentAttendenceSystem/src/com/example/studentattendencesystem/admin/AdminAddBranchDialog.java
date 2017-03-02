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

public class AdminAddBranchDialog extends Dialog implements
		android.view.View.OnClickListener {
	Context context;
	EditText bname;
	Button add;
	String brname = "";

	public AdminAddBranchDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.admin_addbranch);
		bname = (EditText) findViewById(R.id.admin_d_bname);
		add = (Button) findViewById(R.id.admin_d_add);
		add.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {

		case R.id.admin_d_add:
			brname = bname.getText().toString();
			if (brname.equals("")) {
				Toast.makeText(context, "Enter Branch Name..!!",
						Toast.LENGTH_SHORT).show();
			} else {
				final AdminInfoNetwork obj = new AdminInfoNetwork();
				new Thread(new Runnable() {
					public void run() {
						obj.addAdminBranch(brname);

					}
				}).start();
				Toast.makeText(context, "Branch Added Successfully",
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
