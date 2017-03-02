package com.example.studentattendencesystem;

import java.util.ArrayList;

import com.example.studentattendencesystem.javaclasses.StudentInfo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class LecturerActivity extends Activity {

	FragmentManager fm;
	FragmentTransaction ft;
	int fragmentBackStack;
	private Bundle lecturerBundle;
	private Bundle yearBranchid;
	private Bundle examdateBundle;
	int[] presentRollNo;

	private ArrayList<StudentInfo> gridArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// examdateBundle=new Bundle();
		setContentView(R.layout.lecturer_container);
		lecturerLogINFragment();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (fragmentBackStack == 2) {
			Toast.makeText(getApplicationContext(), "You are in Home Page",
					Toast.LENGTH_SHORT).show();
		} else if (fragmentBackStack == 3) {
			firstViewFragment();
		} else if (fragmentBackStack == 4) {
			marksFragment();
		}else
		{
			Intent intent2=new Intent(LecturerActivity.this,LoginActivity.class);
			startActivity(intent2);
		}
	}

	public void lecturerLogINFragment() {
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.lecturer_container, new LecturerLoginFragment());
		ft.commit();
		fragmentBackStack = 1;

	}

	public void firstViewFragment() {
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.lecturer_container, new LecturerFirstViewFragment());
		ft.commit();
		fragmentBackStack = 2;
	}

	public void attendancebluetooth()
	{
		Intent i=new Intent(LecturerActivity.this,BluetoothActivity.class);
		startActivity(i);
		
	}
	
	
	public void attendanceFragment() {

		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.lecturer_container, new LecturerAttendanceFragment());
		ft.commit();
		fragmentBackStack = 3;
	}

	public void attendanceFragmentmanual() {

		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.lecturer_container, new ManualLectAttendance());
		ft.commit();
		fragmentBackStack = 3;
	}

	public void marksFragment() {
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.lecturer_container, new LecturerStudentInfoFragment());
		ft.commit();
		fragmentBackStack = 3;
	}

	public void setexamDate(Bundle examdateBundle) {
		this.examdateBundle = examdateBundle;

	}

	public Bundle getexamDate() {
		return examdateBundle;
	}

	public void setbundleLecturerInfo(Bundle lecturerBundle) {
		this.lecturerBundle = lecturerBundle;
	}

	public Bundle getbundleLecturerInfo() {
		return lecturerBundle;
	}

	public void setYearBranchID(Bundle yearBranchid) {
		this.yearBranchid = yearBranchid;
	}

	public Bundle getyearBranchID() {
		return yearBranchid;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void setSizeOfArray(int sizfArray) {
		presentRollNo = new int[sizfArray];
		for (int i = 0; i < sizfArray; i++)
			presentRollNo[i] = 0;
	}

	public void setPresentRollNo(int subscript, int rollno) {
		presentRollNo[subscript] = rollno;
		System.out.println("PRESENTROLL NO=" + presentRollNo[subscript] + " "
				+ rollno + " " + subscript);
	}

	public void resetPresentRollNo(int rollno) {
		presentRollNo[rollno] = 0;
		System.out.println("PRESENTROLL NO=" + presentRollNo[rollno] + " "
				+ rollno);
	}

	public int[] getPresentRollNo() {
		System.out.println("---" + presentRollNo);
		return presentRollNo;
	}

	public void sendGridData(ArrayList<StudentInfo> gridArray) {
		this.gridArray = gridArray;
	}

	public ArrayList<StudentInfo> getGeidData() {
		return gridArray;
	}

	public void submitStudentInfo() {
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.lecturer_container, new LecturerSubmitStudentInfo());
		ft.commit();
		fragmentBackStack = 4;
	}

	public void viewStudentInfo() {
		fm = getFragmentManager();
		ft = fm.beginTransaction();

		ft.replace(R.id.lecturer_container, new LecturerViewMarksFragment());
		ft.commit();
		fragmentBackStack = 4;
	}
	public void LectAttendancemanual(String subj)
	{
		fm=getFragmentManager();
		ft=fm.beginTransaction();
		
		ft.replace(R.id.lecturer_container, new LecturerGridViewFragment(subj));
		ft.commit();
		fragmentBackStack=5;
		
	}
	public void LecturerAttendance(String brnchid,String yearid,String subname) {
		Intent intent=new Intent(LecturerActivity.this,BluetoothActivity.class);
		intent.putExtra("bid", brnchid);
		intent.putExtra("yid", yearid);
		intent.putExtra("subId2", subname);
		Bundle bundle=new Bundle();
		intent.putExtras(bundle);
		startActivity(intent);
	}
	public void LectSendemail()
	{
		Intent intent=new Intent(LecturerActivity.this,SendEmail.class);
		startActivity(intent);
		
	}

	public void LectSmssend()
	{
		Intent intent=new Intent(LecturerActivity.this,SendSms.class);
		startActivity(intent);
		
	}
	
	public void getrolfnmlnm()
	{
		fm=getFragmentManager();
		ft=fm.beginTransaction();
		
		ft.replace(R.id.lecturer_container, new LecturerAttendanceFragment());
		ft.commit();
		fragmentBackStack=5;
	}
	
	
	
	
}
