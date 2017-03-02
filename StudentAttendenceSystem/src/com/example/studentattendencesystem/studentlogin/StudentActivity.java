package com.example.studentattendencesystem.studentlogin;

import com.example.studentattendencesystem.LecturerActivity;
import com.example.studentattendencesystem.LoginActivity;
import com.example.studentattendencesystem.R;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class StudentActivity extends Activity {

	FragmentManager fm;
	FragmentTransaction ft;
	int fragmentStBackStackst;
	private Bundle loginStudBundle, BYBundle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_container);
		studentLogINFragment();
	}

	public void studentLogINFragment() {
		// TODO Auto-generated method stub
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.student_container, new StudentLoginFragment());
		ft.commit();
		fragmentStBackStackst = 1;

	}

	public void setloginStudentInfoBundle(Bundle loginStudBundle) {
		this.loginStudBundle = loginStudBundle;
	}

	public Bundle getloginStudentInfoBundle() {
		return loginStudBundle;
	}

	public void setStudBrnchYrBundle(Bundle BYBundle) {
		this.BYBundle = BYBundle;
	}

	public Bundle getStudBrnchYrBundle() {
		return BYBundle;
	}

	public void studentFirstViewFrag() {
		// TODO Auto-generated method stub
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.student_container, new StudentFirstViewFragment());
		ft.commit();
		fragmentStBackStackst = 2;

	}

	public void studentAttendanceFrag() {
		// TODO Auto-generated method stub
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.student_container, new StudentAttendanceFragment());
		ft.commit();
		fragmentStBackStackst = 3;

	}

	public void studentViewMarksFrag() {
		// TODO Auto-generated method stub
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.student_container, new StudentViewMarksFragment());
		ft.commit();
		fragmentStBackStackst = 3;

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (fragmentStBackStackst == 2) {
			Toast.makeText(getApplicationContext(), "You are in home page..",
					Toast.LENGTH_SHORT).show();
		} else if (fragmentStBackStackst == 3) {
			studentFirstViewFrag();
		}
		else {
			Intent intent2=new Intent(StudentActivity.this,LoginActivity.class);
			startActivity(intent2);
		}
	}

}
