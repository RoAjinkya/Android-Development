package com.example.studentattendencesystem.admin;

import java.util.ArrayList;

import com.example.studentattendencesystem.LecturerStudentInfoFragment;
import com.example.studentattendencesystem.LecturerSubmitStudentInfo;
import com.example.studentattendencesystem.LoginActivity;
import com.example.studentattendencesystem.R;
import com.example.studentattendencesystem.RegistrationActivity;
import com.example.studentattendencesystem.javaclasses.BranchYearInfo;
import com.example.studentattendencesystem.studentlogin.StudentLoginFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class AdminActivity extends Activity {
	@Override
	protected void onTitleChanged(CharSequence title, int color) {
		// TODO Auto-generated method stub
		super.onTitleChanged(title, color);
	}

	TextView adminName, logout;
	Bundle admininfoBndl;
	FragmentManager fm;
	FragmentTransaction ft;

	Bundle byBundle, lectInfoBundle, dltLectSub;
	int adFrgmntBackpressd;
	BranchYearInfo bname;
	Bundle lectIDbundle;
	ArrayList<BranchYearInfo> idsArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_container);
		adminName = (TextView) findViewById(R.id.admincontainer_lectname);
		logout = (TextView) findViewById(R.id.admincontainer_logout);
		loginFragment();
		logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				loginFragment();
				adminName.setText(admininfoBndl.getString(""));
				logout.setText("");
			}
		});
	}

	public void setAdminInfoBundle(Bundle admininfoBndl) {
		this.admininfoBndl = admininfoBndl;
	}

	public Bundle getAdminInfoBundle() {
		return admininfoBndl;
	}

	public void loginFragment() {
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.admin_container, new AdminLoginFragment());
		ft.commit();
		adFrgmntBackpressd = 1;

	}

	public void adminFirstViewFragment() {
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.admin_container, new AdminFirstViewFragment());
		ft.commit();

		adminName.setText(admininfoBndl.getString("AdminName"));
		logout.setText("Logout");
		adFrgmntBackpressd = 2;
	}

	public void adminBranchYearInfo() {
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.admin_container, new AdminExamBranchYr());
		ft.commit();
		adFrgmntBackpressd = 3;
	}

	public void register()
	{
		Intent intent=new Intent(AdminActivity.this,RegistrationActivity.class);
		startActivity(intent);
		
	}
	public void setBYNameBundle(Bundle byBundle) {
		this.byBundle = byBundle;
	}

	public Bundle getBYNameBundle() {
		return byBundle;
	}

	public void adminViewExamMarks() {
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.admin_container, new AdminViewExamInfoFragment());
		ft.commit();
		adFrgmntBackpressd = 4;

	}

	public void adminBranch() {
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.admin_container, new AdminBranchFragment());
		ft.commit();
		adFrgmntBackpressd = 3;

	}

	public void adminSubject() {
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.admin_container, new AdminSubjectList());
		ft.commit();
		adFrgmntBackpressd = 5;

	}

	public void setAdminBranchName(BranchYearInfo bname) {
		this.bname = bname;
	}

	public BranchYearInfo getAdminBranchName() {
		return bname;
	}

	public void adminLecturerInfo() {
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.admin_container, new AdminLecturerInfoFragment());
		ft.commit();
		adFrgmntBackpressd = 3;

	}
	public void studentinfo()
	{
		fm=getFragmentManager();
		ft=fm.beginTransaction();
		ft.replace(R.id.admin_container, new AdminStudInfo());
		ft.commit();
		adFrgmntBackpressd=7;
	}
	public void adminLecturerSubject(ArrayList<BranchYearInfo> idsArray) {
		this.idsArray = idsArray;
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.admin_container, new AdminLectSubjectFragment(idsArray));
		ft.commit();
		adFrgmntBackpressd = 6;

	}

	public void setAdminLectid(Bundle lectIDbundle) {
		this.lectIDbundle = lectIDbundle;
	}

	public Bundle getAdminLectid() {
		return lectIDbundle;
	}

	public void adminLecturerAddSubject() {
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.admin_container, new AdminlectEntries());
		ft.commit();
		adFrgmntBackpressd = 7;

	}

	public void setDelteLectSubject(Bundle dltLectSub) {
		this.dltLectSub = dltLectSub;
	}

	public Bundle getDelteLectSubject() {
		return dltLectSub;
	}
	public void changepwd() {
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.admin_container, new ChangeAuthenticationId());
		ft.commit();
		adFrgmntBackpressd = 3;

	}


	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (adFrgmntBackpressd == 1) {

			Intent intent2 = new Intent(AdminActivity.this, LoginActivity.class);
			startActivity(intent2);

		} else if (adFrgmntBackpressd == 2) {

			Toast.makeText(getApplicationContext(), "Click on Logout..!!",
					Toast.LENGTH_SHORT).show();

		}

		else if (adFrgmntBackpressd == 3) {
			adminFirstViewFragment();

		} else if (adFrgmntBackpressd == 4) {
			adminBranchYearInfo();
		} else if (adFrgmntBackpressd == 5) {
			adminBranch();
		} else {
			adminLecturerInfo();
		}

	}

}
